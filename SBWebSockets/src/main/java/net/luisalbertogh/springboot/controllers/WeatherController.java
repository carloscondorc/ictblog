/**
 * The weather controller.
 */
package net.luisalbertogh.springboot.controllers;

import net.luisalbertogh.springboot.domain.WeatherData;
import net.luisalbertogh.springboot.dto.PlaceInfo;
import net.luisalbertogh.springboot.dto.WeatherInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

/**
 * @author lagarcia
 *
 */
@Controller
@EnableScheduling
public final class WeatherController {

	/** A logger reference */
	private static Logger logger = Logger.getLogger(WeatherController.class);

	/** The data storage. */
	@Autowired
	private WeatherData wData;

	/** Web socket message template */
	@Autowired
	private SimpMessagingTemplate template;

	/**
	 * Get weather info for a given place.
	 *
	 * @param place
	 *            - The place
	 * @return The weather info
	 */
	@MessageMapping("/weather")
	@SendTo("/topic/weatherinfo")
	public WeatherInfo getWeatherInfo(PlaceInfo place) {
		/* Default */
		WeatherInfo wInfo = new WeatherInfo();
		try {
			logger.info("Get weather from " + place.getPlace());

			/* Lets make a pause */
			Thread.sleep(1000);

			/* Return weather info for the given place */
			wInfo = wData.getWeatherFrom(place.getPlace());

			logger.info("Retrieving weather from " + place.getPlace());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return wInfo;
	}

	/**
	 * Run weather info periodically.
	 */
	// @Scheduled(fixedRate = 2000)
	public void runWInfo() {
		this.template.convertAndSend("/topic/weatherinfo",
				wData.getWeatherFrom("Madrid"));
	}

	/**
	 * @return the wData
	 */
	public WeatherData getwData() {
		return wData;
	}

	/**
	 * @param wDataArg
	 *            the wData to set
	 */
	public void setwData(WeatherData wDataArg) {
		wData = wDataArg;
	}
}
