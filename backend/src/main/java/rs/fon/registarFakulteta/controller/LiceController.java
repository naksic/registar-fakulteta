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
import rs.fon.registarFakulteta.dto.LiceDto;
import rs.fon.registarFakulteta.service.LiceService;

/**
 *
 * @author Korisnik
 */

@RestController
@RequestMapping("/api/lica")
@RequiredArgsConstructor
public class LiceController {
    private final LiceService liceService;

    @GetMapping
    public ResponseEntity<List<LiceDto>> getAll() {
        return ResponseEntity.ok(liceService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LiceDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(liceService.getById(id));
    }

    @GetMapping("/pretraga")
    public ResponseEntity<List<LiceDto>> searchByImePrezime(@RequestParam String imePrezime) {
        return ResponseEntity.ok(liceService.searchByImePrezime(imePrezime));
    }

    @PostMapping
    public ResponseEntity<LiceDto> create(@Valid @RequestBody LiceDto dto) {
        LiceDto sacuvano = liceService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvano);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LiceDto> update(@PathVariable Long id, @Valid @RequestBody LiceDto dto) {
        return ResponseEntity.ok(liceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        liceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
