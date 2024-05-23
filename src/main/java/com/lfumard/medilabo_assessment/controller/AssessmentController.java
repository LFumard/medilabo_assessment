package com.lfumard.medilabo_assessment.controller;

import com.lfumard.medilabo_assessment.service.AssessmentService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AssessmentController {

    private Logger logger = LoggerFactory.getLogger(AssessmentController.class);

    @Autowired
    private AssessmentService assessmentService;

    @GetMapping("/assessment/{id}")
    public String getAssessmentByPatientId(@PathVariable Long id, @CookieValue(value="medilabo", defaultValue = "NotFound") String medilaboCookie, @RequestHeader(value="Authorization") String accessToken, HttpServletRequest request ) {

        logger.info("New request GetMapping : getAssessmentByPatientId : " + id);
        return assessmentService.getAssessmentByPatientId(id, accessToken);
    }
}
