package com.mitocode.mitotuition.repository;

import com.mitocode.mitotuition.model.Student;

import java.util.List;

public interface IStudentRepo extends IGenericRepo<Student, Integer> {
    // Búsqueda por DNI
    Student findByDni(String dni);

    // Listar estudiantes ordenados por edad descendente
    List<Student> findAllByOrderByAgeDesc();

}
