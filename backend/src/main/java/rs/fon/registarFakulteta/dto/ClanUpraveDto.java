/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import rs.fon.registarFakulteta.model.Funkcija;

/**
 *
 * @author Korisnik
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClanUpraveDto {
    private Long id;

    @NotNull(message = "Funkcija je obavezna")
    private Funkcija funkcija;

    @NotNull(message = "Mandat uprave je obavezan")
    private Long mandatUpraveId;

    @NotNull(message = "Lice je obavezno")
    private Long liceId;

    private String imePrezimeLica;
}
