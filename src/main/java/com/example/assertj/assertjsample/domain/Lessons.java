package com.example.assertj.assertjsample.domain;

import java.util.List;

public class Lessons {

    private List<Lesson> list;

    public Lessons(List<Lesson> list) {
        this.list = list;
    }

    public List<Lesson> list() {
        return list;
    }
}
