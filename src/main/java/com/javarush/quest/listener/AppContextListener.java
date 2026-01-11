package com.javarush.quest.listener;

import com.javarush.quest.service.QuestService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        ServletContext context = contextEvent.getServletContext();
        context.setAttribute("questService", new QuestService());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
