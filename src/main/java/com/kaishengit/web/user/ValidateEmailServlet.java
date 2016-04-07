package com.kaishengit.web.user;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.web.BasicServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by qiyawei on 2016/4/7.
 */
@WebServlet("/validate/email.do")
public class ValidateEmailServlet extends BasicServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = new UserDao().findByEmail(email);
        System.out.println(email + "   "+user);
        String result;
        if(user == null){
            result = "true";
        }else{
            result = "false";
        }
        BasicServlet.validateClient(resp,result);
    }
}
