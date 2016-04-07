package com.kaishengit.web;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BasicServlet extends HttpServlet {
    public static void forward(HttpServletRequest req,HttpServletResponse resp,String viewPath) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/" + viewPath+ ".jsp").forward(req,resp);
    }

    public static void responseClient(HttpServletResponse response,Object result) throws IOException {
        response.setContentType("application/plain;charset=UTF-8");
        result = new Gson().toJson(result);
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
    public static void validateClient(HttpServletResponse response,Object result) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        result = new Gson().toJson(result);
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
}
