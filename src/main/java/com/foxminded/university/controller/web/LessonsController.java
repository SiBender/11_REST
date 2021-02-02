package com.foxminded.university.controller.web;

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
import com.foxminded.university.model.Classroom;
import com.foxminded.university.model.Timeslot;

@Controller
public class LessonsController {
    private LessonService lessonService;
    
    @Autowired
    public LessonsController(LessonService lessonService) {
        this.lessonService = lessonService;
    }
    
    @RequestMapping("/lesson")
    public String lessonsInfo(Model model) {
        List<Classroom> classrooms = lessonService.getAllClassrooms();
        List<Timeslot> timeslots = lessonService.getAllTimeslots();
        
        model.addAttribute("classrooms", classrooms);
        model.addAttribute("timeslots", timeslots);
        model.addAttribute("classroom", new Classroom());
        model.addAttribute("timeslot", new Timeslot());
        return "lesson";
    }
    
    @GetMapping("/lesson/addclassroom")
    public String showClassroomForm(Classroom classroom) {
        return "lesson/addclassroom";
    }
    
    @PostMapping("/lesson/addclassroom")
    public String addClassroom(@Valid Classroom classroom, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "lesson/addclassroom";
        }
        lessonService.addClassroom(classroom);
        return "redirect:/lesson";
    }
    
    @GetMapping("/deleteclassroom")
    public String deleteClassroom(@RequestParam String id, Model model) {
        try {
            int classroomId = Integer.parseInt(id);
            lessonService.deleteClassroom(classroomId);
        } catch (NumberFormatException ex) {
            //log error
        }
        return "redirect:/lesson";
    }
    
    @GetMapping("/lesson/editclassroom")
    public String getClassroom(@RequestParam String id, Model model) {
        try {
            int classroomId = Integer.parseInt(id);
            model.addAttribute("classroom", lessonService.getClassroomById(classroomId));
        } catch (NumberFormatException ex) {
            // log error
        }
        return "lesson/editclassroom";
    }
    
    @PostMapping("/lesson/editclassroom")
    public String updateClassroom(@Valid Classroom classroom, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "lesson/editclassroom";
        }
        lessonService.updateClassroom(classroom);
        return "redirect:/lesson";
    }
    
    @GetMapping("/lesson/addtimeslot")
    public String showTimeslotForm(Timeslot timeslot) {
        return "lesson/addtimeslot";
    }
    
    @PostMapping("/lesson/addtimeslot")
    public String addTimeslot(@Valid Timeslot timeslot, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "lesson/addtimeslot";
        }
        lessonService.addTimeslot(timeslot);
        return "redirect:/lesson";
    }
    
    @GetMapping("/lesson/edittimeslot")
    public String getTimeslot(@RequestParam String id, Model model) {
        try {
            int timeslotId = Integer.parseInt(id);
            model.addAttribute("timeslot", lessonService.getTimeslotById(timeslotId));
        } catch (NumberFormatException ex) {
            // log error
        }
        return "lesson/edittimeslot";
    }
    
    @PostMapping("/lesson/edittimeslot")
    public String updateTimeslot(@Valid Timeslot timeslot, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "lesson/edittimeslot";
        }
        lessonService.updateTimeslot(timeslot);
        return "redirect:/lesson";
    }
    
    @GetMapping("/deletetimeslot")
    public String deleteTimeslot(@RequestParam String id, Model model) {
        try {
            int timeslotId = Integer.parseInt(id);
            lessonService.deleteTimeslot(timeslotId);
        } catch (NumberFormatException ex) {
            //log error
        }
        return "redirect:/lesson";
    }
}
