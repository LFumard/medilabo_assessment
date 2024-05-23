package com.lfumard.medilabo_assessment.controller;

import com.lfumard.medilabo_assessment.service.AssessmentService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@WebMvcTest(AssessmentController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AssessmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssessmentService assessmentService;

    @Test
    public void testGetAssessmentByPatientId() throws Exception {

        String strAssessment = "Assessment Test";
        given(assessmentService.getAssessmentByPatientId(1L, "test")).willReturn(strAssessment);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/assessment/{id}", 1L)
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "test"))

                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Assessment Test"));

        verify(assessmentService, times(1)).getAssessmentByPatientId(1L, "test");
        verifyNoMoreInteractions(assessmentService);
    }
}
