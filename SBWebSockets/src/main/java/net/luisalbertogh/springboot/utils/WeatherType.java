/**
 * Weather types.
 */
package net.luisalbertogh.springboot.utils;

/**
 * @author lagarcia
 *
 */
public enum WeatherType {

	/** SUNNY */
	SUNNY("Sunny", "The sun is shinnning"),
	/** RAINY */
	RAINY("Rainy", "The rain is soaking me"),
	/** WINDY */
	WINDY("Windy", "Strong guts of wind");

	/** The weather name. */
	private String name;

	/** The weather description. */
	private String description;

	/**
	 * Constructor with args.
	 *
	 * @param name
	 * @param description
	 */
	WeatherType(String name, String description) {
		this.name = name;
		this.description = description;
	}

	/**
	 * Get the name.
	 * 
	 * @return The name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get the description
	 * 
	 * @return The description
	 */
	public String getDescription() {
		return this.description;
	}
}
