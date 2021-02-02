package com.foxminded.university.rest.v1.dtoconvert;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.university.model.Teacher;
import com.foxminded.university.rest.v1.dto.TeacherDTO;

@Component
public class TeacherDTOConverter {
    private CourseDTOConverter courseConverter;
    
    @Autowired
    public TeacherDTOConverter(CourseDTOConverter courseConverter) {
        this.courseConverter = courseConverter;
    }
    
    public TeacherDTO toDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setFirstName(teacher.getFirstName());
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setFaculty(teacher.getFaculty());
        teacherDTO.setCourses(teacher.getCourses()
                                     .stream()
                                     .map(courseConverter::toDTO)
                                     .collect(Collectors.toList()));
        return teacherDTO;
    }
    
    public Teacher toModel(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherDTO.getId());
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setFaculty(teacherDTO.getFaculty());
        teacher.setCourses(teacherDTO.getCourses()
                                     .stream()
                                     .map(courseConverter::toModel)
                                     .collect(Collectors.toList()));
        return teacher;
    }
}
