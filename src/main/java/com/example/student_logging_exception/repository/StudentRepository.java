package com.example.student_logging_exception.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student_logging_exception.entity.Student;

public interface StudentRepository 
	extends JpaRepository<Student, Long> {

	
	}