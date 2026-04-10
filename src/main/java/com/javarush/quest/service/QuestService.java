package com.javarush.quest.service;

import com.javarush.quest.model.Quest;
import com.javarush.quest.model.QuestStep;
import com.javarush.quest.util.QuestLoader;

public class QuestService {
    private Quest quest;

    private final static String QUEST_FILE_NAME = "quest.json";

    public QuestService() {
        initQuest();
    }

    private void initQuest() {
        try {
            quest = QuestLoader.loadQuestFromResource(QUEST_FILE_NAME);
        } catch (Exception ex) {
            throw new RuntimeException("Ошибка загрузки квеста из файла!", ex);
        }
    }

    public Quest getQuest() {
        return quest;
    }

    public QuestStep getStep(String id) {
        return quest.getSteps() != null ? quest.getSteps().get(id) : null;
    }

    public boolean isFinalStep(String stepId) {
        QuestStep step = quest.getSteps().get(stepId);
        return step != null && (step.getOptions() == null || step.getOptions().isEmpty());
    }
}
