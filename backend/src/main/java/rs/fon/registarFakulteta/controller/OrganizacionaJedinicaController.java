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
import rs.fon.registarFakulteta.dto.OrganizacionaJedinicaDto;
import rs.fon.registarFakulteta.service.OrganizacionaJedinicaService;

/**
 *
 * @author Korisnik
 */

@RestController
@RequestMapping("/api/organizacione-jedinice")
@RequiredArgsConstructor
public class OrganizacionaJedinicaController {
    private final OrganizacionaJedinicaService organizacionaJedinicaService;

    @GetMapping
    public ResponseEntity<List<OrganizacionaJedinicaDto>> getAll() {
        return ResponseEntity.ok(organizacionaJedinicaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizacionaJedinicaDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(organizacionaJedinicaService.getById(id));
    }

    @GetMapping("/fakultet/{fakultetId}")
    public ResponseEntity<List<OrganizacionaJedinicaDto>> getByFakultetId(@PathVariable Long fakultetId) {
        return ResponseEntity.ok(organizacionaJedinicaService.getByFakultetId(fakultetId));
    }

    @PostMapping
    public ResponseEntity<OrganizacionaJedinicaDto> create(@Valid @RequestBody OrganizacionaJedinicaDto dto) {
        OrganizacionaJedinicaDto sacuvana = organizacionaJedinicaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvana);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizacionaJedinicaDto> update(@PathVariable Long id, @Valid @RequestBody OrganizacionaJedinicaDto dto) {
        return ResponseEntity.ok(organizacionaJedinicaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        organizacionaJedinicaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
