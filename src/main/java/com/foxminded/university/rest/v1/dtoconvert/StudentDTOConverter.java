package com.foxminded.university.rest.v1.dtoconvert;

import org.springframework.stereotype.Component;

import com.foxminded.university.model.Student;
import com.foxminded.university.rest.v1.dto.StudentDTO;

@Component
public class StudentDTOConverter {
    public StudentDTO toDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setGroup(student.getGroup());
        return studentDTO;
    }
    
    public Student toModel(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setGroup(studentDTO.getGroup());
        return student;
    }
}
