package com.wkc.service;

import com.spring.*;

/**
 * Created on 2022/1/6.
 *
 * @author Weikaichen
 */
@Component("userService")
@Scope("prototype")
public class UserServiceImpl implements BeanNameAware, InitializingBean,UserService {
    @Autowired
    private OrderService orderService;

    private String beanName;

    @Override
    public void test() {
        System.out.println(orderService);
        System.out.println("==================>beanName===>" + beanName);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("==================InitializingBean==================");
    }
}
