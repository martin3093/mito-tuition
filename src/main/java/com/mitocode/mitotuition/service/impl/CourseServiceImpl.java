package com.mitocode.mitotuition.service.impl;



import com.mitocode.mitotuition.model.Course;
import com.mitocode.mitotuition.repository.ICourseRepo;
import com.mitocode.mitotuition.repository.IGenericRepo;
import com.mitocode.mitotuition.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends CRUDImpl<Course, Integer> implements ICourseService {

    private final ICourseRepo repo;

    @Override
    protected IGenericRepo<Course, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Course> findByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Course> listActive() {
        return repo.findByStatusTrue();
    }
}