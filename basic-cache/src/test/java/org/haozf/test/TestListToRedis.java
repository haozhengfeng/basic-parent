package org.haozf.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.haozf.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestListToRedis {

	@Autowired
	RedisService redisService;

	@Autowired
	StringRedisTemplate redisTemplate;

	@Test
	public void testListToRedis() {
		List<Map<String, String>> rs = new ArrayList<Map<String, String>>();

		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "张三");
		map.put("password", "asfdji12esadfjdfj123");
		map.put("age", "25");
		rs.add(map);

		JSONArray jsonarray = JSONArray.fromObject(rs);
		String js = jsonarray.toString();
		redisService.set("list", js);
	}

	@Test
	public void testGetList() {
		try {
			String list = redisService.get("list");
			ObjectMapper mapper = new ObjectMapper();
			List readValue = mapper.readValue(list, List.class);

			JSONArray jsonarray = JSONArray.fromObject(readValue);
			String js = jsonarray.toString();
			System.out.println("list:" + js);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testKeys() {
		Set<String> keys = redisService.keys("*");
		System.out.println(keys);
		for (String key : keys) {
			System.out.println(key + "  " + redisService.type(key) + "   " + redisService.get(key));
		}
	}

	@Test
	public void removeKeys() {
		Set<String> keys = redisService.keys("*");
		System.out.println(keys);
		for (String key : keys) {
			if (!"STRING".equals(redisService.type(key))) {
				redisService.remove(key);
				continue;
			}

			System.out.println(redisService.get(key));
		}
	}

	@Test
	public void TestProx() {
		HashMap<String, String> param_hm = new HashMap<String, String>();
		param_hm.put("id", "123123");
		param_hm.put("classid", "0001");
		TestDealerDao.getInstance().getDealerList(1, 10, param_hm);
	}

}
