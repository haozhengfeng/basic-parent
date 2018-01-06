package org.haozf.aop;

import java.lang.reflect.Method;

import org.haozf.util.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

@Component
public class RedisProxy {

	@Autowired
	RedisClient redisClient;
	
	/**
	 * 委托类对象
	 */
	private Object target;

	/**
	 * 绑定委托类对象
	 * 
	 * @param target
	 * @return
	 */
	public Object bind(final Object target) {
		this.target = target;
		// 加载需要创建子类的类
		Enhancer hancer = new Enhancer();
		// 设置代理目标
		hancer.setSuperclass(this.target.getClass());
		// 设置回调
		hancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				/**
				 * 没办法确定key
				 */
				System.out.println("判断redis是否存在");
				String key = "";
				for(int i=0;i<args.length;i++){
					key = key + args[i];
				}
				if("true".equals(redisClient.exists(key))){
					
				}
				return method.invoke(target, args);
			}
		});
		hancer.setClassLoader(target.getClass().getClassLoader());
		return hancer.create();// 返回子类对象
	}

}
