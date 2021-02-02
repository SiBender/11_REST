package com.foxminded.university.rest.v1.dtoconvert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.rest.v1.dto.CourseDTO;

class CourseDTOConverterTest {
    CourseDTOConverter courseConverter = new CourseDTOConverter();
    
    @Test
    void toDTOShouldReturnObjectWithProperFieldsTest() {
        int id = 1;
        String name = "courseName";
        String description = "descr";
        Teacher teacher = new Teacher();
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        course.setDescription(description);
        course.setTeacher(teacher);
        
        CourseDTO courseDTO = courseConverter.toDTO(course);
                
        assertEquals(id, courseDTO.getId());
        assertEquals(name, courseDTO.getName());
        assertEquals(description, courseDTO.getDescription());
        assertSame(teacher, courseDTO.getTeacher());
    }

    @Test
    void toModelShouldReturnObjectWithProperFieldsTest() {
        int id = 1;
        String name = "courseName";
        String description = "descr";
        Teacher teacher = new Teacher();
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(id);
        courseDTO.setName(name);
        courseDTO.setDescription(description);
        courseDTO.setTeacher(teacher);
        
        Course course = courseConverter.toModel(courseDTO);
                
        assertEquals(id, course.getId());
        assertEquals(name, course.getName());
        assertEquals(description, course.getDescription());
        assertSame(teacher, course.getTeacher());
    }

}
