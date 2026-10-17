/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.mapper;

import org.springframework.stereotype.Component;
import rs.fon.registarFakulteta.dto.FakultetDto;
import rs.fon.registarFakulteta.model.Fakultet;
import rs.fon.registarFakulteta.model.Univerzitet;

/**
 *
 * @author Korisnik
 */
@Component
public class FakultetMapper {
    
    public FakultetDto toDto(Fakultet f) {
        FakultetDto dto = new FakultetDto();
        dto.setId(f.getId());
        dto.setNaziv(f.getNaziv());
        dto.setSkraceniNaziv(f.getSkraceniNaziv());
        dto.setAdresa(f.getAdresa());
        dto.setEmail(f.getEmail());
        dto.setTelefon(f.getTelefon());
        dto.setGodinaOsnivanja(f.getGodinaOsnivanja());
        dto.setPib(f.getPib());
        dto.setMaticniBroj(f.getMaticniBroj());
        dto.setWebsite(f.getWebsite());
        dto.setStatus(f.getStatus());
        dto.setUniverzitetId(f.getUniverzitet().getId());
        dto.setNazivUniverziteta(f.getUniverzitet().getNaziv());
        return dto;
    }

    public Fakultet toEntity(FakultetDto dto) {
        Fakultet f = new Fakultet();
        f.setId(dto.getId());
        f.setNaziv(dto.getNaziv());
        f.setSkraceniNaziv(dto.getSkraceniNaziv());
        f.setAdresa(dto.getAdresa());
        f.setEmail(dto.getEmail());
        f.setTelefon(dto.getTelefon());
        f.setGodinaOsnivanja(dto.getGodinaOsnivanja());
        f.setPib(dto.getPib());
        f.setMaticniBroj(dto.getMaticniBroj());
        f.setWebsite(dto.getWebsite());
        f.setStatus(dto.getStatus());
        return f;
    }
}
