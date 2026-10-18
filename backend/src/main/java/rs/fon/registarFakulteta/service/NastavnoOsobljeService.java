/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.registarFakulteta.dto.NastavnoOsobljeDto;
import rs.fon.registarFakulteta.mapper.NastavnoOsobljeMapper;
import rs.fon.registarFakulteta.model.NastavnoOsoblje;
import rs.fon.registarFakulteta.repository.NastavnoOsobljeRepository;
import java.util.List;
import rs.fon.registarFakulteta.exception.ResourceNotFoundException;
/**
 *
 * @author Korisnik
 */
@Service
@RequiredArgsConstructor
public class NastavnoOsobljeService {
    private final NastavnoOsobljeRepository nastavnoOsobljeRepository;
    private final NastavnoOsobljeMapper nastavnoOsobljeMapper;

    @Transactional(readOnly = true)
    public List<NastavnoOsobljeDto> getAll() {
        return nastavnoOsobljeRepository.findAll()
                .stream()
                .map(nastavnoOsobljeMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public NastavnoOsobljeDto getById(Long id) {
        NastavnoOsoblje n = nastavnoOsobljeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nastavno osoblje sa id: " + id + " ne postoji"));
        return nastavnoOsobljeMapper.toDto(n);
    }

    @Transactional
    public NastavnoOsobljeDto create(NastavnoOsobljeDto dto) {
        NastavnoOsoblje n = nastavnoOsobljeMapper.toEntity(dto);
        n.setId(null);
        NastavnoOsoblje sacuvano = nastavnoOsobljeRepository.save(n);
        return nastavnoOsobljeMapper.toDto(sacuvano);
    }

    @Transactional
    public NastavnoOsobljeDto update(Long id, NastavnoOsobljeDto dto) {
        NastavnoOsoblje postojeci = nastavnoOsobljeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nastavno osoblje sa id: " + id + " ne postoji"));

        postojeci.setIme(dto.getIme());
        postojeci.setPrezime(dto.getPrezime());
        postojeci.setDatumRodjenja(dto.getDatumRodjenja());
        postojeci.setEmail(dto.getEmail());
        postojeci.setTelefon(dto.getTelefon());
        postojeci.setUzaOblast(dto.getUzaOblast());
        postojeci.setDatumIzbora(dto.getDatumIzbora());
        postojeci.setZvanje(dto.getZvanje());

        NastavnoOsoblje sacuvano = nastavnoOsobljeRepository.save(postojeci);
        return nastavnoOsobljeMapper.toDto(sacuvano);
    }

    @Transactional
    public void delete(Long id) {
        if(!nastavnoOsobljeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Nastavno osoblje sa id: " + id + " ne postoji");
        }
        nastavnoOsobljeRepository.deleteById(id);
    }
}
