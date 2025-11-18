package org.daw.firstweb.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.daw.firstweb.dao.UserOrmDao;
import org.daw.firstweb.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String password = "123456";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
        System.out.println(hashed);
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = UserOrmDao.findByUsername(username);

        String hashedPassword = user.getPassword();

        if (!BCrypt.checkpw(password, hashedPassword)) {
            resp.sendRedirect("login.jsp");
            return;
        }
        HttpSession session = req.getSession();

        session.setAttribute("username",username);
        session.setMaxInactiveInterval(60);

        resp.sendRedirect("movies");
    }
}
