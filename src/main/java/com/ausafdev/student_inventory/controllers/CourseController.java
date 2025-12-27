package com.ausafdev.student_inventory.controllers;

import com.ausafdev.student_inventory.entity.Course;
import com.ausafdev.student_inventory.entity.Student;
import com.ausafdev.student_inventory.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("getCourse/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id)
    {
        return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Course>> findAllCourses()
    {
        return new ResponseEntity<>(courseService.findAllCourses(),HttpStatus.OK);
    }
    @PostMapping("/saveCourse")
    public ResponseEntity<Course> saveCourse(@Valid @RequestBody Course course)
    {
        return new ResponseEntity<>(courseService.saveCourse(course),HttpStatus.OK);
    }

    @DeleteMapping("deleteCourse/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id)
    {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<Course> enrollStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId)
    {
        return new ResponseEntity<>(courseService.addStudentToCourse(studentId,courseId), HttpStatus.OK);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Set<Student>> getEnrolledStudents(@PathVariable Long id)
    {
        return new ResponseEntity<>(courseService.getEnrolledStudents(id),HttpStatus.OK);
    }
}
