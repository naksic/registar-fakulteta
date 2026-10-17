/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.mapper;

import org.springframework.stereotype.Component;
import rs.fon.registarFakulteta.dto.LiceDto;
import rs.fon.registarFakulteta.model.Lice;
/**
 *
 * @author Korisnik
 */

@Component
public class LiceMapper {
    
    public LiceDto toDto(Lice l) {
        LiceDto dto = new LiceDto();
        dto.setId(l.getId());
        dto.setIme(l.getIme());
        dto.setPrezime(l.getPrezime());
        dto.setDatumRodjenja(l.getDatumRodjenja());
        dto.setEmail(l.getEmail());
        dto.setTelefon(l.getTelefon());
        return dto;
    }

    public Lice toEntity(LiceDto dto) {
        Lice l = new Lice();
        l.setId(dto.getId());
        l.setIme(dto.getIme());
        l.setPrezime(dto.getPrezime());
        l.setDatumRodjenja(dto.getDatumRodjenja());
        l.setEmail(dto.getEmail());
        l.setTelefon(dto.getTelefon());
        return l;
    }
}
