package com.spring;

/**
 * Created on 2022/1/7.
 *
 * @author Weikaichen
 */
public class BeanDefinition {
    private Class<?> clazz;
    private String scope;


    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
