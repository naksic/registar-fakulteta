/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.registarFakulteta.dto.ClanUpraveDto;
import rs.fon.registarFakulteta.mapper.ClanUpraveMapper;
import rs.fon.registarFakulteta.model.ClanUprave;
import rs.fon.registarFakulteta.model.Lice;
import rs.fon.registarFakulteta.model.MandatUprave;
import rs.fon.registarFakulteta.repository.ClanUpraveRepository;
import rs.fon.registarFakulteta.repository.LiceRepository;
import rs.fon.registarFakulteta.repository.MandatUpraveRepository;
import java.util.List;
import rs.fon.registarFakulteta.exception.ResourceNotFoundException;
import rs.fon.registarFakulteta.model.NastavnoOsoblje;
import rs.fon.registarFakulteta.model.AdministrativnoOsoblje;
import rs.fon.registarFakulteta.model.Student;
import rs.fon.registarFakulteta.model.Funkcija;
import rs.fon.registarFakulteta.model.Zvanje;
import rs.fon.registarFakulteta.exception.BadRequestException;

/**
 *
 * @author Korisnik
 */

@Service
@RequiredArgsConstructor
public class ClanUpraveService {
    private final ClanUpraveRepository clanUpraveRepository;
    private final MandatUpraveRepository mandatUpraveRepository;
    private final LiceRepository liceRepository;
    private final ClanUpraveMapper clanUpraveMapper;

    @Transactional(readOnly = true)
    public List<ClanUpraveDto> getAll() {
        return clanUpraveRepository.findAll()
                .stream()
                .map(clanUpraveMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClanUpraveDto getById(Long id) {
        ClanUprave c = clanUpraveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clan uprave sa id: " + id + " ne postoji"));
        return clanUpraveMapper.toDto(c);
    }

    @Transactional(readOnly = true)
    public List<ClanUpraveDto> getByMandatId(Long mandatId) {
        return clanUpraveRepository.findAll()
                .stream()
                .filter(c -> c.getMandatUprave().getId().equals(mandatId))
                .map(clanUpraveMapper::toDto)
                .toList();
    }

    @Transactional
    public ClanUpraveDto create(ClanUpraveDto dto) {
        MandatUprave mandat = mandatUpraveRepository.findById(dto.getMandatUpraveId())
                .orElseThrow(() -> new ResourceNotFoundException("Mandat uprave sa id: " + dto.getMandatUpraveId() + " ne postoji"));

        Lice lice = liceRepository.findById(dto.getLiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Lice sa id: " + dto.getLiceId() + " ne postoji"));
        
        validirajFunkcijuIZvanje(dto.getFunkcija(), lice);
        proveriJedinstvenostFunkcije(dto.getMandatUpraveId(), dto.getFunkcija(), null);
        
        ClanUprave c = clanUpraveMapper.toEntity(dto);
        c.setId(null);
        c.setMandatUprave(mandat);
        c.setLice(lice);

        ClanUprave sacuvan = clanUpraveRepository.save(c);
        return clanUpraveMapper.toDto(sacuvan);
    }

    @Transactional
    public ClanUpraveDto update(Long id, ClanUpraveDto dto) {
        ClanUprave postojeci = clanUpraveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clan uprave sa id: " + id + " ne postoji"));

        MandatUprave mandat = mandatUpraveRepository.findById(dto.getMandatUpraveId())
                .orElseThrow(() -> new ResourceNotFoundException("Mandat uprave sa id: " + dto.getMandatUpraveId() + " ne postoji"));

        Lice lice = liceRepository.findById(dto.getLiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Lice sa id: " + dto.getLiceId() + " ne postoji"));

        validirajFunkcijuIZvanje(dto.getFunkcija(), lice);
        proveriJedinstvenostFunkcije(dto.getMandatUpraveId(), dto.getFunkcija(), id);
        
        postojeci.setFunkcija(dto.getFunkcija());
        postojeci.setMandatUprave(mandat);
        postojeci.setLice(lice);

        ClanUprave sacuvan = clanUpraveRepository.save(postojeci);
        return clanUpraveMapper.toDto(sacuvan);
    }

    @Transactional
    public void delete(Long id) {
        if(!clanUpraveRepository.existsById(id)) {
            throw new ResourceNotFoundException("Clan uprave sa id: " + id + " ne postoji");
        }
        clanUpraveRepository.deleteById(id);
    }
    
    private void validirajFunkcijuIZvanje(Funkcija funkcija, Lice lice) {
        switch (funkcija) {
            case DEKAN -> {
                if (!(lice instanceof NastavnoOsoblje no) || no.getZvanje() != Zvanje.REDOVNI_PROFESOR) {
                    throw new BadRequestException("Za funkciju DEKAN, lice mora biti nastavno osoblje sa zvanjem REDOVNI_PROFESOR");
                }
            }
            case SEKRETAR -> {
                if (!(lice instanceof AdministrativnoOsoblje)) {
                    throw new BadRequestException("Za funkciju SEKRETAR, lice mora biti administrativno osoblje");
                }
            }
            case PRODEKAN_STUDENT -> {
                if (!(lice instanceof Student)) {
                    throw new BadRequestException("Za funkciju PRODEKAN_STUDENT, lice mora biti student");
                }
            }
            case PRODEKAN_ZA_NASTAVU, PRODEKAN_ZA_NAUKU, PRODEKAN_ZA_FINANSIJE -> {
                if (!(lice instanceof NastavnoOsoblje)) {
                    throw new BadRequestException("Za ovu funkciju prodekana, lice mora biti nastavno osoblje");
                }
            }
        }
    }
    
    private void proveriJedinstvenostFunkcije(Long mandatUpraveId, Funkcija funkcija, Long idZaIzuzimanje) {
        boolean vecPostoji = clanUpraveRepository.findAll().stream()
                .filter(c -> c.getMandatUprave().getId().equals(mandatUpraveId))
                .filter(c -> c.getFunkcija() == funkcija)
                .anyMatch(c -> idZaIzuzimanje == null || !c.getId().equals(idZaIzuzimanje));

        if (vecPostoji) {
            throw new BadRequestException(
                "Funkcija " + funkcija + " je vec dodeljena clanu uprave u okviru ovog mandata. " +
                "Svaka funkcija moze imati samo jednog nosioca po mandatu."
            );
        }
    }
}
