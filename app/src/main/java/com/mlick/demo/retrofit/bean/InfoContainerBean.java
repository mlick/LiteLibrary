package com.mlick.demo.retrofit.bean;

import java.io.Serializable;

/**
 * Created by lxx on 2016/3/28 10:25.
 */
public class InfoContainerBean implements Serializable {
    /**
     * ArticleID : zxArticle625ID201603251505239utkv1
     * Title : 3个方法教你去除体内湿气
     * ImageUrl : http://zixun.wenbing.cn/upload/ColumnImage/2016/3/25/qz201603250336567002.jpg
     * Introduction : 湿气是潜伏在人体中的健康炸弹很多人突然觉得困倦、身体四肢沉重...
     * ColumnName : 生活
     * ReleaseTime : 2016-03-25 15:05:24
     * CollectionNumber : 2
     * CommentNumber : 0
     */

    private String ArticleID;
    private String Title;
    private String ImageUrl;
    private String Introduction;
    private String ColumnName;
    private String ReleaseTime;
    private String CollectionNumber;
    private String CommentNumber;

    public String getArticleID() {
        return ArticleID;
    }

    public void setArticleID(String ArticleID) {
        this.ArticleID = ArticleID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String Introduction) {
        this.Introduction = Introduction;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String ColumnName) {
        this.ColumnName = ColumnName;
    }

    public String getReleaseTime() {
        return ReleaseTime;
    }

    public void setReleaseTime(String ReleaseTime) {
        this.ReleaseTime = ReleaseTime;
    }

    public String getCollectionNumber() {
        return CollectionNumber;
    }

    public void setCollectionNumber(String CollectionNumber) {
        this.CollectionNumber = CollectionNumber;
    }

    public String getCommentNumber() {
        return CommentNumber;
    }

    public void setCommentNumber(String CommentNumber) {
        this.CommentNumber = CommentNumber;
    }
}
