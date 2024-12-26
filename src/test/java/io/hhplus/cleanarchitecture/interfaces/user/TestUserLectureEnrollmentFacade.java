package io.hhplus.cleanarchitecture.interfaces.user;

import io.hhplus.cleanarchitecture.HangHeaException;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollmentService;
import io.hhplus.cleanarchitecture.domain.user.User;
import io.hhplus.cleanarchitecture.domain.user.UserService;
import io.hhplus.cleanarchitecture.facade.UserLectureEnrollmentFacade;
import io.hhplus.cleanarchitecture.interfaces.lectureEnrollment.LectureEnrollmentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TestUserLectureEnrollmentFacade {

    @Mock
    private LectureEnrollmentService lectureEnrollmentService;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserLectureEnrollmentFacade userLectureEnrollmentFacade;

    @Test
    @DisplayName("유저 특강서비스 호출해 특정 사용자 ID로 신청한 강의 목록을 조회한다.")
    void shouldFindUserEnrolledLectures() {
        // Given
        Long userId = 1L;
        LectureEnrollment enrollment = LectureEnrollment.builder().id(1L).build();
        User user = User.builder().id(1L).build();
        when(userService.findByUserId(userId)).thenReturn(user);
        when(lectureEnrollmentService.findUserEnrolledLectures(user.getId()))
                .thenReturn(List.of(enrollment));

        List<LectureEnrollment> result = userLectureEnrollmentFacade.findUserEnrolledLectures(userId);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("사용자가 없으면 HangHeaException 던진다.")
    void shouldThrowUserNotFoundExceptionWhenUserNotExists() {
        // Given
        Long userId = 1L;
        User emptyUser = new User();
        when(userService.findByUserId(userId)).thenReturn(emptyUser);
        // When & Then
        HangHeaException exception = assertThrows(
                HangHeaException.class,
                () -> userLectureEnrollmentFacade.findUserEnrolledLectures(userId));
        //예외 메세지 검증
        assertEquals("존재하지 않는 사용자입니다.", exception.getMessage());
    }

    @Test
    @DisplayName("특강 신청 성공 테스트")
    void shouldEnrollInLectureSuccessfully() {
        // Given
        Long userId = 1L;
        Long lectureId = 100L;
        Long instructorId = 90L;
        LectureEnrollmentRequest request = new LectureEnrollmentRequest(userId, lectureId, instructorId);

        // When
        userLectureEnrollmentFacade.enrollInLecture(request); // 특강 신청 호출

        // Then
        verify(lectureEnrollmentService, times(1)).enrollInLecture(any(LectureEnrollment.class));
    }

    @Test
    @DisplayName("사용자가 없는 경우 특강 신청 실패 테스트")
    void shouldThrowExceptionWhenUserNotFound() {
        // Given
        Long userId = 99L; // 존재하지 않는 사용자 ID
        Long lectureId = 100L;
        Long instructorId = 90L;
        LectureEnrollmentRequest request = new LectureEnrollmentRequest(userId, lectureId, instructorId);

        // 빈 User 객체를 반환하도록 설정
        when(userService.findByUserId(userId)).thenReturn(new User());
        // When & Then
        HangHeaException exception = assertThrows(
                HangHeaException.class,
                () -> userLectureEnrollmentFacade.enrollInLecture(request));
        //예외 메세지 검증
        assertEquals("존재하지 않는 사용자입니다.", exception.getMessage());
    }
}
