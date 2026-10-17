/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.registarFakulteta.dto.LiceDto;
import rs.fon.registarFakulteta.mapper.LiceMapper;
import rs.fon.registarFakulteta.model.Lice;
import rs.fon.registarFakulteta.repository.LiceRepository;
import java.util.List;
/**
 *
 * @author Korisnik
 */
@Service
@RequiredArgsConstructor
public class LiceService {
    private final LiceRepository liceRepository;
    private final LiceMapper liceMapper;

    @Transactional(readOnly = true)
    public List<LiceDto> getAll() {
        return liceRepository.findAll()
                .stream()
                .map(liceMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public LiceDto getById(Long id) {
        Lice lice = liceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lice sa id: " + id + " ne postoji"));
        return liceMapper.toDto(lice);
    }

    @Transactional
    public LiceDto create(LiceDto dto) {
        Lice lice = liceMapper.toEntity(dto);
        lice.setId(null);
        Lice sacuvano = liceRepository.save(lice);
        return liceMapper.toDto(sacuvano);
    }

    @Transactional
    public LiceDto update(Long id, LiceDto dto) {
        Lice postojeci = liceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lice sa id: " + id + " ne postoji"));

        postojeci.setIme(dto.getIme());
        postojeci.setPrezime(dto.getPrezime());
        postojeci.setDatumRodjenja(dto.getDatumRodjenja());
        postojeci.setEmail(dto.getEmail());
        postojeci.setTelefon(dto.getTelefon());

        Lice sacuvano = liceRepository.save(postojeci);
        return liceMapper.toDto(sacuvano);
    }

    @Transactional
    public void delete(Long id) {
        if(!liceRepository.existsById(id)) {
            throw new RuntimeException("Lice sa id: " + id + " ne postoji");
        }
        liceRepository.deleteById(id);
    }
}
