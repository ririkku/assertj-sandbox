package com.example.assertj.assertjsample.domain;

public class Tutor {

    private TutorIdentifier identifier;
    private TutorName name;

    public Tutor(TutorIdentifier identifier, TutorName name) {
        this.identifier = identifier;
        this.name = name;
    }

    public TutorIdentifier identifier() {
        return identifier;
    }

    public TutorName name() {
        return name;
    }
}
