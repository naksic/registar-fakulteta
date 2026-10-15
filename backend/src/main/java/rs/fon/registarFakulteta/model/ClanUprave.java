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
/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "clan_uprave")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClanUprave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Funkcija funkcija;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mandat_uprave_id", nullable = false)
    private MandatUprave mandatUprave;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lice_id", nullable = false)
    private Lice lice;
}
