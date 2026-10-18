/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.exception;

/**
 *
 * @author Korisnik
 */
public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String poruka) {
        super(poruka);
    }
}
