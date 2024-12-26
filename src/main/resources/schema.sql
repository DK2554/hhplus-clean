-- User Table
CREATE TABLE "user" (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      user_name VARCHAR NOT NULL,
                      create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP
);

-- Lecture Table
CREATE TABLE lecture (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         lecture_name VARCHAR NOT NULL,
                         max_capacity INT NOT NULL,
                         current_capacity INT DEFAULT 0,
                         application_start_at TIMESTAMP NOT NULL,
                         application_end_at TIMESTAMP NOT NULL,
                         lecture_start TIMESTAMP NOT NULL,
                         lecture_end TIMESTAMP NOT NULL,
                         create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP
);

-- Instructor Table
CREATE TABLE instructor (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            instructor_name VARCHAR NOT NULL,
                            create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP
);

-- Lecture Enrollment Table
CREATE TABLE lecture_enrollment (
                                    id INT PRIMARY KEY AUTO_INCREMENT,
                                    user_id INT NOT NULL,
                                    lecture_id INT NOT NULL,
                                    instructor_id INT NOT NULL,
                                    enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    updated_at TIMESTAMP,
                                    status VARCHAR
);