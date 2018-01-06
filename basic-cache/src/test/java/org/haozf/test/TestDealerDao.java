package org.haozf.test;

import java.util.ArrayList;
import java.util.HashMap;
import org.haozf.aop.RedisProxy;

public class TestDealerDao {

	private static TestDealerDao s = null;

	public static TestDealerDao getInstance() {
		if (s == null) {
			s = (TestDealerDao) new RedisProxy().bind(new TestDealerDao());
		}
		return s;
	}
	
	public ArrayList getDealerList(int currPage, int pageSize,HashMap<String, String> param_hm) {
		ArrayList list = new ArrayList();
		return list;
	}
}
