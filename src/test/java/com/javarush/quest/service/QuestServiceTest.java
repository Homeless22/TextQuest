package com.javarush.quest.service;

import com.javarush.quest.model.QuestStep;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class QuestServiceTest {
    private QuestService questService = new QuestService();

    @Test
    @DisplayName("Тест получения начального шага")
    void testGetStartStep() {
        QuestStep step = questService.getStep("start");

        assertNotNull(step, "Шаг 'start' не должен быть null");
        assertEquals("start", step.getId(), "ID шага должен быть 'start'");
        assertNotNull(step.getOptions(), "Шаг 'start' должен содержать ответы");
    }

    @Test
    @DisplayName("Тест проверки начального шага")
    void testIsFinalStepReturnsFalse() {
        boolean isFinalStep = questService.isFinalStep("start");
        assertFalse(isFinalStep, "Шаг 'start' не может быть финальным");
    }

    @Test
    @DisplayName("Тест проверки финального шага")
    void testIsFinalStepReturnsTrue() {
        boolean isFinalStep = questService.isFinalStep("game_over_win");
        assertTrue(isFinalStep, "Шаг 'game_over_win' должен быть финальным");
    }

    @Test
    @DisplayName("Тест получения несуществующего шага")
    void testGetNonExistentStep() {
        QuestStep step = questService.getStep("non-existent");
        assertNull(step, "Несуществующий шаг должен возвращать null");
    }

}