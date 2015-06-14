package com.yangjianzhou.baobaotao.exception;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yangjianzhou on 15-6-14.
 */
public class ForumHandlerExceptionResolver extends SimpleMappingExceptionResolver {

    protected ModelAndView doResolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, Exception exception) {
        httpServletRequest.setAttribute("ex", exception);
        exception.printStackTrace();
        return super.doResolveException(httpServletRequest, httpServletResponse, object, exception);
    }
}
