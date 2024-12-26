package io.hhplus.cleanarchitecture.interfaces.lecture;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;



import java.time.LocalDate;

@Getter
public class LectureDateRangeRequest {

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "applicationStartAt must be in yyyy-MM-dd format")
    private String applicationStartAt;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "applicationEndAt must be in yyyy-MM-dd format")
    private String applicationEndAt;

    // 도메인 객체와 결합된 생성자
    public LectureDateRangeRequest(String applicationStartAt, String applicationEndAt) {
        if (applicationStartAt == null || applicationEndAt == null) {
            throw new IllegalArgumentException("날짜는 null이 될 수 없습니다.");
        }

        this.applicationStartAt = applicationStartAt;
        this.applicationEndAt = applicationEndAt;

        // 검증: 시작일이 종료일보다 이후면 예외
        LocalDate startDate = LocalDate.parse(applicationStartAt);
        LocalDate endDate = LocalDate.parse(applicationEndAt);
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작일이 종료일보다 클 수 없습니다.");
        }
    }

    // 날짜 변환 메서드
    public LocalDate getStartDate() {
        return LocalDate.parse(applicationStartAt);
    }

    public LocalDate getEndDate() {
        return LocalDate.parse(applicationEndAt);
    }
}