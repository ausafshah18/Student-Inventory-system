package com.ausafdev.student_inventory.service;

import com.ausafdev.student_inventory.Repository.CourseRepository;
import com.ausafdev.student_inventory.Repository.GradeRepository;
import com.ausafdev.student_inventory.Repository.StudentRepository;
import com.ausafdev.student_inventory.entity.Course;
import com.ausafdev.student_inventory.entity.Grade;
import com.ausafdev.student_inventory.entity.Student;
import com.ausafdev.student_inventory.exception.GradeNotFoundException;
import com.ausafdev.student_inventory.exception.StudentNotEnrolledException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private CourseServiceImpl courseService;
    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId,courseId);
        return unwrappedGrade(grade, studentId, courseId);
    }

    @Override
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        Student student = studentService.getStudent(studentId);
        Course course = courseService.getCourse(courseId);
        if(!student.getCourses().contains(course)) throw new StudentNotEnrolledException(studentId,courseId);
        grade.setCourse(course);
        grade.setStudent(student);
        return gradeRepository.save(grade);
    }

    @Override
    @Transactional
    public Grade updateGrade(String score, Long studentId, Long courseId) {
        Grade grade = getGrade(studentId,courseId);
        grade.setScore(score);
        return grade;
    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {
        gradeRepository.deleteByStudentIdAndCourseId(studentId,courseId);
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    @Override
    public List<Grade> getAllGrades() {
        return (List<Grade>) gradeRepository.findAll();
    }

    static Grade unwrappedGrade(Optional<Grade> entity, Long studentId, Long courseId){
        if(entity.isPresent()) return entity.get();
        else throw new GradeNotFoundException(studentId,courseId);
    }
}
