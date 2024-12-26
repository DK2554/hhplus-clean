package io.hhplus.cleanarchitecture.interfaces.lecture;

import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.infra.lecture.JpaLectureRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TestLectureRepository {

    @Autowired
    private JpaLectureRepository JpaLectureRepository;

    @Test
    @DisplayName("특정날짜를 입력받아서 해당 날짜 범위에 있는 특강 목록을 반환한다")
    void shouldFindLecturesBetweenDates() {
        // Given
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.minusDays(5);
        LocalDate endDate = now.plusDays(5);

        Lecture backLecture = Lecture.builder()
                .lectureName("HangHeaBackEnd")
                .maxCapacity(50L)
                .currentCapacity(30L)
                .applicationStartAt(startDate)
                .applicationEndAt(endDate)
                .lectureStart(startDate.atTime(9, 0))
                .lectureEnd(endDate.atTime(18, 0))
                .build();

        Lecture frontLecture = Lecture.builder()
                .lectureName("HangHeaFrontEnd")
                .maxCapacity(40L)
                .currentCapacity(20L)
                .applicationStartAt(startDate.plusDays(1))
                .applicationEndAt(endDate.minusDays(1))
                .lectureStart(startDate.plusDays(1).atTime(10, 0))
                .lectureEnd(endDate.minusDays(1).atTime(17, 0))
                .build();

        JpaLectureRepository.save(backLecture);
        JpaLectureRepository.save(frontLecture);

        // When
        List<Lecture> lectures = JpaLectureRepository.findByApplicationStartAtBetween(
                startDate, endDate
        );

        // Then
        assertThat(lectures).hasSize(2); // 범위에 포함된 2개의 강의만 반환
        assertThat(lectures)
                .extracting(Lecture::getLectureName)
                .containsExactlyInAnyOrder("HangHeaBackEnd", "HangHeaFrontEnd");
    }

    @Test
    @DisplayName("특정 날짜 범위에 강의가 없으면 빈 리스트를 반환한다")
    void shouldReturnEmptyListWhenNoLecturesInRange() {
        // Given
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.plusDays(10);  // 현재 날짜로부터 10일 후
        LocalDate endDate = now.plusDays(20);   // 현재 날짜로부터 20일 후

        // 강의 데이터가 없으므로 해당 범위에는 강의가 없다.

        // When
        List<Lecture> lectures = JpaLectureRepository.findByApplicationStartAtBetween(startDate, endDate);

        // Then
        assertThat(lectures).isEmpty();  // 빈 리스트가 반환되어야 한다.
    }
}
