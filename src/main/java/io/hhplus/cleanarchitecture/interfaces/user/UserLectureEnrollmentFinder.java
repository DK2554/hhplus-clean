package io.hhplus.cleanarchitecture.interfaces.user;

import io.hhplus.cleanarchitecture.HangHeaException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLectureEnrollmentFinder {

    private final Long userId;

    // 빌더를 통해 객체 생성 시 검증 로직 포함
    public UserLectureEnrollmentFinder(Long userId) {
        if (userId == null || userId <= 0) {
            throw new HangHeaException("잘못된 사용자 ID 입니다.");
        }
        this.userId = userId;
    }
}