import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysql.fabric.xmlrpc.base.Param;

import cn.lf.oa.common.util.ParamUtil;
import cn.lf.oa.test.service.ITestService;

public class TestMain {
	@Test
	public void testMybaties(){
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		System.out.println(context);
		System.out.println();
		/*SqlSessionTemplate sqlSession=(SqlSessionTemplate) context.getBean("sqlSessionTemplate");
		System.out.println(sqlSession.selectOne("test.testMapper"));*/
		ITestService testService = context.getBean(ITestService.class);
		System.out.println(testService.getNumber());
	}
	
	@Test
	public void testUUID(){
		System.out.println(ParamUtil.buildUUID());
	}
	
	
	@Test
	public void testMather(){
		
	}
}
