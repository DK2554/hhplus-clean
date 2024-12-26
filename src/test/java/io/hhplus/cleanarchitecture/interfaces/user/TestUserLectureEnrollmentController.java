package io.hhplus.cleanarchitecture.interfaces.user;

import io.hhplus.cleanarchitecture.domain.Instructor.Instructor;
import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.domain.lectureEnrollment.LectureEnrollment;
import io.hhplus.cleanarchitecture.facade.UserLectureEnrollmentFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserLectureEnrollmentController.class)
public class TestUserLectureEnrollmentController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserLectureEnrollmentFacade userLectureEnrollmentFacade;

    @Test
    @DisplayName("GET - /api/v1/lectures/enrollments/user/{id} 200K.")
    void findUserEnrolledLectures() throws Exception{

        //given
        List<LectureEnrollment> mockEnrollments = List.of(
                LectureEnrollment.builder()
                        .id(1L)
                        .enrollmentDate(LocalDate.now())
                        .lecture(Lecture.builder()
                                .id(1L)
                                .lectureName("HanheaBackEnd")
                                .build()
                        )
                        .instructor(Instructor.builder()
                                .id(1L)
                                .instructorName("하헌우")
                                .build()
                        )
                        .build(),
                LectureEnrollment.builder()
                        .id(2L)
                        .enrollmentDate(LocalDate.now())
                        .lecture(Lecture.builder()
                                .id(2L)
                                .lectureName("HanheaFrontEnd")
                                .build()
                        )
                        .instructor(Instructor.builder()
                                .id(2L)
                                .instructorName("허재")
                                .build()
                        )
                        .build());
        Long userId = 1L;
        //when
        when(userLectureEnrollmentFacade.findUserEnrolledLectures(userId)).thenReturn(mockEnrollments);
        //특강 목록을 조회할거야
        //then
        mockMvc.perform(get("/api/v1/lectures/enrollments/user/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].lecture.lectureName").value("HanheaBackEnd"))
                .andExpect(jsonPath("$[0].instructor.instructorName").value("하헌우"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].lecture.lectureName").value("HanheaFrontEnd"))
                .andExpect(jsonPath("$[1].instructor.instructorName").value("허재"));
    }




}
