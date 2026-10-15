/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "administrativno_osoblje")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class AdministrativnoOsoblje extends Lice {
    @Column(name = "radno_mesto", nullable = false, length = 50)
    private String radnoMesto;

    @Column(length = 50)
    private String sektor;
}
