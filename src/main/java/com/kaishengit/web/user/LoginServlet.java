package com.kaishengit.web.user;

import com.kaishengit.entity.User;
import com.kaishengit.service.UserService;
import com.kaishengit.util.PatchcaUtil;
import com.kaishengit.web.BasicServlet;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiyawei on 2016/4/7.
 */
@WebServlet("/login.do")
public class LoginServlet extends BasicServlet {
    private String lastName;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasicServlet.forward(req,resp,"user/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Object> map = new HashMap<>();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String ip = req.getRemoteAddr();
        HttpSession session = req.getSession();


        if(!username.equals(lastName)){
            PatchcaUtil.put("patchca",0);
            lastName = username;
        }

        int pwdErrorNo = PatchcaUtil.get("patchca");


        if(StringUtils.isNotEmpty(username)&&StringUtils.isNotEmpty(password)){
            User user = new UserService().login(username,password,ip);
            if(user == null){
                pwdErrorNo++;
                PatchcaUtil.put("patchca",pwdErrorNo);
                map.put("state","error");
                map.put("num",pwdErrorNo);
                map.put("message","账号或者密码错误");
            }else{
                PatchcaUtil.put("patchca",0);
                session.setAttribute("user",user);
                resp.sendRedirect("/index.do");
            }
        }else{
            map.put("state","error");
            map.put("message","表单参数错误");
        }
        BasicServlet.responseClient(resp,map);
    }
}
