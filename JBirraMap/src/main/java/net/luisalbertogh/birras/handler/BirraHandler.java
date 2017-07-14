/**
 * © 2009-2012 Tex Toll Services, LLC
 */
package net.luisalbertogh.birras.handler;

import net.luisalbertogh.birras.BirraApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.interactions.MouseHandler;

/**
 * Birra handler.
 * 
 * @author lagarcia
 */
public class BirraHandler extends MouseHandler {

    /** Birra applet. */
    BirraApplet birraApplet;

    /** Birra map. */
    UnfoldingMap birraMap;

    /**
     * Constructor.
     * 
     * @param papletArg
     * @param mapArg
     */
    public BirraHandler(BirraApplet papletArg, UnfoldingMap mapArg) {
        super(papletArg, mapArg);
        birraApplet = papletArg;
        birraMap = mapArg;
    }

    /**
     * @see de.fhpotsdam.unfolding.interactions.MouseHandler#mouseClicked()
     */
    @Override
    public void mouseClicked() {
        System.out.println("CLICK!");
    }
}
