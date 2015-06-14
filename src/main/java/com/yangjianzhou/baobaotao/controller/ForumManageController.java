package com.yangjianzhou.baobaotao.controller;

import com.yangjianzhou.baobaotao.entity.Board;
import com.yangjianzhou.baobaotao.entity.User;
import com.yangjianzhou.baobaotao.service.ForumService;
import com.yangjianzhou.baobaotao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by yangjianzhou on 15-6-14.
 */
@Controller
public class ForumManageController extends BaseController {

    @Autowired
    private ForumService forumService ;

    @Autowired
    private UserService userService ;


    @RequestMapping(value = "index" , method = RequestMethod.GET)
    public ModelAndView listAllBoards(){
        ModelAndView mav = new ModelAndView();
        List<Board> boards = forumService.getAllBoards();
        mav.addObject("boards",boards);
        mav.setViewName("listAllBoards");
        return mav;
    }

    @RequestMapping(value = "/forum/addBoardPage" , method = RequestMethod.GET)
    public  String addBoardPage(){
        return "addBoard";
    }

    @RequestMapping(value = "/forum/addBoard" , method = RequestMethod.POST)
    public  String addBoard(Board board){
        forumService.addBoard(board);
        return "addBoardSuccess";
    }


    @RequestMapping(value = "/forum/setBoardManagerPage" , method = RequestMethod.GET)
    public  ModelAndView setBoardManagerPage(){
        ModelAndView mav = new ModelAndView();
        List<Board> boards = forumService.getAllBoards();
        List<User> users = userService.getAllUsers();
        mav.addObject("boards" , boards);
        mav.addObject("users",users);
        mav.setViewName("/setBoardManager");
        return mav;
    }
}
