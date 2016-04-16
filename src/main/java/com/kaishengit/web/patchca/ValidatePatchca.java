package com.kaishengit.web.patchca;

import com.kaishengit.web.BasicServlet;

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
@WebServlet("/validatePatchca.do")
public class ValidatePatchca extends BasicServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String patchca = req.getParameter("patchca");
        String result;
        if(patchca.equals(req.getSession().getAttribute("patchca"))){
            result = "true";
        }else {
            result = "false";
        }
        BasicServlet.validateClient(resp,result);
    }
}
