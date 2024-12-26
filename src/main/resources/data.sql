-- 강사 정보
INSERT INTO instructor (instructor_name) VALUES ('하헌우'), ('허재');

-- 사용자 정보
INSERT INTO "user" ( user_name) VALUES ('임동욱'), ( '설한정');

-- 특강 정보
INSERT INTO lecture (lecture_name, max_capacity, application_start_at, application_end_at, lecture_start, lecture_end)
VALUES
    ('Spring Boot 특강', 30, '2024-12-01', '2024-12-10', '2024-12-20 10:00:00', '2024-12-20 17:00:00'),
    ( 'JPA 심화 특강', 30, '2024-12-05', '2024-12-15', '2024-12-25 10:00:00', '2024-12-25 17:00:00');

-- 수강 신청
;