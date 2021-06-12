package com.hcl.user.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAspect {
	
	Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("execution(* com.hcl.user.controller.*")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("Inside Before Advice"+joinPoint.getSignature().getName());
	}
	
	
	@After("execution(* com.hcl.user.controller.*")
	public void logAfter(JoinPoint joinPoint) {
		logger.info("Inside After Advice "+joinPoint.getSignature().getName());
	}
	
	@AfterReturning("execution(* com.hcl.user.controller.*")
	public void logAfterReturning(JoinPoint joinPoint) {
		logger.info("Inside AfterReturning Advice "+joinPoint.getSignature().getName());
	}
	
	@AfterThrowing("execution(* com.hcl.user.controller.*")
	public void logAfterThrowing(JoinPoint joinPoint){
		logger.info("Inside AfterThrowing Advice "+joinPoint.getSignature().getName());
	}


}
