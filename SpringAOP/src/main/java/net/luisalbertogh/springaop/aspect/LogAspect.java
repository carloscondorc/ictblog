/**
 * A logging aspect.
 */
package net.luisalbertogh.springaop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author lagarcia
 *
 */
@Aspect
@Component
public class LogAspect {

	/** A logger reference */
	private static Logger logger = Logger.getLogger(LogAspect.class);

	/**
	 * Log before method is executed.
	 *
	 * @param joinPoint
	 */
	@Before("execution(* net.luisalbertogh.springaop..*Service*(..))")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("logBefore");
		logger.info("Log before in: " + joinPoint.getSignature().getName());
	}

	/**
	 * Log after method is executed.
	 *
	 * @param joinPoint
	 */
	// @After("execution(* net.luisalbertogh.springaop..*Service*(..))")
	// public void logAfter(JoinPoint joinPoint) {
	// logger.info("logAfter");
	// logger.info("Log after in: " + joinPoint.getSignature().getName());
	// }

	/**
	 * Log after method is executed and get returned value.
	 *
	 * @param joinPoint
	 * @param result
	 */
	@AfterReturning(pointcut = "execution(* net.luisalbertogh.springaop..*Service*(..))", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		logger.info("logAfterReturning");
		logger.info("Log after in: " + joinPoint.getSignature().getName());
		logger.info("- And value returned is: " + result);
	}

	/**
	 * Log after exception is thrown.
	 *
	 * @param joinPoint
	 * @param exception
	 */
	@AfterThrowing(pointcut = "execution(* net.luisalbertogh.springaop..*Service*(..))", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		logger.info("logAfterThrowing");
		logger.info("Log in: " + joinPoint.getSignature().getName());
		logger.info("- And thrown exception is: " + exception.getMessage());
	}

	/**
	 * Log around the method invocation.
	 *
	 * @param joinPoint
	 * @throws Throwable
	 */
	// @Around("execution(* net.luisalbertogh.springaop..*Service*(..))")
	// public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
	// logger.info("logAround");
	// logger.info("Log before in: " + joinPoint.getSignature().getName());
	// /* Back to invoked method */
	// joinPoint.proceed();
	// logger.info("Log after in: " + joinPoint.getSignature().getName());
	// }
}
