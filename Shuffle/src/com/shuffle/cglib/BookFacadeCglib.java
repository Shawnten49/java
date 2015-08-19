package com.shuffle.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class BookFacadeCglib implements MethodInterceptor {

	private Object target;
	
	public Object getInstance(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		
		return enhancer.create();
	}
	
	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2,
			MethodProxy proxy) throws Throwable {
		Object result = null;
		System.out.println("事务开始");
		result = proxy.invoke(arg0, arg2);
		System.out.println("事务结束");
		
		return result;
	}

}
