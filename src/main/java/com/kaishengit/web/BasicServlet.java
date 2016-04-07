package com.kaishengit.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BasicServlet extends HttpServlet {
    public static void forward(HttpServletRequest req,HttpServletResponse resp,String viewPath) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/" + viewPath+ ".jsp").forward(req,resp);
    }
}
