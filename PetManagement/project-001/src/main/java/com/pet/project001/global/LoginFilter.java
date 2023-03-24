package com.pet.project001.global;

import com.pet.project001.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.dsig.spec.XPathType;
import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class LoginFilter implements Filter {
    private static final List<String> passList = List.of("/user/login", "/user/logout", "/user/captcha");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        String uri = req.getRequestURI();
        String ctx = req.getContextPath();
        String action = uri.replace(ctx, "");

        User user = (User) session.getAttribute(Global.USER_LOGIN_KEY);

        boolean match = false;
        if (uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith(".jpg") || uri.endsWith(".png") || uri.endsWith(".jsp")) {
            match = true;
        } else {
            for (String url : passList) {
                if (action.startsWith(url)) {
                    //如果action为passList（可通过）里面的，match设置为匹配，在下方放行
                    match = true;
                    break;
                }
            }
        }

        //如果action为passList（可通过）里面的，match设置为匹配，直接放行
        if (match) {
            filterChain.doFilter(req, resp);
        } else {
            if (user == null) {
                //如果用户没有登录过，跳转到登录页面
                resp.sendRedirect(req.getContextPath() + "/user/login");
            } else {
                //如果有用户的登录记录，放行
                filterChain.doFilter(req, resp);
            }
        }


    }
}
