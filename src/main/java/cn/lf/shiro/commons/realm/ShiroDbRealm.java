package cn.lf.shiro.commons.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.lf.shiro.model.AuthenticaUser;
import cn.lf.shiro.service.IRoleService;
import cn.lf.shiro.service.IUserService;

/**
 * shiro配置Realm
 * 
 * @author LFSenior
 *
 */

public class ShiroDbRealm extends AuthorizingRealm {
	private static Logger LOGGER = LoggerFactory.getLogger(ShiroDbRealm.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;

	@Override
	public String getName() {
		return "shiroDbRealm";
	}

	// 支持什么类型的token
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取用户角色
		AuthenticaUser authenticaUser = (AuthenticaUser) principals.getPrimaryPrincipal();
		List<String> roleList = authenticaUser.getRoleList();
		// 根据角色赋相应的权限
		Set<String> urlSet = new HashSet<String>();
		for (String roleId : roleList) {
			List<Map<String, String>> roleResourceList = roleService.findRoleResourceListByRoleId(roleId);
			if (roleResourceList != null) {
				for (Map<String, String> map : roleResourceList) {
					if (StringUtils.isNoneBlank(map.get("URL"))) {
						urlSet.add(map.get("URL"));
					}
				}
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(urlSet);
		return info;
	}

	/**
	 * 认证
	 * 
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		LOGGER.info("Shiro开始登陆认证");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		AuthenticaUser authenticaUser = userService.findUserByLoginName(token.getUsername());
		// 账号不存在
		if (authenticaUser == null) {
			return null;
		}
		// 账号未启用
		if (authenticaUser.getLocked() == 1) {
			return null;
		}

		/* 获取该用户的角色id列表 */
		List<String> roleList = roleService.findRoleIdListByUserId(authenticaUser.getId());
		authenticaUser.setRoleList(roleList);
		// 认证缓存信息
		return new SimpleAuthenticationInfo(authenticaUser, authenticaUser.getPassword(),getName());
	}

}
