package io.hhplus.cleanarchitecture.interfaces.lectureEnrollment;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureEnrollmentRequest {
    private Long userId;       // 신청하는 사용자 ID
    private Long lectureId;    // 신청하려는 특강 ID
    private Long instructorId;
}
