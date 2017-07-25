package com.mohamedcodes.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	
	 //setup logger
	private Logger myLogger=Logger.getLogger(getClass().getName());
	
	//setup pointcut declarations
	@Pointcut("execution (* com.mohamedcodes.controller.*.*(..))")
	private void forControllerPackage(){}
	
	
	//the same for service 
		@Pointcut("execution (* com.mohamedcodes.service.*.*(..))")
		private void forServicePackage(){}
	
		//the same for dao
		@Pointcut("execution (* com.mohamedcodes.dao.*.*(..))")
		private void forDaoPackage(){}
		
		
		//combine all pointcut
		@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
		private void forAppFlow(){}
		
	//add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint){
		
	//display method we are calling
		String theMethod=theJoinPoint.getSignature().toShortString();
		myLogger.info("====>>>> in@Before calling method: " + theMethod);
		
		//display arguments to the method
		
		//get the arguments (array of objects)
		Object[]args=theJoinPoint.getArgs();
  
		
		//loop thru and display args
		for(Object tempArg : args){
			
			myLogger.info("======>>>" + tempArg);
		}
	}
		
		
	//add @AfterReturning advice
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="theResult")
	
	public void afterReturning(JoinPoint theJoinPoint, Object theResult){
	
	//display method we are returning from
	String theMethod= theJoinPoint.getSignature().toShortString();
	myLogger.info("====>>>> in @AfterReturning : from  method: " + theMethod);
	
	//display data returned 
	
			
				myLogger.info("======>>> results: " + theResult);
	}
			
	
	
}
