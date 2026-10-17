/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.registarFakulteta.dto.OrganizacionaJedinicaDto;
import rs.fon.registarFakulteta.mapper.OrganizacionaJedinicaMapper;
import rs.fon.registarFakulteta.model.Fakultet;
import rs.fon.registarFakulteta.model.OrganizacionaJedinica;
import rs.fon.registarFakulteta.repository.FakultetRepository;
import rs.fon.registarFakulteta.repository.OrganizacionaJedinicaRepository;
import java.util.List;

/**
 *
 * @author Korisnik
 */

@Service
@RequiredArgsConstructor
public class OrganizacionaJedinicaService {
    private final OrganizacionaJedinicaRepository organizacionaJedinicaRepository;
    private final FakultetRepository fakultetRepository;
    private final OrganizacionaJedinicaMapper organizacionaJedinicaMapper;

    @Transactional(readOnly = true)
    public List<OrganizacionaJedinicaDto> getAll() {
        return organizacionaJedinicaRepository.findAll()
                .stream()
                .map(organizacionaJedinicaMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public OrganizacionaJedinicaDto getById(Long id) {
        OrganizacionaJedinica o = organizacionaJedinicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organizaciona jedinica sa id: " + id + " ne postoji"));
        return organizacionaJedinicaMapper.toDto(o);
    }

    @Transactional(readOnly = true)
    public List<OrganizacionaJedinicaDto> getByFakultetId(Long fakultetId) {
        return organizacionaJedinicaRepository.findAll()
                .stream()
                .filter(o -> o.getFakultet().getId().equals(fakultetId))
                .map(organizacionaJedinicaMapper::toDto)
                .toList();
    }

    @Transactional
    public OrganizacionaJedinicaDto create(OrganizacionaJedinicaDto dto) {
        Fakultet fakultet = fakultetRepository.findById(dto.getFakultetId())
                .orElseThrow(() -> new RuntimeException("Fakultet sa id: " + dto.getFakultetId() + " ne postoji"));

        OrganizacionaJedinica o = organizacionaJedinicaMapper.toEntity(dto);
        o.setId(null);
        o.setFakultet(fakultet);

        OrganizacionaJedinica sacuvana = organizacionaJedinicaRepository.save(o);
        return organizacionaJedinicaMapper.toDto(sacuvana);
    }

    @Transactional
    public OrganizacionaJedinicaDto update(Long id, OrganizacionaJedinicaDto dto) {
        OrganizacionaJedinica postojeca = organizacionaJedinicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organizaciona jedinica sa id: " + id + " ne postoji"));

        Fakultet fakultet = fakultetRepository.findById(dto.getFakultetId())
                .orElseThrow(() -> new RuntimeException("Fakultet sa id: " + dto.getFakultetId() + " ne postoji"));

        postojeca.setNaziv(dto.getNaziv());
        postojeca.setFakultet(fakultet);

        OrganizacionaJedinica sacuvana = organizacionaJedinicaRepository.save(postojeca);
        return organizacionaJedinicaMapper.toDto(sacuvana);
    }

    @Transactional
    public void delete(Long id) {
        if(!organizacionaJedinicaRepository.existsById(id)) {
            throw new RuntimeException("Organizaciona jedinica sa id: " + id + " ne postoji");
        }
        organizacionaJedinicaRepository.deleteById(id);
    }
}
