package com.wkc.sso.ssoserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created on 2021/08/25.
 *
 * @author Weikaichen
 */
@Controller
public class LoginController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/userinfo")
    @ResponseBody
    public String user(@RequestParam(value = "token",required = false) String token){
        return stringRedisTemplate.opsForValue().get(token);
    }

    @RequestMapping("/dologin")
    public String login(Model model,
                        @RequestParam(value = "redirect_url",required = false) String url,
                        @CookieValue(value = "sso-token",required = false) String token){

        if (token !=null){
            return "redirect:"+url+"?token="+token;
        }

        model.addAttribute("url", url);

        return "login";
    }

    @PostMapping("/login")
    public String login1(String username,
                         String password ,@RequestParam(value = "url",required = false) String url,
                         HttpServletResponse response
                        ){
        if (!StringUtils.isEmptyOrWhitespace(username)&&!StringUtils.isEmptyOrWhitespace(password)){

            String s = UUID.randomUUID().toString();
            //存一个cookie
            Cookie cookie = new Cookie("sso-token", s);
            response.addCookie(cookie);
           //存进redis
            stringRedisTemplate.opsForValue().set(s, username);
            return "redirect:"+url+"?token="+s;
        }else {
            return "login";
        }
    }

}
