package com.example.crud.test.services;

import com.example.crud.test.entities.Student;
import com.example.crud.test.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student addStudente(Student studente){
        return studentRepository.save(studente);
    }
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
    public Optional<Student> getStudenteId(Long id){
        return studentRepository.findById(id);
    }
    public Optional<Student> updateStudente(Student studente,Long id){
        Optional<Student> studentUpdate = studentRepository.findById(id);
        if(studentUpdate.isPresent()){
            studentUpdate.get().setNome(studente.getNome());
            studentUpdate.get().setSurname(studente.getSurname());
            Student updateStudente = studentRepository.save(studentUpdate.get());
            return Optional.of(updateStudente);
        }
        return Optional.empty();
    }
    public Optional<Student> updateIsWorking(Long id,boolean isWorking){
        Optional<Student> updateBooleean = studentRepository.findById(id);
            if(updateBooleean.isPresent()){
                updateBooleean.get().setWorking(isWorking);
                Student update = studentRepository.save(updateBooleean.get());
                return Optional.of(update);
            }
            return Optional.empty();
        }

    public Optional<Student> deleteStudenteId(Long id){
        Optional<Student> deleteStudente = studentRepository.findById(id);
        if(deleteStudente.isPresent()){
            studentRepository.delete(deleteStudente.get());
        }else{
            return Optional.empty();
        }
        return deleteStudente;
    }
}
