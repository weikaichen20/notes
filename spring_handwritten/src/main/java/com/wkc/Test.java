package com.wkc;

import com.spring.WkcApplicationContext;
import com.wkc.service.UserService;
import com.wkc.service.UserServiceImpl;

/**
 * Created on 2022/1/6.
 *
 * @author Weikaichen
 */
public class Test {
    public static void main(String[] args) {
        WkcApplicationContext applicationContext = new WkcApplicationContext(AppConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.test();
    }
}
