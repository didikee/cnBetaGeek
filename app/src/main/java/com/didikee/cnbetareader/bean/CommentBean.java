package com.didikee.cnbetareader.bean;

/**
 * Created by didik on 2017/2/10.
 */

public class CommentBean {
    /**
     * tid : 13936685
     * pid : 0
     * username :
     * content : 世界首款吗?你把中国的孕橙APP放哪里啦?
     * created_time : 2017-02-10 17:29:14
     * support : 1
     * against : 2
     */

    private String tid;
    private String pid;
    private String username;
    private String content;
    private String created_time;
    private String support;
    private String against;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public String getAgainst() {
        return against;
    }

    public void setAgainst(String against) {
        this.against = against;
    }
}
