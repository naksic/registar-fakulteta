/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import rs.fon.registarFakulteta.model.Zvanje;
import java.time.LocalDate;

/**
 *
 * @author Korisnik
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NastavnoOsobljeDto {
    private Long id;

    @NotBlank(message = "Ime je obavezno")
    @Size(max = 50)
    private String ime;

    @NotBlank(message = "Prezime je obavezno")
    @Size(max = 50)
    private String prezime;

    @NotNull(message = "Datum rodjenja je obavezan")
    private LocalDate datumRodjenja;

    @NotBlank(message = "Email je obavezan")
    @Size(max = 50)
    private String email;

    @NotBlank(message = "Telefon je obavezan")
    @Size(max = 50)
    private String telefon;

    @NotBlank(message = "Uza oblast je obavezna")
    @Size(max = 100)
    private String uzaOblast;

    @NotNull(message = "Datum izbora je obavezan")
    private LocalDate datumIzbora;

    @NotNull(message = "Zvanje je obavezno")
    private Zvanje zvanje;
}
