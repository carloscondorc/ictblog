/**
 * Superhero MVC controller.
 */
package net.luisalbertogh.springbatch.controllers;

import java.util.List;

import net.luisalbertogh.springbatch.dao.OtherSuperheroDAO;
import net.luisalbertogh.springbatch.dao.SuperheroDAO;
import net.luisalbertogh.springbatch.domain.Superhero;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Example controller class.
 *
 * @author lagarcia
 *
 */
@RestController
public final class SuperheroController {

	/** A logger reference */
	private static Logger logger = Logger.getLogger(SuperheroController.class);

	/** The superhero DAO */
	@Autowired
	private SuperheroDAO heroDAO;

	/** The other superhero DAO */
	@Autowired
	private OtherSuperheroDAO otherHeroDAO;

	/**
	 * Go to home.
	 *
	 * @return
	 */
	@RequestMapping("/home")
	public String home() {
		logger.info("Entering in HOME");
		return "Hola Spring Boot";
	}

	/**
	 * Find all superheros.
	 *
	 * @return The superheros list.
	 */
	@RequestMapping("/superheros")
	public String findAllSuperheros() {
		String list = "";
		List<Superhero> sheros = (List<Superhero>) this.getHeroDAO().findAll();
		if (sheros != null && !sheros.isEmpty()) {
			for (Superhero hero : sheros) {
				list += hero + "<br/>";
			}
		}
		return list;
	}

	/**
	 * Find all superheros.
	 *
	 * @return The superheros list.
	 */
	@RequestMapping("/superheros2")
	public String findAllSuperheros2() {
		String list = "";
		List<Superhero> sheros = this.getOtherHeroDAO().findAll();
		if (sheros != null && !sheros.isEmpty()) {
			for (Superhero hero : sheros) {
				list += hero + "<br/>";
			}
		}
		return list;
	}

	/**
	 * @return the heroDAO
	 */
	public SuperheroDAO getHeroDAO() {
		return heroDAO;
	}

	/**
	 * @param heroDAOArg
	 *            the heroDAO to set
	 */
	public void setHeroDAO(SuperheroDAO heroDAOArg) {
		heroDAO = heroDAOArg;
	}

	/**
	 * @return the otherHeroDAO
	 */
	public OtherSuperheroDAO getOtherHeroDAO() {
		return otherHeroDAO;
	}

	/**
	 * @param otherHeroDAOArg
	 *            the otherHeroDAO to set
	 */
	public void setOtherHeroDAO(OtherSuperheroDAO otherHeroDAOArg) {
		otherHeroDAO = otherHeroDAOArg;
	}
}
