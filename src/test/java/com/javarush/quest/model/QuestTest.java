package com.javarush.quest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuestTest {
    private Quest quest;
    private QuestStep step1;
    private QuestStep step2;

    @BeforeEach
    void setUp() {
        quest = new Quest("Test Title", "Test Prolog");
        Map<Integer, QuestStepOption> options1 = new HashMap<>();
        options1.put(1, new QuestStepOption("Option 1", "step2"));
        options1.put(2, new QuestStepOption("Option 2", "game_over"));

        step1 = new QuestStep("step1", "First step text", options1);
        step2 = new QuestStep("step2", "Second step text", null);

        Map<String, QuestStep> steps = new HashMap<>();
        steps.put("step1", step1);
        steps.put("step2", step2);

        quest.setSteps(steps);
    }

    @Test
    @DisplayName("Тест создания квеста")
    void testQuestCreation() {
        assertEquals("Test Title", quest.getTitle());
        assertEquals("Test Prolog", quest.getProlog());
        assertEquals(2, quest.getSteps().size());
    }
}