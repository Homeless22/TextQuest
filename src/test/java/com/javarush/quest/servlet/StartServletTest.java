package com.javarush.quest.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StartServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    private StartServlet startServlet;

    @BeforeEach
    void setUp() {
        startServlet = new StartServlet();
    }

    @Test
    void testDoGet() throws ServletException, IOException {
        when(request.getRequestDispatcher("/index.jsp")).thenReturn(requestDispatcher);
        startServlet.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoPostWithValidName() throws ServletException, IOException {
        when(request.getParameter("playerName")).thenReturn("TestPlayer");
        when(request.getContextPath()).thenReturn("/TextQuest");
        when(request.getSession()).thenReturn(session);

        startServlet.doPost(request, response);

        verify(session).setAttribute("playerName", "TestPlayer");
        verify(session).setAttribute("gamesPlayed", 0);
        verify(response).sendRedirect("/TextQuest/game?step=start");
    }

    @Test
    void testDoPostWithEmptyName() throws ServletException, IOException {
        when(request.getParameter("playerName")).thenReturn("");
        when(request.getContextPath()).thenReturn("/TextQuest");
        when(request.getSession()).thenReturn(session);
        startServlet.doPost(request, response);
        verify(session).setAttribute("playerName", "Искатель приключений");
        verify(session).setAttribute("gamesPlayed", 0);
        verify(response).sendRedirect("/TextQuest/game?step=start");
    }

    @Test
    void testDoPostWithNullName() throws ServletException, IOException {
        when(request.getParameter("playerName")).thenReturn(null);
        when(request.getContextPath()).thenReturn("/TextQuest");
        when(request.getSession()).thenReturn(session);

        startServlet.doPost(request, response);

        verify(session).setAttribute("playerName", "Искатель приключений");
        verify(session).setAttribute("gamesPlayed", 0);
        verify(response).sendRedirect("/TextQuest/game?step=start");
    }

    @Test
    void testDoPostWithWhitespaceName() throws ServletException, IOException {
        when(request.getParameter("playerName")).thenReturn("   ");
        when(request.getContextPath()).thenReturn("/TextQuest");
        when(request.getSession()).thenReturn(session);

        startServlet.doPost(request, response);

        verify(session).setAttribute("playerName", "Искатель приключений");
        verify(session).setAttribute("gamesPlayed", 0);
        verify(response).sendRedirect("/TextQuest/game?step=start");
    }
}