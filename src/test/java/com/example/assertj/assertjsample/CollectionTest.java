package com.example.assertj.assertjsample;

import com.example.assertj.assertjsample.domain.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

class CollectionTest {

    @Test
    void サイズの検証() {
        List<String> actual = Arrays.asList("a", "b", "c");
        Assertions.assertThat(actual).hasSize(3);
    }

    @Test
    void 全要素の検証() {

        // 1つ目のLessonを生成
        String student1Identifier = generateUUID();
        String tutor1Identifier = generateUUID();
        Lesson lesson1 = new Lesson(
                new Student(new StudentIdentifier(student1Identifier), new StudentName("student1")),
                new Tutor(new TutorIdentifier(tutor1Identifier), new TutorName("tutor1")));

        // 2つ目のLessonを生成
        String student2Identifier = generateUUID();
        String tutor2Identifier = generateUUID();
        Lesson lesson2 = new Lesson(
                new Student(new StudentIdentifier(student2Identifier), new StudentName("student2")),
                new Tutor(new TutorIdentifier(tutor2Identifier), new TutorName("tutor2")));

        Lessons actual = new Lessons(Arrays.asList(lesson1, lesson2));
        Assertions.assertThat(actual.list())
                .usingRecursiveFieldByFieldElementComparator()
                // 全ての値の検証、並び順はなんでもいい
                .containsOnly(
                        new Lesson(new Student(new StudentIdentifier(student2Identifier), new StudentName("student2")), new Tutor(new TutorIdentifier(tutor2Identifier), new TutorName("tutor2"))),
                        new Lesson(new Student(new StudentIdentifier(student1Identifier), new StudentName("student1")), new Tutor(new TutorIdentifier(tutor1Identifier), new TutorName("tutor1"))));
    }

    @Test
    void 順番の検証() {
        // 1つ目のLessonを生成
        String student1Identifier = generateUUID();
        String tutor1Identifier = generateUUID();
        Lesson lesson1 = new Lesson(
                new Student(new StudentIdentifier(student1Identifier), new StudentName("student1")),
                new Tutor(new TutorIdentifier(tutor1Identifier), new TutorName("tutor1")));

        // 2つ目のLessonを生成
        String student2Identifier = generateUUID();
        String tutor2Identifier = generateUUID();
        Lesson lesson2 = new Lesson(
                new Student(new StudentIdentifier(student2Identifier), new StudentName("student2")),
                new Tutor(new TutorIdentifier(tutor2Identifier), new TutorName("tutor2")));

        // 3つ目のLessonを生成
        String student3Identifier = generateUUID();
        String tutor3Identifier = generateUUID();
        Lesson lesson3 = new Lesson(
                new Student(new StudentIdentifier(student3Identifier), new StudentName("student3")),
                new Tutor(new TutorIdentifier(tutor3Identifier), new TutorName("tutor3")));

        Lessons actual = new Lessons(Arrays.asList(lesson1, lesson2, lesson3));

        Assertions.assertThat(actual.list())
                .usingRecursiveFieldByFieldElementComparator()
                // 全ての値の検証
                .containsExactly(
                        new Lesson(new Student(new StudentIdentifier(student1Identifier), new StudentName("student1")), new Tutor(new TutorIdentifier(tutor1Identifier), new TutorName("tutor1"))),
                        new Lesson(new Student(new StudentIdentifier(student2Identifier), new StudentName("student2")), new Tutor(new TutorIdentifier(tutor2Identifier), new TutorName("tutor2"))),
                        new Lesson(new Student(new StudentIdentifier(student3Identifier), new StudentName("student3")), new Tutor(new TutorIdentifier(tutor3Identifier), new TutorName("tutor3"))));
    }

    @Test
    void オブジェクトの一部の値を取り出して検証() {
        // 1つ目のLessonを生成
        Lesson lesson1 = new Lesson(
                new Student(new StudentIdentifier(generateUUID()), new StudentName("student1")),
                new Tutor(new TutorIdentifier(generateUUID()), new TutorName("tutor1")));

        // 2つ目のLessonを生成
        Lesson lesson2 = new Lesson(
                new Student(new StudentIdentifier(generateUUID()), new StudentName("student2")),
                new Tutor(new TutorIdentifier(generateUUID()), new TutorName("tutor2")));

        Lessons actual = new Lessons(Arrays.asList(lesson1, lesson2));
        Assertions.assertThat(actual.list())
                // Iterableの要素を分解して新たにTupleのIterableを作る
                .extracting(
                        lesson -> lesson.student().name().value(),
                        lesson -> lesson.tutor().name().value())
                // 全ての値の検証、並び順はなんでもいい
                .containsOnly(
                        // Tupleで検証
                        Tuple.tuple("student2", "tutor2"),
                        Tuple.tuple("student1", "tutor1"));
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
