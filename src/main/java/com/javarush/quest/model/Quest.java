package com.javarush.quest.model;

import java.util.HashMap;
import java.util.Map;

public class Quest {
    private String title;
    private String prolog;
    private Map<String, QuestStep> steps = new HashMap<>();

    public Quest() {
    }

    public Quest(String title, String prolog) {
        this.title = title;
        this.prolog = prolog;
    }

    public String getTitle() {
        return title;
    }

    public String getProlog() {
        return prolog;
    }

    public Map<String, QuestStep> getSteps() {
        return steps;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProlog(String prolog) {
        this.prolog = prolog;
    }

    public void setSteps(Map<String, QuestStep> steps) {
        this.steps = steps;
    }
}
