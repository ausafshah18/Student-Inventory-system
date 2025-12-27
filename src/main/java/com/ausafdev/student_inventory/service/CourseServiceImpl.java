package com.ausafdev.student_inventory.service;

import com.ausafdev.student_inventory.Repository.CourseRepository;
import com.ausafdev.student_inventory.Repository.StudentRepository;
import com.ausafdev.student_inventory.entity.Course;
import com.ausafdev.student_inventory.entity.Student;
import com.ausafdev.student_inventory.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return unwrapCourse(course,id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> findAllCourses() {
        return (List<Course>)courseRepository.findAll();
    }

    @Override
    public Set<Student> getEnrolledStudents(Long id) {
        Course course = getCourse(id);
        return course.getStudents();
    }

    @Override
    @Transactional
    public Course addStudentToCourse(Long studentId, Long courseId) {
        Course course = getCourse(courseId);
        Student student = studentService.getStudent(studentId);
        course.getStudents().add(student);
        student.getCourses().add(course);
        return course;

    }

    static Course unwrapCourse(Optional<Course> entity, Long id){
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id,Course.class);
    }

}
