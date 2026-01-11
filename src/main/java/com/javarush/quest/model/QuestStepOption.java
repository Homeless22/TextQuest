package com.javarush.quest.model;

public class QuestStepOption {
    private String text;
    private String nextStepId;

    public QuestStepOption() {
    }

    public QuestStepOption(String text, String nextStepId) {
        this.text = text;
        this.nextStepId = nextStepId;
    }

    public String getText() {
        return text;
    }

    public String getNextStepId() {
        return nextStepId;
    }

    @Override
    public String toString() {
        return "QuestStepOption{" +
                ", text='" + text + '\'' +
                ", nextStepId='" + nextStepId + '\'' +
                '}';
    }
}
