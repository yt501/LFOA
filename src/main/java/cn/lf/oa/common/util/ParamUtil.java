package cn.lf.oa.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;

import cn.lf.shiro.model.AuthenticaUser;

public class ParamUtil {
	/**
	 * 获取前台参数
	 * @param request
	 * @return
	 */
	public static Map<String,Object> buildParameter(HttpServletRequest request){
		Map<String, Object> parameter = new HashMap<String, Object>();
		java.util.Enumeration<String> paremEnu = null;
		paremEnu = request.getParameterNames();
		while (paremEnu.hasMoreElements()) {
			String paramName = paremEnu.nextElement();
			parameter.put(paramName, request.getParameter(paramName));
		}
		return parameter;
	}
	
	/**
	 * 获取16位的uuid
	 * @return
	 */
	public static String buildUUID(){
		int machineId = 1;//最大支持1-9个集群机器部署  
        int hashCodeV = UUID.randomUUID().toString().hashCode();  
        if(hashCodeV < 0) {//有可能是负数  
            hashCodeV = - hashCodeV;  
        }  
        return machineId + String.format("%015d", hashCodeV);  
	}
	
	
	/**
	 * 获取用户ID
	 * @return
	 */
	public static String buildUserId(){
		return getLoginObject().getId();
	}
	
	/**
	 * 获取用户名
	 * @return
	 */
	public static String buildUserName(){
		return getLoginObject().getUsername();
	}
	
	/**
	 * 构建默认密码
	 * @return
	 */
	public static String buildDefaultPassword(){
		return new Md5Hash("LFSenior").toString();
	}
	
	/**
	 * 将字符串转换成md5值
	 * @param str
	 * @return
	 */
	public static String md5Transformation(String str){
		return new Md5Hash(str).toString();
	}
	
	/**
	 * 获取登录对象
	 * @return
	 */
	public static AuthenticaUser getLoginObject(){
		return (AuthenticaUser) SecurityUtils.getSubject().getPrincipal(); 
	}
}
