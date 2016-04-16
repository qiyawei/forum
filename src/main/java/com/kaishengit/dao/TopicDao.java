package com.kaishengit.dao;

import com.kaishengit.entity.Topic;
import com.kaishengit.entity.TopicType;
import com.kaishengit.entity.User;
import com.kaishengit.util.DpHelper;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.annotation.processing.Processor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiyawei on 2016/4/11.
 */
public class TopicDao {
    public Integer save(Topic topic) {
        String sql = "insert into t_topic(uid,typeid,title,content,createtime,replytime,replynum,likenum,favnum,viewnum)" +
                "values(?,?,?,?,?,?,?,?,?,?)";
        return DpHelper.insert(sql,topic.getUid(),topic.getTypeid(),topic.getTitle(),topic.getContent(),topic.getCreatetime(),
                topic.getReplytime(),topic.getReplynum(),topic.getLikenum(),topic.getFavnum(),topic.getViewnum()).intValue();
    }

    public Topic findById(Integer id) {
        String sql = "select *from t_topic where id=?";
        return DpHelper.query(sql,new BeanHandler<Topic>(Topic.class),id);
    }

    public Topic findTopicAndUserAndTopicTypeById(Integer id) {
        String sql = "select t_topic.*,t_user.username,t_user.img,t_topicType.typename from t_topic" +
                " inner join t_user on t_topic.uid = t_user.id" +
                " inner join t_topicType on t_topic.typeid = t_topicType.id" +
                " where t_topic.id = ?";
        return DpHelper.query(sql, new ResultSetHandler<Topic>() {
            @Override
            public Topic handle(ResultSet resultSet) throws SQLException {
                if(resultSet.next()){

                    RowProcessor rowProcessor = new BasicRowProcessor();
                    Topic topic = rowProcessor.toBean(resultSet, Topic.class);
                    User user = rowProcessor.toBean(resultSet, User.class);
                    TopicType topicType = rowProcessor.toBean(resultSet, TopicType.class);
                    topic.setUser(user);
                    topic.setTopicType(topicType);
                    return topic;
                }
                return null;
            }
        },id);
    }

    public List<Topic> findByTypeAndPage(Integer typeid, int start, int size) {
        String sql = "select t_topic.*,t_user.username,t_user.img from t_topic" +
                     "  inner join t_user on t_topic.uid = t_user.id" +
                     "  where t_topic.typeid = ?" +
                     "  order by replytime desc limit ?,?";
        return DpHelper.query(sql,new TopicHandler(),typeid,start,size);
    }

    public static Integer count(Integer typeid) {
        String sql = "select count(*) from t_topic where typeid=?";
        return DpHelper.query(sql,new ScalarHandler<Long>(),typeid).intValue();

    }
    public static Integer count() {
        String sql = "select count(*) from t_topic";
        return DpHelper.query(sql,new ScalarHandler<Long>()).intValue();

    }

    public List<Topic> findByPage(int start, int size) {
        String sql = "select t_topic.*,t_user.username,t_user.img from t_topic" +
                "  inner join t_user on t_topic.uid = t_user.id" +
                "  order by replytime desc limit ?,?";

        return DpHelper.query(sql,new TopicHandler(),start,size);

    }

    public void update(Topic topic) {
        String sql = "update t_topic set title = ?,content=?,typeid=?,viewnum=?,favnum=?,likenum=?,replynum=?,replytime=? where id = ?";
        DpHelper.update(sql,topic.getTitle(),topic.getContent(),topic.getTypeid(),topic.getViewnum(),topic.getFavnum(),topic.getLikenum(),topic.getReplynum(),topic.getReplytime(),topic.getId());
    }

    private class TopicHandler implements ResultSetHandler<List<Topic>>{

        @Override
        public List<Topic> handle(ResultSet resultSet) throws SQLException {
            RowProcessor rowProcessor = new BasicRowProcessor();

            List<Topic> topicList = new ArrayList<>();
            while (resultSet.next()){
                Topic topic = rowProcessor.toBean(resultSet, Topic.class);
                User user = rowProcessor.toBean(resultSet, User.class);
                TopicType topicType = rowProcessor.toBean(resultSet, TopicType.class);
                topic.setUser(user);
                topic.setTopicType(topicType);
                topicList.add(topic);
            }
            return topicList;
        }
    }
}

