package com.spring;

import com.sun.istack.internal.Nullable;

/**
 * Created on 2022/1/8.
 *
 * @author Weikaichen
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName);

    Object postProcessAfterInitialization(Object bean, String beanName);
}
