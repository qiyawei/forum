package com.kaishengit.web;

import com.kaishengit.entity.Topic;
import com.kaishengit.entity.TopicType;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index.do")
public class IndexServlet extends BasicServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       TopicService topicService = new TopicService();
        String typeid = req.getParameter("typeid");
        String pageNo = req.getParameter("p");
        List<TopicType> topicTypeList = topicService.findAllType();
        Page<Topic> page = topicService.findTopicAndUserAndTopicType(typeid,pageNo);
        req.setAttribute("page",page);
        req.setAttribute("typeList",topicTypeList);
        BasicServlet.forward(req,resp,"index");
    }
}
