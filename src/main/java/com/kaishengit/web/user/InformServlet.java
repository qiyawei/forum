package com.kaishengit.web.user;

import com.kaishengit.dao.CommentDao;
import com.kaishengit.entity.User;
import com.kaishengit.util.PatchcaUtil;
import com.kaishengit.util.TimeUtil;
import com.kaishengit.web.BasicServlet;
import org.joda.time.DateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiyawei on 2016/4/13.
 */
@WebServlet("/user/inform.do")
public class InformServlet extends BasicServlet{
    //Integer lastnum = 0;
    private String time = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentDao commentDao = new CommentDao();
        User user = getSesssionUser(req);
        String inttime = TimeUtil.get(user);
        //System.out.println(inttime);
        if(inttime == null){
            inttime = time;
        }
        List<Integer> topicidList =  commentDao.findByCreatetime(user,inttime);
        responseClient(resp,topicidList);

       /* int respnum;
        int nextNum = commentDao.num(user.getId());
        if(lastnum == 0){
            lastnum = nextNum;
        }else {
            if(nextNum != lastnum){
                respnum = nextNum-lastnum;
                //lastnum = nextNum;
                //String time = DateTime.now().minusMillis(60).toString("yyyy-MM-dd HH:mm:ss");
                topicidList = commentDao.findByCreatetime(user,time);

            }
        }*/

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String newtime =  DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
       // System.out.println(newtime);
        User user = getSesssionUser(req);
        TimeUtil.put(user,newtime);
    }
}
