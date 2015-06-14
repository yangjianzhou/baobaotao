package com.yangjianzhou.baobaotao.controller;

import com.yangjianzhou.baobaotao.entity.Board;
import com.yangjianzhou.baobaotao.entity.Post;
import com.yangjianzhou.baobaotao.entity.Topic;
import com.yangjianzhou.baobaotao.entity.User;
import com.yangjianzhou.baobaotao.service.ForumService;
import com.yangjianzhou.baobaotao.utils.CommonConstant;
import com.yangjianzhou.baobaotao.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by yangjianzhou on 15-6-14.
 */
@Controller
public class BoardManageController extends BaseController {
    @Autowired
    private ForumService forumService;

    /**
     * 列出论坛模块下的主题帖子
     *
     * @param boardId
     * @return
     */
    @RequestMapping(value = "/board/listBoardTopics-{boardId}", method = RequestMethod.GET)
    public ModelAndView listBoardTopics(@PathVariable String boardId, @RequestParam(value = "pageNo", required = false) Integer pageNo) {
        ModelAndView view = new ModelAndView();
        Board board = forumService.getBoardById(boardId);
        pageNo = pageNo == null ? 1 : pageNo;
        Pagination pagedTopic = forumService.getPagedTopics(boardId, pageNo, CommonConstant.PAGE_SIZE);
        view.addObject("board", board);
        view.addObject("pagedTopic", pagedTopic);
        view.setViewName("/listBoardTopics");
        return view;
    }

    /**
     * 添加主题帖页面
     *
     * @param boardId
     * @return
     */
    @RequestMapping(value = "/board/addTopicPage-{boardId}", method = RequestMethod.GET)
    public ModelAndView addTopicPage(@PathVariable Integer boardId) {
        ModelAndView view = new ModelAndView();
        view.addObject("boardId", boardId);
        view.setViewName("/addTopic");
        return view;
    }

    /**
     * 添加一个主题帖
     *
     * @param request
     * @param topic
     * @return
     */
    @RequestMapping(value = "/board/addTopic", method = RequestMethod.POST)
    public String addTopic(HttpServletRequest request, Topic topic) {
        User user = getSessionUser(request);
        topic.setUserId(user.getUserId());
        Date now = new Date();
        topic.setCreateTime(now);
        topic.setLastPostTime(now);
        forumService.addTopic(topic);
        String targetUrl = "/board/listBoardTopics-" + topic.getBoardId()
                + ".html";
        return "redirect:" + targetUrl;
    }

    /**
     * 列出主题的所有帖子
     *
     * @param topicId
     * @return
     */
    @RequestMapping(value = "/board/listTopicPosts-{topicId}", method = RequestMethod.GET)
    public ModelAndView listTopicPosts(@PathVariable String topicId, @RequestParam(value = "pageNo", required = false) Integer pageNo) {
        ModelAndView view = new ModelAndView();
        Topic topic = forumService.getTopicByTopicId(topicId);
        pageNo = pageNo == null ? 1 : pageNo;
        Pagination pagedPost = forumService.getPagedPosts(topicId, pageNo, CommonConstant.PAGE_SIZE);
        // 为回复帖子表单准备数据
        view.addObject("topic", topic);
        view.addObject("pagedPost", pagedPost);
        view.setViewName("/listTopicPosts");
        return view;
    }

    /**
     * 回复主题
     *
     * @param request
     * @param post
     * @return
     */
    @RequestMapping(value = "/board/addPost")
    public String addPost(HttpServletRequest request, Post post) {
        post.setCreateTime(new Date());
        post.setUserId(getSessionUser(request).getUserId());
        forumService.addPost(post);
        String targetUrl = "/board/listTopicPosts-"
                + post.getTopicId() + ".html";
        return "redirect:" + targetUrl;
    }

    /**
     * 删除版块
     */
    @RequestMapping(value = "/board/removeBoard", method = RequestMethod.GET)
    public String removeBoard(@RequestParam("boardIds") String boardIds) {
        String[] arrIds = boardIds.split(",");
        for (int i = 0; i < arrIds.length; i++) {
            forumService.removeBoard(arrIds[i]);
        }
        String targetUrl = "/index.html";
        return "redirect:" + targetUrl;
    }

    /**
     * 删除主题
     */
    @RequestMapping(value = "/board/removeTopic", method = RequestMethod.GET)
    public String removeTopic(@RequestParam("topicIds") String topicIds, @RequestParam("boardId") String boardId) {
        String[] arrIds = topicIds.split(",");
        for (int i = 0; i < arrIds.length; i++) {
            forumService.removeTopic(arrIds[i]);
        }
        String targetUrl = "/board/listBoardTopics-" + boardId + ".html";
        return "redirect:" + targetUrl;
    }

    /**
     * 设置精华帖
     */
    @RequestMapping(value = "/board/makeDigestTopic", method = RequestMethod.GET)
    public String makeDigestTopic(@RequestParam("topicIds") String topicIds, @RequestParam("boardId") String boardId) {
        String[] arrIds = topicIds.split(",");
        for (int i = 0; i < arrIds.length; i++) {
            forumService.makeDigestTopic(arrIds[i]);
        }
        String targetUrl = "/board/listBoardTopics-" + boardId + ".html";
        return "redirect:" + targetUrl;
    }
}
