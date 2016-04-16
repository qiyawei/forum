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
@WebServlet("/validate/username.do")
public class ValidateUsernameServlet extends BasicServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        username = new String(username.getBytes("ISO8859-1"),"UTF-8");
        User user = new UserDao().findByUsername(username);
        String from = req.getParameter("from");
        String result;
        if(user == null){
            if(from != null){
                result = "false";
            }else{
                result = "true";
            }

        }else{
            if(from != null){
                result = "true";
            }else{
                result = "false";
            }

        }
        BasicServlet.validateClient(resp,result);
    }
}
