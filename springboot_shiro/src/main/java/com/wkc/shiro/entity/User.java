package com.wkc.shiro.entity;

/**
 * Created on 2021/09/15.
 *
 * @author Weikaichen
 */

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class User {
    private String id;
    private String userName;
    private String password;
    /**
     * 用户对应的角色集合
     */
    private Set<Role> roles;
}