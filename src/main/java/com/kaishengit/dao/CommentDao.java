package com.kaishengit.dao;

import com.google.common.collect.Lists;
import com.kaishengit.entity.Comment;
import com.kaishengit.entity.User;
import com.kaishengit.util.DpHelper;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiyawei on 2016/4/12.
 */
public class CommentDao {

    public int num(Integer id) {
        String sql = "select count(*) from t_user INNER JOIN t_topic on t_user.id = t_topic.uid  " +
                "INNER JOIN t_comment on t_topic.id = t_comment.topicid " +
                "where t_user.id=?";
        return DpHelper.query(sql,new ScalarHandler<Long>(),id).intValue();
    }




    public Integer save(Comment comment) {
        String sql = "insert into t_comment(uid,topicid,text,createtime) values(?,?,?,?)";
        return DpHelper.insert(sql,comment.getUid(),comment.getTopicid(),comment.getText(),comment.getCreatetime()).intValue();
    }

    public List<Comment> findAllByTopicId(Integer topicId) {
        String sql = "SELECT t_comment.*,username,img FROM t_comment \n" +
                "INNER JOIN t_user ON t_comment.`uid` = t_user.`id`\n" +
                "WHERE t_comment.`topicid` = ?";
        return DpHelper.query(sql, new ResultSetHandler<List<Comment>>() {
            @Override
            public List<Comment> handle(ResultSet rs) throws SQLException {
                List<Comment> commentList = Lists.newArrayList();
                BasicRowProcessor rowProcessor = new BasicRowProcessor();
                while(rs.next()) {
                    Comment comment = rowProcessor.toBean(rs,Comment.class);
                    User user = rowProcessor.toBean(rs,User.class);
                    comment.setUser(user);
                    commentList.add(comment);
                }
                return commentList;
            }
        }, topicId);
    }

    public List<Integer> findByCreatetime(User user,String time) {
        String sql = "select t_topic.id from t_user INNER JOIN t_topic on t_user.id = t_topic.uid  " +
                "INNER JOIN t_comment on t_topic.id = t_comment.topicid  " +
                "where t_user.id=? and t_comment.createtime > ?";
        return DpHelper.query(sql, new ResultSetHandler<List<Integer>>() {
            @Override
            public List<Integer> handle(ResultSet resultSet) throws SQLException {
                List<Integer> list = new ArrayList<Integer>();
                while(resultSet.next()){
                    list.add(resultSet.getInt("id"));
                }
                return list;
            }
        }, user.getId(), time);
    }
}
