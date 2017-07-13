/**
 * Superhero batch configuration.
 */
package net.luisalbertogh.springbatch.batch;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import net.luisalbertogh.springbatch.domain.Superhero;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lagarcia
 *
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	/** Entity manager factory */
	@Autowired
	private EntityManagerFactory emFactory;

	/**
	 * Superhero item reader.
	 *
	 * @return The superhero item
	 */
	@Bean
	public ItemReader<Superhero> reader() {
		JpaPagingItemReader<Superhero> ireader = new JpaPagingItemReader<>();
		ireader.setEntityManagerFactory(this.getEmFactory());
		ireader.setQueryString("select entity from Superhero entity order by entity.name desc");
		ireader.setPageSize(5);
		return ireader;
	}

	/**
	 * Superhero item processor.
	 *
	 * @return The superhero processor
	 */
	@Bean
	public ItemProcessor<Superhero, Superhero> processor() {
		return new SuperheroProcessor();
	}

	/**
	 * Superhero item writer.
	 *
	 * @param dataSource
	 *            - The datasource
	 * @return The superhero item writer
	 */
	@Bean
	public ItemWriter<Superhero> writer(DataSource dataSource) {
		JpaItemWriter<Superhero> iwriter = new JpaItemWriter<>();
		iwriter.setEntityManagerFactory(this.getEmFactory());
		return iwriter;
	}

	/**
	 * Spring batch job.
	 *
	 * @param jobs
	 * @param s1
	 * @param listener
	 * @return
	 */
	@Bean
	public Job superHeroJob(JobBuilderFactory jobs, Step s1,
			JobExecutionListener listener) {
		return jobs.get("superHeroJob").incrementer(new RunIdIncrementer())
				.listener(listener).flow(s1).end().build();
	}

	/**
	 * Spring batch superhero job - Step 01
	 *
	 * @param stepBuilderFactory
	 * @param reader
	 * @param writer
	 * @param processor
	 * @return
	 */
	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory,
			ItemReader<Superhero> reader, ItemWriter<Superhero> writer,
			ItemProcessor<Superhero, Superhero> processor) {
		return stepBuilderFactory.get("step01")
				.<Superhero, Superhero> chunk(10).reader(reader)
				.processor(processor).writer(writer).build();
	}

	/**
	 * @return the emFactory
	 */
	public EntityManagerFactory getEmFactory() {
		return emFactory;
	}

	/**
	 * @param emFactoryArg
	 *            the emFactory to set
	 */
	public void setEmFactory(EntityManagerFactory emFactoryArg) {
		emFactory = emFactoryArg;
	}
}
