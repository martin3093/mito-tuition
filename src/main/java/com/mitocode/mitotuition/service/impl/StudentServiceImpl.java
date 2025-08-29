package com.mitocode.mitotuition.service.impl;

import com.mitocode.mitotuition.model.Student;
import com.mitocode.mitotuition.repository.IGenericRepo;
import com.mitocode.mitotuition.repository.IStudentRepo;
import com.mitocode.mitotuition.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CRUDImpl<Student, Integer> implements IStudentService {

    private final IStudentRepo repo;


    @Override
    protected IGenericRepo<Student, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Student> listOrderedByAgeDesc() {
        return repo.findAllByOrderByAgeDesc();
    }

    @Override
    public Student findByDni(String dni) {
        return repo.findByDni(dni);
    }
}
