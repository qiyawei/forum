package com.kaishengit.web.topic;

import com.google.common.collect.Maps;
import com.kaishengit.entity.Comment;
import com.kaishengit.entity.Topic;
import com.kaishengit.service.TopicService;
import com.kaishengit.web.BasicServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by qiyawei on 2016/4/12.
 */
@WebServlet("/topic/comment/load.do")
public class LoadCommentServlet extends BasicServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(isAjaxRequest(req)) {
            TopicService topicService = new TopicService();

            Map<String,Object> result = Maps.newHashMap();
            String topicId = req.getParameter("topicId");

            if(StringUtils.isNumeric(topicId)) {
                Topic topic = topicService.findTopicById(Integer.valueOf(topicId));
                if(topic == null) {
                    result.put("state","error");
                    result.put("message","参数错误");
                } else {
                    List<Comment> commentList = topicService.findAllCommentByTopicId(Integer.valueOf(topicId));
                    result.put("state","success");
                    result.put("data",commentList);
                }


            } else {
                result.put("state","error");
                result.put("message","参数错误");
            }


           responseClient(resp,result);
        }

    }

}
