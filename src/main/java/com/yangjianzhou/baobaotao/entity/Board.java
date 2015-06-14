package com.yangjianzhou.baobaotao.entity;

/**
 * Created by yangjianzhou on 15-5-31.
 */
public class Board extends BaseBean {

    private String boardId;

    private String boardName;

    private String description;

    private int topicNum;

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public int getTopicNum() {
        return topicNum;
    }

    public void setTopicNum(int topicNum) {
        this.topicNum = topicNum;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
