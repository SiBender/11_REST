package com.foxminded.university.rest.v1.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.university.model.Faculty;

public class TeacherDTO {
    private int id;
    private Faculty faculty;
    @JsonIgnoreProperties("teacher")
    private List<CourseDTO> courses;
    private String firstName;
    private String lastName;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Faculty getFaculty() {
        return faculty;
    }
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    public List<CourseDTO> getCourses() {
        return courses;
    }
    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
