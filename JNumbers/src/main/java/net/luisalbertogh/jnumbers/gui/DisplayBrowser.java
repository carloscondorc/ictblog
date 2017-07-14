/**
 * 
 */
package net.luisalbertogh.jnumbers.gui;

import java.awt.Color;

import prefuse.Display;
import prefuse.Visualization;
import prefuse.controls.DragControl;
import prefuse.controls.NeighborHighlightControl;
import prefuse.controls.PanControl;
import prefuse.controls.WheelZoomControl;
import prefuse.controls.ZoomControl;

/**
 * @author lagarcia
 */
public class DisplayBrowser extends Display {

    /**
     * TODO Description
     */
    public DisplayBrowser() {
        super(new Visualization());
        initDisplay();
    }

    /**
     * TODO Description
     * 
     * @param vis
     */
    public DisplayBrowser(Visualization vis) {
        super(vis);
        initDisplay();
    }

    private void initDisplay() {
        this.setBackground(Color.GRAY);
        this.pan(getWidth() / 2, getHeight() / 2); // set initial position
        this.setSize(720, 500); // set display size
        this.addControlListener(new DragControl()); // drag items around
        this.addControlListener(new PanControl()); // pan with background left-drag
        this.addControlListener(new ZoomControl()); // zoom with vertical right-drag
        this.addControlListener(new WheelZoomControl()); // zoom with mouse wheel
        this.addControlListener(new NeighborHighlightControl()); // Neighbour highlight control
    }
}
