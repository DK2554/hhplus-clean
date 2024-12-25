package io.hhplus.cleanarchitecture.interfaces.lecture;

import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.infra.JpaLectureRepository;
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
}
