package com.yangjianzhou.baobaotao.entity;

/**
 * Created by yangjianzhou on 15-5-31.
 */
public class BoardManager  extends   BaseBean {

    private  String boardId ;

    private  String userId ;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
}
