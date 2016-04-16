package com.kaishengit.web.user;

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
 * Created by qiyawei on 2016/4/8.
 */
@WebServlet("/forget/newpassword.do")
public class ForgetNewPassword extends BasicServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        if(StringUtils.isNotEmpty(token)){
            try{
                new UserService().validateToken(token);
                req.setAttribute("token",token);
                BasicServlet.forward(req,resp,"/user/forgetNewPassword");
            }catch(ValidateException ex){
                req.setAttribute("message",ex.getMessage());
                forward(req,resp,"user/tokenerror");
            }

        }else{
            resp.sendError(400);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String token = req.getParameter("token");
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isEmpty(token)||StringUtils.isEmpty(password)){
            map.put("state","error");
            map.put("message","表单填写错误");
        }else{
            try{
                new UserService().saveForgetPassword(password,token);
                map.put("state", "success");
            }catch (ValidateException ex){
                map.put("state","error");
                map.put("message",ex.getMessage());
            }

        }
        BasicServlet.responseClient(resp,map);
    }
}
