package io.hhplus.cleanarchitecture.interfaces.user;

import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollmentService;
import io.hhplus.cleanarchitecture.domain.user.User;
import io.hhplus.cleanarchitecture.facade.UserLectureEnrollmentFacade;
import io.hhplus.cleanarchitecture.interfaces.lectureEnrollment.LectureEnrollmentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;


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

    @Test
    @DisplayName("특강 신청 성공 테스트")
    void shouldEnrollInLectureSuccessfully() {

        Long userId = 1L;
        Long lectureId = 100L;
        LectureEnrollmentRequest request = new LectureEnrollmentRequest(userId, lectureId, LocalDate.of(2024, 12, 31));

        ArgumentCaptor<LectureEnrollment> captor = ArgumentCaptor.forClass(LectureEnrollment.class);
        doNothing().when(lectureEnrollmentService).enrollInLecture(any(LectureEnrollment.class));

        // When
        userLectureEnrollmentFacade.enrollInLecture(request);

        // Then
        verify(lectureEnrollmentService, times(1)).enrollInLecture(captor.capture());


        LectureEnrollment captured = captor.getValue();

        assertThat(captured.getUser().getId()).isEqualTo(userId);
        assertThat(captured.getLecture().getId()).isEqualTo(lectureId);
        assertThat(captured.getEnrollmentDate()).isEqualTo(LocalDate.of(2024, 12, 31));
    }
}
