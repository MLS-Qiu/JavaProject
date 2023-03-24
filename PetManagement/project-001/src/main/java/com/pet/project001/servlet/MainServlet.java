package com.pet.project001.servlet;

import com.pet.project001.global.Global;
import com.pet.project001.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Global.USER_LOGIN_KEY);
        if (user != null) {
            req.getSession().setAttribute("user", user);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/main/main.jsp").forward(req, resp);
    }
}
