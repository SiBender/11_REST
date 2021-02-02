package com.foxminded.university.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.university.model.Group;

public class StudentDTO {
    private int id;
    @JsonIgnoreProperties({"students", "courses"})
    private Group group;
    private String firstName;
    private String lastName;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
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
