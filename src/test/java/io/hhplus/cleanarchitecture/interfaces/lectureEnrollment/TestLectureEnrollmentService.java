package io.hhplus.cleanarchitecture.interfaces.lectureEnrollment;

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


}
