package com.university.student_service.controller;


import com.university.student_service.entity.Student;
import com.university.student_service.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/search")
public List<Student> search(@RequestParam String keyword) {
    return studentService.searchStudents(keyword);
}

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
    Student existingStudent = studentService.getStudentById(id);
    if (existingStudent == null) {
        return ResponseEntity.notFound().build();
    }

    existingStudent.setEmail(studentDetails.getEmail());
    existingStudent.setFirstName(studentDetails.getFirstName());
    existingStudent.setLastName(studentDetails.getLastName());

    Student updatedStudent = studentService.saveStudent(existingStudent);
    return ResponseEntity.ok(updatedStudent);
}

}

