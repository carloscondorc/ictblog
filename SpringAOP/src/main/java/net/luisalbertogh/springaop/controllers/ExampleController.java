/**
 *
 */
package net.luisalbertogh.springaop.controllers;

import net.luisalbertogh.springaop.service.SpringAOPService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Example controller class.
 *
 * @author lagarcia
 *
 */
@RestController
public class ExampleController {

	/** A logger reference */
	private static Logger logger = Logger.getLogger(ExampleController.class);

	/** A Spring service */
	@Autowired
	private SpringAOPService aService;

	/**
	 * Go to home.
	 *
	 * @return
	 */
	@RequestMapping("/home")
	String home() {
		logger.info("Entering in HOME");
		return "Hola Spring AOP";
	}

	/**
	 * Perform a calculation.
	 *
	 * @return
	 */
	@RequestMapping("/calculate/{word}/{c}")
	String calculate(@PathVariable String word, @PathVariable String c) {
		logger.info("Entering in CALCULATE");
		int count = 0;
		try {
			count = this.getaService().countCharsService(word, c.charAt(0));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		logger.info("Finishing CALCULATE");

		return "¿Cuántas " + c + "es hay en la palabra " + word + " ? Hay "
		+ count;
	}

	/**
	 * @return the aService
	 */
	public SpringAOPService getaService() {
		return aService;
	}

	/**
	 * @param aServiceArg
	 *            the aService to set
	 */
	public void setaService(SpringAOPService aServiceArg) {
		aService = aServiceArg;
	}
}
