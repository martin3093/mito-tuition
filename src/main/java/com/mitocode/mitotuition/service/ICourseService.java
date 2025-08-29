package com.mitocode.mitotuition.service;
import com.mitocode.mitotuition.model.Course;

import java.util.List;

public interface ICourseService extends ICRUD<Course, Integer> {

    List<Course> findByName(String name);

    List<Course> listActive();
}