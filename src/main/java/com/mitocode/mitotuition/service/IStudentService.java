package com.mitocode.mitotuition.service;




import com.mitocode.mitotuition.model.Student;

import java.util.List;

public interface IStudentService extends ICRUD<Student, Integer> {

    List<Student> listOrderedByAgeDesc();

    Student findByDni(String dni);
}