package com.ausafdev.student_inventory.Repository;

import com.ausafdev.student_inventory.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
