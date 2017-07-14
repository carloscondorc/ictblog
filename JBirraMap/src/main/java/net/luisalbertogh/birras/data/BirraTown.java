/**
 * © 2009-2012 Tex Toll Services, LLC
 */
package net.luisalbertogh.birras.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Data model.
 * 
 * @author lagarcia
 */
public class BirraTown {
    private String town;
    private Float latitude;
    private Float longitude;
    private List<String> birras;

    /**
     * Add birra.
     * 
     * @param birra
     */
    public final void addBirra(String birra) {
        if (birras == null) {
            birras = new ArrayList<String>();
        }

        birras.add(birra);
    }

    /**
     * Return town attribute.
     * 
     * @return town - Attribute returned
     */
    public final String getTown() {
        return town;
    }

    /**
     * Set attribute town.
     * 
     * @param townArg - Set value
     */
    public final void setTown(String townArg) {
        town = townArg;
    }

    /**
     * Return latitude attribute.
     * 
     * @return latitude - Attribute returned
     */
    public final Float getLatitude() {
        return latitude;
    }

    /**
     * Set attribute latitude.
     * 
     * @param latitudeArg - Set value
     */
    public final void setLatitude(Float latitudeArg) {
        latitude = latitudeArg;
    }

    /**
     * Return longitude attribute.
     * 
     * @return longitude - Attribute returned
     */
    public final Float getLongitude() {
        return longitude;
    }

    /**
     * Set attribute longitude.
     * 
     * @param longitudeArg - Set value
     */
    public final void setLongitude(Float longitudeArg) {
        longitude = longitudeArg;
    }

    /**
     * Return birras attribute.
     * 
     * @return birras - Attribute returned
     */
    public final List<String> getBirras() {
        return birras;
    }

    /**
     * Set attribute birras.
     * 
     * @param birrasArg - Set value
     */
    public final void setBirras(List<String> birrasArg) {
        birras = birrasArg;
    }
}
