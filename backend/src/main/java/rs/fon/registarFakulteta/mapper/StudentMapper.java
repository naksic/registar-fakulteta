/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.mapper;

import org.springframework.stereotype.Component;
import rs.fon.registarFakulteta.dto.StudentDto;
import rs.fon.registarFakulteta.model.Student;

/**
 *
 * @author Korisnik
 */
@Component
public class StudentMapper {
    
    public StudentDto toDto(Student s) {
        StudentDto dto = new StudentDto();
        dto.setId(s.getId());
        dto.setIme(s.getIme());
        dto.setPrezime(s.getPrezime());
        dto.setDatumRodjenja(s.getDatumRodjenja());
        dto.setEmail(s.getEmail());
        dto.setTelefon(s.getTelefon());
        dto.setBrojIndeksa(s.getBrojIndeksa());
        dto.setGodinaStudija(s.getGodinaStudija());
        return dto;
    }

    public Student toEntity(StudentDto dto) {
        Student s = new Student();
        s.setIme(dto.getIme());
        s.setPrezime(dto.getPrezime());
        s.setDatumRodjenja(dto.getDatumRodjenja());
        s.setEmail(dto.getEmail());
        s.setTelefon(dto.getTelefon());
        s.setBrojIndeksa(dto.getBrojIndeksa());
        s.setGodinaStudija(dto.getGodinaStudija());
        return s;
    }
}
