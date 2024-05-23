package com.lfumard.medilabo_assessment.service;

import com.lfumard.medilabo_assessment.model.NoteBean;
import com.lfumard.medilabo_assessment.model.PatientBean;
import com.lfumard.medilabo_assessment.proxies.NoteProxy;
import com.lfumard.medilabo_assessment.proxies.PatientProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AssessmentServiceTest {

    @Mock
    private PatientProxy patientProxy;

    @Mock
    private NoteProxy noteProxy;

    @InjectMocks
    private AssessmentService assessmentService;

    @Test
    public void getAssessmentByPatientId_return_None() {

        PatientBean patient = new PatientBean(1L, "patientFirstName1", "patientLastName1", LocalDate.of(1971,1,1), "1111111111", "M", "patientAddress1");
        when(patientProxy.getPatientById(1L, "test")).thenReturn(patient);

        List<NoteBean> noteList = new ArrayList<>();
        noteList.add(new NoteBean("1", 1L, LocalDate.of(2024,1,1).atStartOfDay(),"No Trigger 1"));
        noteList.add(new NoteBean("2", 1L, LocalDate.of(2024,1,2).atStartOfDay(),"No Trigger 2"));
        when(noteProxy.getListNoteByPatientId(1L, "test")).thenReturn(noteList);

        String strAssessment = assessmentService.getAssessmentByPatientId(1L, "test");

        assertEquals("None", strAssessment);
        verify(patientProxy, times(1)).getPatientById(anyLong(), eq("test"));
        verify(noteProxy, times(1)).getListNoteByPatientId(anyLong(), eq("test"));
    }

    @Test
    public void getAssessmentByPatientId_return_Borderline() {

        PatientBean patient = new PatientBean(1L, "patientFirstName1", "patientLastName1", LocalDate.of(1971,1,1), "1111111111", "M", "patientAddress1");
        when(patientProxy.getPatientById(1L, "test")).thenReturn(patient);

        List<NoteBean> noteList = new ArrayList<>();
        noteList.add(new NoteBean("1", 1L, LocalDate.of(2024,1,1).atStartOfDay(),"Taille"));
        noteList.add(new NoteBean("2", 1L, LocalDate.of(2024,1,2).atStartOfDay(),"Poids"));
        noteList.add(new NoteBean("3", 1L, LocalDate.of(2024,1,3).atStartOfDay(),"No Trigger"));
        when(noteProxy.getListNoteByPatientId(1L,"test")).thenReturn(noteList);

        String strAssessment = assessmentService.getAssessmentByPatientId(1L,"test");

        assertEquals("Borderline", strAssessment);
        verify(patientProxy, times(1)).getPatientById(anyLong(), eq("test"));
        verify(noteProxy, times(1)).getListNoteByPatientId(anyLong(), eq("test"));
    }

    @Test
    public void getAssessmentByPatientIdYoungM_return_In_Danger() {

        PatientBean patient;
        patient = new PatientBean(1L, "patientFirstName1", "patientLastName1", LocalDate.of(2000,1,1), "1111111111", "M", "patientAddress1");
        when(patientProxy.getPatientById(1L, "test")).thenReturn(patient);

        List<NoteBean> noteList = new ArrayList<>();
        noteList.add(new NoteBean("1", 1L, LocalDate.of(2024,1,1).atStartOfDay(),"Taille"));
        noteList.add(new NoteBean("2", 1L, LocalDate.of(2024,1,2).atStartOfDay(),"Poids"));
        noteList.add(new NoteBean("3", 1L, LocalDate.of(2024,1,3).atStartOfDay(),"No Trigger"));
        noteList.add(new NoteBean("4", 1L, LocalDate.of(2024,1,4).atStartOfDay(),"Vertiges"));
        when(noteProxy.getListNoteByPatientId(1L,"test")).thenReturn(noteList);

        String strAssessment = assessmentService.getAssessmentByPatientId(1L, "test");

        assertEquals("In Danger", strAssessment);
        verify(patientProxy, times(1)).getPatientById(anyLong(), eq("test"));
        verify(noteProxy, times(1)).getListNoteByPatientId(anyLong(), eq("test"));
    }

    @Test
    public void getAssessmentByPatientIdYoungF_return_In_Danger() {

        PatientBean patient;
        patient = new PatientBean(1L, "patientFirstName1", "patientLastName1", LocalDate.of(2000,1,1), "1111111111", "F", "patientAddress1");
        when(patientProxy.getPatientById(1L, "test")).thenReturn(patient);

        List<NoteBean> noteList = new ArrayList<>();
        noteList.add(new NoteBean("1", 1L, LocalDate.of(2024,1,1).atStartOfDay(),"Taille"));
        noteList.add(new NoteBean("2", 1L, LocalDate.of(2024,1,2).atStartOfDay(),"Poids"));
        noteList.add(new NoteBean("3", 1L, LocalDate.of(2024,1,3).atStartOfDay(),"No Trigger"));
        noteList.add(new NoteBean("4", 1L, LocalDate.of(2024,1,4).atStartOfDay(),"Vertiges"));
        noteList.add(new NoteBean("4", 1L, LocalDate.of(2024,1,4).atStartOfDay(),"Rechute"));
        when(noteProxy.getListNoteByPatientId(1L, "test")).thenReturn(noteList);

        String strAssessment = assessmentService.getAssessmentByPatientId(1L, "test");

        assertEquals("In Danger", strAssessment);
        verify(patientProxy, times(1)).getPatientById(anyLong(), eq("test"));
        verify(noteProxy, times(1)).getListNoteByPatientId(anyLong(), eq("test"));
    }

    @Test
    public void getAssessmentByPatientIdOld_return_In_Danger() {

        PatientBean patient;
        patient = new PatientBean(1L, "patientFirstName1", "patientLastName1", LocalDate.of(1970,1,1), "1111111111", "M", "patientAddress1");
        when(patientProxy.getPatientById(1L, "test")).thenReturn(patient);

        List<NoteBean> noteList = new ArrayList<>();
        noteList.add(new NoteBean("1", 1L, LocalDate.of(2024,1,1).atStartOfDay(),"Taille"));
        noteList.add(new NoteBean("2", 1L, LocalDate.of(2024,1,2).atStartOfDay(),"Poids"));
        noteList.add(new NoteBean("3", 1L, LocalDate.of(2024,1,3).atStartOfDay(),"No Trigger"));
        noteList.add(new NoteBean("4", 1L, LocalDate.of(2024,1,4).atStartOfDay(),"Vertiges"));
        noteList.add(new NoteBean("4", 1L, LocalDate.of(2024,1,4).atStartOfDay(),"Rechute"));
        noteList.add(new NoteBean("4", 1L, LocalDate.of(2024,1,4).atStartOfDay(),"Réaction"));
        noteList.add(new NoteBean("4", 1L, LocalDate.of(2024,1,4).atStartOfDay(),"Anticorps"));
        when(noteProxy.getListNoteByPatientId(1L, "test")).thenReturn(noteList);

        String strAssessment = assessmentService.getAssessmentByPatientId(1L, "test");

        assertEquals("In Danger", strAssessment);
        verify(patientProxy, times(1)).getPatientById(anyLong(), eq("test"));
        verify(noteProxy, times(1)).getListNoteByPatientId(anyLong(), eq("test"));
    }

    @Test
    public void getAssessmentByPatientIdOld_return_Early_Onset() {

        PatientBean patient;
        patient = new PatientBean(1L, "patientFirstName1", "patientLastName1", LocalDate.of(1971,1,1), "1111111111", "M", "patientAddress1");
        when(patientProxy.getPatientById(1L, "test")).thenReturn(patient);

        List<NoteBean> noteList = new ArrayList<>();
        noteList.add(new NoteBean("1", 1L, LocalDate.of(2024,1,1).atStartOfDay(),"Taille"));
        noteList.add(new NoteBean("2", 1L, LocalDate.of(2024,1,2).atStartOfDay(),"Poids"));
        noteList.add(new NoteBean("3", 1L, LocalDate.of(2024,1,3).atStartOfDay(),"No Trigger"));
        noteList.add(new NoteBean("4", 1L, LocalDate.of(2024,1,4).atStartOfDay(),"Vertiges"));
        noteList.add(new NoteBean("5", 1L, LocalDate.of(2024,1,5).atStartOfDay(),"Anormal"));
        noteList.add(new NoteBean("6", 1L, LocalDate.of(2024,1,6).atStartOfDay(),"Rechute"));
        noteList.add(new NoteBean("7", 1L, LocalDate.of(2024,1,7).atStartOfDay(),"Réaction"));
        noteList.add(new NoteBean("8", 1L, LocalDate.of(2024,1,8).atStartOfDay(),"Anticorps"));
        noteList.add(new NoteBean("9", 1L, LocalDate.of(2024,1,9).atStartOfDay(),"Hémoglobine A1C"));
        when(noteProxy.getListNoteByPatientId(1L, "test")).thenReturn(noteList);

        String strAssessment = assessmentService.getAssessmentByPatientId(1L, "test");

        assertEquals("Early Onset", strAssessment);
        verify(patientProxy, times(1)).getPatientById(anyLong(), eq("test"));
        verify(noteProxy, times(1)).getListNoteByPatientId(anyLong(), eq("test"));
    }

    @Test
    public void getAssessmentByPatientIdYoungM_return_Early_Onset() {

        PatientBean patient;
        patient = new PatientBean(1L, "patientFirstName1", "patientLastName1", LocalDate.of(2000,1,1), "1111111111", "M", "patientAddress1");
        when(patientProxy.getPatientById(1L, "test")).thenReturn(patient);

        List<NoteBean> noteList = new ArrayList<>();
        noteList.add(new NoteBean("1", 1L, LocalDate.of(2024,1,1).atStartOfDay(),"Taille"));
        noteList.add(new NoteBean("2", 1L, LocalDate.of(2024,1,2).atStartOfDay(),"Poids"));
        noteList.add(new NoteBean("3", 1L, LocalDate.of(2024,1,3).atStartOfDay(),"No Trigger"));
        noteList.add(new NoteBean("4", 1L, LocalDate.of(2024,1,4).atStartOfDay(),"Vertiges"));
        noteList.add(new NoteBean("5", 1L, LocalDate.of(2024,1,5).atStartOfDay(),"Anormal"));
        noteList.add(new NoteBean("6", 1L, LocalDate.of(2024,1,6).atStartOfDay(),"Rechute"));
        when(noteProxy.getListNoteByPatientId(1L, "test")).thenReturn(noteList);

        String strAssessment = assessmentService.getAssessmentByPatientId(1L, "test");

        assertEquals("Early Onset", strAssessment);
        verify(patientProxy, times(1)).getPatientById(anyLong(), eq("test"));
        verify(noteProxy, times(1)).getListNoteByPatientId(anyLong(), eq("test"));
    }

    @Test
    public void getAssessmentByPatientIdYoungF_return_Early_Onset() {

        PatientBean patient;
        patient = new PatientBean(1L, "patientFirstName1", "patientLastName1", LocalDate.of(2000,1,1), "1111111111", "F", "patientAddress1");
        when(patientProxy.getPatientById(1L, "test")).thenReturn(patient);

        List<NoteBean> noteList = new ArrayList<>();
        noteList.add(new NoteBean("1", 1L, LocalDate.of(2024,1,1).atStartOfDay(),"Taille"));
        noteList.add(new NoteBean("2", 1L, LocalDate.of(2024,1,2).atStartOfDay(),"Poids"));
        noteList.add(new NoteBean("3", 1L, LocalDate.of(2024,1,3).atStartOfDay(),"No Trigger"));
        noteList.add(new NoteBean("4", 1L, LocalDate.of(2024,1,4).atStartOfDay(),"Vertiges"));
        noteList.add(new NoteBean("5", 1L, LocalDate.of(2024,1,5).atStartOfDay(),"Anormal"));
        noteList.add(new NoteBean("6", 1L, LocalDate.of(2024,1,6).atStartOfDay(),"Rechute"));
        noteList.add(new NoteBean("7", 1L, LocalDate.of(2024,1,7).atStartOfDay(),"Réaction"));
        noteList.add(new NoteBean("8", 1L, LocalDate.of(2024,1,8).atStartOfDay(),"Anticorps"));
        when(noteProxy.getListNoteByPatientId(1L, "test")).thenReturn(noteList);

        String strAssessment = assessmentService.getAssessmentByPatientId(1L, "test");

        assertEquals("Early Onset", strAssessment);
        verify(patientProxy, times(1)).getPatientById(anyLong(), eq("test"));
        verify(noteProxy, times(1)).getListNoteByPatientId(anyLong(), eq("test"));
    }
}
