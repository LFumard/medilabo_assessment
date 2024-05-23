package com.lfumard.medilabo_assessment.proxies;

import com.lfumard.medilabo_assessment.model.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "note", url = "${medilabo_gateway.url}")
public interface NoteProxy {

    @GetMapping(value = "/service_note/list/{patId}")
    List<NoteBean> getListNoteByPatientId(@PathVariable Long patId, @RequestHeader("Authorization") String token);
}
