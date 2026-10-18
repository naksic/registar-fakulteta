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
    public List<FakultetDto> searchByNaziv(String naziv) {
        return fakultetRepository.findAll()
                .stream()
                .filter(f -> f.getNaziv().toLowerCase().contains(naziv.toLowerCase()))
                .map(fakultetMapper::toDto)
                .toList();
    }
}
