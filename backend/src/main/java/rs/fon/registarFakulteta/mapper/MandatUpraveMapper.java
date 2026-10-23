/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.mapper;

import org.springframework.stereotype.Component;
import rs.fon.registarFakulteta.dto.MandatUpraveDto;
import rs.fon.registarFakulteta.model.MandatUprave;

/**
 *
 * @author Korisnik
 */
@Component
public class MandatUpraveMapper {
    
    public MandatUpraveDto toDto(MandatUprave m) {
        MandatUpraveDto dto = new MandatUpraveDto();
        dto.setId(m.getId());
        dto.setDatumPocetka(m.getDatumPocetka());
        dto.setDatumZavrsetka(m.getDatumZavrsetka());
        dto.setFakultetId(m.getFakultet().getId());
        dto.setNazivFakulteta(m.getFakultet().getNaziv());
        dto.setNazivUniverziteta(m.getFakultet().getUniverzitet().getNaziv());
        return dto;
    }

    public MandatUprave toEntity(MandatUpraveDto dto) {
        MandatUprave m = new MandatUprave();
        m.setId(dto.getId());
        m.setDatumPocetka(dto.getDatumPocetka());
        m.setDatumZavrsetka(dto.getDatumZavrsetka());
        return m;
    }
}
