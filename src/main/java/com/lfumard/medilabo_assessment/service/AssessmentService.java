package com.lfumard.medilabo_assessment.service;

import com.lfumard.medilabo_assessment.model.NoteBean;
import com.lfumard.medilabo_assessment.model.PatientBean;
import com.lfumard.medilabo_assessment.model.TriggerWord;
import com.lfumard.medilabo_assessment.proxies.NoteProxy;
import com.lfumard.medilabo_assessment.proxies.PatientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;

@Service
public class AssessmentService {

    private TriggerWord triggerWord;

    @Autowired
    private final NoteProxy noteProxy;

    @Autowired
    private final PatientProxy patientProxy;

    public AssessmentService(NoteProxy noteProxy, PatientProxy patientProxy) {
        this.noteProxy = noteProxy;
        this.patientProxy = patientProxy;
        this.triggerWord = new TriggerWord();
    }

    public String getAssessmentByPatientId(Long id, String accessToken ) {

        PatientBean patientBean = patientProxy.getPatientById(id, accessToken);
        List<NoteBean> noteBeanList = noteProxy.getListNoteByPatientId(id, accessToken);

        int nbTrigger = countNumberOfTrigger(noteBeanList);
        int agePatient = getAge(patientBean.getBirthdate());

        String levelRisk = "To define";
        levelRisk = defineLevelRisk(nbTrigger, agePatient, patientBean.getSex());

        return levelRisk;
    }

    private String defineLevelRisk(int nbTrigger, int agePatient, String sex) {

        String strLevelRisk = "None";

        if(nbTrigger == 0) strLevelRisk = "None";
        if(nbTrigger >= 2 && nbTrigger <= 5 && agePatient >= 30) strLevelRisk =  "Borderline";
        if((sex.equals("M") && agePatient < 30 && nbTrigger == 3) ||
           (sex.equals("F") && agePatient < 30 && nbTrigger == 4) ||
           (agePatient >= 30 && nbTrigger >= 6 && nbTrigger <=7))
            strLevelRisk =  "In Danger";
        if((sex.equals("M") && agePatient < 30 && nbTrigger >= 5) ||
           (sex.equals("F") && agePatient < 30 && nbTrigger >= 7) ||
           (agePatient >= 30 && nbTrigger >= 8))
            strLevelRisk =  "Early Onset";

        return strLevelRisk;
    }

    private Integer getAge(LocalDate birthdate) {

        Period period = Period.between(birthdate, LocalDate.now());
        return period.getYears();
    }

    private int countNumberOfTrigger(List<NoteBean> noteBeanList) {

        // Gestion de l'unicité des mots clés
        HashSet<String> strTriggerFound = new HashSet<>();

        for (NoteBean noteBean : noteBeanList)
            for (int i = 0; i < triggerWord.getTriggerList().size(); i++)
                if (noteBean.getPatientNote().toUpperCase().contains(triggerWord.getTriggerList().get(i).toUpperCase()))
                    strTriggerFound.add(triggerWord.getTriggerList().get(i).toUpperCase());

        return strTriggerFound.size();
    }
}
