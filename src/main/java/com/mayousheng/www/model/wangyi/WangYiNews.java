package com.mayousheng.www.model.wangyi;

import java.util.Date;

/**
 * Created by marking on 2017/4/30.
 */
public class WangYiNews {

    private String title;//标题
    private String imgurl;//图片地址
    private String docurl;//正文地址
    private Date time;//新闻时间
    private String channelname;//新闻类型

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDocurl() {
        return docurl;
    }

    public void setDocurl(String docurl) {
        this.docurl = docurl;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    @Override
    public String toString() {
        return "WangYiNews{" +
                "title='" + title + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", docurl='" + docurl + '\'' +
                ", time='" + time + '\'' +
                ", channelname='" + channelname + '\'' +
                '}';
    }
}
