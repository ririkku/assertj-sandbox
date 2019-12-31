package com.example.assertj.assertjsample;

import com.example.assertj.assertjsample.domain.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class OriginalClassTest {

    @Test
    void オブジェクトのフィールドを再帰的に検証() {
        String studentIdentifier = generateUUID();
        String tutorIdentifier = generateUUID();
        Lesson actual = new Lesson(
                new Student(new StudentIdentifier(studentIdentifier), new StudentName("studentName")),
                new Tutor(new TutorIdentifier(tutorIdentifier), new TutorName("tutorName")));

        Lesson expected = new Lesson(
                new Student(new StudentIdentifier(studentIdentifier), new StudentName("studentName")),
                new Tutor(new TutorIdentifier(tutorIdentifier), new TutorName("tutorName")));

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void 検証を途中で終わらせない() {
        String studentIdentifier = generateUUID();
        String tutorIdentifier = generateUUID();
        Lesson actual = new Lesson(
                new Student(new StudentIdentifier(studentIdentifier), new StudentName("studentName")),
                new Tutor(new TutorIdentifier(tutorIdentifier), new TutorName("tutorName")));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actual.student().identifier().value()).as("OK").isEqualTo(studentIdentifier);
        softAssertions.assertThat(actual.student().name().value()).as("NG").isEqualTo("x");
        softAssertions.assertThat(actual.tutor().identifier().value()).as("OK").isEqualTo(tutorIdentifier);
        softAssertions.assertThat(actual.tutor().name().value()).as("OK").isEqualTo("tutorName");
        softAssertions.assertAll();
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
