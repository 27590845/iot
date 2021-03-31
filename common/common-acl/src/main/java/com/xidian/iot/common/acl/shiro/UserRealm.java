package com.xidian.iot.common.acl.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: mrl
 * @date: 2021/3/28 上午9:39
 */
public class UserRealm extends CasRealm {

//	@Resource
//	private RoleService roleService;

//	@Resource
//	private UserService userService;

    protected final Map<String, SimpleAuthorizationInfo> roles = new ConcurrentHashMap<String, SimpleAuthorizationInfo>();

    /**
     * 设置角色和权限信息
     */
//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//
//		String account = (String) principals.getPrimaryPrincipal();
//		SimpleAuthorizationInfo authorizationInfo = null;
//		if (authorizationInfo == null) {
//			authorizationInfo = new SimpleAuthorizationInfo();
//			authorizationInfo.addStringPermissions(roleService.getPermissions(account));
//			authorizationInfo.addRoles(roleService.getRoles(account));
//			roles.put(account, authorizationInfo);
//		}
//
//		return authorizationInfo;
//	}


    /**
     * 1、CAS认证 ,验证用户身份
     * 2、将用户基本信息设置到会话中
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {

        AuthenticationInfo authc = super.doGetAuthenticationInfo(token);

        String account = (String) authc.getPrincipals().getPrimaryPrincipal();

//		User user = userService.getUserByAccount(account);
//
        SecurityUtils.getSubject().getSession().setAttribute("account", account);

        return authc;
    }
}
