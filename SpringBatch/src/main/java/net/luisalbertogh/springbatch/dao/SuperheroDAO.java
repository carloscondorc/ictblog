/**
 * The superhero interface using Spring JPA.
 */
package net.luisalbertogh.springbatch.dao;

import net.luisalbertogh.springbatch.domain.Superhero;

import org.springframework.data.repository.CrudRepository;

/**
 * @author lagarcia
 *
 */
public interface SuperheroDAO extends CrudRepository<Superhero, String> {
}
