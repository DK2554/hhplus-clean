package io.hhplus.cleanarchitecture.domain.lectureEnrollment;


import io.hhplus.cleanarchitecture.domain.lecture.Lecture;

import java.util.List;
import java.util.Optional;

public interface LectureEnrollmentRepository {
    List<LectureEnrollment> findUserEnrolledLectures(Long userId);

    void enrollInLecture(LectureEnrollment lectureEnrollment);

    Optional<LectureEnrollment> findByUserIdAndLectureId(Long userId, Long lectureId);

    Optional<Lecture> findByIdWithLock(Long lectureId);
}
