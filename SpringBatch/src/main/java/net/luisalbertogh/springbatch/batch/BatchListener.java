/**
 *
 */
package net.luisalbertogh.springbatch.batch;

import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * @author lagarcia
 *
 */
@Component
public class BatchListener extends JobExecutionListenerSupport {

	/** A logger reference */
	private static Logger logger = Logger.getLogger(BatchListener.class);

	/**
	 * After job execution.
	 */
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			logger.info("¡¡Job " + jobExecution.getJobId() + " finalizado!!");
		}
	}
}
