package com.mitocode.mitotuition.controller;



import com.mitocode.mitotuition.dto.CourseDTO;
import com.mitocode.mitotuition.model.Course;
import com.mitocode.mitotuition.service.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAll() throws Exception {
        List<CourseDTO> list = service.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable("id") Integer id) throws Exception {
        CourseDTO dto = convertToDto(service.findById(id));
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/active")
    public ResponseEntity<List<CourseDTO>> listActive() {
        List<CourseDTO> list = service.listActive()
                .stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CourseDTO>> findByName(@PathVariable("name") String name) {
        List<CourseDTO> list = service.findByName(name)
                .stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> save(@Valid @RequestBody CourseDTO dto) throws Exception {
        Course obj = service.save(convertToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CourseDTO dto) throws Exception {
        dto.setId(id);
        Course obj = service.update(id, convertToEntity(dto));
        return ResponseEntity.ok().body(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private CourseDTO convertToDto(Course course) {
        return modelMapper.map(course, CourseDTO.class);
    }

    private Course convertToEntity(CourseDTO dto) {
        return modelMapper.map(dto, Course.class);
    }
}