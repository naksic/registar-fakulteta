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
public class KorisnikDto {
    private Long id;

    @NotBlank(message = "Korisnicko ime je obavezno")
    @Size(max = 50)
    private String username;

    @NotBlank(message = "Lozinka je obavezna")
    @Size(max = 255)
    private String password;

    private boolean ulogovan;

    @NotNull(message = "Univerzitet je obavezan")
    private Long univerzitetId;

    private String nazivUniverziteta;
}
