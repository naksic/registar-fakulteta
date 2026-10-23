/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.registarFakulteta.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.fon.registarFakulteta.model.Korisnik;

/**
 *
 * @author Korisnik
 */
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Optional<Korisnik> findByUsername(String username);
}
