package com.mitocode.mitotuition.repository;



import com.mitocode.mitotuition.model.Registration;
import com.mitocode.mitotuition.model.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRegistrationRepo extends IGenericRepo<Registration, Integer> {

    // Obtener matrículas por estudiante
    List<Registration> findByStudent(Student student);

    // Consulta personalizada para obtener la relación de cursos y estudiantes
    @Query("SELECT c.name, s.firstName, s.lastName FROM Registration r " +
            "JOIN r.student s " +
            "JOIN r.details d " +
            "JOIN d.course c " +
            "WHERE r.status = true " +
            "ORDER BY c.name, s.lastName")
    List<Object[]> listCoursesWithStudents();
}