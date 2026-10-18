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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.registarFakulteta.dto.FakultetDto;
import rs.fon.registarFakulteta.service.FakultetService;

/**
 *
 * @author Korisnik
 */

@RestController
@RequestMapping("/api/fakulteti")
@RequiredArgsConstructor
public class FakultetController {
    private final FakultetService fakultetService;

    @GetMapping
    public ResponseEntity<List<FakultetDto>> getAll() {
        return ResponseEntity.ok(fakultetService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FakultetDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(fakultetService.getById(id));
    }

    @GetMapping("/univerzitet/{univerzitetId}")
    public ResponseEntity<List<FakultetDto>> getByUniverzitetId(@PathVariable Long univerzitetId) {
        return ResponseEntity.ok(fakultetService.getByUniverzitetId(univerzitetId));
    }

    @GetMapping("/pretraga")
    public ResponseEntity<List<FakultetDto>> searchByNaziv(@RequestParam String naziv) {
        return ResponseEntity.ok(fakultetService.searchByNaziv(naziv));
    }

    @PostMapping
    public ResponseEntity<FakultetDto> create(@Valid @RequestBody FakultetDto dto) {
        FakultetDto sacuvan = fakultetService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FakultetDto> update(@PathVariable Long id, @Valid @RequestBody FakultetDto dto) {
        return ResponseEntity.ok(fakultetService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fakultetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
