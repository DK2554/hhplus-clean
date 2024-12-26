package io.hhplus.cleanarchitecture.interfaces.lectureEnrollment;


import io.hhplus.cleanarchitecture.domain.Instructor.Instructor;
import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import io.hhplus.cleanarchitecture.domain.user.User;
import io.hhplus.cleanarchitecture.infra.lectureEnrollment.JpaLectureEnrollmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
public class TestLectureEnrollmentRepository {

    @Autowired
    private JpaLectureEnrollmentRepository jpaLectureEnrollmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("특정 사용자 ID로 신청한 강의 목록을 조회한다.")
    void shouldFindUserEnrolledLectures() {
        // Given
        User user = User.builder().userName("임동욱").build();
        user = entityManager.persist(user); // 유저를 데이터베이스에 저장

        Instructor instructor = Instructor.builder().instructorName("하헌우").build();
        instructor = entityManager.persist(instructor); // 강사 저장

        Lecture lecture = Lecture.builder()
                .lectureName("HangHeaBackEnd")
                .maxCapacity(50L)
                .currentCapacity(30L)
                .applicationStartAt(LocalDate.now())
                .applicationEndAt(LocalDate.now().plusDays(5))
                .lectureStart(LocalDate.now().atTime(9, 0))
                .lectureEnd(LocalDate.now().atTime(18, 0))
                .build();
        lecture = entityManager.persist(lecture); // 강의를 데이터베이스에 저장

        LectureEnrollment enrollment = LectureEnrollment.builder()
                .user(user)
                .lecture(lecture)
                .instructor(instructor)
                .enrollmentDate(LocalDate.now())
                .build();
        entityManager.persist(enrollment); // 강의 신청을 데이터베이스에 저장

        // When
        List<LectureEnrollment> result = jpaLectureEnrollmentRepository.findByUserId(user.getId());

        // Then
        assertThat(result).hasSize(1); // 유저가 등록한 강의는 하나여야 한다.
        assertThat(result.get(0).getLecture().getLectureName()).isEqualTo("HangHeaBackEnd");
        assertThat(result.get(0).getUser().getUserName()).isEqualTo("임동욱");
    }

    @Test
    @DisplayName("특강 신청 JPA 테스트")
    void shouldCallSaveMethodOfRepository() {
        User user = User.builder().userName("임동욱").build();
        user = entityManager.persist(user); // 저장 후 영속 상태에서 ID 가져오기

        Instructor instructor = Instructor.builder().instructorName("하헌우").build();
        instructor = entityManager.persist(instructor);

        Lecture backLecture = Lecture.builder()
                .lectureName("HangHeaBackEnd")
                .maxCapacity(50L)
                .currentCapacity(30L)
                .applicationStartAt(LocalDate.now())
                .applicationEndAt(LocalDate.now().plusDays(5))
                .lectureStart(LocalDate.now().atTime(9, 0))
                .lectureEnd(LocalDate.now().atTime(18, 0))
                .build();
        backLecture = entityManager.persist(backLecture);

        LectureEnrollment enrollment = LectureEnrollment.builder()
                .user(user)
                .lecture(backLecture)
                .instructor(instructor)
                .enrollmentDate(LocalDate.of(2024, 12, 31))
                .build();

        // When
        LectureEnrollment savedEnrollment = jpaLectureEnrollmentRepository.save(enrollment);

        // Then
        assertThat(savedEnrollment.getId()).isNotNull();
        assertThat(savedEnrollment.getUser().getId()).isEqualTo(user.getId());
        assertThat(savedEnrollment.getLecture().getId()).isEqualTo(backLecture.getId());
    }
}
