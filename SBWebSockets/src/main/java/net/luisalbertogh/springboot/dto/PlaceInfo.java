/**
 * The palce info bean.
 */
package net.luisalbertogh.springboot.dto;

/**
 * @author lagarcia
 *
 */
public final class PlaceInfo {
	/** The place name. */
	private String place;

	/**
	 * @param placeArg
	 */
	public PlaceInfo() {
		/* Empty */
	}

	/**
	 * @param placeArg
	 */
	public PlaceInfo(String placeArg) {
		place = placeArg;
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

}
