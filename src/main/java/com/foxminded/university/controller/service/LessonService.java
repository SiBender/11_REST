package com.foxminded.university.controller.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.foxminded.university.controller.repository.ClassroomRepository;
import com.foxminded.university.controller.repository.CourseRepository;
import com.foxminded.university.controller.repository.LessonRepository;
import com.foxminded.university.controller.repository.TimeslotRepository;

import com.foxminded.university.model.Classroom;
import com.foxminded.university.model.Course;
import com.foxminded.university.model.Lesson;
import com.foxminded.university.model.Timeslot;

@Service
public class LessonService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    private final ClassroomRepository classroomRepository;
    private final LessonRepository lessonRepository;
    private final TimeslotRepository timeslotRepository;
    private final CourseRepository courseRepository;
    
    @Autowired
    public LessonService(ClassroomRepository classroomRepository, LessonRepository lessonRepository,
            TimeslotRepository timeslotRepository, CourseRepository courseRepository) {
        this.classroomRepository = classroomRepository;
        this.lessonRepository = lessonRepository;
        this.timeslotRepository = timeslotRepository;
        this.courseRepository = courseRepository;
    }
      
    public List<Classroom> getAllClassrooms() {
        if (logger.isInfoEnabled()) {
            logger.info("Get all classrooms");
        }
        return classroomRepository.findAll(Sort.by("id"));
    }
    
    public Classroom getClassroomById(int id) {
        return classroomRepository.findById(id);
    }
    
    public void addClassroom(Classroom classroom) {
        classroomRepository.save(classroom);
    }
    
    public void updateClassroom(Classroom classroom) {
        classroomRepository.save(classroom);
    }
    
    public void deleteClassroom(int id) {
        Classroom classroom = new Classroom();
        classroom.setId(id);
        classroomRepository.delete(classroom);
    }

    public List<Timeslot> getAllTimeslots() {
        if (logger.isInfoEnabled()) {
            logger.info("Get all timeslots");
        }
        return timeslotRepository.findAll(Sort.by("description"));
    }
    
    public Timeslot getTimeslotById(int id) {
        return timeslotRepository.findById(id);
    }
    
    public void addTimeslot(Timeslot timeslot) {
        timeslotRepository.save(timeslot); 
    }
    
    public void updateTimeslot(Timeslot timeslot) {
        timeslotRepository.save(timeslot); 
    }
    
    public void deleteTimeslot(int id) {
        Timeslot timeslot = new Timeslot();
        timeslot.setId(id);
        timeslotRepository.delete(timeslot);
    }
    
    public List<Course> getCoursesByTeacher(int teacherId) {
        logger.info("Get courses by teacher (id = {})", teacherId);

        return courseRepository.findByTeacherId(teacherId);
    }
    
    public void createLesson(String date, int timeslotId, int classroomId, int courseId) {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("Try to create lesson ({}, {}, {}, {})", date, timeslotId, classroomId, courseId);
            }
            LocalDate lessonDate = LocalDate.parse(date);
            
            Timeslot timeslot = new Timeslot();
            timeslot.setId(timeslotId);
            
            Classroom classroom = new Classroom();
            classroom.setId(classroomId);
            
            Course course = new Course();
            course.setId(courseId);
            
            Lesson lesson = new Lesson();
            lesson.setDate(lessonDate);
            lesson.setTime(timeslot);
            lesson.setClassroom(classroom);
            lesson.setCourse(course);
            
            lessonRepository.save(lesson);
            if (logger.isInfoEnabled()) {
                logger.info("Lesson created");
            }
        } catch (DateTimeParseException ex) {
            if (logger.isErrorEnabled()) {
                logger.error("Error while creating new lesson", ex);
            }
        }  
    }
    
    public Lesson getLessonById(int id) {
        return lessonRepository.findById(id);
    }
    
    public void updateLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }
    
    public void deleteLesson(int id) {
        Lesson lesson = new Lesson();
        lesson.setId(id);
        lessonRepository.delete(lesson);
    }
}
