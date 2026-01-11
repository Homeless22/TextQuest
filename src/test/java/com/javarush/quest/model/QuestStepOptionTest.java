package com.javarush.quest.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuestStepOptionTest {
    @Test
    @DisplayName("Тест создания варианта ответа")
    void testQuestStepOptionCreation() {
        QuestStepOption option = new QuestStepOption("Option Text", "nextStepId");
        assertEquals("Option Text", option.getText());
        assertEquals("nextStepId", option.getNextStepId());
    }
}