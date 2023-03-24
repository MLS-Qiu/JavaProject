package com.pet.project001.servlet;

import com.pet.project001.global.Global;
import com.pet.project001.model.Pet;
import com.pet.project001.model.PetSearchBean;
import com.pet.project001.model.User;
import com.pet.project001.service.log.LoginService;
import com.pet.project001.service.log.impl.LoginServiceImpl;
import com.pet.project001.util.BeanFactory;
import com.pet.project001.util.Servlets;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//处理登录、注销等请求
@WebServlet("/user/*")
public class logServlet extends HttpServlet {
    private final LoginService service = BeanFactory.getBean(LoginServiceImpl.class);
    private HttpServletRequest req;
    private HttpServletResponse resp;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String ctx = req.getContextPath();
        String action = uri.replace(ctx, "");

        if (action.equals("/user/login")) {
            String error = (String) req.getSession().getAttribute("error");
            if (error != null) {
                req.setAttribute("error", error);
                req.getSession().removeAttribute("error");
            }
            req.getRequestDispatcher("/WEB-INF/jsp/log/login.jsp").forward(req, resp);
        } else if (action.equals("/user/logout")) {
            req.getSession().removeAttribute(Global.USER_LOGIN_KEY);
            System.out.println(req.getContextPath());
            resp.sendRedirect(req.getContextPath() + "/main");
        } else if (action.equals("/user/captcha")) {
            CaptchaUtil.out(80, 35, 4, req, resp);
        } else if (action.equals("/user/userUpdate")) {
            User buser = (User) req.getSession().getAttribute(Global.USER_LOGIN_KEY);
            User user = service.findByName(buser.getUsername());
            if (user!=null){
                req.setAttribute("user", user);
            }

            HttpSession session = req.getSession();
            user = (User) session.getAttribute("user");
            String error = (String) session.getAttribute("error");

            if (user != null) {
                req.setAttribute("user", user);
                session.removeAttribute("user");
                req.setAttribute("error", error);
                session.removeAttribute("error");
            }

            String updateUser = (String) req.getSession().getAttribute("updateOK");
            if (updateUser != null) {
                req.setAttribute("updateOK", updateUser);
                req.getSession().removeAttribute("updateOK");
            }
            req.getRequestDispatcher("/WEB-INF/jsp/user/userUpdate.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;

        String uri = req.getRequestURI();
        String ctx = req.getContextPath();
        String action = uri.replace(ctx, "");

        if (action.equals("/user/login")) {
            login();
        } else if (action.equals("/user/checkUsername")) {
            checkUsername();
        } else if (action.equals("/user/userUpdate")) {
            userUpdate();
        }
    }

    private void login() throws IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String captcha = req.getParameter("captcha");

        User user = service.findByName(userName);
        //判断
        boolean b = CaptchaUtil.ver(captcha, req);
        if (!b) {
            req.getSession().setAttribute("error", "验证码不正确");
            resp.sendRedirect(req.getContextPath() + "/user/login");
            return;
        }


        if (user == null) {
            req.getSession().setAttribute("error", "该用户未注册");
            req.getRequestDispatcher("/user/login");
        }

        boolean pass = service.checkLogin(user, password);

        if (pass) {
            //将用户名和密码放进session域，在session域的会话期间就可以直接进入
            req.getSession().setAttribute(Global.USER_LOGIN_KEY, user);
            resp.sendRedirect(req.getContextPath() + "/main");
        } else {
            req.getSession().setAttribute("error", "用户名或密码不正确！");
            resp.sendRedirect(req.getContextPath() + "/main");
        }
    }

    private void checkUsername() {
        String username = req.getParameter("username");
        boolean result = service.checkUsername(username);
        if (result) {
            Servlets.renderJson(Map.of("exist", true), resp);
        } else {
            Servlets.renderJson(Map.of("exist", false), resp);
        }

    }

    private void userUpdate() throws IOException {
        User user = new User();
        HttpSession session = req.getSession();
        boolean passed = true;//是否通过校验
        List<String> errors = new ArrayList<>();

        String username = req.getParameter("username");
        boolean result = service.checkUsername(username);
        if (result) {
            passed = false;
            errors.add("用户名已存在");
        }
        user.setUsername(username);

        String firPassword = service.makePassword(req.getParameter("firPassword"), username);
        String secPassword = service.makePassword(req.getParameter("secPassword"), username);
        if (!service.checkPassword(firPassword, secPassword)) {
            passed = false;
            errors.add("两次密码不一致");
        }
        user.setPassword(firPassword);

        String nickname = req.getParameter("nickname");
        user.setNickname(nickname);

        String phone = req.getParameter("phone");
        user.setPhone(phone);

        String address = req.getParameter("address");
        user.setAddress(address);

        if (!passed) {
            session.setAttribute("user", user);
            String error = errors.stream().collect(Collectors.joining(","));
            session.setAttribute("error", error);
            resp.sendRedirect(req.getContextPath() + "/user/userUpdate");
            return;
        }

        User buser = (User) session.getAttribute(Global.USER_LOGIN_KEY);
        boolean b = service.userUpdate(user, buser.getUsername());
        if (b != true) {
            session.setAttribute("user", user);
            session.setAttribute("error", "信息修改失败");
        } else {
            session.setAttribute("updateOK", "修改成功！");
            resp.sendRedirect(req.getContextPath() + "/user/userUpdate");
        }
    }
}
