/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "univerzitet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Univerzitet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String naziv;

    @Column(nullable = false, length = 100)
    private String adresa;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String telefon;

    @Column(name = "godina_osnivanja", nullable = false)
    private Integer godinaOsnivanja;

    @Column(nullable = false, length = 50)
    private String website;

    @OneToMany(mappedBy = "univerzitet", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Fakultet> fakulteti = new ArrayList<>();
    
    @OneToMany(mappedBy = "univerzitet", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Korisnik> korisnici = new ArrayList<>();
}
