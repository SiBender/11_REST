package com.foxminded.university.rest.v1.dtoconvert;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.foxminded.university.model.Course;
import com.foxminded.university.model.Faculty;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.rest.v1.dto.CourseDTO;
import com.foxminded.university.rest.v1.dto.TeacherDTO;

class TeacherDTOConverterTest {
    TeacherDTOConverter teacherConverter = new TeacherDTOConverter(new CourseDTOConverter());
    
    @Test
    void toDTOShouldReturnObjectWithProperFieldsTest() {
        int id = 1;
        String firstName = "firstName";
        String lastName = "lastName";
        Faculty faculty = new Faculty();
        Course course = new Course();
        int courseId = 100;
        course.setId(courseId);
        List<Course> courses = Arrays.asList(course);
        
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setFaculty(faculty);
        teacher.setCourses(courses);
        
        TeacherDTO teacherDTO = teacherConverter.toDTO(teacher);
        
        assertEquals(id, teacherDTO.getId());
        assertEquals(firstName, teacherDTO.getFirstName());
        assertEquals(lastName, teacherDTO.getLastName());
        assertSame(faculty, teacherDTO.getFaculty());
        assertEquals(courseId, teacherDTO.getCourses().get(0).getId());
    }

    @Test
    void toModelShouldReturnObjectWithProperFieldsTest() {
        int id = 1;
        String firstName = "firstName";
        String lastName = "lastName";
        Faculty faculty = new Faculty();
        CourseDTO courseDTO = new CourseDTO();
        int courseId = 100;
        courseDTO.setId(courseId);
        List<CourseDTO> courses = Arrays.asList(courseDTO);
        
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(id);
        teacherDTO.setFirstName(firstName);
        teacherDTO.setLastName(lastName);
        teacherDTO.setFaculty(faculty);
        teacherDTO.setCourses(courses);
        
        Teacher teacher = teacherConverter.toModel(teacherDTO);
        
        assertEquals(id, teacher.getId());
        assertEquals(firstName, teacher.getFirstName());
        assertEquals(lastName, teacher.getLastName());
        assertSame(faculty, teacher.getFaculty());
        assertEquals(courseId, teacher.getCourses().get(0).getId());
    }
}
