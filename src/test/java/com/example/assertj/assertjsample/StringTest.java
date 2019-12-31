package com.example.assertj.assertjsample;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StringTest {

    @Test
    void isEquals() {
        Assertions.assertThat("test").isEqualTo("test");
    }

    @Test
    void doesNotContain() {
        // 文字列を含まなければOK
        Assertions.assertThat("test").doesNotContain("x");
    }

    @Test
    void isEmpty() {
        // 空文字ならOK
        Assertions.assertThat("").isEmpty();
    }

    @Test
    void isBlank() {
        String sut = null;
        // nullか空文字ならOK
        Assertions.assertThat(sut).isBlank();
    }

    @Test
    void isGreaterThan() {
        // actual未満のlengthならOK
        Assertions.assertThat("test").isGreaterThan("tes");
    }

    @Test
    void isGreaterThanOrEqualTo() {
        // actual以下のlengthならOK
        Assertions.assertThat("test").isGreaterThanOrEqualTo("test");
    }

    @Test
    void isBetween() {
        Assertions.assertThat("ab").isBetween("a", "c");
    }
}
