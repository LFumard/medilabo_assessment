package com.lfumard.medilabo_assessment.model;

import java.util.List;

public class TriggerWord {

    private List<String> triggerList = List.of(
            "Hémoglobine A1C",
            "Microalbumine",
            "Taille",
            "Poids",
            "Fumeur",
            "Fumeuse",
            "Anormal",
            "Cholestérol",
            "Vertiges",
            "Rechute",
            "Réaction",
            "Anticorps");

    public List<String> getTriggerList() {
        return triggerList;
    }

    public TriggerWord(List<String> triggerList) {
        this.triggerList = triggerList;
    }

    public TriggerWord() {
    }

    @Override
    public String toString() {
        return "TriggerWord{" +
                "triggerList=" + triggerList +
                '}';
    }
}
