package com.mitocode.mitotuition.service;
import com.mitocode.mitotuition.model.Course;

import java.util.List;



import com.mitocode.mitotuition.model.Registration;
import com.mitocode.mitotuition.model.Student;

import java.util.List;
import java.util.Map;

public interface IRegistrationService extends ICRUD<Registration, Integer> {

    List<Registration> findByStudent(Student student);

    Map<String, List<String>> listCoursesWithStudents();
}