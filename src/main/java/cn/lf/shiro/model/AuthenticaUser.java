package cn.lf.shiro.model;

import java.io.Serializable;
import java.util.List;

/**
 * 登陆用户信息数据
 * @author LFSenior
 *
 */
public class AuthenticaUser implements Serializable{
	//表示用户被锁定
	public static int LOCKED=1;
	//表示用户未被说定
	public static int UNLOCKED=0;
	private String id;//用户id
	private String username;//用户名称
	private String usercode;//用户代码
	private String password;//用户密码
	private String salt;//加密用的盐
	private int locked;//是否可用
	private List<String> roleList;//角色列表
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public List<String> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}
	
}
