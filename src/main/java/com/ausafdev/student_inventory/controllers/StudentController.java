package com.ausafdev.student_inventory.controllers;

import com.ausafdev.student_inventory.entity.Course;
import com.ausafdev.student_inventory.entity.Student;
import com.ausafdev.student_inventory.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id)
    {
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student)
    {
        return new ResponseEntity<>(studentService.saveStudent(student),HttpStatus.OK);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudents()
    {
        return new ResponseEntity<>(studentService.getStudents(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id)
    {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<Set<Course>> getEnrolledCourses(@PathVariable Long id)
    {
        return new ResponseEntity<>(studentService.getEnrolledCourses(id),HttpStatus.OK);
    }
}
