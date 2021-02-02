package com.foxminded.university.controller.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxminded.university.controller.repository.GroupRepository;
import com.foxminded.university.controller.repository.StudentRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentsServiceTest {
    @Mock
    GroupRepository groupRepository;
    @Mock
    StudentRepository studentRepository;
    
    @InjectMocks
    StudentsService studentsService;
    
    @BeforeAll
    void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    void getStudentShouldCallStudentRepository() {
        studentsService.getStudent(111);
        verify(studentRepository).findById(111);
    }

    @Test
    void getGroupByStudentShouldCallGroupRepositoryTest() {
        studentsService.getGroupByStudent(222);
        verify(groupRepository).findByStudentId(anyInt());
    }

    @Test
    void getGroupByIdShouldCallGroupRepositoryTest() {
        studentsService.getGroupById(333);
        verify(groupRepository).findById(333);
    }
}
