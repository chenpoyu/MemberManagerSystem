package com.poyu.mms.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAop {

	Logger logger = LoggerFactory.getLogger(LoggerAop.class);

	@Pointcut("execution(* com.poyu.practice..controller.*.*(..))")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void beginTransaction() {
		System.out.println("2. before beginTransaction");
	}

	@After("pointcut()")
	public void commit() {
		System.out.println("4. after commit");
	}

	@AfterReturning(pointcut = "pointcut()", returning = "returnObject")
	public void afterReturning(JoinPoint joinPoint, Object returnObject) {
		System.out.println("5. afterReturning");
	}

	@AfterThrowing("pointcut()")
	public void afterThrowing() {
		System.out.println("afterThrowing afterThrowing  rollback");
	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			System.out.println("1. around");

//			joinPoint.getSignature() -> login
//			joinPoint.getTarget() -> LoginController
			logger.info("ajasdjfjsadljlkl");
			logger.warn("dddddddddddddddddddddddddddd");
			logger.error("ccccccccccccccccccccccccccc");
			return joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		} finally {
			System.out.println("3. around");
		}
	}
}
