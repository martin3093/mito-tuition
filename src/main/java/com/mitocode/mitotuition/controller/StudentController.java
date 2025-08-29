package com.mitocode.mitotuition.controller;



import com.mitocode.mitotuition.dto.StudentDTO;
import com.mitocode.mitotuition.model.Student;
import com.mitocode.mitotuition.service.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAll() throws Exception {
        List<StudentDTO> list = service.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable("id") Integer id) throws Exception {
        StudentDTO dto = convertToDto(service.findById(id));
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/ordered-by-age")
    public ResponseEntity<List<StudentDTO>> listOrderedByAge() {
        List<StudentDTO> list = service.listOrderedByAgeDesc()
                .stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<StudentDTO> findByDni(@PathVariable("dni") String dni) {
        Student student = service.findByDni(dni);
        return student != null ?
                ResponseEntity.ok().body(convertToDto(student)) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody StudentDTO dto) throws Exception {
        Student obj = service.save(convertToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody StudentDTO dto) throws Exception {
        dto.setId(id);
        Student obj = service.update(id, convertToEntity(dto));
        return ResponseEntity.ok().body(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private StudentDTO convertToDto(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }

    private Student convertToEntity(StudentDTO dto) {
        return modelMapper.map(dto, Student.class);
    }
}
