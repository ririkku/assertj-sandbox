package com.example.assertj.assertjsample.domain;

public class Lesson {

    private Student student;
    private Tutor tutor;

    public Lesson(Student student, Tutor tutor) {
        this.student = student;
        this.tutor = tutor;
    }

    public Student student() {
        return student;
    }

    public Tutor tutor() {
        return tutor;
    }
}
