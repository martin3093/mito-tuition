package com.mitocode.mitotuition.controller;



import com.mitocode.mitotuition.dto.RegistrationDTO;
import com.mitocode.mitotuition.model.Registration;
import com.mitocode.mitotuition.service.IRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final IRegistrationService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<RegistrationDTO>> findAll() throws Exception {
        List<RegistrationDTO> list = service.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDTO> findById(@PathVariable("id") Integer id) throws Exception {
        RegistrationDTO dto = convertToDto(service.findById(id));
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/courses-students")
    public ResponseEntity<Map<String, List<String>>> listCoursesWithStudents() {
        Map<String, List<String>> result = service.listCoursesWithStudents();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<RegistrationDTO> save(@Valid @RequestBody RegistrationDTO dto) throws Exception {
        Registration obj = service.save(convertToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistrationDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody RegistrationDTO dto) throws Exception {
        dto.setId(id);
        Registration obj = service.update(id, convertToEntity(dto));
        return ResponseEntity.ok().body(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private RegistrationDTO convertToDto(Registration registration) {
        return modelMapper.map(registration, RegistrationDTO.class);
    }

    private Registration convertToEntity(RegistrationDTO dto) {
        return modelMapper.map(dto, Registration.class);
    }
}