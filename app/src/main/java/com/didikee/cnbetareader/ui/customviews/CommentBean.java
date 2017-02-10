package com.didikee.cnbetareader.ui.customviews;

/**
 * Created by didik 
 * Created time 2017/2/10
 * Description: 
 */

public class CommentBean {
    private String name;
    private String content;

    public CommentBean(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
