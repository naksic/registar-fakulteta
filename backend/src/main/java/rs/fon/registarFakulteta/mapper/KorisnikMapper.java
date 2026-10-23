/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.mapper;

import org.springframework.stereotype.Component;
import rs.fon.registarFakulteta.dto.KorisnikDto;
import rs.fon.registarFakulteta.model.Korisnik;

/**
 *
 * @author Korisnik
 */

@Component
public class KorisnikMapper {
    
    public KorisnikDto toDto(Korisnik k) {
        KorisnikDto dto = new KorisnikDto();
        dto.setId(k.getId());
        dto.setUsername(k.getUsername());
        dto.setPassword(k.getPassword());
        dto.setUlogovan(k.isUlogovan());
        dto.setUniverzitetId(k.getUniverzitet().getId());
        dto.setNazivUniverziteta(k.getUniverzitet().getNaziv());
        return dto;
    }

    public Korisnik toEntity(KorisnikDto dto) {
        Korisnik k = new Korisnik();
        k.setId(dto.getId());
        k.setUsername(dto.getUsername());
        k.setPassword(dto.getPassword());
        k.setUlogovan(dto.isUlogovan());
        return k;
    }
}
