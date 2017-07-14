/**
 * 
 */
package net.luisalbertogh.jnumbers.renderer;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import prefuse.render.LabelRenderer;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;

/**
 * @author lagarcia
 */
public class MyNodeRenderer extends LabelRenderer {

    /**
     * TODO Description
     */
    public MyNodeRenderer() {
        super();
    }

    /**
     * TODO Description
     * 
     * @param attr
     */
    public MyNodeRenderer(String attr) {
        super(attr);
    }

    @Override
    protected Image getImage(VisualItem item) {
        Image retImage = null;

        if (item instanceof NodeItem) {
            try {
                String type = (String) ((NodeItem) item).get("type");
                if (type.equalsIgnoreCase("dir")) {
                    retImage = ImageIO.read(new File("icons/folder-16.png"));
                } else if (type.equalsIgnoreCase("file")) {
                    retImage = ImageIO.read(new File("icons/file-16.png"));
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        return retImage;
    }

    @Override
    protected String getText(VisualItem item) {
        item.setFont(new Font("Arial", Font.PLAIN, 8));
        String str = (String) item.get("name");
        return str;
    }
}
