/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.mapper;

import org.springframework.stereotype.Component;
import rs.fon.registarFakulteta.dto.AdministrativnoOsobljeDto;
import rs.fon.registarFakulteta.model.AdministrativnoOsoblje;

/**
 *
 * @author Korisnik
 */
@Component
public class AdministrativnoOsobljeMapper {
    
    public AdministrativnoOsobljeDto toDto(AdministrativnoOsoblje a) {
        AdministrativnoOsobljeDto dto = new AdministrativnoOsobljeDto();
        dto.setId(a.getId());
        dto.setIme(a.getIme());
        dto.setPrezime(a.getPrezime());
        dto.setDatumRodjenja(a.getDatumRodjenja());
        dto.setEmail(a.getEmail());
        dto.setTelefon(a.getTelefon());
        dto.setRadnoMesto(a.getRadnoMesto());
        dto.setSektor(a.getSektor());
        return dto;
    }

    public AdministrativnoOsoblje toEntity(AdministrativnoOsobljeDto dto) {
        AdministrativnoOsoblje a = new AdministrativnoOsoblje();
        a.setIme(dto.getIme());
        a.setPrezime(dto.getPrezime());
        a.setDatumRodjenja(dto.getDatumRodjenja());
        a.setEmail(dto.getEmail());
        a.setTelefon(dto.getTelefon());
        a.setRadnoMesto(dto.getRadnoMesto());
        a.setSektor(dto.getSektor());
        return a;
    }
}
