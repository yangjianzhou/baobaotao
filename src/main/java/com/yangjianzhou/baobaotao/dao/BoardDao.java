package com.yangjianzhou.baobaotao.dao;

import com.yangjianzhou.baobaotao.entity.Board;

import java.util.List;

/**
 * Created by yangjianzhou on 15-6-13.
 */
public class BoardDao  extends  BaseDao<Board> {
    @Override
    public Board getById(String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public List<Board> getAll() {
        return null;
    }

    @Override
    public void insert(Board board) {

    }

    @Override
    public void update(Board board) {

    }

    public long  getTotalCount() throws Exception{
        return  (Long)this.sqlMapClient.queryForObject("board_hand.getTotalCount");
    }
}
