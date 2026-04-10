package com.javarush.quest.servlet;

import com.javarush.quest.service.QuestService;

import javax.servlet.http.HttpServlet;

public class BaseServlet extends HttpServlet {
    protected QuestService getQuestService() {
        return (QuestService) getServletContext().getAttribute("questService");
    }
}
