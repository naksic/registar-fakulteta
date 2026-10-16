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
public class FakultetDto {
    private Long id;

    @NotBlank(message = "Naziv je obavezan")
    @Size(max = 50)
    private String naziv;

    @NotBlank(message = "Skraceni naziv je obavezan")
    @Size(max = 10)
    private String skraceniNaziv;

    @NotBlank(message = "Adresa je obavezna")
    @Size(max = 50)
    private String adresa;

    @NotBlank(message = "Email je obavezan")
    @Size(max = 50)
    private String email;

    @NotBlank(message = "Telefon je obavezan")
    @Size(max = 50)
    private String telefon;

    @NotNull(message = "Godina osnivanja je obavezna")
    private Integer godinaOsnivanja;

    @NotBlank(message = "PIB je obavezan")
    @Size(max = 20)
    private String pib;

    @NotBlank(message = "Maticni broj je obavezan")
    @Size(max = 20)
    private String maticniBroj;

    @NotBlank(message = "Website je obavezan")
    @Size(max = 50)
    private String website;

    @NotBlank(message = "Status je obavezan")
    @Size(max = 30)
    private String status;

    @NotNull(message = "Univerzitet je obavezan")
    private Long univerzitetId;

    private String nazivUniverziteta;
}
