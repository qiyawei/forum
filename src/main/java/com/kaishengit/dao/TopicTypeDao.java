package com.kaishengit.dao;

import com.kaishengit.entity.TopicType;
import com.kaishengit.util.DpHelper;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by qiyawei on 2016/4/11.
 */
public class TopicTypeDao {
    public List<TopicType> findAll() {
        String sql = "select * from t_topicType";
        return DpHelper.query(sql,new BeanListHandler<TopicType>(TopicType.class));
    }
}
