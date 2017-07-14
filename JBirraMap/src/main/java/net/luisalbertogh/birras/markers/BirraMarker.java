/**
 * © 2009-2012 Tex Toll Services, LLC
 */
package net.luisalbertogh.birras.markers;

import java.awt.Dimension;

import net.luisalbertogh.birras.data.BirraTown;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;

/**
 * Birra marker.
 * 
 * @author lagarcia
 */
public class BirraMarker extends SimplePointMarker {

    /** The name */
    private String name;

    /** The image. */
    private PImage img;

    /** The elipse dimension. */
    private Dimension elipseDim;

    /** The text. */
    private String text;

    /** The hover flag. */
    private boolean hover;

    /** Icon width */
    private static final int ICON_WIDTH = 100;

    /** Icon height */
    private static final int ICON_HEIGHT = 70;

    /** Number of shown birras. */
    private int numBirras;

    /**
     * Constructor.
     * 
     * @param bTown
     * @param img
     */
    public BirraMarker(BirraTown bTown, PImage img) {
        if (bTown != null) {
            this.location = new Location(bTown.getLatitude(), bTown.getLongitude());
            this.img = img;
            this.name = bTown.getTown();
            String text = "";
            for (String s : bTown.getBirras()) {
                text += s + "\n";
            }
            this.numBirras = bTown.getBirras().size();
            int height = this.numBirras * 18;
            this.elipseDim = new Dimension(ICON_WIDTH, height);
            this.text = text;
        }
    }

    /**
     * Constructor.
     * 
     * @param location
     * @param img
     * @param name
     * @param text
     */
    @Deprecated
    public BirraMarker(Location location, PImage img, String name, String text) {
        this.location = location;
        this.img = img;
        this.name = name;
        this.elipseDim = new Dimension(ICON_WIDTH, ICON_HEIGHT);
        this.text = text;
    }

    /**
     * @see de.fhpotsdam.unfolding.marker.AbstractMarker#draw(processing.core.PGraphics, float, float)
     */
    @Override
    public void draw(PGraphics pg, float x, float y) {
        pg.pushStyle();
        pg.pushMatrix();

        pg.imageMode(PConstants.CORNER);
        // The image is drawn in object coordinates, i.e. the marker's origin (0,0) is at its geo-location.
        pg.image(img, x - (img.width / 2), y - (img.width / 2));

        /* Selected */
        if (selected) {
            /* Rounded rectangle */
            pg.translate(x, (int) (y - (this.numBirras * 12.5)));
            pg.stroke(0);
            pg.fill(255);
            pg.strokeWeight(1);
            pg.rectMode(PConstants.CENTER);
            pg.rect(0, 0, elipseDim.width, elipseDim.height, 7, 7);

            /* Triangle */
            pg.beginShape();
            pg.vertex(-5, (int) (this.numBirras * 8.75));
            pg.vertex(5, (int) (this.numBirras * 8.75));
            pg.vertex(0, (int) (this.numBirras * 12.5));
            pg.endShape(PConstants.CLOSE);

            /* Text */
            pg.fill(0);
            pg.text(this.text, -((elipseDim.width / 2) - 5), -((elipseDim.height / 2) - 12));
        }
        /* Hover */
        else if (hover) {
            pg.translate(x, y);
            pg.stroke(0);
            pg.fill(255);
            pg.strokeWeight(1);
            pg.rectMode(PConstants.CENTER);
            pg.rect(40, 0, 80, 20, 7, 7);

            pg.fill(0);
            pg.textAlign(PConstants.CENTER);
            pg.text(this.name, 40, 5);
        }

        pg.popMatrix();
        pg.popStyle();
    }

    /**
     * Return name attribute.
     * 
     * @return name - Attribute returned
     */
    public final String getName() {
        return name;
    }

    /**
     * Set attribute name.
     * 
     * @param nameArg - Set value
     */
    public final void setName(String nameArg) {
        name = nameArg;
    }

    /**
     * Return text attribute.
     * 
     * @return text - Attribute returned
     */
    public final String getText() {
        return text;
    }

    /**
     * Set attribute text.
     * 
     * @param textArg - Set value
     */
    public final void setText(String textArg) {
        text = textArg;
    }

    /**
     * Return hover attribute.
     * 
     * @return hover - Attribute returned
     */
    public final boolean isHover() {
        return hover;
    }

    /**
     * Set attribute hover.
     * 
     * @param hoverArg - Set value
     */
    public final void setHover(boolean hoverArg) {
        hover = hoverArg;
    }
}
