package io.hhplus.cleanarchitecture.interfaces.lecture;

import io.hhplus.cleanarchitecture.HangHeaException;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LectureDateRangeRequest {

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "applicationStartAt must be in yyyy-MM-dd format")
    private String applicationStartAt;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "applicationEndAt must be in yyyy-MM-dd format")
    private String applicationEndAt;

    // 도메인 객체와 결합된 생성자
    public LectureDateRangeRequest(String applicationStartAt, String applicationEndAt) {
        if (applicationStartAt == null || applicationEndAt == null) {
            throw new HangHeaException("날짜는 null이 될 수 없습니다.");
        }

        this.applicationStartAt = applicationStartAt;
        this.applicationEndAt = applicationEndAt;

        LocalDate startDate = LocalDate.parse(applicationStartAt);
        LocalDate endDate = LocalDate.parse(applicationEndAt);

        if (startDate.isAfter(endDate)) {
            throw new HangHeaException("시작일이 종료일보다 클 수 없습니다.");
        }
    }

    // 날짜 변환 메서드
    public LocalDate getStartDate() {
        System.out.println(applicationStartAt);
        return LocalDate.parse(applicationStartAt);
    }

    public LocalDate getEndDate() {
        return LocalDate.parse(applicationEndAt);
    }
}