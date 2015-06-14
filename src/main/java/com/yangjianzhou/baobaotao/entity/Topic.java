package com.yangjianzhou.baobaotao.entity;

import com.yangjianzhou.baobaotao.enums.TopicType;

import java.util.Date;

/**
 * Created by yangjianzhou on 15-5-31.
 */
public class Topic  extends   BaseBean {

    private  String topicId ;

    private String  boardId ;

    private String topicTitle ;

    private  String userId ;

    private TopicType topicType ;

    private Date  createTime ;

    private Date  lastPostTime ;

    private  int topsViews ;

    private  int  topicReplies ;

    public int getTopicReplies() {
        return topicReplies;
    }

    public void setTopicReplies(int topicReplies) {
        this.topicReplies = topicReplies;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TopicType getTopicType() {
        return topicType;
    }

    public void setTopicType(TopicType topicType) {
        this.topicType = topicType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastPostTime() {
        return lastPostTime;
    }

    public void setLastPostTime(Date lastPostTime) {
        this.lastPostTime = lastPostTime;
    }

    public int getTopsViews() {
        return topsViews;
    }

    public void setTopsViews(int topsViews) {
        this.topsViews = topsViews;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
}
