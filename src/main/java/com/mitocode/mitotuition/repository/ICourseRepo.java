package com.mitocode.mitotuition.repository;



import com.mitocode.mitotuition.model.Course;

import java.util.List;

public interface ICourseRepo extends IGenericRepo<Course, Integer> {

    // Buscar cursos por nombre
    List<Course> findByNameContainingIgnoreCase(String name);

    // Buscar cursos activos
    List<Course> findByStatusTrue();
}