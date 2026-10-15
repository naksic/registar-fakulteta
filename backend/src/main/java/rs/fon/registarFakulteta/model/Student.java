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
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class Student extends Lice {
    @Column(name = "broj_indeksa", nullable = false, length = 20)
    private String brojIndeksa;

    @Column(name = "godina_studija", nullable = false)
    private Integer godinaStudija;
}
