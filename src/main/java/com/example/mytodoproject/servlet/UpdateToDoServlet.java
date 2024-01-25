package com.example.mytodoproject.servlet;

import com.example.mytodoproject.manager.ToDoManager;
import com.example.mytodoproject.model.Status;
import com.example.mytodoproject.model.ToDo;
import com.example.mytodoproject.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns = "/update")
public class UpdateToDoServlet extends HttpServlet {
    private final ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ToDo toDoById = toDoManager.getToDoById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("toDoById", toDoById);
        req.getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            toDoManager.updateToDo(ToDo.builder()
                    .id(Integer.parseInt(req.getParameter("toDoId")))
                    .title(req.getParameter("title"))
                    .finishDate(DateUtil.stringToDate(req.getParameter("finishDate")))
                    .status(Status.valueOf(req.getParameter("statusName")))
                    .build());
            resp.sendRedirect("/home");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }}
