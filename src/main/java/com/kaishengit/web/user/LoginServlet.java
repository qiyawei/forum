package com.kaishengit.web.user;

import com.kaishengit.web.BasicServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by qiyawei on 2016/4/7.
 */
@WebServlet("/login.do")
public class LoginServlet extends BasicServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasicServlet.forward(req,resp,"user/login");
    }
}
