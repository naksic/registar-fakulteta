/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.registarFakulteta.dto.AdministrativnoOsobljeDto;
import rs.fon.registarFakulteta.service.AdministrativnoOsobljeService;

/**
 *
 * @author Korisnik
 */
@RestController
@RequestMapping("/api/administrativno-osoblje")
@RequiredArgsConstructor
public class AdministrativnoOsobljeController {
    private final AdministrativnoOsobljeService administrativnoOsobljeService;

    @GetMapping
    public ResponseEntity<List<AdministrativnoOsobljeDto>> getAll() {
        return ResponseEntity.ok(administrativnoOsobljeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministrativnoOsobljeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(administrativnoOsobljeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<AdministrativnoOsobljeDto> create(@Valid @RequestBody AdministrativnoOsobljeDto dto) {
        AdministrativnoOsobljeDto sacuvano = administrativnoOsobljeService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvano);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministrativnoOsobljeDto> update(@PathVariable Long id, @Valid @RequestBody AdministrativnoOsobljeDto dto) {
        return ResponseEntity.ok(administrativnoOsobljeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        administrativnoOsobljeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
