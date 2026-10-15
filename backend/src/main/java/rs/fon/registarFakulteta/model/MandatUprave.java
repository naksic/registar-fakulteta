/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "mandat_uprave")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MandatUprave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datum_pocetka", nullable = false)
    private LocalDate datumPocetka;

    @Column(name = "datum_zavrsetka")
    private LocalDate datumZavrsetka;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fakultet_id", nullable = false)
    private Fakultet fakultet;

    @OneToMany(mappedBy = "mandatUprave", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClanUprave> clanovi = new ArrayList<>();
}
