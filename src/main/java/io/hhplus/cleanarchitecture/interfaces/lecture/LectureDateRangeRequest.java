package io.hhplus.cleanarchitecture.interfaces.lecture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureDateRangeRequest {
    private String applicationStartAt; // 요청에서 yyyy-MM-dd 형식으로 받음
    private String applicationEndAt;

    public LocalDate getStartDate() {
        return LocalDate.parse(applicationStartAt);
    }

    public LocalDate getEndDate() {
        return LocalDate.parse(applicationEndAt);
    }
}