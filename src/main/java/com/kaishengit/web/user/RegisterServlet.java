package com.kaishengit.web.user;

import com.google.gson.Gson;
import com.kaishengit.exception.ValidateException;
import com.kaishengit.service.UserService;
import com.kaishengit.web.BasicServlet;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiyawei on 2016/4/7.
 */
@WebServlet("/register.do")
public class RegisterServlet extends BasicServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasicServlet.forward(req,resp,"user/register");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Map<String,Object>  map = new HashMap<>();

        if(StringUtils.isNotEmpty(username)&&StringUtils.isNotEmpty(password)&&StringUtils.isNotEmpty(email)){
            try {
                new UserService().saveUser(username,password,email);
                map.put("state","success");
            }catch (ValidateException ex){
                map.put("state","error");
                map.put("message",ex.getMessage());
            }

        }else {
            map.put("state","error");
            map.put("message","表单内容不正确");
        }
        BasicServlet.responseClient(resp,map);
    }
}
