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
import rs.fon.registarFakulteta.dto.UniverzitetDto;
import rs.fon.registarFakulteta.service.UniverzitetService;

/**
 *
 * @author Korisnik
 */

@RestController
@RequestMapping("/api/univerziteti")
@RequiredArgsConstructor
public class UniverzitetController {
    private final UniverzitetService univerzitetService;

    @GetMapping
    public ResponseEntity<List<UniverzitetDto>> getAll() {
        return ResponseEntity.ok(univerzitetService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniverzitetDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(univerzitetService.getById(id));
    }

    @PostMapping
    public ResponseEntity<UniverzitetDto> create(@Valid @RequestBody UniverzitetDto dto) {
        UniverzitetDto sacuvan = univerzitetService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniverzitetDto> update(@PathVariable Long id, @Valid @RequestBody UniverzitetDto dto) {
        return ResponseEntity.ok(univerzitetService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        univerzitetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
