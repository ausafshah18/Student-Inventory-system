package com.ausafdev.student_inventory.service;

import com.ausafdev.student_inventory.entity.Course;
import com.ausafdev.student_inventory.entity.Student;

import java.util.List;
import java.util.Set;

public interface CourseService {
    Course getCourse(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
    List<Course> findAllCourses();
    Course addStudentToCourse(Long studentId, Long courseId);
    Set<Student> getEnrolledStudents(Long id);
}
