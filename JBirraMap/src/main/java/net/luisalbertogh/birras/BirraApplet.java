/**
 * © 2009-2012 Tex Toll Services, LLC
 */
package net.luisalbertogh.birras;

import java.util.List;
import java.util.Properties;

import net.luisalbertogh.birras.data.DataLoader;
import net.luisalbertogh.birras.markers.BirraMarker;
import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;

/**
 * Birra applet.
 * 
 * @author lagarcia
 */
public class BirraApplet extends PApplet {

    /** The unfolding map. */
    private UnfoldingMap birraMap;

    /** Markers. */
    private List<BirraMarker> markers;

    /**
     * @see processing.core.PApplet#setup()
     */
    @Override
    public void setup() {
        /* Set canvas size */
        size(800, 600);

        /* Set proxy settings */
        Properties systemSettings = System.getProperties();
        systemSettings.put("http.proxyHost", "proxy.indra.es");
        systemSettings.put("http.proxyPort", "8080");
        systemSettings.put("http.proxyUser", "lagarcia");
        systemSettings.put("http.proxyPassword", "Zaratrusta02*");
        System.setProperties(systemSettings);

        /* Init map */
        birraMap = new UnfoldingMap(this, new OpenStreetMap.OpenStreetMapProvider());

        /* To basically interact with the map */
        MapUtils.createDefaultEventDispatcher(this, birraMap);

        /* Set origin */
        Location madridLocation = new Location(40.4f, -3.7f);
        birraMap.zoomAndPanTo(madridLocation, 6);

        /* Set restrictions */
        birraMap.setZoomRange(6, 8);

        /* Set markers */
        loadMarkers();

        /* Set event dispatcher on marker */
        // EventDispatcher ed = new EventDispatcher();
        // ed.addBroadcaster(new BirraHandler(this, birraMap));
        // ed.register(birraMap, "click");
    }

    /**
     * @see processing.core.PApplet#draw()
     */
    @Override
    public void draw() {
        /* Draw map */
        birraMap.draw();

        /* Get location from mouse pointer */
        // Location location = birraMap.getLocation(mouseX, mouseY);
        // fill(0);
        //
        // /* Print location coordinates */
        // text(location.getLat() + ", " + location.getLon(), mouseX, mouseY);
    }

    /**
     * @see processing.core.PApplet#mouseClicked()
     */
    @Override
    public void mouseClicked() {
        if (markers == null) {
            return;
        }

        /* Clear selection */
        for (Marker m : markers) {
            m.setSelected(false);
        }

        /* Select clicked marker */
        Marker m = birraMap.getFirstHitMarker(mouseX, mouseY);
        if (m != null) {
            m.setSelected(true);
        }
    }

    /**
     * @see processing.core.PApplet#mouseMoved()
     */
    @Override
    public void mouseMoved() {
        if (markers == null) {
            return;
        }

        /* Clear selection */
        for (Marker m : markers) {
            ((BirraMarker) m).setHover(false);
        }

        Marker m = birraMap.getFirstHitMarker(mouseX, mouseY);
        if (m != null) {
            ((BirraMarker) m).setHover(true);
        }
    }

    /**
     * Load markers.
     */
    private void loadMarkers() {
        /* Load data */
        DataLoader dl = new DataLoader();
        dl.loadData("data/birras.csv");

        /* Get markers */
        markers = dl.getBirraMarkers(loadImage("img/beer-icon.png"));

        /* Add markers to map */
        for (Marker m : markers) {
            birraMap.addMarkers(m);
        }
    }
}
