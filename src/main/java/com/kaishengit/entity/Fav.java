package com.kaishengit.entity;

/**
 * Created by qiyawei on 2016/4/12.
 */
public class Fav {
    private Integer id;
    private Integer uid;
    private Integer topicid;
    private String favtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTopicid() {
        return topicid;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }

    public String getFavtime() {
        return favtime;
    }

    public void setFavtime(String favtime) {
        this.favtime = favtime;
    }
}
