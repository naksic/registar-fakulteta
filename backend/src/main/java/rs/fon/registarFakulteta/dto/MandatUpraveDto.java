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
import java.time.LocalDate;

/**
 *
 * @author Korisnik
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MandatUpraveDto {
    private Long id;

    @NotNull(message = "Datum pocetka je obavezan")
    private LocalDate datumPocetka;

    private LocalDate datumZavrsetka;

    @NotNull(message = "Fakultet je obavezan")
    private Long fakultetId;

    private String nazivFakulteta;
}
