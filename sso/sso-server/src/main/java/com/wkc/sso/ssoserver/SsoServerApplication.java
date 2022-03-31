package com.wkc.sso.ssoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
// 单点登陆
* 给登陆服务器留下登陆痕迹
* 登录服务器要将token信息重定向的时候，带到url地址上
* 其他系统要处理url地址上的关键token，只要有，将token对应的用户保存到自己的session中
* 自己系统将用户保存到自己的会话中
*
* */
@SpringBootApplication
public class SsoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoServerApplication.class, args);
    }

}
