/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.registarFakulteta.dto.UniverzitetDto;
import rs.fon.registarFakulteta.exception.ResourceNotFoundException;
import rs.fon.registarFakulteta.mapper.UniverzitetMapper;
import rs.fon.registarFakulteta.model.Univerzitet;
import rs.fon.registarFakulteta.repository.UniverzitetRepository;

/**
 *
 * @author Korisnik
 */

@Service
@RequiredArgsConstructor
public class UniverzitetService {
    private final UniverzitetRepository univerzitetRepository;
    private final UniverzitetMapper univerzitetMapper;

    @Transactional(readOnly = true)
    public List<UniverzitetDto> getAll() {
        return univerzitetRepository.findAll()
                .stream()
                .map(univerzitetMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public UniverzitetDto getById(Long id) {
        Univerzitet univerzitet = univerzitetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Univerzitet sa id: " + id + " ne postoji"));
        return univerzitetMapper.toDto(univerzitet);
    }

    @Transactional
    public UniverzitetDto create(UniverzitetDto dto) {
        Univerzitet univerzitet = univerzitetMapper.toEntity(dto);
        univerzitet.setId(null);
        Univerzitet sacuvan = univerzitetRepository.save(univerzitet);
        return univerzitetMapper.toDto(sacuvan);
    }

    @Transactional
    public UniverzitetDto update(Long id, UniverzitetDto dto) {
        Univerzitet postojeci = univerzitetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Univerzitet sa id: " + id + " ne postoji"));

        postojeci.setNaziv(dto.getNaziv());
        postojeci.setAdresa(dto.getAdresa());
        postojeci.setEmail(dto.getEmail());
        postojeci.setTelefon(dto.getTelefon());
        postojeci.setGodinaOsnivanja(dto.getGodinaOsnivanja());
        postojeci.setWebsite(dto.getWebsite());

        Univerzitet sacuvan = univerzitetRepository.save(postojeci);
        return univerzitetMapper.toDto(sacuvan);
    }

    @Transactional
    public void delete(Long id) {
        if(!univerzitetRepository.existsById(id)) {
            throw new ResourceNotFoundException("Univerzitet sa id: " + id + " ne postoji");
        }
        univerzitetRepository.deleteById(id);
    }
}
