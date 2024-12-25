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

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TestLectureRepository {

    @Autowired
    private JpaLectureEnrollmentRepository jpaLectureEnrollmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("특정날짜를 입력받아서 해당 날짜 범위에 있는 특강 목록을 반환한다")
    void shouldFindLecturesBetweenDates() {

        long userId = 1L;

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
                .enrollmentDate(LocalDate.now())
                .lecture(backLecture)
                .instructor(instructor)
                .user(user)
                .build();
        jpaLectureEnrollmentRepository.save(enrollment);

        // When
        List<LectureEnrollment> lectures = jpaLectureEnrollmentRepository.findByUserId(
                userId
        );
        for (LectureEnrollment lecture : lectures) {
            System.out.println("Lecture ID: " + lecture.getId());
            System.out.println("Lecture Name: " + lecture.getLecture().getLectureName());
            System.out.println("Instructor Name: " + lecture.getInstructor().getInstructorName());
            System.out.println("User Name: " + lecture.getUser().getUserName());
        }
        //then
        assertThat(lectures).hasSize(1);
        assertThat(lectures.get(0).getLecture().getLectureName()).isEqualTo("HangHeaBackEnd");
        assertThat(lectures.get(0).getInstructor().getInstructorName()).isEqualTo("하헌우");
        assertThat(lectures.get(0).getUser().getUserName()).isEqualTo("임동욱");
    }

}
