package com.spring;

/**
 * Created on 2022/1/8.
 *
 * @author Weikaichen
 */
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
