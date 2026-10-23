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
import org.springframework.web.bind.annotation.*;
import rs.fon.registarFakulteta.dto.KorisnikDto;
import rs.fon.registarFakulteta.service.KorisnikService;

/**
 *
 * @author Korisnik
 */

@RestController
@RequestMapping("/api/korisnici")
@RequiredArgsConstructor
public class KorisnikController {
    private final KorisnikService korisnikService;

    @GetMapping
    public ResponseEntity<List<KorisnikDto>> getAll() {
        return ResponseEntity.ok(korisnikService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KorisnikDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(korisnikService.getById(id));
    }

    @PostMapping
    public ResponseEntity<KorisnikDto> create(@Valid @RequestBody KorisnikDto dto) {
        KorisnikDto sacuvan = korisnikService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KorisnikDto> update(@PathVariable Long id, @Valid @RequestBody KorisnikDto dto) {
        return ResponseEntity.ok(korisnikService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        korisnikService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<KorisnikDto> login(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok(korisnikService.login(username, password));
    }

    @PostMapping("/logout/{id}")
    public ResponseEntity<Void> logout(@PathVariable Long id) {
        korisnikService.logout(id);
        return ResponseEntity.noContent().build();
    }
}
