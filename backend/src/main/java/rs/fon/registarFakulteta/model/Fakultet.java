/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "fakultet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fakultet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String naziv;

    @Column(name = "skraceni_naziv", nullable = false, length = 10)
    private String skraceniNaziv;

    @Column(nullable = false, length = 50)
    private String adresa;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String telefon;

    @Column(name = "godina_osnivanja", nullable = false)
    private Integer godinaOsnivanja;

    @Column(nullable = false, length = 20)
    private String pib;

    @Column(name = "maticni_broj", nullable = false, length = 20)
    private String maticniBroj;

    @Column(nullable = false, length = 50)
    private String website;

    @Column(nullable = false, length = 30)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "univerzitet_id", nullable = false)
    private Univerzitet univerzitet;

    @OneToMany(mappedBy = "fakultet", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<OrganizacionaJedinica> organizacioneJedinice = new ArrayList<>();

    @OneToMany(mappedBy = "fakultet", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<MandatUprave> mandati = new ArrayList<>();
}
