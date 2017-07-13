/**
 * Weather info bean.
 */
package net.luisalbertogh.springboot.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import net.luisalbertogh.springboot.utils.WeatherType;

/**
 * @author lagarcia
 *
 */
public final class WeatherInfo {
	/** The weather type. */
	private WeatherType type;
	/** The local date and time. */
	private LocalDateTime time;
	/** The local date time as string. */
	private String localDateTimeStr;
	/** The place. */
	private String place;

	/**
	 * Defaul constructor.
	 */
	public WeatherInfo() {
		/* Empty */
	}

	/**
	 * Constructor with args.
	 *
	 * @param typeArg
	 * @param timeArg
	 * @param placeArg
	 */
	public WeatherInfo(WeatherType typeArg, LocalDateTime timeArg,
			String placeArg) {
		super();
		type = typeArg;
		time = timeArg;
		place = placeArg;
	}

	/**
	 * @return the type
	 */
	public WeatherType getType() {
		return type;
	}

	/**
	 * @param typeArg
	 *            the type to set
	 */
	public void setType(WeatherType typeArg) {
		type = typeArg;
	}

	/**
	 * @return the time
	 */
	public LocalDateTime getTime() {
		return time;
	}

	/**
	 * @param timeArg
	 *            the time to set
	 */
	public void setTime(LocalDateTime timeArg) {
		time = timeArg;
	}

	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param placeArg
	 *            the place to set
	 */
	public void setPlace(String placeArg) {
		place = placeArg;
	}

	/**
	 * @return the localDateTimeStr
	 */
	public String getLocalDateTimeStr() {
		return time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	/**
	 * @param localDateTimeStrArg
	 *            the localDateTimeStr to set
	 */
	public void setLocalDateTimeStr(String localDateTimeStrArg) {
		localDateTimeStr = localDateTimeStrArg;
	}

}
