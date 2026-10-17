/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.mapper;

import org.springframework.stereotype.Component;
import rs.fon.registarFakulteta.dto.UniverzitetDto;
import rs.fon.registarFakulteta.model.Univerzitet;
/**
 *
 * @author Korisnik
 */
@Component
public class UniverzitetMapper {
    
    public UniverzitetDto toDto(Univerzitet u) {
        UniverzitetDto dto = new UniverzitetDto();
        dto.setId(u.getId());
        dto.setNaziv(u.getNaziv());
        dto.setAdresa(u.getAdresa());
        dto.setEmail(u.getEmail());
        dto.setTelefon(u.getTelefon());
        dto.setGodinaOsnivanja(u.getGodinaOsnivanja());
        dto.setWebsite(u.getWebsite());
        return dto;
    }

    public Univerzitet toEntity(UniverzitetDto dto) {
        Univerzitet u = new Univerzitet();
        u.setId(dto.getId());
        u.setNaziv(dto.getNaziv());
        u.setAdresa(dto.getAdresa());
        u.setEmail(dto.getEmail());
        u.setTelefon(dto.getTelefon());
        u.setGodinaOsnivanja(dto.getGodinaOsnivanja());
        u.setWebsite(dto.getWebsite());
        return u;
    }
}
