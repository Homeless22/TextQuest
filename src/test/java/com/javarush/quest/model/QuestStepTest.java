package com.javarush.quest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuestStepTest {
    private QuestStep questStep;
    private Map<Integer, QuestStepOption> options;

    @BeforeEach
    void setUp() {
        options = new HashMap<>();
        options.put(1, new QuestStepOption("Option 1", "nextStep1"));
        options.put(2, new QuestStepOption("Option 2", "nextStep2"));
        questStep = new QuestStep("testId", "Test Text", options);
    }

    @Test
    void testQuestStepCreation() {
        assertEquals("testId", questStep.getId());
        assertEquals("Test Text", questStep.getText());
        assertEquals(2, questStep.getOptions().size());
        assertEquals("Option 1", questStep.getOptions().get(1).getText());
        assertEquals("Option 2", questStep.getOptions().get(2).getText());
    }

    @Test
    void testGetNextStepIdByOptionId() {
        assertEquals("nextStep1", questStep.getNextStepIdByOptionId(1));
        assertEquals("nextStep2", questStep.getNextStepIdByOptionId(2));
        assertNull(questStep.getNextStepIdByOptionId(3)); // Несуществующий вариант
    }

    @Test
    void testGetNextStepIdByOptionIdWithNullOptions() {
        QuestStep emptyStep = new QuestStep("empty", "Empty", null);
        assertNull(emptyStep.getNextStepIdByOptionId(1));
    }

    @Test
    void testGetNextStepIdByOptionIdWithEmptyOptions() {
        QuestStep emptyStep = new QuestStep("empty", "Empty", new HashMap<>());
        assertNull(emptyStep.getNextStepIdByOptionId(1));
    }
}