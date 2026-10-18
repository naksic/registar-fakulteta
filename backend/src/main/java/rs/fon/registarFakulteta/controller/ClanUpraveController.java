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
import rs.fon.registarFakulteta.dto.ClanUpraveDto;
import rs.fon.registarFakulteta.service.ClanUpraveService;

/**
 *
 * @author Korisnik
 */
@RestController
@RequestMapping("/api/clanovi-uprave")
@RequiredArgsConstructor
public class ClanUpraveController {
    private final ClanUpraveService clanUpraveService;

    @GetMapping
    public ResponseEntity<List<ClanUpraveDto>> getAll() {
        return ResponseEntity.ok(clanUpraveService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClanUpraveDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(clanUpraveService.getById(id));
    }

    @GetMapping("/mandat/{mandatId}")
    public ResponseEntity<List<ClanUpraveDto>> getByMandatId(@PathVariable Long mandatId) {
        return ResponseEntity.ok(clanUpraveService.getByMandatId(mandatId));
    }

    @PostMapping
    public ResponseEntity<ClanUpraveDto> create(@Valid @RequestBody ClanUpraveDto dto) {
        ClanUpraveDto sacuvan = clanUpraveService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClanUpraveDto> update(@PathVariable Long id, @Valid @RequestBody ClanUpraveDto dto) {
        return ResponseEntity.ok(clanUpraveService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clanUpraveService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
