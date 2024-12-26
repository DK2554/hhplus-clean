package io.hhplus.cleanarchitecture.interfaces.lecture;

import io.hhplus.cleanarchitecture.HangHeaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestLectureDateRangeRequest {
    @Test
    @DisplayName("특강신청목록조회 파라미터에서 시작일이 종료일보다 나중인경우 HangHeaException 발생 ")
    public void shouldThrowExceptionWhenStartDateIsAfterEndDate() {
        // Given: 시작일이 종료일보다 나중인 경우
        String startDate = "2024-12-10";  // 시작일
        String endDate = "2024-12-01";    // 종료일

        // When & Then: 생성자에서 HangHeaException 발생해야 함
        assertThrows(HangHeaException.class, () -> {
            new LectureDateRangeRequest(startDate, endDate);
        });
    }

    @Test
    @DisplayName("특강신청목록조회 파라미터에서 날짜가 null인 경우 HangHeaException 발생")
    public void shouldThrowExceptionWhenStartDateOrEndDateIsNull() {

        // When & Then: 생성자에서 HangHeaException 발생해야 함
        assertThrows(HangHeaException.class, () -> {
            new LectureDateRangeRequest(null, "2024-12-10");
        });

        // When & Then: 생성자에서 HangHeaException 발생해야 함
        assertThrows(HangHeaException.class, () -> {
            new LectureDateRangeRequest("2024-12-01",null);
        });
        // When & Then: 생성자에서 HangHeaException 발생해야 함
        assertThrows(HangHeaException.class, () -> {
            new LectureDateRangeRequest(null, null);
        });
    }

    @DisplayName("특강신청목록조회 파라미터에서 잘못된 날짜 포맷일 경우 DateTimeParseException 발생")
    public void shouldThrowExceptionWhenDateFormatIsIncorrect() {

        // When & Then: 잘못된 날짜 형식으로 DateTimeParseException이 발생해야 함
        assertThrows(java.time.format.DateTimeParseException.class, () -> {
            new LectureDateRangeRequest("2024-12-32", "2024-12-01");
        });
        // When & Then: DateTimeParseException 발생
        assertThrows(java.time.format.DateTimeParseException.class, () -> {
            new LectureDateRangeRequest("2024-13-01", "2024-12-01");
        });

        // When & Then: DateTimeParseException 발생
        assertThrows(java.time.format.DateTimeParseException.class, () -> {
            new LectureDateRangeRequest("12012024", "20241201");
        });
    }
}

