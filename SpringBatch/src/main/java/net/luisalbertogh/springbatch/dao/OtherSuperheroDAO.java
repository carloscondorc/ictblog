/**
 * Other superhero DAO with Hibernate JPA.
 */
package net.luisalbertogh.springbatch.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.luisalbertogh.springbatch.domain.Superhero;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * @author lagarcia
 *
 */
@Repository
public class OtherSuperheroDAO {

	/** A logger reference */
	private static Logger logger = Logger.getLogger(OtherSuperheroDAO.class);

	/** JPA entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Find all superheros.
	 *
	 * @return A superhero list
	 */
	public List<Superhero> findAll() {
		logger.info("En Other supherhero DAO");
		/* Create query */
		Query query = getEntityManager().createQuery(
				"select entity from Superhero entity");
		return query.getResultList();
	}

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManagerArg
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManagerArg) {
		entityManager = entityManagerArg;
	}
}
