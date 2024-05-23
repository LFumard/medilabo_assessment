package com.lfumard.medilabo_assessment.model;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Order(0)
public class NoteBean {

    private String id;
    private long patientId;
    private LocalDateTime date;
    private String patientNote;

    public NoteBean() {
    }

    public NoteBean(String id, long patientId, LocalDateTime date, String patientNote) {
        this.id = id;
        this.patientId = patientId;
        this.date = date;
        this.patientNote = patientNote;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPatientNote() {
        return patientNote;
    }

    public void setPatientNote(String patientNote) {
        this.patientNote = patientNote;
    }
}
