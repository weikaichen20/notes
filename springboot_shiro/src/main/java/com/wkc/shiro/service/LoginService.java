package com.wkc.shiro.service;

import com.wkc.shiro.entity.User;

/**
 * Created on 2021/09/15.
 *
 * @author Weikaichen
 */
public interface LoginService {
    User getUserByName(String getMapByName);
}
