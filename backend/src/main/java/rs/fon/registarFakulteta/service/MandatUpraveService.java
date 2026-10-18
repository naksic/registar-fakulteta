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
}
