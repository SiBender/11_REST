package com.foxminded.university.controller.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.foxminded.university.controller.repository.ClassroomRepository;
import com.foxminded.university.controller.repository.CourseRepository;
import com.foxminded.university.controller.repository.FacultyRepository;
import com.foxminded.university.controller.repository.GroupRepository;
import com.foxminded.university.controller.repository.StudentRepository;
import com.foxminded.university.controller.repository.TeacherRepository;
import com.foxminded.university.model.Faculty;
import com.foxminded.university.model.Group;
import com.foxminded.university.model.Teacher;
import com.foxminded.university.model.Student;
import com.foxminded.university.model.Classroom;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdministrativeServiceTest {
    @Mock
    FacultyRepository facultyRepository;
    @Mock
    GroupRepository groupRepository;
    @Mock
    StudentRepository studentRepository;
    @Mock
    TeacherRepository teacherRepository;
    @Mock
    ClassroomRepository classroomRepository;
    @Mock
    CourseRepository courseRepository;
    
    @InjectMocks
    AdministrativeService administrativeService;
    
    @BeforeAll
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @ParameterizedTest
    @CsvSource({"aa, aaaaa, 1",
                "bb, bbbbbbbb, 2", 
                "cc, ccccccc, 3"})
    void createFacultyShouldCallFacultyRepositloryTest(String shortName, String fullName, int times) {
        administrativeService.createFaculty(shortName, fullName);
        verify(facultyRepository, times(times)).save(any(Faculty.class));
    }

    @ParameterizedTest
    @CsvSource({"group1, 1, 1",
                "group10, 22, 2", 
                "group100, 33, 3"})
    void createGroupShouldCallGroupRepositoryTest(String name, int facultyId, int times) {
        administrativeService.createGroup(name, facultyId);
        verify(groupRepository, times(times)).save(any(Group.class));
    }

    @Test
    void createTeacherShouldCallTeacherRepositoryTest() {
        administrativeService.createTeacher("firstName", "lastName", 1000);
        verify(teacherRepository).save(any(Teacher.class));
    }

    @Test
    void createStudentShouldCallStudentRepositoryTest() {
        administrativeService.createStudent("stName", "stLastName", 2000);
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void createClassroomShouldCallClassroomRepositoryTest() {
        administrativeService.createClassroom("AnyNumber", 1000);
        verify(classroomRepository).save(any(Classroom.class));
    }

    @Test
    void getAllFacultiesShouldCallFacultyRepositoryTest() {
        administrativeService.getAllFaculties();
        verify(facultyRepository).findAll(any(Sort.class));
    }

    @Test
    void getGroupsByFacultyShouldCallGroupRepositoryTest() {
        administrativeService.getGroupsByFaculty(111);
        verify(groupRepository).findByFacultyId(anyInt());
    }

    @Test
    void getTeachersByFacultyShouldCallTeacherRepositoryTest() {
        administrativeService.getTeachersByFaculty(111);
        verify(teacherRepository).findByFacultyId(anyInt());
    }

    @Test
    void getCoursesShouldCallCourseRepositoryTest() {
        administrativeService.getCourses(222);
        verify(courseRepository).findByTeacherId(anyInt());
    }

}
