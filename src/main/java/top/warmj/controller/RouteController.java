package top.warmj.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WarMj
 * @ClassName RouteController.java
 * @Description TODO
 * @createTime 2020年11月04日 14:49:00
 */
@Controller
public class RouteController {

    @RequestMapping({"/", "/index"})
    public String index() {
        System.out.println("index");
        return "index";
    }

    @RequestMapping("/retrieve-document")
    public String retrieveDocument() {
        return "retrieve-document";
    }

    @RequestMapping("/upload-document")
    public String uploadDocument() {
        return "upload-document";
    }
//
//    @RequestMapping("/user/updateUser")
//    public String updateUser() {
//        return "user/updateUser";
//    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/auth")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token); //执行登录方法
            return "index";
        } catch (UnknownAccountException e) {
            request.setAttribute("msg", "用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            request.setAttribute("msg", "密码错误");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
        }

        return "login";
    }
}
