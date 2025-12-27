package com.ausafdev.student_inventory.service;

import com.ausafdev.student_inventory.entity.Course;
import com.ausafdev.student_inventory.entity.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {
    Student getStudent(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    List<Student> getStudents();
    Set<Course> getEnrolledCourses(Long id);
}
