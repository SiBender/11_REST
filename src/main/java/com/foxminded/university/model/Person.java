package com.foxminded.university.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Person {
    @Column(name = "first_name")
    @NotBlank(message = "First name can not be empty")
    private String firstName;
    
    @Column(name = "last_name")
    @NotNull(message = "Last name can not be empty")
    private String lastName;
    
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
