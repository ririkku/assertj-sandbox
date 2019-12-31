package com.example.assertj.assertjsample;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ExceptionTest {

    @Test
    void 例外を受け取る() {
        Assertions.assertThatExceptionOfType(IOException.class)
                .isThrownBy(() -> {
                    throw new IOException("IOException Message");
                })
                .withMessage("%s Message", "IOException")
                .withMessageContaining("IOException")
                .withNoCause();
    }

    @Test
    void ぬるぽ専用() {
        Assertions.assertThatNullPointerException().isThrownBy(() -> {
            throw new NullPointerException("ぬるぽ がっ");
        })
                .withMessage("ぬるぽ %s", "がっ")
                .withMessageContaining("がっ")
                .withNoCause();
    }
}
