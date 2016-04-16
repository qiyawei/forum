package com.kaishengit.web.user;

import com.kaishengit.entity.User;
import com.kaishengit.service.UserService;
import com.kaishengit.web.BasicServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiyawei on 2016/4/9.
 */
@WebServlet("/user/changeEmail.do")
public class ChangeEmailServlet extends BasicServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        Map<String,Object> map = new HashMap<>();
        if(email != null){
            User user = BasicServlet.getSesssionUser(req);
            user.setEmail(email);
            new UserService().updateUser(user);

            map.put("state","success");
        }
        BasicServlet.responseClient(resp,map);
    }
}
