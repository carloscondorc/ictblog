/**
 * The batch step superhero processor.
 */
package net.luisalbertogh.springbatch.batch;

import net.luisalbertogh.springbatch.domain.Superhero;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author lagarcia
 *
 */
public class SuperheroProcessor implements ItemProcessor<Superhero, Superhero> {

	/** A logger reference */
	private static Logger logger = Logger.getLogger(SuperheroProcessor.class);

	/**
	 * Process superhero.
	 */
	@Override
	public Superhero process(Superhero superheroArg) throws Exception {
		if (superheroArg != null) {
			logger.info("Procesando a " + superheroArg.getName());
			switch (superheroArg.getName()) {
			case "Superman": {
				superheroArg.setWeakness("Kryptonite");
				break;
			}
			case "Flash": {
				superheroArg.setWeakness("Speed of light");
				break;
			}
			case "Dr. Magneto": {
				superheroArg.setWeakness("Plastic");
				break;
			}
			}

			return superheroArg;
		}
		return null;
	}

}
