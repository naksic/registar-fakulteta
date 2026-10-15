/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "nastavno_osoblje")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class NastavnoOsoblje extends Lice {
    @Column(name = "uza_oblast", nullable = false, length = 100)
    private String uzaOblast;

    @Column(name = "datum_izbora", nullable = false)
    private LocalDate datumIzbora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Zvanje zvanje;
}
