package com.wkc.shiro.shiro;

import com.wkc.shiro.entity.Permissions;
import com.wkc.shiro.entity.Role;
import com.wkc.shiro.entity.User;
import com.wkc.shiro.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 2021/09/15.
 *
 * @author Weikaichen
 */
@Component
@SuppressWarnings("all")
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    LoginService loginService;

    /**
     * @param principalCollection
     * @return
     * @desc 权限配置类
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登陆用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        User user = loginService.getUserByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //添加权限
            for (Permissions permissions : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
            }
        }
        return simpleAuthorizationInfo;

    }

    /**
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     * @desc 认证配置类
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        if (StringUtils.isNotEmpty(authenticationToken.getPrincipal().toString())) {
            String name = authenticationToken.getPrincipal().toString();
            User user = loginService.getUserByName(name);
            if (user == null) {
                //这里返回后回报出异常
                return null;
            } else {
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword(), getName());
                return simpleAuthenticationInfo;
            }

        } else {
            return null;

        }
    }
}
