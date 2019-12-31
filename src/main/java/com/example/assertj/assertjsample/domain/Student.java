package com.example.assertj.assertjsample.domain;

public class Student {

    private StudentIdentifier identifier;
    private StudentName name;

    public Student(StudentIdentifier identifier, StudentName name) {
        this.identifier = identifier;
        this.name = name;
    }

    public StudentIdentifier identifier() {
        return identifier;
    }

    public StudentName name() {
        return name;
    }
}
