/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.exception;

/**
 *
 * @author Korisnik
 */
public class BadRequestException extends RuntimeException {
    
    public BadRequestException(String poruka) {
        super(poruka);
    }
}
