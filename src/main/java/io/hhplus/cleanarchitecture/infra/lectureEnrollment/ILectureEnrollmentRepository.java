package io.hhplus.cleanarchitecture.infra.lectureEnrollment;

import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ILectureEnrollmentRepository implements LectureEnrollmentRepository {

    private final JpaLectureEnrollmentRepository jpaLectureEnrollmentRepository;

    @Override
    public List<LectureEnrollment> findUserEnrolledLectures(Long userId) {
        return jpaLectureEnrollmentRepository.findByUserId(userId);
    }

    @Override
    public void save(LectureEnrollment lectureEnrollment) {
        jpaLectureEnrollmentRepository.save(lectureEnrollment);
    }
}
