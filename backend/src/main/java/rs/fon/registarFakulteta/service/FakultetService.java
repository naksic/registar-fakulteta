/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.registarFakulteta.dto.FakultetDto;
import rs.fon.registarFakulteta.mapper.FakultetMapper;
import rs.fon.registarFakulteta.model.Fakultet;
import rs.fon.registarFakulteta.model.Univerzitet;
import rs.fon.registarFakulteta.repository.FakultetRepository;
import rs.fon.registarFakulteta.repository.UniverzitetRepository;
import java.util.List;
import rs.fon.registarFakulteta.exception.ResourceNotFoundException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Korisnik
 */

@Service
@RequiredArgsConstructor
public class FakultetService {
    private final FakultetRepository fakultetRepository;
    private final UniverzitetRepository univerzitetRepository;
    private final FakultetMapper fakultetMapper;

    @Transactional(readOnly = true)
    public List<FakultetDto> getAll() {
        return fakultetRepository.findAll()
                .stream()
                .map(fakultetMapper::toDto)
                .toList();
    }
    
    @Transactional(readOnly = true)
    public Page<FakultetDto> getStranicu(Pageable pageable) {
        return fakultetRepository.findAll(pageable).map(fakultetMapper::toDto);
    }

    @Transactional(readOnly = true)
    public FakultetDto getById(Long id) {
        Fakultet fakultet = fakultetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fakultet sa id: " + id + " ne postoji"));
        return fakultetMapper.toDto(fakultet);
    }

    @Transactional
    public FakultetDto create(FakultetDto dto) {
        Univerzitet univerzitet = univerzitetRepository.findById(dto.getUniverzitetId())
                .orElseThrow(() -> new ResourceNotFoundException("Univerzitet sa id:" + dto.getUniverzitetId() + " ne postoji"));

        Fakultet fakultet = fakultetMapper.toEntity(dto);
        fakultet.setId(null);
        fakultet.setUniverzitet(univerzitet);

        Fakultet sacuvan = fakultetRepository.save(fakultet);
        return fakultetMapper.toDto(sacuvan);
    }

    @Transactional
    public FakultetDto update(Long id, FakultetDto dto) {
        Fakultet postojeci = fakultetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fakultet sa id: " + id + " ne postoji"));

        Univerzitet univerzitet = univerzitetRepository.findById(dto.getUniverzitetId())
                .orElseThrow(() -> new ResourceNotFoundException("Univerzitet sa id: " + dto.getUniverzitetId() + " ne postoji"));

        postojeci.setNaziv(dto.getNaziv());
        postojeci.setSkraceniNaziv(dto.getSkraceniNaziv());
        postojeci.setAdresa(dto.getAdresa());
        postojeci.setEmail(dto.getEmail());
        postojeci.setTelefon(dto.getTelefon());
        postojeci.setGodinaOsnivanja(dto.getGodinaOsnivanja());
        postojeci.setPib(dto.getPib());
        postojeci.setMaticniBroj(dto.getMaticniBroj());
        postojeci.setWebsite(dto.getWebsite());
        postojeci.setStatus(dto.getStatus());
        postojeci.setUniverzitet(univerzitet);

        Fakultet sacuvan = fakultetRepository.save(postojeci);
        return fakultetMapper.toDto(sacuvan);
    }

    @Transactional
    public void delete(Long id) {
        if(!fakultetRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fakultet sa id: " + id + " ne postoji");
        }
        fakultetRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<FakultetDto> getByUniverzitetId(Long univerzitetId) {
        return fakultetRepository.findAll()
                .stream()
                .filter(f -> f.getUniverzitet().getId().equals(univerzitetId))
                .map(fakultetMapper::toDto)
                .toList();
    }
    
    @Transactional(readOnly = true)
    public Page<FakultetDto> pretraga(String naziv, String skraceniNaziv, Long univerzitetId, String status, Pageable pageable) {
        List<FakultetDto> filtrirano = fakultetRepository.findAll(pageable.getSort())
                .stream()
                .filter(f -> naziv == null || naziv.isBlank()
                        || f.getNaziv().toLowerCase().contains(naziv.toLowerCase()))
                .filter(f -> skraceniNaziv == null || skraceniNaziv.isBlank()
                        || f.getSkraceniNaziv().toLowerCase().contains(skraceniNaziv.toLowerCase()))
                .filter(f -> univerzitetId == null
                        || f.getUniverzitet().getId().equals(univerzitetId))
                .filter(f -> status == null || status.isBlank()
                        || f.getStatus().equalsIgnoreCase(status))
                .map(fakultetMapper::toDto)
                .toList();

        int pocetak = (int) pageable.getOffset();
        int kraj = Math.min(pocetak + pageable.getPageSize(), filtrirano.size());

        List<FakultetDto> stranica = pocetak >= filtrirano.size()
                ? List.of()
                : filtrirano.subList(pocetak, kraj);

        return new PageImpl<>(stranica, pageable, filtrirano.size());
    }
    
    @Transactional(readOnly = true)
    public byte[] exportUExcel() {
        List<FakultetDto> fakulteti = getAll();

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Fakulteti");

            String[] kolone = {
                "ID", "Naziv", "Skraceni naziv", "Univerzitet", "Adresa",
                "Email", "Telefon", "Godina osnivanja", "PIB",
                "Maticni broj", "Website", "Status"
            };

            CellStyle stilZaglavlja = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            stilZaglavlja.setFont(font);

            Row zaglavlje = sheet.createRow(0);
            for (int i = 0; i < kolone.length; i++) {
                Cell celija = zaglavlje.createCell(i);
                celija.setCellValue(kolone[i]);
                celija.setCellStyle(stilZaglavlja);
            }

            int brojReda = 1;
            for (FakultetDto f : fakulteti) {
                Row red = sheet.createRow(brojReda++);
                red.createCell(0).setCellValue(f.getId());
                red.createCell(1).setCellValue(f.getNaziv());
                red.createCell(2).setCellValue(f.getSkraceniNaziv());
                red.createCell(3).setCellValue(f.getNazivUniverziteta());
                red.createCell(4).setCellValue(f.getAdresa());
                red.createCell(5).setCellValue(f.getEmail());
                red.createCell(6).setCellValue(f.getTelefon());
                red.createCell(7).setCellValue(f.getGodinaOsnivanja());
                red.createCell(8).setCellValue(f.getPib());
                red.createCell(9).setCellValue(f.getMaticniBroj());
                red.createCell(10).setCellValue(f.getWebsite());
                red.createCell(11).setCellValue(f.getStatus());
            }

            for (int i = 0; i < kolone.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream izlaz = new ByteArrayOutputStream();
            workbook.write(izlaz);
            return izlaz.toByteArray();
        } catch (IOException e) {
            throw new UncheckedIOException("Greška pri generisanju Excel fajla", e);
        }
    }
}
