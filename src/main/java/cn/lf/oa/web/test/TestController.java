package cn.lf.oa.web.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
public class TestController {
	@RequestMapping("redicterTestPage")
	public String redicterTestPage(){
		return "test/test";
	}
	
	@RequestMapping("redicterTestPage2.json")
	public String redicterTestPage2(){
		return "test/test";
	}
}
