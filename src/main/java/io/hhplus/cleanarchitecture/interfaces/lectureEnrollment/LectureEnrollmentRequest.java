package io.hhplus.cleanarchitecture.interfaces.lectureEnrollment;

import io.hhplus.cleanarchitecture.HangHeaException;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
public class LectureEnrollmentRequest {
    private Long userId;       // 신청하는 사용자 ID
    private Long lectureId;    // 신청하려는 특강 ID
    private Long instructorId;

    public LectureEnrollmentRequest(Long userId, Long lectureId, Long instructorId) {
        if (userId == null || userId <= 0) {
            throw new HangHeaException("잘못된 사용자 아이디입니다.");
        }
        if (lectureId == null || lectureId <= 0) {
            throw new HangHeaException("잘못된 특강 ID 입니다");
        }
        if (instructorId == null || instructorId <= 0) {
            throw new HangHeaException("잘못된 강연자 ID 입니다.");
        }
        this.userId = userId;
        this.lectureId = lectureId;
        this.instructorId = instructorId;
    }
}
