/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.mapper;

import org.springframework.stereotype.Component;
import rs.fon.registarFakulteta.dto.NastavnoOsobljeDto;
import rs.fon.registarFakulteta.model.NastavnoOsoblje;

/**
 *
 * @author Korisnik
 */
@Component
public class NastavnoOsobljeMapper {
    
    public NastavnoOsobljeDto toDto(NastavnoOsoblje n) {
        NastavnoOsobljeDto dto = new NastavnoOsobljeDto();
        dto.setId(n.getId());
        dto.setIme(n.getIme());
        dto.setPrezime(n.getPrezime());
        dto.setDatumRodjenja(n.getDatumRodjenja());
        dto.setEmail(n.getEmail());
        dto.setTelefon(n.getTelefon());
        dto.setUzaOblast(n.getUzaOblast());
        dto.setDatumIzbora(n.getDatumIzbora());
        dto.setZvanje(n.getZvanje());
        return dto;
    }

    public NastavnoOsoblje toEntity(NastavnoOsobljeDto dto) {
        NastavnoOsoblje n = new NastavnoOsoblje();
        n.setIme(dto.getIme());
        n.setPrezime(dto.getPrezime());
        n.setDatumRodjenja(dto.getDatumRodjenja());
        n.setEmail(dto.getEmail());
        n.setTelefon(dto.getTelefon());
        n.setUzaOblast(dto.getUzaOblast());
        n.setDatumIzbora(dto.getDatumIzbora());
        n.setZvanje(dto.getZvanje());
        return n;
    }
}
