package com.mitocode.mitotuition.service.impl;


import com.mitocode.mitotuition.model.Registration;
import com.mitocode.mitotuition.model.Student;
import com.mitocode.mitotuition.repository.IGenericRepo;
import com.mitocode.mitotuition.repository.IRegistrationRepo;
import com.mitocode.mitotuition.service.IRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl extends CRUDImpl<Registration, Integer> implements IRegistrationService {

    private final IRegistrationRepo repo;

    @Override
    protected IGenericRepo<Registration, Integer> getRepo() {
        return repo;
    }

    @Override
    @Transactional
    public Registration save(Registration registration) throws Exception {
        registration.setRegistrationDate(LocalDateTime.now());
        registration.getDetails().forEach(detail -> detail.setRegistration(registration));
        return repo.save(registration);
    }

    @Override
    public List<Registration> findByStudent(Student student) {
        return repo.findByStudent(student);
    }

    @Override
    public Map<String, List<String>> listCoursesWithStudents() {
        List<Object[]> result = repo.listCoursesWithStudents();


        Map<String, List<String>> coursesStudents = new HashMap<>();

        result.forEach(row -> {
            String courseName = (String) row[0];
            String studentName = (String) row[1] + " " + (String) row[2];

            if (!coursesStudents.containsKey(courseName)) {
                coursesStudents.put(courseName, new java.util.ArrayList<>());
            }
            coursesStudents.get(courseName).add(studentName);
        });

        return coursesStudents;
    }
}