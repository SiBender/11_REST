package com.foxminded.university.controller.web;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxminded.university.controller.service.LessonService;
import com.foxminded.university.controller.service.TimetableService;
import com.foxminded.university.controller.util.TimetableFormatter;
import com.foxminded.university.model.Lesson;
import com.foxminded.university.model.Timeslot;
import com.foxminded.university.model.Timetable;

@Controller
public class TimetablesController {
    private TimetableService timetableService;
    private LessonService lessonService;
    private TimetableFormatter timetableFormatter;
    
    @Autowired
    public TimetablesController(TimetableService timetableService, LessonService lessonService,
                               TimetableFormatter timetableFormatter) {
        this.timetableService = timetableService;
        this.lessonService = lessonService;
        this.timetableFormatter = timetableFormatter;
    }
    
    @RequestMapping("/timetable")
    public String timetableInfo(Model model) {
        int studentId = 1;
        Timetable timetable = timetableService.getStudentTimetable("2020-06-15", "2020-06-21", studentId);
        model.addAttribute("studentId", studentId);
        model.addAttribute("timetable", timetable);

        List<Timeslot> timeslots = lessonService.getAllTimeslots();
        model.addAttribute("timeslots", timeslots);
        model.addAttribute("timemap", timetableFormatter.generateFormattedTable(timetable, timeslots));
        return "timetable";
    }
    
    @GetMapping("/teacherstimetable")
    public String getTimetableByTeacher(Model model) {
        int teacherId = 1;
        model.addAttribute("teacherId", teacherId);
        
        Timetable timetable = timetableService.getTeacherTimetable("2020-06-15", "2020-06-21", teacherId);
        List<Timeslot> timeslots = lessonService.getAllTimeslots();
        model.addAttribute("dateInterval", timetable.getDateInterval());
        model.addAttribute("timeslots", timeslots);
        model.addAttribute("timemap", timetableFormatter.generateFormattedTable(timetable, timeslots));
        
        return "teacherstimetable";
    }
    
    @GetMapping("/timetable/addlesson")
    public String createNewLesson(@RequestParam String date, 
                                  @RequestParam int tid,
                                  @RequestParam int timeslot,
                                  Model model) {
        try {
            Lesson lesson = new Lesson();
            lesson.setDate(LocalDate.parse(date));
            lesson.setTime(new Timeslot());
            lesson.getTime().setId(timeslot);
            model.addAttribute("lesson", lesson);
            model.addAttribute("timeslotId", timeslot);
            model.addAttribute("classrooms", lessonService.getAllClassrooms());
            model.addAttribute("timeslots", lessonService.getAllTimeslots());
            model.addAttribute("courses", lessonService.getCoursesByTeacher(tid));
        } catch (Exception ex) {
            //log error
        }
        return "timetable/addlesson";
    }
    
    @PostMapping("/timetable/addlesson")
    public String saveNewLesson(@Valid Lesson lesson, BindingResult bindingResult, Model model) {
        if(lesson.getTime() == null || lesson.getTime().getId() < 1) {
            bindingResult.rejectValue("time.id", "error.lesson", "Choose a timeslot"); 
        }
        if(lesson.getCourse() == null || lesson.getCourse().getId() < 1) {
            bindingResult.rejectValue("course.id", "error.lesson", "Choose a course"); 
        }
        if(lesson.getClassroom() == null || lesson.getClassroom().getId() < 1) {
            bindingResult.rejectValue("time.id", "error.lesson", "Choose a classroom"); 
        }
        
        if(bindingResult.hasErrors()) {
            model.addAttribute("timeslotId", lesson.getTime().getId());
            model.addAttribute("classrooms", lessonService.getAllClassrooms());
            model.addAttribute("timeslots", lessonService.getAllTimeslots());
            model.addAttribute("courses", lessonService.getCoursesByTeacher(1));
            return "timetable/addlesson";
        }
        lessonService.createLesson(lesson.getDate().toString(), lesson.getTime().getId(), 
                                   lesson.getClassroom().getId(), lesson.getCourse().getId());
        return "redirect:/teacherstimetable";
    }
    
    @GetMapping("/timetable/editlesson")
    public String getLessonInfo(@RequestParam int id, @RequestParam int tid, Model model) {
        model.addAttribute("lesson", lessonService.getLessonById(id));
        model.addAttribute("classrooms", lessonService.getAllClassrooms());
        model.addAttribute("timeslots", lessonService.getAllTimeslots());
        model.addAttribute("courses", lessonService.getCoursesByTeacher(tid));
        return "timetable/editlesson";
    }
    
    @PostMapping("/timetable/editlesson")
    public String updateLesson(@Valid Lesson lesson, BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()) {
            int teacherId = 1;
            model.addAttribute("classrooms", lessonService.getAllClassrooms());
            model.addAttribute("timeslots", lessonService.getAllTimeslots());
            model.addAttribute("courses", lessonService.getCoursesByTeacher(teacherId));
            return "timetable/editlesson";
        }
        
        lessonService.updateLesson(lesson);
        return "redirect:/teacherstimetable";
    }
    
    @GetMapping("/deletelesson")
    public String deleteLesson(@RequestParam int id) {
        lessonService.deleteLesson(id);
        return "redirect:/teacherstimetable";
    }
}
