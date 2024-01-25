package com.example.mytodoproject.servlet;

import com.example.mytodoproject.manager.ToDoManager;
import com.example.mytodoproject.model.ToDo;
import com.example.mytodoproject.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToDoByDateServlet {
    private ToDoManager toDoManager = new ToDoManager();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<ToDo> toDos = toDoManager.getToDoByDate(user.getId());
        req.setAttribute("todosSort", toDos);
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }
}
