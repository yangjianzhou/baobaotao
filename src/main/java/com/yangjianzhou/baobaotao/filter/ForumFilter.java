package com.yangjianzhou.baobaotao.filter;

import com.yangjianzhou.baobaotao.entity.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.yangjianzhou.baobaotao.utils.CommonConstant.*;

/**
 * Created by yangjianzhou on 15-6-14.
 */
public class ForumFilter implements Filter {

    private static final String FILTERED_REQUEST = "@@session_context_filtered_requested";

    // ① 不需要登录即可访问的URI资源
    private static final String[] INHERENT_ESCAPE_URIS = {"/index.jsp",
            "/index.html", "/login.jsp", "/login/doLogin.html",
            "/register.jsp", "/register.html", "/board/listBoardTopics-",
            "/board/listTopicPosts-"};

    // ② 执行过滤
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // ②-1 保证该过滤器在一次请求中只被调用一次
        if (request != null && request.getAttribute(FILTERED_REQUEST) != null) {
            chain.doFilter(request, response);
        } else {
            // ②-2 设置过滤标识，防止一次请求多次过滤
            request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            User userContext = getSessionUser(httpServletRequest);
            if (userContext == null && !isURILoginLogin(httpServletRequest.getRequestURI(), httpServletRequest)) {
                StringBuilder toUrl = new StringBuilder(httpServletRequest.getRequestURL().toString());
                if (StringUtils.isNotEmpty(httpServletRequest.getQueryString())) {
                    toUrl.append("?");
                    toUrl.append(httpServletRequest.getQueryString());
                }
                httpServletRequest.getSession().setAttribute(LOGIN_TO_URL, toUrl.toString());
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            chain.doFilter(request, response);
        }

    }

    /**
     * 当前URI资源是否需要登录才能访问
     * @param requestURI
     * @param request
     * @return
     * true:not need login
     * false: need login
     */
    private boolean isURILoginLogin(String requestURI, HttpServletRequest request) {
        if (request.getContextPath().equalsIgnoreCase(requestURI) || (request.getContextPath() + "/").equalsIgnoreCase(requestURI)) {
            return true;
        }
        for (String uri : INHERENT_ESCAPE_URIS) {
            if (requestURI != null && requestURI.indexOf(uri) >= 0) {
                return true;
            }
        }
        return false;
    }

    protected User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(USER_CONTEXT);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
