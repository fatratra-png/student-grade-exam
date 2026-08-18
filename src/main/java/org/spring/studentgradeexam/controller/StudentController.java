package org.spring.studentgradeexam.controller;

import org.spring.studentgradeexam.model.Student;
import org.spring.studentgradeexam.repository.GradeRepository;
import org.spring.studentgradeexam.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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

    @PostMapping("/students")
    public ResponseEntity<Student> postStudent(@RequestBody Student student) {
        String id = student.id() == null ? UUID.randomUUID().toString() : student.id();
        Student created = new Student(id, student.firstName(), student.lastName(), null);
        if (!studentRepository.save(created)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
