package com.wkc.sso.ssoclient2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created on 2021/08/25.
 *
 * @author Weikaichen
 */
@Controller
public class IndexController {




    @RequestMapping("/index")
    public String index(HttpSession session, @RequestParam(value = "token", required = false) String token) {

        if (token != null) {

            RestTemplate restTemplate = new RestTemplate();
            String object = restTemplate.getForObject("http://localhost:8080/userinfo?token="+token, String.class);
            session.setAttribute("loginUser", object);



            return "index";
        }


        if (session.getAttribute("loginUser") == null) {
            return "redirect:http://localhost:8080/dologin?redirect_url=http://localhost:8082/index";
        } else {
            return "index";

        }

    }

}

