package io.hhplus.cleanarchitecture.infra.lectureEnrollment;

import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ILectureEnrollmentRepository implements LectureEnrollmentRepository {

    private final JpaLectureEnrollmentRepository jpaLectureEnrollmentRepository;

    @Override
    public List<LectureEnrollment> findUserEnrolledLectures(Long userId) {
        return jpaLectureEnrollmentRepository.findByUserId(userId);
    }

    @Override
    public void enrollInLecture(LectureEnrollment lectureEnrollment) {
        jpaLectureEnrollmentRepository.save(lectureEnrollment);
    }

    @Override
    public Optional<LectureEnrollment> findByUserIdAndLectureId(Long userId, Long lectureId) {
        return jpaLectureEnrollmentRepository.findByUserIdAndLectureId(userId, lectureId);
    }

    @Override
    public Optional<Lecture> findByIdWithLock(Long lectureId) {
        return jpaLectureEnrollmentRepository.findByIdWithLock(lectureId);
    }
}
