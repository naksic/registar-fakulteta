/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.mapper;

import org.springframework.stereotype.Component;
import rs.fon.registarFakulteta.dto.ClanUpraveDto;
import rs.fon.registarFakulteta.model.ClanUprave;

/**
 *
 * @author Korisnik
 */
@Component
public class ClanUpraveMapper {
    public ClanUpraveDto toDto(ClanUprave c) {
        ClanUpraveDto dto = new ClanUpraveDto();
        dto.setId(c.getId());
        dto.setFunkcija(c.getFunkcija());
        dto.setMandatUpraveId(c.getMandatUprave().getId());
        dto.setLiceId(c.getLice().getId());
        dto.setImePrezimeLica(c.getLice().getIme() + " " + c.getLice().getPrezime());
        return dto;
    }

    public ClanUprave toEntity(ClanUpraveDto dto) {
        ClanUprave c = new ClanUprave();
        c.setId(dto.getId());
        c.setFunkcija(dto.getFunkcija());
        return c;
    }
}
