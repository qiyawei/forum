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
@WebServlet("/user/changeImg.do")
public class ChangeImgServlet extends BasicServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(BasicServlet.isAjaxRequest(req)){
            String key = req.getParameter("key");
            User user = BasicServlet.getSesssionUser(req);
            user.setImg(key);
            new UserService().updateUser(user);
            Map<String,Object> map = new HashMap<>();
            map.put("state","success");
            BasicServlet.responseClient(resp,map);
        }
    }
}
