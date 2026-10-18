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
import rs.fon.registarFakulteta.dto.NastavnoOsobljeDto;
import rs.fon.registarFakulteta.service.NastavnoOsobljeService;

/**
 *
 * @author Korisnik
 */

@RestController
@RequestMapping("/api/nastavno-osoblje")
@RequiredArgsConstructor
public class NastavnoOsobljeController {
    private final NastavnoOsobljeService nastavnoOsobljeService;

    @GetMapping
    public ResponseEntity<List<NastavnoOsobljeDto>> getAll() {
        return ResponseEntity.ok(nastavnoOsobljeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NastavnoOsobljeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(nastavnoOsobljeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<NastavnoOsobljeDto> create(@Valid @RequestBody NastavnoOsobljeDto dto) {
        NastavnoOsobljeDto sacuvano = nastavnoOsobljeService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvano);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NastavnoOsobljeDto> update(@PathVariable Long id, @Valid @RequestBody NastavnoOsobljeDto dto) {
        return ResponseEntity.ok(nastavnoOsobljeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nastavnoOsobljeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
