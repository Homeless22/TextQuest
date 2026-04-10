package com.javarush.quest.servlet;

import com.javarush.quest.model.QuestStep;
import com.javarush.quest.service.QuestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stepId = request.getParameter("step");
        if (stepId == null || stepId.isEmpty()) {
            stepId = "start";
        }

        QuestService questService = getQuestService();
        QuestStep step = questService.getStep(stepId);
        if (step == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Шаг не найден!");
        }

        request.setAttribute("step", step);
        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        // Получаем параметры
        String answer = request.getParameter("answer");
        String currentStepId = request.getParameter("currentStep");

        // Валидация
        if (answer == null || currentStepId == null) {
            response.sendRedirect(request.getContextPath() + "/game?step=start");
            return;
        }

        QuestService questService = getQuestService();
        QuestStep currentStep = questService.getStep(currentStepId);
        if (currentStep == null) {
            response.sendRedirect(request.getContextPath() + "/game?step=start");
            return;
        }

        String nextStepId = currentStep.getNextStepIdByOptionId(Integer.parseInt(answer));

        if (questService.isFinalStep(nextStepId)) {
            Integer gamesPlayed = (Integer) session.getAttribute("gamesPlayed");
            if (gamesPlayed == null) {
                gamesPlayed = 0;
            }
            session.setAttribute("gamesPlayed", gamesPlayed + 1);
        }
        // на след шаг
        response.sendRedirect(request.getContextPath() + "/game?step=" + nextStepId);
    }

}
