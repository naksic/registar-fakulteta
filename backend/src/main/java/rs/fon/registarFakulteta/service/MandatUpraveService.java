/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.registarFakulteta.dto.MandatUpraveDto;
import rs.fon.registarFakulteta.mapper.MandatUpraveMapper;
import rs.fon.registarFakulteta.model.Fakultet;
import rs.fon.registarFakulteta.model.MandatUprave;
import rs.fon.registarFakulteta.repository.FakultetRepository;
import rs.fon.registarFakulteta.repository.MandatUpraveRepository;
import java.util.List;
import rs.fon.registarFakulteta.exception.BadRequestException;
import rs.fon.registarFakulteta.exception.ResourceNotFoundException;
import java.time.LocalDate;
/**
 *
 * @author Korisnik
 */

@Service
@RequiredArgsConstructor
public class MandatUpraveService {
    private final MandatUpraveRepository mandatUpraveRepository;
    private final FakultetRepository fakultetRepository;
    private final MandatUpraveMapper mandatUpraveMapper;

    @Transactional(readOnly = true)
    public List<MandatUpraveDto> getAll() {
        return mandatUpraveRepository.findAll()
                .stream()
                .map(mandatUpraveMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public MandatUpraveDto getById(Long id) {
        MandatUprave m = mandatUpraveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mandat uprave sa id: " + id + " ne postoji"));
        return mandatUpraveMapper.toDto(m);
    }

    @Transactional(readOnly = true)
    public List<MandatUpraveDto> getByFakultetId(Long fakultetId) {
        return mandatUpraveRepository.findAll()
                .stream()
                .filter(m -> m.getFakultet().getId().equals(fakultetId))
                .map(mandatUpraveMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MandatUpraveDto> getTrenutniMandati() {
        return mandatUpraveRepository.findAll()
                .stream()
                .filter(m -> m.getDatumZavrsetka() == null)
                .map(mandatUpraveMapper::toDto)
                .toList();
    }
    
    @Transactional(readOnly = true)
    public List<MandatUpraveDto> getBivsiMandati() {
        return mandatUpraveRepository.findAll()
                .stream()
                .filter(m -> m.getDatumZavrsetka() != null)
                .map(mandatUpraveMapper::toDto)
                .toList();
    }

    @Transactional
    public MandatUpraveDto create(MandatUpraveDto dto) {
        if(dto.getDatumZavrsetka() != null && dto.getDatumZavrsetka().isBefore(dto.getDatumPocetka())) {
            throw new BadRequestException("Datum zavrsetka ne moze biti pre datuma pocetka");
        }
        
        proveriPreklapanjeMandata(dto, null);

        Fakultet fakultet = fakultetRepository.findById(dto.getFakultetId())
                .orElseThrow(() -> new ResourceNotFoundException("Fakultet sa id: " + dto.getFakultetId() + " ne postoji"));

        MandatUprave m = mandatUpraveMapper.toEntity(dto);
        m.setId(null);
        m.setFakultet(fakultet);

        MandatUprave sacuvan = mandatUpraveRepository.save(m);
        return mandatUpraveMapper.toDto(sacuvan);
    }

    @Transactional
    public MandatUpraveDto update(Long id, MandatUpraveDto dto) {
        MandatUprave postojeci = mandatUpraveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mandat uprave sa id: " + id + " ne postoji"));

        if(dto.getDatumZavrsetka() != null &&
            dto.getDatumZavrsetka().isBefore(dto.getDatumPocetka())) {
            throw new BadRequestException("Datum zavrsetka ne moze biti pre datuma pocetka");
        }
        
        proveriPreklapanjeMandata(dto, id);

        Fakultet fakultet = fakultetRepository.findById(dto.getFakultetId())
                .orElseThrow(() -> new ResourceNotFoundException("Fakultet sa id: " + dto.getFakultetId() + " ne postoji"));

        postojeci.setDatumPocetka(dto.getDatumPocetka());
        postojeci.setDatumZavrsetka(dto.getDatumZavrsetka());
        postojeci.setFakultet(fakultet);

        MandatUprave sacuvan = mandatUpraveRepository.save(postojeci);
        return mandatUpraveMapper.toDto(sacuvan);
    }

    @Transactional
    public void delete(Long id) {
        if(!mandatUpraveRepository.existsById(id)) {
            throw new ResourceNotFoundException("Mandat uprave sa id: " + id + " ne postoji");
        }
        mandatUpraveRepository.deleteById(id);
    }
    
    private void proveriPreklapanjeMandata(MandatUpraveDto dto, Long idZaIzuzimanje) {
        LocalDate novaPocetak = dto.getDatumPocetka();
        LocalDate novaKraj = dto.getDatumZavrsetka() != null ? dto.getDatumZavrsetka() : LocalDate.MAX;

        List<MandatUprave> postojeciMandati = mandatUpraveRepository.findAll().stream()
                .filter(m -> m.getFakultet().getId().equals(dto.getFakultetId()))
                .filter(m -> idZaIzuzimanje == null || !m.getId().equals(idZaIzuzimanje))
                .toList();

        for (MandatUprave postojeci : postojeciMandati) {
            LocalDate postojeciPocetak = postojeci.getDatumPocetka();
            LocalDate postojeciKraj = postojeci.getDatumZavrsetka() != null ? postojeci.getDatumZavrsetka() : LocalDate.MAX;

            boolean preklapaSe = !novaPocetak.isAfter(postojeciKraj) && !postojeciPocetak.isAfter(novaKraj);

            if (preklapaSe) {
                String period = postojeci.getDatumZavrsetka() != null
                        ? postojeci.getDatumPocetka() + " do " + postojeci.getDatumZavrsetka()
                        : postojeci.getDatumPocetka() + " (trenutno aktivan, bez datuma završetka)";

                throw new BadRequestException("Na ovom fakultetu vec postoji mandat uprave u periodu " + period +
                    ". Mandati uprave na istom fakultetu ne smeju se vremenski preklapati.");
            }
        }
    }
}
