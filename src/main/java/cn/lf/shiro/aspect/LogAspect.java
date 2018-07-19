package cn.lf.shiro.aspect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.javassist.ClassClassPath;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import cn.lf.oa.common.util.ParamUtil;
import cn.lf.shiro.dao.IOSInfoDao;
import cn.lf.shiro.service.ILogService;
import cn.lf.shiro.service.IOSInfoService;

/**
 * 日志相关切面用来监视所有类的使用情况
 * 
 * @author LFSenior
 *
 */
@Component
@Aspect
public class LogAspect {
	@Autowired
	ILogService logService;
	
	@Autowired
	IOSInfoService osInfoService;
	
	private static  ObjectWriter writer=new ObjectMapper().writer();
	/**
	 * 定义切点扫描所有cn.lf下所有的类的所有方法
	 */
	@Pointcut("execution(* cn.lf.shiro.controller.*.*(..)) || execution(* cn.lf.oa.web..*.*(..))")
	public void controller() {
	}

	/**
	 * 插入日志
	 * @throws Throwable 
	 */
	@SuppressWarnings("unchecked")
	@Around("controller()")
	public Object insertLog(ProceedingJoinPoint pjp) throws Throwable {
		Map param=new HashMap<>();
		try {
			/**获取类主对象*/
			Signature signature = pjp.getSignature();
			/**获取方法主对象*/
			MethodSignature methodSignature = (MethodSignature) signature;
			/**获取方法*/
			Method targetMethod = methodSignature.getMethod();
			/**获取所有参数的值*/
			Object[] args = pjp.getArgs();
			param.put("classname", targetMethod.getDeclaringClass().getName());
			/**
			 * 如果是日志相关类，就放行
			 */
			if(targetMethod.getDeclaringClass().getName().toUpperCase().contains("LOG")){
				return pjp.proceed();
			}
			param.put("superclass", targetMethod.getDeclaringClass().getSuperclass().getName());
			param.put("isinterface", targetMethod.getDeclaringClass().isInterface());
			param.put("target", pjp.getTarget().getClass().getName());
			param.put("proxy", pjp.getThis().getClass().getName());
			param.put("method", targetMethod.getName());
			param.put("nameandargs",args.toString());
			param.put("createdate", new Date());
			param.put("id", ParamUtil.buildUUID());
			logService.insertLog(param);
			insertOptionInfo(targetMethod.getDeclaringClass().getName());
		} catch (Exception e) {
			e.printStackTrace();
			return pjp.proceed();
		}
		return pjp.proceed();

	}

	/**
	 * 获取哦字段的名称与值的键值对
	 * @param cls
	 * @param clazzName
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		ClassPool pool = ClassPool.getDefault();
		ClassClassPath classPath = new ClassClassPath(cls);
		pool.insertClassPath(classPath);

		CtClass cc = pool.get(clazzName);
		CtMethod cm = cc.getDeclaredMethod(methodName);
		MethodInfo methodInfo = cm.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		if (attr == null) {
			// exception
		}
		int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
		for (int i = 0; i < cm.getParameterTypes().length; i++) {
			map.put(attr.variableName(i + pos), args[i]);// paramNames即参数名
		}
		return map;
	}
	
	/**
	 * 相关应用字段
	 */
	private static String PERSONOFFICE="personoffice";
	private static String PUBLICINFO="publicinfo";
	private static String INSTATION="instation";
	private static String MANAGEMENT="management";
	private static String AUTHORITY="authority";
	private static String SYSMANAGER="sysmanager";
	
	/**
	 * 更新用户操作记录表
	 */
	public void insertOptionInfo(String className){
		try {
			Map param=new HashMap<>();
			param.put("id", ParamUtil.buildUUID());
			try {
				String userId = ParamUtil.buildUserId();
				param.put("user_id", userId);
			} catch (Exception e) {
				e.printStackTrace();
				param.put("user_id", "1");
			}
			String cate="/";
			if(className.contains(INSTATION)){
				cate+=INSTATION;
			}else if(className.contains(PERSONOFFICE)){
				cate+=PERSONOFFICE;
			}else if(className.contains(SYSMANAGER)){
				cate+=SYSMANAGER;
			}else if(className.contains("controller")){
				//特殊点
				cate+=AUTHORITY;
			}else if(className.contains(MANAGEMENT)){
				cate+=MANAGEMENT;
			}else if(className.contains(PUBLICINFO)){
				cate+=PUBLICINFO;
			}else{
				return;
			}
			param.put("cate", cate);
			param.put("createdate", new Date());
			osInfoService.insertUserOption(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
