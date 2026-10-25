/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.mapper;

import org.springframework.stereotype.Component;
import rs.fon.registarFakulteta.dto.OrganizacionaJedinicaDto;
import rs.fon.registarFakulteta.model.OrganizacionaJedinica;

/**
 *
 * @author Korisnik
 */
@Component
public class OrganizacionaJedinicaMapper {
    
    public OrganizacionaJedinicaDto toDto(OrganizacionaJedinica o) {
        OrganizacionaJedinicaDto dto = new OrganizacionaJedinicaDto();
        dto.setId(o.getId());
        dto.setNaziv(o.getNaziv());
        dto.setFakultetId(o.getFakultet().getId());
        dto.setNazivFakulteta(o.getFakultet().getNaziv());
        dto.setNazivUniverziteta(o.getFakultet().getUniverzitet().getNaziv());
        return dto;
    }

    public OrganizacionaJedinica toEntity(OrganizacionaJedinicaDto dto) {
        OrganizacionaJedinica o = new OrganizacionaJedinica();
        o.setId(dto.getId());
        o.setNaziv(dto.getNaziv());
        return o;
    }
}
