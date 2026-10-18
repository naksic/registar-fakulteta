/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.registarFakulteta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.registarFakulteta.dto.StudentDto;
import rs.fon.registarFakulteta.mapper.StudentMapper;
import rs.fon.registarFakulteta.model.Student;
import rs.fon.registarFakulteta.repository.StudentRepository;
import java.util.List;
import rs.fon.registarFakulteta.exception.ResourceNotFoundException;

/**
 *
 * @author Korisnik
 */
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Transactional(readOnly = true)
    public List<StudentDto> getAll() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public StudentDto getById(Long id) {
        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student sa id: " + id + " ne postoji"));
        return studentMapper.toDto(s);
    }

    @Transactional
    public StudentDto create(StudentDto dto) {
        Student s = studentMapper.toEntity(dto);
        s.setId(null);
        Student sacuvan = studentRepository.save(s);
        return studentMapper.toDto(sacuvan);
    }

    @Transactional
    public StudentDto update(Long id, StudentDto dto) {
        Student postojeci = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student sa id: " + id + " ne postoji"));

        postojeci.setIme(dto.getIme());
        postojeci.setPrezime(dto.getPrezime());
        postojeci.setDatumRodjenja(dto.getDatumRodjenja());
        postojeci.setEmail(dto.getEmail());
        postojeci.setTelefon(dto.getTelefon());
        postojeci.setBrojIndeksa(dto.getBrojIndeksa());
        postojeci.setGodinaStudija(dto.getGodinaStudija());

        Student sacuvan = studentRepository.save(postojeci);
        return studentMapper.toDto(sacuvan);
    }

    @Transactional
    public void delete(Long id) {
        if(!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student sa id: " + id + " ne postoji");
        }
        studentRepository.deleteById(id);
    }
}
