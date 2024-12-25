package io.hhplus.cleanarchitecture.domain.lectureEnrollment;


import java.util.List;

public interface LectureEnrollmentRepository {
    List<LectureEnrollment> findUserEnrolledLectures(Long userId);

    void save(LectureEnrollment lectureEnrollment);
}
