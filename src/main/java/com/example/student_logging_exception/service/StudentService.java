package com.example.student_logging_exception.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.student_logging_exception.entity.Student;
import com.example.student_logging_exception.exception.StudentNotFoundException;
import com.example.student_logging_exception.repository.StudentRepository;

@Service
public class StudentService {

    private static final Logger logger =
            LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student createStudent(Student student) {
        logger.info("Creating student with email: {}", student.getEmail());

        Student saved = repository.save(student);

        logger.info("Student created successfully with ID: {}",
                saved.getId());

        return saved;
    }

    public List<Student> getAllStudents() {
        logger.debug("Fetching all students");

        List<Student> students = repository.findAll();

        logger.info("Total students found: {}", students.size());

        return students;
    }

    public Student getStudentById(Long id) {
        logger.debug("Searching for student ID: {}", id);

        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Student not found with ID: {}", id);

                    return new StudentNotFoundException(
                            "Student not found with ID: " + id);
                });
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student existing = getStudentById(id);

        existing.setName(updatedStudent.getName());
        existing.setEmail(updatedStudent.getEmail());
        existing.setAge(updatedStudent.getAge());

        Student saved = repository.save(existing);

        logger.info("Student updated successfully with ID: {}", id);

        return saved;
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);

        repository.delete(student);

        logger.info("Student deleted successfully with ID: {}", id);
    }
}