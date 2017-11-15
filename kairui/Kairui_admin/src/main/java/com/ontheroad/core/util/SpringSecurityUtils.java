package com.ontheroad.core.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.Subject;

import com.ontheroad.entity.SysUser;



/**
 * Shiro Security的工具类.
 * 
 */
public class SpringSecurityUtils {

	/**
	 * 取得当前用户的登录名, 如果当前用户未登录则返回空字符串.
	 */
	public static String getCurrentUserName() {
		return String.valueOf(SecurityUtils.getSubject().getPrincipal());
	}
	
	
	public static SysUser getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		try {
			return (SysUser) subject.getSession().getAttribute("user");
		} catch (Exception e) {
			if (subject.isAuthenticated()) {
				subject.getSession().stop();
			}
			return null;
		}
	}

	public static String generatePassword(String userName, String password) {
		String algorithmName = "md5";
		int hashIterations = 2;
		SimpleHash hash = new SimpleHash(algorithmName, password,
				userName, hashIterations);
		return hash.toHex();
	}
	
	public static  void clearAllCachedAuthorizationInfo(){
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();  
		AuthorizingRealm realm = (AuthorizingRealm)securityManager.getRealms().iterator().next(); 
		Cache<Object, AuthorizationInfo> cache = realm.getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

}