package org.spring.studentgradeexam.controller;

import org.spring.studentgradeexam.model.Student;
import org.spring.studentgradeexam.repository.GradeRepository;
import org.spring.studentgradeexam.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository studentRepository = new StudentRepository();
    private final GradeRepository gradeRepository = new GradeRepository();

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepository.findAll().stream()
                .map(student -> new Student(
                        student.id(),
                        student.firstName(),
                        student.lastName(),
                        gradeRepository.findByStudentId(student.id())
                ))
                .toList();
    }
}
