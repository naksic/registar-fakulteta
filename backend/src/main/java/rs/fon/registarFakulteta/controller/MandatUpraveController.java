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
import rs.fon.registarFakulteta.dto.MandatUpraveDto;
import rs.fon.registarFakulteta.service.MandatUpraveService;

/**
 *
 * @author Korisnik
 */
@RestController
@RequestMapping("/api/mandati-uprave")
@RequiredArgsConstructor
public class MandatUpraveController {
    private final MandatUpraveService mandatUpraveService;

    @GetMapping
    public ResponseEntity<List<MandatUpraveDto>> getAll() {
        return ResponseEntity.ok(mandatUpraveService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MandatUpraveDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mandatUpraveService.getById(id));
    }

    @GetMapping("/fakultet/{fakultetId}")
    public ResponseEntity<List<MandatUpraveDto>> getByFakultetId(@PathVariable Long fakultetId) {
        return ResponseEntity.ok(mandatUpraveService.getByFakultetId(fakultetId));
    }

    @GetMapping("/trenutni")
    public ResponseEntity<List<MandatUpraveDto>> getTrenutniMandati() {
        return ResponseEntity.ok(mandatUpraveService.getTrenutniMandati());
    }

    @GetMapping("/bivsi")
    public ResponseEntity<List<MandatUpraveDto>> getBivsiMandati() {
        return ResponseEntity.ok(mandatUpraveService.getBivsiMandati());
    }

    @PostMapping
    public ResponseEntity<MandatUpraveDto> create(@Valid @RequestBody MandatUpraveDto dto) {
        MandatUpraveDto sacuvan = mandatUpraveService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MandatUpraveDto> update(@PathVariable Long id, @Valid @RequestBody MandatUpraveDto dto) {
        return ResponseEntity.ok(mandatUpraveService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mandatUpraveService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
