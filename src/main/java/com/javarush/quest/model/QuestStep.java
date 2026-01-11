package com.javarush.quest.model;

import java.util.Map;
import java.util.Objects;

public class QuestStep {
    private String id;
    private String text;
    Map<Integer, QuestStepOption> options;

    public QuestStep() {
    }

    public QuestStep(String id, String text, Map<Integer, QuestStepOption> options) {
        this.id = id;
        this.text = text;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Map<Integer, QuestStepOption> getOptions() {
        return options;
    }

    public String getNextStepIdByOptionId(Integer optionId) {
        if (options == null || optionId == null) {
            return null;
        }
        QuestStepOption option = options.getOrDefault(optionId, null);
        return option != null ? option.getNextStepId() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        QuestStep questStep = (QuestStep) o;
        return Objects.equals(id, questStep.id) && Objects.equals(text, questStep.text) && Objects.equals(options, questStep.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, options);
    }

    @Override
    public String toString() {
        return "QuestStep{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                '}';
    }
}
