package com.example.mytodoproject.servlet;

import com.example.mytodoproject.manager.ToDoManager;
import com.example.mytodoproject.model.ToDo;
import com.example.mytodoproject.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    private final ToDoManager  TO_DO_MANAGER = new ToDoManager();
    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<ToDo> toDoByUser = TO_DO_MANAGER.getToDoByUser(user.getId());
        req.setAttribute("todos", toDoByUser);
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req,resp);
    }
}
