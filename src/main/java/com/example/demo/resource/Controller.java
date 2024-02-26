package com.example.demo.resource;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final Map<Integer, Student> map = new HashMap<>();
    private int idCount = 1;

    @GetMapping
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        for (Student st : map.values()) list.add(st);

        if (list.isEmpty()) {
            throw new RuntimeException("No data found");
        }
        return list;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        Student st = map.get(id);
        if (st == null) {
            throw new RuntimeException("data not found");
        }
        return st;
    }

    @GetMapping("/university/{uni}")
    public List<Student> getStudentsByUniversity(@PathVariable String uni) {
        List<Student> list = new ArrayList<>();
        for (Student st : map.values()) {
            if (st.getUniversity().equalsIgnoreCase(uni)) {
                list.add(st);
            }
        }
        return list;
    }

    @PostMapping
    public String addStudent(@RequestBody Student st) {
        st.setId(idCount);
        map.put(idCount, st);
        idCount++;
        return "Student added successfully";
    }
}

class Student {
    private String name;
    private String university;
    private String aadhar;
    private int age;
    private int id;

    public Student() { }

    public Student(String name, String uni, String aadhar, int age) {
        this.name = name;
        this.university = uni;
        this.aadhar = aadhar;
        this.age = age;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }


    public void setUniversity(String university) { this.university = university; }
    public String getUniversity() { return university; }


    public void setAdhar(String adhar) { this.adhar = adhar; }
    public String getAdhar() { return adhar; }


    public void setAge(int age) { this.age = age; }
    public int getAge() { return age; }


    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
}