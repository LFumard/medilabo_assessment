package com.lfumard.medilabo_assessment.proxies;

import com.lfumard.medilabo_assessment.model.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "patient", url = "${medilabo_gateway.url}")
public interface PatientProxy {
    @GetMapping("/patient/{patientId}")
    PatientBean getPatientById(@PathVariable("patientId") Long patientId, @RequestHeader("Authorization") String token);

}
