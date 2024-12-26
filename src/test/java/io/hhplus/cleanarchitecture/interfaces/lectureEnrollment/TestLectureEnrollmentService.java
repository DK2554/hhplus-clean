package io.hhplus.cleanarchitecture.interfaces.lectureEnrollment;

import io.hhplus.cleanarchitecture.domain.Instructor.Instructor;
import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollmentRepository;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollmentService;
import io.hhplus.cleanarchitecture.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestLectureEnrollmentService {

    @Mock
    private LectureEnrollmentRepository lectureEnrollmentRepository;

    @InjectMocks
    private LectureEnrollmentService lectureEnrollmentService;

    @Test
    @DisplayName("특정 사용자 ID로 신청한 강의 목록을 조회한다.")
    void shouldFindUserEnrolledLectures() {
        // Given
        Long userId = 1L;

        List<LectureEnrollment> mockEnrollments = List.of(
                LectureEnrollment.builder()
                        .id(1L)
                        .enrollmentDate(LocalDate.now())
                        .lecture(
                                Lecture.builder()
                                        .id(1L)
                                        .lectureName("Backend Lecture")
                                        .build()
                        )
                        .user(User.builder().userName("임동욱").build())
                        .build()
        );

        when(lectureEnrollmentRepository.findUserEnrolledLectures(userId)).thenReturn(mockEnrollments);

        // When
        List<LectureEnrollment> enrollments = lectureEnrollmentService.findUserEnrolledLectures(userId);

        // Then
        assertThat(enrollments).hasSize(1);
        assertThat(enrollments.get(0).getLecture().getLectureName()).isEqualTo("Backend Lecture");
        assertThat(enrollments.get(0).getUser().getUserName()).isEqualTo("임동욱");
        verify(lectureEnrollmentRepository, times(1)).findUserEnrolledLectures(userId);
    }

    @Test
    @DisplayName("특강 신청 성공 테스트")
    void shouldEnrollInLectureSuccessfully() {
        // Given
        Long userId = 1L;
        Long lectureId = 100L;
        Long instructorId = 90L;

        // LectureEnrollment 객체 생성 (빌더 사용)
        LectureEnrollment enrollment = LectureEnrollment.builder()
                .user(User.builder().id(userId).build())  // 사용자 설정
                .lecture(Lecture.builder().id(lectureId).build())  // 강의 설정
                .instructor(Instructor.builder().id(instructorId).build())  // 강연자 설정
                .enrollmentDate(LocalDate.now())  // 특강 신청일 설정
                .build();
        // When
        lectureEnrollmentService.enrollInLecture(enrollment);

        // Then
        // verify가 정확히 한 번 호출되었는지 확인
        verify(lectureEnrollmentRepository, times(1)).enrollInLecture(any(LectureEnrollment.class));  // Mocked 객체에서 메서드 호출을 검증

    }


}
