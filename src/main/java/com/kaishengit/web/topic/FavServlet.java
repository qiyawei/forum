package com.kaishengit.web.topic;

import com.kaishengit.dao.TopicDao;
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
@WebServlet("/topic/fav.do")
public class FavServlet extends BasicServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String topicid = req.getParameter("topicid");
        User user = getSesssionUser(req);
        Map<String,Object> map = new HashMap<>();
        if(isAjaxRequest(req)){
            if(!StringUtils.isNumeric(topicid)||(!"fav".equals(action)&&!"unfav".equals(action))||user == null){
                map.put("state","error");
                map.put("message","参数错误");
            }else{
                Topic topic = new TopicDao().findById(Integer.valueOf(topicid));
                if(topic == null){
                    map.put("state","error");
                    map.put("message","参数错误");
                }else{
                    try {
                        new TopicService().saveOrDelFav(topic,user,action);
                        map.put("state","success");
                    }catch(Exception ex){
                        map.put("state","error");
                        map.put("message",ex.getMessage());
                    }

                }
            }
        }
        responseClient(resp,map);
    }
}
