package com.example.nicepicbbs.model;

import java.util.ArrayList;
import java.util.List;

public class CommunityContent {
    private String name;
    private String vipLevel;
    private String powerLevel;
    private String headUrl;
    private List<String> contentPicUrls;
    private List<Comment> comments;
    private String text;
    private int type;


    public CommunityContent(int type){
        name = "上官爷爷";
        vipLevel = "尊享会员";
        powerLevel = "活力Lv.12";
        headUrl = null;
        contentPicUrls = new ArrayList<>();
        comments = new ArrayList<>();
        text = "大家笑一笑就好";
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(String powerLevel) {
        this.powerLevel = powerLevel;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public List<String> getContentPicUrls() {
        return contentPicUrls;
    }

    public void setContentPicUrls(List<String> contentPicUrls) {
        this.contentPicUrls = contentPicUrls;
    }

    public void addContentPicUrls(String url){
        contentPicUrls.add(url);
    }

    public void addNewComment(Comment comment){
        comments.add(comment);
    }



    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
