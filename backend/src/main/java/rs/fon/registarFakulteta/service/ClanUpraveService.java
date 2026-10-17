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
                .orElseThrow(() -> new RuntimeException("Clan uprave sa id: " + id + " ne postoji"));
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
                .orElseThrow(() -> new RuntimeException("Mandat uprave sa id: " + dto.getMandatUpraveId() + " ne postoji"));

        Lice lice = liceRepository.findById(dto.getLiceId())
                .orElseThrow(() -> new RuntimeException("Lice sa id: " + dto.getLiceId() + " ne postoji"));

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
                .orElseThrow(() -> new RuntimeException("Clan uprave sa id: " + id + " ne postoji"));

        MandatUprave mandat = mandatUpraveRepository.findById(dto.getMandatUpraveId())
                .orElseThrow(() -> new RuntimeException("Mandat uprave sa id: " + dto.getMandatUpraveId() + " ne postoji"));

        Lice lice = liceRepository.findById(dto.getLiceId())
                .orElseThrow(() -> new RuntimeException("Lice sa id: " + dto.getLiceId() + " ne postoji"));

        postojeci.setFunkcija(dto.getFunkcija());
        postojeci.setMandatUprave(mandat);
        postojeci.setLice(lice);

        ClanUprave sacuvan = clanUpraveRepository.save(postojeci);
        return clanUpraveMapper.toDto(sacuvan);
    }

    @Transactional
    public void delete(Long id) {
        if(!clanUpraveRepository.existsById(id)) {
            throw new RuntimeException("Clan uprave sa id: " + id + " ne postoji");
        }
        clanUpraveRepository.deleteById(id);
    }
}
