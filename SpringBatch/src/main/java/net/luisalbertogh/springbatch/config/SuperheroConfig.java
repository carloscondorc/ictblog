/**
 * Superhero configuration class.
 */
package net.luisalbertogh.springbatch.config;

import net.luisalbertogh.springbatch.audit.AuditorAwareImpl;
import net.luisalbertogh.springbatch.dao.SuperheroDAO;
import net.luisalbertogh.springbatch.domain.Superhero;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author lagarcia
 *
 */
@Configuration
@EnableAutoConfiguration
@EnableJpaAuditing
public class SuperheroConfig {
	/**
	 * Store some superheros for testing.
	 *
	 * @param sheroDAO
	 *            - The superhero DAO
	 * @return The command line runner
	 */
	@Bean
	public CommandLineRunner demo(SuperheroDAO sheroDAO) {
		return (args) -> {
			sheroDAO.save(new Superhero("Superman", "Super strengh", false));
			sheroDAO.save(new Superhero("Dr. Magneto", "Super mutant", true));
			sheroDAO.save(new Superhero("Flash", "Super speed", false));
		};
	}

	/**
	 * Spring JPA auditor aware implementation.
	 *
	 * @return The auditor aware bean
	 */
	@Bean
	public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}
}
