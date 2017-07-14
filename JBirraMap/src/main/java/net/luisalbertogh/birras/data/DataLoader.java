/**
 * © 2009-2012 Tex Toll Services, LLC
 */
package net.luisalbertogh.birras.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import net.luisalbertogh.birras.markers.BirraMarker;
import processing.core.PImage;

/**
 * Data loader.
 * 
 * @author lagarcia
 */
public class DataLoader {

    /** The birras list */
    private List<BirraTown> birras;

    /**
     * Load data from the file.
     * 
     * @param filePath
     */
    public void loadData(String filePath) {
        try {
            birras = new ArrayList<BirraTown>();
            BufferedReader bf = new BufferedReader(new FileReader(filePath));
            String line = null;
            while ((line = bf.readLine()) != null) {
                /* Skip comments */
                if (line.charAt(0) == '#') {
                    continue;
                }
                String[] parts = line.split(",");
                BirraTown bt = new BirraTown();
                bt.setTown(parts[0]);
                bt.setLatitude(new Float(parts[1]));
                bt.setLongitude(new Float(parts[2]));
                String[] beers = parts[3].split("-");
                for (String beer : beers) {
                    bt.addBirra(beer);
                }
                birras.add(bt);
            }
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the markers list.
     * 
     * @param imgMarker
     * @return
     */
    public List<BirraMarker> getBirraMarkers(PImage imgMarker) {
        List<BirraMarker> birraMarkers = new ArrayList<BirraMarker>();
        for (BirraTown bt : birras) {
            BirraMarker marker = new BirraMarker(bt, imgMarker);
            birraMarkers.add(marker);
        }
        return birraMarkers;
    }
}
