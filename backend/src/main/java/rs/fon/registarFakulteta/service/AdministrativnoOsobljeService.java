/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.registarFakulteta.dto.AdministrativnoOsobljeDto;
import rs.fon.registarFakulteta.mapper.AdministrativnoOsobljeMapper;
import rs.fon.registarFakulteta.model.AdministrativnoOsoblje;
import rs.fon.registarFakulteta.repository.AdministrativnoOsobljeRepository;
import java.util.List;
import rs.fon.registarFakulteta.exception.ResourceNotFoundException;

/**
 *
 * @author Korisnik
 */
@Service
@RequiredArgsConstructor
public class AdministrativnoOsobljeService {
    private final AdministrativnoOsobljeRepository administrativnoOsobljeRepository;
    private final AdministrativnoOsobljeMapper administrativnoOsobljeMapper;

    @Transactional(readOnly = true)
    public List<AdministrativnoOsobljeDto> getAll() {
        return administrativnoOsobljeRepository.findAll()
                .stream()
                .map(administrativnoOsobljeMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public AdministrativnoOsobljeDto getById(Long id) {
        AdministrativnoOsoblje a = administrativnoOsobljeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrativno osoblje sa id: " + id + " ne postoji"));
        return administrativnoOsobljeMapper.toDto(a);
    }

    @Transactional
    public AdministrativnoOsobljeDto create(AdministrativnoOsobljeDto dto) {
        AdministrativnoOsoblje a = administrativnoOsobljeMapper.toEntity(dto);
        a.setId(null);
        AdministrativnoOsoblje sacuvano = administrativnoOsobljeRepository.save(a);
        return administrativnoOsobljeMapper.toDto(sacuvano);
    }

    @Transactional
    public AdministrativnoOsobljeDto update(Long id, AdministrativnoOsobljeDto dto) {
        AdministrativnoOsoblje postojeci = administrativnoOsobljeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrativno osoblje sa id: " + id + " ne postoji"));

        postojeci.setIme(dto.getIme());
        postojeci.setPrezime(dto.getPrezime());
        postojeci.setDatumRodjenja(dto.getDatumRodjenja());
        postojeci.setEmail(dto.getEmail());
        postojeci.setTelefon(dto.getTelefon());
        postojeci.setRadnoMesto(dto.getRadnoMesto());
        postojeci.setSektor(dto.getSektor());

        AdministrativnoOsoblje sacuvano = administrativnoOsobljeRepository.save(postojeci);
        return administrativnoOsobljeMapper.toDto(sacuvano);
    }

    @Transactional
    public void delete(Long id) {
        if(!administrativnoOsobljeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Administrativno osoblje sa id: " + id + " ne postoji");
        }
        administrativnoOsobljeRepository.deleteById(id);
    }
}
