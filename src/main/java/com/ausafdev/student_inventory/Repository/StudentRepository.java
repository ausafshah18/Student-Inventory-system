package com.ausafdev.student_inventory.Repository;

import com.ausafdev.student_inventory.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {
}
