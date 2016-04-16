package com.kaishengit.service;

import com.kaishengit.dao.CommentDao;
import com.kaishengit.dao.FavDao;
import com.kaishengit.dao.TopicDao;
import com.kaishengit.dao.TopicTypeDao;
import com.kaishengit.entity.*;
import com.kaishengit.exception.ValidateException;
import com.kaishengit.util.Page;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by qiyawei on 2016/4/11.
 */
public class TopicService {
    private TopicDao topicDao = new TopicDao();
    private TopicTypeDao typeDao = new TopicTypeDao();
    private CommentDao commentDao = new CommentDao();
    private FavDao favDao = new FavDao();
    public List<TopicType> findAllType() {
        return typeDao.findAll();
    }

    public Integer saveTopic(String title, String content, String node, User user) {
        System.out.println(title);
        Topic topic = new Topic();
        String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        topic.setContent(content);
        topic.setCreatetime(now);
        topic.setTitle(title);
        topic.setTypeid(Integer.valueOf(node));
        topic.setUid(user.getId());
        topic.setLikenum(0);
        topic.setReplytime(now);
        topic.setFavnum(0);
        topic.setReplynum(0);
        topic.setViewnum(0);

        return topicDao.save(topic);


    }

    public Topic findTopicById(Integer id) {
        return topicDao.findById(id);
    }

    public Topic findTopicAndUserAndTopicTypeById(Integer id) {
        Topic topic = topicDao. findTopicAndUserAndTopicTypeById(id);
        if(topic != null){
            topic.setViewnum(topic.getViewnum() + 1);
            topicDao.update(topic);
        }else{
            throw new ValidateException("你要查看的内容不存在或已被删除");
        }

        return topic;
    }

    public Page<Topic> findTopicAndUserAndTopicType(String typeid, String pageNo) {
        int pageSize = 3;
        Page<Topic> page;
        //System.out.println(typeid);
        if(StringUtils.isNumeric(typeid)&&StringUtils.isNotEmpty(typeid)){
            int count = TopicDao.count(Integer.valueOf(typeid));
            page = new Page<Topic>(pageNo,count,pageSize);
            List<Topic> list = topicDao.findByTypeAndPage(Integer.valueOf(typeid),page.getStart(), page.getSize());
            page.setItems(list);
        }else{
            int count = TopicDao.count();
            page = new Page<Topic>(pageNo,count,pageSize);
            List<Topic> list = topicDao.findByPage(page.getStart(),page.getSize());
            page.setItems(list);

        }
        return page;
    }

    public Comment saveComment(Topic topic, String text, User user) {

        Comment comment = new Comment();
        comment.setTopicid(topic.getId());
        comment.setText(text);
        comment.setUid(user.getId());
        comment.setUser(user);
        comment.setCreatetime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        Integer id = commentDao.save(comment);
        comment.setId(id);
        topic.setReplynum(topic.getReplynum()+1);
        System.out.println(text);
        topicDao.update(topic);
        return comment;

    }

    public List<Comment> findAllCommentByTopicId(Integer topicid) {

        List<Comment> commentList = commentDao.findAllByTopicId(topicid);
        return commentList;
    }

    public void saveOrDelFav(Topic topic, User user, String action) {
        Fav fav = favDao.findByUidAndTopicid(user.getId(),topic.getId());
        if("fav".equals(action)){
            if(fav == null){
                fav = new Fav();
                fav.setUid(user.getId());
                fav.setTopicid(topic.getId());
                fav.setFavtime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                favDao.save(fav);
                topic.setFavnum(topic.getFavnum()+1);
                topicDao.update(topic);
            }

        }else{
            if(fav == null){
                throw new ValidateException("参数错误");
            }else{
                favDao.delByTopicid(fav.getId());
                topic.setFavnum(topic.getFavnum()-1);
                topicDao.update(topic);
            }
        }
    }
}
