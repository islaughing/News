package com.example.news.laughing.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class NewsBean implements Serializable{
/*
* 新闻标题
* */
    private String title;
    /*
    * 图片地址*/


    private String url;
    /*
    * 小内容
    * */
    private String desc;
    /*
    * 正文地址*/
    private String link;

    public void setTilte(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDesc() {

        return desc;
    }

    public String geturl() {
        return url;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {

        return title;
    }
}
