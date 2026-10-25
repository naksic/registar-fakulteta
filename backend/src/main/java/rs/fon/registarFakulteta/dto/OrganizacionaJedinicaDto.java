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
public class OrganizacionaJedinicaDto {
    private Long id;

    @NotBlank(message = "Naziv je obavezan")
    @Size(max = 50)
    private String naziv;

    @NotNull(message = "Fakultet je obavezan")
    private Long fakultetId;

    private String nazivFakulteta;
    
    private String nazivUniverziteta;
}
