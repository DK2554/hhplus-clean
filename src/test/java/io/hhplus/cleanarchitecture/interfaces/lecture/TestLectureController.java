package io.hhplus.cleanarchitecture.interfaces.lecture;

import io.hhplus.cleanarchitecture.domain.lecture.Lecture;
import io.hhplus.cleanarchitecture.domain.lecture.LectureService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LectureController.class)
public class TestLectureController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LectureService lectureService;

    @Test
    @DisplayName("GET - /api/v1/lectures 200K.")
    void lectures() throws Exception{

        //given
        List<Lecture> mockLectures = Arrays.asList(
                Lecture.builder()
                        .id(1L)
                        .lectureName("HanheaBackEnd")
                        .maxCapacity(30L)
                        .currentCapacity(10L)
                        .applicationStartAt(LocalDate.of(2024, 12, 1))
                        .applicationEndAt(LocalDate.of(2024, 12, 10))
                        .lectureStart(LocalDateTime.of(2024, 12, 15, 9, 0))
                        .lectureEnd(LocalDateTime.of(2024, 12, 15, 12, 0))
                        .build(),
                Lecture.builder()
                        .id(2L)
                        .lectureName("HanheaFrontEnd")
                        .maxCapacity(25L)
                        .currentCapacity(15L)
                        .applicationStartAt(LocalDate.of(2024, 12, 5))
                        .applicationEndAt(LocalDate.of(2024, 12, 15))
                        .lectureStart(LocalDateTime.of(2024, 12, 20, 10, 0))
                        .lectureEnd(LocalDateTime.of(2024, 12, 20, 12, 0))
                        .build()
        );

        String applicationStartAt = "2024-12-01";
        String applicationEndAt = "2024-12-17";

        //when
        when(lectureService.findLecturesBetweenDates(LocalDate.parse("2024-12-01"),LocalDate.parse("2024-12-17")))
                .thenReturn(mockLectures);

        //특강 목록을 조회할거야
        //then
        mockMvc.perform(get("/api/v1/lectures")
                        .param("applicationStartAt", applicationStartAt)
                        .param("applicationEndAt", applicationEndAt))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].lectureName").value("HanheaBackEnd"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].lectureName").value("HanheaFrontEnd"));
    }

}
