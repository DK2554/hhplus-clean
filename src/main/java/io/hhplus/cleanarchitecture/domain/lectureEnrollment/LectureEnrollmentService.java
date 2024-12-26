package io.hhplus.cleanarchitecture.domain.lectureEnrollment;

import io.hhplus.cleanarchitecture.HangHeaException;
import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureEnrollmentService {

    private final LectureEnrollmentRepository lectureEnrollmentRepository;

    public List<LectureEnrollment> findUserEnrolledLectures(Long userId) {
        return lectureEnrollmentRepository.findUserEnrolledLectures(userId);
    }
    @Transactional
    public void enrollInLecture(LectureEnrollment lectureEnrollment) {

        Long userId = lectureEnrollment.getUser().getId();
        Long lectureId = lectureEnrollment.getLecture().getId();

        // 1. 중복 신청 확인
        Optional<LectureEnrollment> existingEnrollment = lectureEnrollmentRepository.findByUserIdAndLectureId(userId, lectureId);
        if (existingEnrollment.isPresent()) {
            throw new HangHeaException("특강은 중복으로 등록할 수 없습니다.");
        }

        Optional<Lecture> optionalLecture = lectureEnrollmentRepository.findByIdWithLock(lectureId);

        Lecture lecture = optionalLecture.orElseThrow(() -> new HangHeaException("특강을 찾을 수 없습니다."));

        if (lecture.isFull()) {
            throw new IllegalStateException("정원 초과로 신청이 불가능합니다.");
        }
        // 3. 강의 정원 증가
        lecture.incrementCapacity();

        // 4. 강의 신청 저장
        lectureEnrollmentRepository.enrollInLecture(lectureEnrollment);
    }
}
