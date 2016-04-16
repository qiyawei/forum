package com.kaishengit.web.topic;

import com.kaishengit.dao.FavDao;
import com.kaishengit.entity.Fav;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.service.TopicService;
import com.kaishengit.web.BasicServlet;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by qiyawei on 2016/4/11.
 */
@WebServlet("/topic/view.do")
public class ViewTopicServlet extends BasicServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(StringUtils.isNumeric(id)){
            //Topic topic = new TopicService().findTopicById(Integer.valueOf(id));
            Topic topic = new TopicService().findTopicAndUserAndTopicTypeById(Integer.valueOf(id));
            User user = getSesssionUser(req);
            Fav fav = null;
            if(user != null){
                fav = new FavDao().findByUidAndTopicid(user.getId(),Integer.valueOf(id));
                if(fav  != null){
                    req.setAttribute("action","fav");
                }
            }



            req.setAttribute("topic", topic);
            forward(req, resp, "topic/view");

        }else{
            resp.sendError(400);
        }
    }
}
