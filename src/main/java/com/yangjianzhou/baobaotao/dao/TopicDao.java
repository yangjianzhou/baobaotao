package com.yangjianzhou.baobaotao.dao;

import com.yangjianzhou.baobaotao.entity.Topic;

import java.util.List;

/**
 * Created by yangjianzhou on 15-6-13.
 */
public class TopicDao extends BaseDao<Topic> {
    @Override
    public Topic getById(String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public List<Topic> getAll() {
        return null;
    }

    @Override
    public void insert(Topic topic) {

    }

    @Override
    public void update(Topic topic) {

    }

    public  List<Topic>  getEliteByBoardId(String  boardId) throws   Exception{
        return this.sqlMapClient.queryForList("topic_hand.getEliteByBoardId",boardId) ;
    }

    public  List<Topic>  getbyBoardId(String  boardId) throws  Exception{
        return this.sqlMapClient.queryForList("topic_hand.getByBoardId",boardId) ;
    }

    public  List<Topic>  getbyTitle(String title) throws  Exception{
        return this.sqlMapClient.queryForList("topic_hand.getByTitle",title) ;
    }
}
