package com.ausafdev.student_inventory.service;

import com.ausafdev.student_inventory.Repository.StudentRepository;
import com.ausafdev.student_inventory.entity.Course;
import com.ausafdev.student_inventory.entity.Student;
import com.ausafdev.student_inventory.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student getStudent(Long id)
    {
        Optional<Student> student = studentRepository.findById(id);
        return unwrapStudent(student,id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Set<Course> getEnrolledCourses(Long id) {
        Student student = getStudent(id);
        return student.getCourses();
    }

    static Student unwrapStudent(Optional<Student> entity, Long id){
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id,Student.class);
    }
}
