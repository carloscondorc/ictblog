/**
 * 
 */
package net.luisalbertogh.jnumbers.renderer;

import prefuse.action.assignment.ColorAction;
import prefuse.util.ColorLib;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;

/**
 * @author lagarcia
 */
public class MyColorFiller extends ColorAction {

    private String attr;
    private String colorField;
    private int[] palette = new int[] {ColorLib.rgb(255, 215, 0), ColorLib.rgb(135, 206, 235) };

    /**
     * TODO Description
     * 
     * @param group
     * @param colorField
     * @param attr
     */
    public MyColorFiller(String group, String colorField, String attr) {
        super(group, colorField);
        this.attr = attr;
        this.colorField = colorField;
    }

    /**
     * @see prefuse.action.assignment.ColorAction#getColor(prefuse.visual.VisualItem)
     */
    @Override
    public int getColor(VisualItem item) {
        int color = super.getColor(item);

        if (item instanceof NodeItem && !item.isFixed()) {
            String type = (String) ((NodeItem) item).get(this.attr);
            if (type.equalsIgnoreCase("dir")) {
                color = palette[0];
            } else {
                color = palette[1];
            }
        }

        return color;
    }
}
