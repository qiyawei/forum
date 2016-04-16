package com.kaishengit.web.user;

import com.kaishengit.util.ConfigProp;
import com.kaishengit.web.BasicServlet;
import com.qiniu.util.Auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by qiyawei on 2016/4/9.
 */
@WebServlet("/user/setting.do")
public class SettingServlet extends BasicServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Auth auth = Auth.create(ConfigProp.get("ak"),ConfigProp.get("sk"));
        String token = auth.uploadToken(ConfigProp.get("bucket"));
        req.setAttribute("token",token);
        forward(req,resp,"user/setting");
    }
}
