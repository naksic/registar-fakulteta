/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.registarFakulteta.dto.KorisnikDto;
import rs.fon.registarFakulteta.exception.BadRequestException;
import rs.fon.registarFakulteta.exception.ResourceNotFoundException;
import rs.fon.registarFakulteta.mapper.KorisnikMapper;
import rs.fon.registarFakulteta.model.Korisnik;
import rs.fon.registarFakulteta.model.Univerzitet;
import rs.fon.registarFakulteta.repository.KorisnikRepository;
import rs.fon.registarFakulteta.repository.UniverzitetRepository;
/**
 *
 * @author Korisnik
 */

@Service
@RequiredArgsConstructor
public class KorisnikService {
    private final KorisnikRepository korisnikRepository;
    private final UniverzitetRepository univerzitetRepository;
    private final KorisnikMapper korisnikMapper;

    @Transactional(readOnly = true)
    public List<KorisnikDto> getAll() {
        return korisnikRepository.findAll()
                .stream()
                .map(korisnikMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public KorisnikDto getById(Long id) {
        Korisnik korisnik = korisnikRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik sa id: " + id + " ne postoji"));
        return korisnikMapper.toDto(korisnik);
    }

    @Transactional
    public KorisnikDto create(KorisnikDto dto) {
        Univerzitet univerzitet = univerzitetRepository.findById(dto.getUniverzitetId())
                .orElseThrow(() -> new ResourceNotFoundException("Univerzitet sa id: " + dto.getUniverzitetId() + " ne postoji"));

        Korisnik korisnik = korisnikMapper.toEntity(dto);
        korisnik.setId(null);
        korisnik.setUniverzitet(univerzitet);

        Korisnik sacuvan = korisnikRepository.save(korisnik);
        return korisnikMapper.toDto(sacuvan);
    }

    @Transactional
    public KorisnikDto update(Long id, KorisnikDto dto) {
        Korisnik postojeci = korisnikRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik sa id: " + id + " ne postoji"));

        Univerzitet univerzitet = univerzitetRepository.findById(dto.getUniverzitetId())
                .orElseThrow(() -> new ResourceNotFoundException("Univerzitet sa id: " + dto.getUniverzitetId() + " ne postoji"));

        postojeci.setUsername(dto.getUsername());
        postojeci.setPassword(dto.getPassword());
        postojeci.setUniverzitet(univerzitet);

        Korisnik sacuvan = korisnikRepository.save(postojeci);
        return korisnikMapper.toDto(sacuvan);
    }

    @Transactional
    public void delete(Long id) {
        if (!korisnikRepository.existsById(id)) {
            throw new ResourceNotFoundException("Korisnik sa id: " + id + " ne postoji");
        }
        korisnikRepository.deleteById(id);
    }

    @Transactional
    public KorisnikDto login(String username, String password) {
        Korisnik korisnik = korisnikRepository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException("Pogrešno korisničko ime ili lozinka"));

        if (!korisnik.getPassword().equals(password)) {
            throw new BadRequestException("Pogrešno korisničko ime ili lozinka");
        }

        korisnik.setUlogovan(true);
        Korisnik sacuvan = korisnikRepository.save(korisnik);
        return korisnikMapper.toDto(sacuvan);
    }

    @Transactional
    public void logout(Long id) {
        Korisnik korisnik = korisnikRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik sa id: " + id + " ne postoji"));

        korisnik.setUlogovan(false);
        korisnikRepository.save(korisnik);
    }
}
