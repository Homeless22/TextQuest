package com.javarush.quest.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.model.Quest;

import java.io.InputStream;

public class QuestLoader {
    private QuestLoader() {}

    public static Quest loadQuestFromResource(String resourcePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream = QuestLoader.class.getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new RuntimeException("Resource not found: " + resourcePath);
        }
        return mapper.readValue(inputStream, Quest.class);
    }
}