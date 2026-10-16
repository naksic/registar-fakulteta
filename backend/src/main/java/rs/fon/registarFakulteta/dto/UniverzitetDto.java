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

/**
 *
 * @author Korisnik
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UniverzitetDto {
    private Long id;

    @NotBlank(message = "Naziv je obavezan")
    @Size(max = 100, message = "Naziv ne sme biti duzi od 100 karaktera")
    private String naziv;

    @NotBlank(message = "Adresa je obavezna")
    @Size(max = 100)
    private String adresa;

    @NotBlank(message = "Email je obavezan")
    @Size(max = 50)
    private String email;

    @NotBlank(message = "Telefon je obavezan")
    @Size(max = 50)
    private String telefon;

    @NotNull(message = "Godina osnivanja je obavezna")
    private Integer godinaOsnivanja;

    @NotBlank(message = "Website je obavezan")
    @Size(max = 50)
    private String website;
}
