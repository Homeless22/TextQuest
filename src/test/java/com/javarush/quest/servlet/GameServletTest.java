package com.javarush.quest.servlet;

import com.javarush.quest.model.QuestStep;
import com.javarush.quest.service.QuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private ServletConfig servletConfig;

    @Mock
    private ServletContext servletContext;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private QuestService questService;

    @Mock
    private QuestStep questStep;

    private GameServlet gameServlet;

    @BeforeEach
    void setUp() throws ServletException {
        gameServlet = new GameServlet();
        gameServlet.init(servletConfig);
    }

    @Test
    void testDoGetWithStepParameter() throws ServletException, IOException, IllegalAccessException, NoSuchFieldException {
        when(gameServlet.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("questService")).thenReturn(questService);
        when(request.getParameter("step")).thenReturn("testStep");
        when(questService.getStep("testStep")).thenReturn(questStep);
        when(request.getRequestDispatcher("/game.jsp")).thenReturn(requestDispatcher);

        gameServlet.doGet(request, response);

        verify(request).setAttribute("step", questStep);
        verify(requestDispatcher).forward(request, response);

        String stepId = request.getParameter("step");
        if (stepId == null || stepId.isEmpty()) {
            stepId = "start";
        }
    }

    @Test
    void testDoGetWithoutStepParameter() throws ServletException, IOException, NoSuchFieldException, IllegalAccessException {
        when(gameServlet.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("questService")).thenReturn(questService);
        when(request.getParameter("step")).thenReturn(null);
        when(questService.getStep("start")).thenReturn(questStep);
        when(request.getRequestDispatcher("/game.jsp")).thenReturn(requestDispatcher);

        gameServlet.doGet(request, response);

        verify(questService).getStep("start");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoGetWithEmptyStepParameter() throws ServletException, IOException, NoSuchFieldException, IllegalAccessException {
        when(gameServlet.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("questService")).thenReturn(questService);
        when(request.getParameter("step")).thenReturn("");
        when(questService.getStep("start")).thenReturn(questStep);
        when(request.getRequestDispatcher("/game.jsp")).thenReturn(requestDispatcher);

        gameServlet.doGet(request, response);

        verify(questService).getStep("start");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoGetStepNotFound() throws ServletException, IOException, NoSuchFieldException, IllegalAccessException {
        when(gameServlet.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("questService")).thenReturn(questService);
        when(request.getParameter("step")).thenReturn("nonExistent");
        when(questService.getStep("nonExistent")).thenReturn(null);
        when(request.getRequestDispatcher("/game.jsp")).thenReturn(requestDispatcher);

        gameServlet.doGet(request, response);

        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, "Шаг не найден!");
    }

    @Test
    void testDoPostValidAnswer() throws ServletException, IOException, NoSuchFieldException, IllegalAccessException {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("answer")).thenReturn("1");
        when(request.getParameter("currentStep")).thenReturn("current");
        when(gameServlet.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("questService")).thenReturn(questService);
        when(request.getContextPath()).thenReturn("/TextQuest");

        when(questService.getStep("current")).thenReturn(questStep);
        when(questStep.getNextStepIdByOptionId(1)).thenReturn("nextStep");
        when(questService.isFinalStep("nextStep")).thenReturn(true);
        when(session.getAttribute("gamesPlayed")).thenReturn(0);

        gameServlet.doPost(request, response);

        verify(session).setAttribute("gamesPlayed", 1);
        verify(response).sendRedirect("/TextQuest/game?step=nextStep");
    }

    @Test
    void testDoPostWithNullAnswer() throws ServletException, IOException {
        when(request.getParameter("answer")).thenReturn(null);
        when(request.getParameter("currentStep")).thenReturn("current");
        when(request.getContextPath()).thenReturn("/TextQuest");
        when(request.getSession()).thenReturn(session);

        gameServlet.doPost(request, response);

        verify(response).sendRedirect("/TextQuest/game?step=start");
    }

    @Test
    void testDoPostWithNullCurrentStep() throws ServletException, IOException {
        when(request.getParameter("answer")).thenReturn("1");
        when(request.getParameter("currentStep")).thenReturn(null);
        when(request.getContextPath()).thenReturn("/TextQuest");

        gameServlet.doPost(request, response);

        verify(response).sendRedirect("/TextQuest/game?step=start");
    }

    @Test
    void testDoPostWithNonExistentStep() throws ServletException, IOException, NoSuchFieldException, IllegalAccessException {
        when(gameServlet.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("questService")).thenReturn(questService);
        when(request.getParameter("answer")).thenReturn("1");
        when(request.getParameter("currentStep")).thenReturn("nonExistent");
        when(request.getContextPath()).thenReturn("/TextQuest");
        when(questService.getStep("nonExistent")).thenReturn(null);

        gameServlet.doPost(request, response);

        verify(response).sendRedirect("/TextQuest/game?step=start");
    }
}