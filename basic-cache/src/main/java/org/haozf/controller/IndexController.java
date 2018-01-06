package org.haozf.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.haozf.common.BaseController;
import org.haozf.service.RedisService;
import org.haozf.test.TestDealerDao;
import org.haozf.util.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController extends BaseController {

	@Autowired
	RedisService redisComponet;

	@Autowired
	RedisClient redisClient;

	@RequestMapping(value = { "", "index" })
	@ResponseBody
	public Map<String, String> index(HttpServletRequest request) {
		Map<String, String> rt = new HashMap<String, String>();
//		if (!"true".equals(redisClient.exists("test"))) {
//			String key = redisClient.set("test", "测试生成策略");
//			String value = redisClient.get(key);
//			rt.put("data", value);
//		}
		
		TestDealerDao.getInstance().getDealerList(1, 10, null);
		
		return rt;
	}

}
