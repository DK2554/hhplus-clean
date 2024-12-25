package io.hhplus.cleanarchitecture.interfaces.lectureEnrollment;

import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollmentService;
import io.hhplus.cleanarchitecture.facade.UserLectureEnrollmentFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestUserLectureEnrollmentFacade {

    @Mock
    private LectureEnrollmentService lectureEnrollmentService;

    @InjectMocks
    private UserLectureEnrollmentFacade userLectureEnrollmentFacade;

    @Test
    @DisplayName("유저 특강서비스를 파사드패턴으로 만든 서비스를 호출해 특정 사용자 ID로 신청한 강의 목록을 조회한다.")
    void shouldFindUserEnrolledLectures() {
        // Given
        Long userId = 1L;
        LectureEnrollment enrollment = LectureEnrollment.builder().id(1L).build();
        when(lectureEnrollmentService.findUserEnrolledLectures(userId))
                .thenReturn(List.of(enrollment));

        List<LectureEnrollment> result = userLectureEnrollmentFacade.findUserEnrolledLectures(userId);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(1L);
    }
}
