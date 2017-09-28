package com.ontheroad.service.impl.realm;

import com.ontheroad.core.util.MD5Encoder;
import com.ontheroad.core.util.SpringSecurityUtils;
import com.ontheroad.system.entity.SysMenu;
import com.ontheroad.system.entity.SysRole;
import com.ontheroad.system.entity.SysUser;
import com.ontheroad.system.service.SysMenuService;
import com.ontheroad.system.service.SysRoleService;
import com.ontheroad.system.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * The Spring application's one and only configured Apache Shiro Realm.
 */
/**
 * Shiro-1.2.2内置的FilterChain
 * 
 * @see 
 *      ==========================================================================
 *      ===================================================
 * @see 1)Shiro验证URL时,URL匹配成功便不再继续匹配查找(所以要注意配置文件中的URL顺序,尤其在使用通配符时)
 * @see 故filterChainDefinitions的配置顺序为自上而下,以最上面的为准
 * @see 2)当运行一个Web应用程序时,Shiro将会创建一些有用的默认Filter实例,并自动地在[main]项中将它们置为可用
 * @see 自动地可用的默认的Filter实例是被DefaultFilter枚举类定义的,枚举的名称字段就是可供配置的名称
 * @see anon---------------org.apache.shiro.web.filter.authc.AnonymousFilter
 * @see authc
 *      --------------org.apache.shiro.web.filter.authc.FormAuthenticationFilter
 * @see authcBasic
 *      ---------org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
 * @see logout-------------org.apache.shiro.web.filter.authc.LogoutFilter
 * @see noSessionCreation
 *      --org.apache.shiro.web.filter.session.NoSessionCreationFilter
 * @see perms--------------org.apache.shiro.web.filter.authz.
 *      PermissionAuthorizationFilter
 * @see port---------------org.apache.shiro.web.filter.authz.PortFilter
 * @see rest
 *      ---------------org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
 * @see roles
 *      --------------org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
 * @see ssl----------------org.apache.shiro.web.filter.authz.SslFilter
 * @see user---------------org.apache.shiro.web.filter.authz.UserFilter
 * @see 
 *      ==========================================================================
 *      ===================================================
 * @see 3)通常可将这些过滤器分为两组
 * @see anon,authc,authcBasic,user是第一组认证过滤器
 * @see perms,port,rest,roles,ssl是第二组授权过滤器
 * @see 注意user和authc不同
 *      ：当应用开启了rememberMe时,用户下次访问时可以是一个user,但绝不会是authc,因为authc是需要重新认证的
 * @see user表示用户不一定已通过认证,只要曾被Shiro记住过登录状态的用户就可以正常发起请求,比如rememberMe
 * @see 说白了,以前的一个用户登录时开启了rememberMe,然后他关闭浏览器,下次再访问时他就是一个user,而不会authc
 * @see 
 *      ==========================================================================
 *      ===================================================
 * @see 4)举几个例子
 * @see /admin=authc,roles[admin] 表示用户必需已通过认证,并拥有admin角色才可以正常发起'/admin'请求
 * @see /edit=authc,perms[admin:edit]
 *      表示用户必需已通过认证,并拥有admin:edit权限才可以正常发起'/edit'请求
 * @see /home=user 表示用户不一定需要已经通过认证,只需要曾经被Shiro记住过登录状态就可以正常发起'/home'请求
 * @see 
 *      ==========================================================================
 *      ===================================================
 * @see 5)各默认过滤器常用如下(注意URL Pattern里用到的是两颗星,这样才能实现任意层次的全匹配)
 * @see /admins/**=anon 无参,表示可匿名使用,可以理解为匿名用户或游客
 * @see /admins/user/**=authc 无参,表示需认证才能使用
 * @see /admins/user/**=authcBasic 无参,表示httpBasic认证
 * @see /admins/user/**=user 无参,表示必须存在用户,当登入操作时不做检查
 * @see /admins/user/**=ssl 无参,表示安全的URL请求,协议为https
 * @see /admins/user/**=perms[user:add:*]
 * @see 参数可写多个
 *      ,多参时必须加上引号,且参数之间用逗号分割,如/admins/user/**=perms["user:add:*,user:modify:*"]
 * @see 当有多个参数时必须每个参数都通过才算通过,相当于isPermitedAll()方法
 * @see /admins/user/**=port[8081]
 * @see 当请求的URL端口不是8081时,跳转到schemal://serverName:8081?queryString
 * @see 其中schmal是协议http或https等
 *      ,serverName是你访问的Host,8081是Port端口,queryString是你访问的URL里的?后面的参数
 * @see /admins/user/**=rest[user]
 * @see 根据请求的方法,相当于/admins/user/**=perms[user:method],其中method为post,get,delete等
 * @see /admins/user/**=roles[admin]
 * @see 参数可写多个,多个时必须加上引号,且参数之间用逗号分割,如/admins/user/**=roles["admin,guest"]
 * @see 当有多个参数时必须每个参数都通过才算通过,相当于hasAllRoles()方法
 * @see 
 *      ==========================================================================
 *      ===================================================
 * 
 */
//@Component
public class DatabaseRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(DatabaseRealm.class);
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	SysMenuService sysMenuService;
	
	/*
	 * @Autowired private SystemPersonnelCollectionService loginService;
	 */

	/**
	 * 认证回调函数,登录时调用.
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		Object object = authcToken.getCredentials();
		token.setPassword(MD5Encoder.encode(String.valueOf((char[]) object)).toCharArray());
        String userName = (String)token.getPrincipal();  //得到用户名   
		logger.info("user = {} ,ip = {},pass{} login .......",authcToken.getPrincipal(), token.getHost(),new String(token.getPassword()));
		try {
			Map<String, Object> condititon = new HashMap<String, Object>();
			condititon.put("userName", userName);
			condititon.put("userStatus", 1);
			List<SysUser> userList = sysUserService.findModelsByCondition(condititon);
			if (userList.size() > 0) {
				SysUser user = userList.get(0);
				SimpleAuthenticationInfo sai = new SimpleAuthenticationInfo(userName,SpringSecurityUtils.generatePassword(user.getUserName(),user.getUserPwd()), getName());
				sai.setCredentialsSalt(ByteSource.Util.bytes(userName));
				/**
				 * 将用户信息放到session
				 */
				setSession("user",user);
				return sai;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.debug("shiro security auth exception. ", e);
			throw new AccountException(e.getMessage(), e);
		}
	}

	/**
	 * 重写获取用户权限的方法,获取用户角色和菜单权限
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next();
		String currentUsername = (String) super.getAvailablePrincipal(principals);
		logger.info("AuthorizationInfo userName = {}", currentUsername);
		SysUser userInfo = SpringSecurityUtils.getCurrentUser();
		if(userInfo!=null){
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
			List<SysRole> roleList = sysRoleService.selectRolesByUserId(userInfo.getUserId());
			Set<String> roleSet = new HashSet<String>();
			for(SysRole role : roleList){
				roleSet.add(role.getRoleName());
			}
			info.setRoles(roleSet);
			List<SysMenu> menuList = sysMenuService.selectMenusByUserId(userInfo.getUserId());
			for(SysMenu menu : menuList){
				if(!StringUtils.isEmpty(menu.getMenuUrl())){
					info.addStringPermission(menu.getMenuUrl());
				}				
			}			
            return info;
		}
		else{
			return null;
		}		
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用 使用时直接用HttpSession.getAttribute(key)就可以取到
	 * 
	 * @param key
	 * @param value
	 */
	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			logger.info("Session默认超时时间为 [{}]毫秒", session.getTimeout());
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	public static int SESSION_TIMEOUT = 1440 * 30;
}