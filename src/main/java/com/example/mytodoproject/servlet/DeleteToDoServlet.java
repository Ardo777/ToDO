package com.example.mytodoproject.servlet;

import com.example.mytodoproject.manager.ToDoManager;
import com.example.mytodoproject.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete")
public class DeleteToDoServlet extends HomeServlet{
    private final ToDoManager toDoManager=new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String toDoId = req.getParameter("toDoId");
        User user = (User) req.getSession().getAttribute("user");
        toDoManager.deleteToDo(Integer.parseInt(toDoId));
        resp.sendRedirect("/home");
    }
}
