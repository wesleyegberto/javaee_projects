package com.github.wesleyegberto.cditests.interceptors;

import javax.annotation.Priority;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.github.wesleyegberto.cditests.qualifiers.Loggable;

@Interceptor
@Loggable
@Priority(value = javax.interceptor.Interceptor.Priority.APPLICATION)
public class LoggerInterceptor {
	
	@AroundInvoke
	public Object log(InvocationContext ctx) throws Exception {
		System.out.println("Logging method: about to call " + ctx.getMethod().getName() + "() at " + System.currentTimeMillis());
		
		Object returnValue = ctx.proceed();
		
		System.out.println("Logging method: " + ctx.getMethod().getName() + "() ended at " + System.currentTimeMillis());
		
		return returnValue;
	}
	
	@AroundConstruct
	public void constructorLogger(InvocationContext ctx) throws Exception {
		System.out.println("Logging constructor: about to call the constructor at " + System.currentTimeMillis());
		// we must proceed to constructor before call getTarget(), otherwise we'll get a NullPointerException
		ctx.proceed();
		
		// now we can get the created object
		System.out.println("Logging constructor: " + ctx.getTarget() + " at " + System.currentTimeMillis());
	}
}
