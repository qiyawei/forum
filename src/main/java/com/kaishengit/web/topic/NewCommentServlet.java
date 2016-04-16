package com.kaishengit.web.topic;

import com.kaishengit.entity.Comment;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.service.TopicService;
import com.kaishengit.web.BasicServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiyawei on 2016/4/12.
 */
@WebServlet("/topic/comment/new.do")
public class NewCommentServlet extends BasicServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid = req.getParameter("topicid");
        String text  = req.getParameter("text");
        Map<String,Object> map = new HashMap<>();
        TopicService topicService = new TopicService();

        if(isAjaxRequest(req)){
            if(!StringUtils.isNumeric(topicid)||StringUtils.isEmpty(text)){
                map.put("state","error");
                map.put("message","参数错误");
            }else{
                Topic topic = topicService.findTopicById(Integer.valueOf(topicid));
                User user = getSesssionUser(req);
                if(topic != null&&user!=null){
                    Comment comment = topicService.saveComment(topic,text,user);
                    map.put("state","success");
                }else{
                    map.put("state","error");
                    map.put("message","参数错误");
                }
            }
            responseClient(resp,map);
        }
    }
}
