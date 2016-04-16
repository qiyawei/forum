package com.kaishengit.web.topic;

import com.kaishengit.entity.TopicType;
import com.kaishengit.service.TopicService;
import com.kaishengit.web.BasicServlet;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by qiyawei on 2016/4/11.
 */
@WebServlet("/topic/new.do")
public class NewTopicServlet extends BasicServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TopicType> typeList = new TopicService().findAllType();
        req.setAttribute("typeList",typeList);
        BasicServlet.forward(req,resp,"topic/new");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String node = req.getParameter("node");

        //System.out.println(title);

        if(StringUtils.isEmpty(title)||StringUtils.isEmpty(node)){
            resp.sendError(400,"参数错误");
        }else{
            Integer id = new TopicService().saveTopic(title, content, node, BasicServlet.getSesssionUser(req));
            resp.sendRedirect("/topic/view.do?id=" + id);
        }
    }
}
