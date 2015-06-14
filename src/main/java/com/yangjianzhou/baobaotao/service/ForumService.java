package com.yangjianzhou.baobaotao.service;

import com.yangjianzhou.baobaotao.dao.BoardDao;
import com.yangjianzhou.baobaotao.dao.PostDao;
import com.yangjianzhou.baobaotao.dao.TopicDao;
import com.yangjianzhou.baobaotao.dao.UserDao;
import com.yangjianzhou.baobaotao.entity.Board;
import com.yangjianzhou.baobaotao.entity.Post;
import com.yangjianzhou.baobaotao.entity.Topic;
import com.yangjianzhou.baobaotao.entity.User;
import com.yangjianzhou.baobaotao.enums.TopicType;
import com.yangjianzhou.baobaotao.utils.IDGenerator;
import com.yangjianzhou.baobaotao.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangjianzhou on 15-6-13.
 */
@Service
public class ForumService {

    @Autowired
    private TopicDao topicDao ;

    @Autowired
    private BoardDao boardDao ;

    @Autowired
    private PostDao postDao ;

    @Autowired
    private UserDao userDao ;

    public  void addTopic(Topic topic){
        topic.setTopicId(IDGenerator.generatorID());
        Board board = boardDao.getById(topic.getBoardId());
        board.setTopicNum(board.getTopicNum()+1);
        topicDao.insert(topic);

        //topic.get

        Post post = new Post();
        post.setPostId(IDGenerator.generatorID());
        post.setCreateTime(new Date());
       post.setUserId(topic.getUserId());
        post.setPostTitle(topic.getTopicTitle());
        post.setBoardId(topic.getBoardId());

        postDao.insert(post);

        User  user  = userDao.getById(topic.getUserId());
        user.setCredits(user.getCredits()+5);
        userDao.update(user);
    }

    public void removeTopic(String topicId) {
        Topic topic = topicDao.getById(topicId);
        Board board = boardDao.getById(topic.getBoardId());
        board.setTopicNum(board.getTopicNum()-1);

        User user = userDao.getById(topic.getUserId());
        user.setCredits(user.getCredits()-50);

        topicDao.deleteById(topicId);
       // postDao.deleteById();
    }

    public void addPost(Post post){
        postDao.insert(post);
        User user = userDao.getById(post.getUserId());
        user.setCredits(user.getCredits()+5);
        userDao.update(user);

        Topic topic = topicDao.getById(post.getTopicId());
        topic.setTopicReplies(topic.getTopicReplies()+1);
        topic.setLastPostTime(new Date());

        topicDao.update(topic);
    }

    public void removePost(String postId){
        Post  post = postDao.getById(postId);
        postDao.deleteById(postId);

        Topic topic = topicDao.getById(post.getTopicId());
        topic.setTopicReplies(topic.getTopicReplies()-1);

        User user = userDao.getById(post.getUserId());
        user.setCredits(user.getCredits()-20);

    }

    public  void addBoard(Board  board){
        boardDao.insert(board);
    }

    public  void removeBoard(String boardId){
        Board board = boardDao.getById(boardId);

        boardDao.deleteById(boardId);
    }

    public  void  makeDigestTopic(String topicId){
        Topic topic = topicDao.getById(topicId);
        topic.setTopicType(TopicType.ELITE);
        User user = userDao.getById(topic.getUserId());
        user.setCredits(user.getCredits()+100);
    }

/*
    public Pagination getPaginationTopics(String boardId , int pageNo , int pageSize){
        return topicDao.getPaginationTopics(boardId,pageNo,pageSize);
    }

    public Pagination getPaginationPosts(String topicId , int pageNo , int pageSize){
        return postDao.getPaginationTopics(topicId,pageNo,pageSize);
    }
*/

    public void  addBoardManager(String boardId , String userName) throws  Exception{
        User user = userDao.getUserByUserName(userName);
        if(user == null){
            throw new Exception();
        }else {
            Board board = boardDao.getById(boardId);
            userDao.update(user);
        }
    }

    public List<Board>  getAllBoards(){
        List<Board>  boards = new ArrayList<Board>();
        return  boards;
    }

    public   Board  getBoardById(String boardId){
        return   new Board();
    }

    public Pagination   getPagedTopics(String  boardId , int pageNo , int pageSize){
        return  null ;
    }

    public Pagination   getPagedPosts(String  topicId , int pageNo , int pageSize){
        return  null ;
    }

    public  Topic  getTopicByTopicId(String topicId){
        return topicDao.getById(topicId);
    }

}
