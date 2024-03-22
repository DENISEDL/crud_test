package com.example.crud.test.entities;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private String surname;
    @Column
    private boolean isWorking;

    public Student(Long id, String nome, String surname, boolean isWorking) {
        this.id = id;
        this.nome = nome;
        this.surname = surname;
        this.isWorking = isWorking;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isWorking(boolean b) {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }
}
