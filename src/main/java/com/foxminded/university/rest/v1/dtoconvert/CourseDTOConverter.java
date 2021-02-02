package com.foxminded.university.rest.v1.dtoconvert;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Course;
import com.foxminded.university.rest.v1.dto.CourseDTO;

@Component
public class CourseDTOConverter {
    
    public CourseDTO toDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setTeacher(course.getTeacher());
        return courseDTO;
    }
    
    public Course toModel(CourseDTO courseDTO) {
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setTeacher(courseDTO.getTeacher());
        return course;
    }
}
