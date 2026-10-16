/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.registarFakulteta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.fon.registarFakulteta.model.Lice;

/**
 *
 * @author Korisnik
 */
public interface LiceRepository extends JpaRepository<Lice, Long> {
    
}
