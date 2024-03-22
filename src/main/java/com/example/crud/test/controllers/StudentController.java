package com.example.crud.test.controllers;

import com.example.crud.test.entities.Student;
import com.example.crud.test.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/studente")

public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudente")
    public ResponseEntity<Student> addStudenti(@RequestBody Student studenti){
        return ResponseEntity.ok().body(studentService.addStudente(studenti));
    }
    @GetMapping("/getListaStudenti")
    public ResponseEntity<List<Student>> getList(){
        return ResponseEntity.ok().body(studentService.getAllStudent());
    }
    @GetMapping("/getStudenteId/{id}")
    public ResponseEntity<Optional<Student>> getStudenteId(@PathVariable Long id){
        Optional<Student> studentOptional = studentService.getStudenteId(id);
        return ResponseEntity.ok().body(studentOptional);
    }
    @PutMapping("/updateStudenteId/{id}")
    public ResponseEntity<Student> updateStudenteId(@PathVariable Long id,@RequestBody Student studenti){
        Optional<Student> studenteUpdate = studentService.updateStudente(studenti,id);
        if(studenteUpdate.isPresent()){
            return ResponseEntity.ok().body(studenti);
        }
        return ResponseEntity.notFound().build();
    }
    @PatchMapping("/setIsWorking/{id}")
    public ResponseEntity<Optional<Student>> setIsWorking(@PathVariable Long id,@RequestParam boolean working){
        Optional<Student> studentUpdateWorking = studentService.updateIsWorking(id,working);
            return ResponseEntity.ok().body(studentUpdateWorking);
        }

    @DeleteMapping("/deleteStudenteId/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        Optional<Student> studentDelete = studentService.deleteStudenteId(id);
        return ResponseEntity.ok().body(studentDelete.get());
    }

    }




