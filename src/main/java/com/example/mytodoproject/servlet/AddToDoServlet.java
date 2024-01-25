package com.example.mytodoproject.servlet;

import com.example.mytodoproject.manager.ToDoManager;
import com.example.mytodoproject.model.Status;
import com.example.mytodoproject.model.ToDo;
import com.example.mytodoproject.model.User;
import com.example.mytodoproject.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@WebServlet(urlPatterns = "/addToDo")
public class AddToDoServlet extends HttpServlet {
    ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            toDoManager.addToDo(ToDo.builder()
                    .title(req.getParameter("title"))
                    .createdDate(new Date())
                    .finishDate(DateUtil.stringToDate(req.getParameter("finishDate")))
                    .user((User) req.getSession().getAttribute("user"))
                    .status(Status.NEW)
                    .build());
            resp.sendRedirect("/home");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
