/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.exception;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Korisnik
 */
@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String poruka;
    private Map<String, String> greske;

    public ErrorResponse(int status, String poruka) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.poruka = poruka;
    }

    public ErrorResponse(int status, String poruka, Map<String, String> greske) {
        this(status, poruka);
        this.greske = greske;
    }
}
