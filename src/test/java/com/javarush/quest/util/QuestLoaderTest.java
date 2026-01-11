package com.javarush.quest.util;

import com.javarush.quest.model.Quest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestLoaderTest {
    private final String resourcePath = "test.json";

    @Test
    void loadQuestFromResource_shouldReturnQuest_whenResourceExists() throws Exception {
        Quest result = QuestLoader.loadQuestFromResource(resourcePath);

        assertNotNull(result);
        assertEquals("Test Title", result.getTitle());
        assertEquals("Test Prolog", result.getProlog());
        assertNotNull(result.getSteps());
        assertEquals(3, result.getSteps().size());
        assertNotNull(result.getSteps().get("start"));
        assertEquals("start", result.getSteps().get("start").getId());
        assertEquals("Start text", result.getSteps().get("start").getText());
        assertNotNull(result.getSteps().get("start").getOptions());
        assertEquals(2, result.getSteps().get("start").getOptions().size());
        assertNotNull(result.getSteps().get("start").getOptions().get(1));
        assertEquals("Option 1", result.getSteps().get("start").getOptions().get(1).getText());
        assertEquals("next", result.getSteps().get("start").getOptions().get(1).getNextStepId());
        assertNotNull(result.getSteps().get("game_over_win"));
        assertEquals("game_over_win", result.getSteps().get("game_over_win").getId());
        assertEquals("Game Over", result.getSteps().get("game_over_win").getText());
        assertNull(result.getSteps().get("game_over_win").getOptions());
    }

    @Test
    void testLoadQuestFromResourceNotFound() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            QuestLoader.loadQuestFromResource("non_existent.json");
        });
        assertTrue(exception.getMessage().contains("Resource not found"));
    }
}