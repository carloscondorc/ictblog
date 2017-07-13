/**
 * 
 */
package net.luisalbertogh.springboot.controllers;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Example controller class.
 * @author lagarcia
 *
 */
@RestController
public class ExampleController {

	/** A logger reference */
	private static Logger logger = Logger.getLogger(ExampleController.class);
	
	/**
	 * Go to home.
	 * @return
	 */
	@RequestMapping("/home")
	String home(){
		logger.info("Entering in HOME");
		return "Hola Spring Boot";
	}
}
