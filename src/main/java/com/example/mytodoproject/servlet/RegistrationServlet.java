package com.example.mytodoproject.servlet;

import com.example.mytodoproject.manager.UserManager;
import com.example.mytodoproject.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmationPassword = req.getParameter("confirmationPassword");


        if (password.length() < 5) {
            req.getSession().setAttribute("msg", "Password cannot be shorter than 5 characters");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
        if (!password.equals(confirmationPassword)) {
            req.getSession().setAttribute("msg", "Wrong confirmation password");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }

        if (userManager.getUserByEmail(email) != null) {
            req.getSession().setAttribute("msg", "User with this email already exists");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        } else {

            userManager.add(User.builder()
                    .username(username)
                    .email(email)
                    .password(password)
                    .build());
            req.getSession().setAttribute("user", userManager.getUserByEmail(email));
            resp.sendRedirect("/home");

        }
    }

}
