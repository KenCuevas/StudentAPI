package com.pruebaClaro.PruebaClaro.repositories;

import com.pruebaClaro.PruebaClaro.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
