package io.hhplus.cleanarchitecture.interfaces.lecture;

import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.domain.lecture.LectureRepository;
import io.hhplus.cleanarchitecture.domain.lecture.LectureService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestLectureService {

    @Mock
    private LectureRepository lectureRepository;

    @InjectMocks
    private LectureService lectureService;

    @Test
    @DisplayName("특정시작날짜와 종료날짜를 입력받아 해당 날짜에 속한 강의 목록을 반환한다.")
    void shouldGetLecturesBetweenDates() {

        // Given
        LocalDateTime now = LocalDateTime.now();
        LocalDate startDate = LocalDate.of(2024, 12, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 10);

        Lecture lecture = Lecture.builder()
                .lectureName("HangHeaBackEnd")
                .maxCapacity(50L)
                .currentCapacity(30L)
                .applicationStartAt(startDate)
                .applicationEndAt(endDate)
                .lectureStart(startDate.plusDays(2).atStartOfDay())
                .lectureEnd(endDate.minusDays(2).atStartOfDay())
                .build();


        when(lectureRepository.findLecturesBetweenDates(startDate, endDate))
                .thenReturn(List.of(lecture));

        // When
        List<Lecture> lectures = lectureService.findLecturesBetweenDates(startDate, endDate);

        // Then
        assertThat(lectures).hasSize(1);
        verify(lectureRepository, times(1)).findLecturesBetweenDates(startDate, endDate);
    }

    @Test
    @DisplayName("특정시작날짜와 종료날짜를 입력받아 해당 날짜에 속한 강의가 없을 경우 빈 배열을 반환")
    void shouldReturnEmptyListIfNoLecturesInRange() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        LocalDate startDate = LocalDate.of(2024, 12, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 10);

        when(lectureRepository.findLecturesBetweenDates(startDate, endDate))
                .thenReturn(List.of());  // Empty list

        // When
        List<Lecture> lectures = lectureService.findLecturesBetweenDates(startDate, endDate);

        // Then
        assertThat(lectures).isEmpty();  // Assert that the returned list is empty
        verify(lectureRepository, times(1)).findLecturesBetweenDates(startDate, endDate);
    }


}
