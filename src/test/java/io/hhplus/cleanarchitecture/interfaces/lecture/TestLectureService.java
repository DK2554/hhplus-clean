package io.hhplus.cleanarchitecture.interfaces.lecture;

import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.domain.lecture.LectureRepository;
import io.hhplus.cleanarchitecture.domain.lecture.LectureService;
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
}
