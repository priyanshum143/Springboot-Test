package com.example.demo.resource;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/students")
public class Controller {
    private Map<Integer, Student> studentMap = new HashMap();
    private int idCounter = 1;

    @GetMapping
    public List<Student> getAllStudents(){
        List<Student> studentsList = new ArrayList<>();
        for (Student student : studentMap.values()) {
            studentsList.add(student);
        }
        if (studentsList.isEmpty()) {
            throw new RuntimeException("No students found");
        }
        return studentsList;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        Student student = studentMap.get(id);
        if (student == null) {
            throw new RuntimeException("Student not found");
        }
        return student;
    }

    @GetMapping("/uni/{uni}")
    public List<Student> getStudentByUni(@PathVariable String uni){
        List<Student> studentsList = new ArrayList<>();
        for (Student student : studentMap.values()) {
            if (student.getUniversity().equalsIgnoreCase(uni)) {
                studentsList.add(student);
            }
        }
        return studentsList;
    } 

    @PostMapping
    public String createStudent(@RequestBody Student student) {
        student.setId(idCounter);
        studentMap.put(idCounter, student);
        idCounter++;
        return "Added Student successfully!";
    }

}

@Getter
@Setter
@AllArgsConstructor
class Student {
    private String name;
    private String university;
    private String adhar;
    private int age;
    private int id;
}
