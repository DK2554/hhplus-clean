package io.hhplus.cleanarchitecture.interfaces.user;

import io.hhplus.cleanarchitecture.HangHeaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUserLectureEnrollmentFinder {

    @Test
    @DisplayName("정상 UserLectureEnrollmentFinderDTO 객체 생성")
    void shouldCreateUserLectureEnrollmentFinderWhenValidUserId() {
        // Given
        Long validUserId = 1L;

        // When
        UserLectureEnrollmentFinder finder = new UserLectureEnrollmentFinder(validUserId);

        // Then
        assertThat(finder).isNotNull();
        assertThat(finder.getUserId()).isEqualTo(validUserId);
    }

    @Test
    @DisplayName("유저아이디가 null인경우에 HangHeaException을 반환한다.")
    void shouldThrowExceptionWhenUserIdIsNull() {
        // When & Then
        HangHeaException exception = assertThrows(
                HangHeaException.class,
                () -> new UserLectureEnrollmentFinder(null));
        //예외 메세지 검증
        assertEquals("잘못된 사용자 ID 입니다.", exception.getMessage());
    }

    @Test
    @DisplayName("사용자가 아이디가 음수이거나 0인경우 HangHeaException을 반환한다.")
    void shouldThrowExceptionWhenUserIdIsZeroOrNegative() {
        // Given
        Long zeroUserId = 0L;
        Long negativeUserId = -1L;

        HangHeaException exception = assertThrows(
                HangHeaException.class,
                () -> new UserLectureEnrollmentFinder(zeroUserId));
        //예외 메세지 검증
        assertEquals("잘못된 사용자 ID 입니다.", exception.getMessage());

        HangHeaException negativeUser = assertThrows(
                HangHeaException.class,
                () -> new UserLectureEnrollmentFinder(negativeUserId));
        //예외 메세지 검증
        assertEquals("잘못된 사용자 ID 입니다.", negativeUser.getMessage());
    }
}
