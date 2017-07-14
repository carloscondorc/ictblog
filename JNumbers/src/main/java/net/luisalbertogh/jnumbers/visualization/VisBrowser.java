/**
 * 
 */
package net.luisalbertogh.jnumbers.visualization;

import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.data.Graph;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

/**
 * @author lagarcia
 */
public class VisBrowser extends Visualization {

    private Graph graph;

    /**
     * TODO Description
     * 
     * @param graph
     */
    public VisBrowser(Graph graph) {
        super();
        this.graph = graph;
        initVisualization();
    }

    private void initVisualization() {
        this.add("graph", this.graph);

        // draw the "name" label for NodeItems
        // MyNodeRenderer r = new MyNodeRenderer("value");
        LabelRenderer r = new LabelRenderer("value");
        r.setRoundedCorner(8, 8); // round the corners

        // create a new default renderer factory
        // return our name label renderer as the default for all non-EdgeItems
        // includes straight line edges for EdgeItems by default
        this.setRendererFactory(new DefaultRendererFactory(r));

        // map nominal data values to colors using our provided palette
        ColorAction fill = new ColorAction("graph.nodes", VisualItem.FILLCOLOR, ColorLib.rgb(135, 206, 235));
        fill.add(VisualItem.HIGHLIGHT, ColorLib.rgb(255, 215, 0));
        // use black for node text
        ColorAction text = new ColorAction("graph.nodes", VisualItem.TEXTCOLOR, ColorLib.gray(0));
        // use light grey for edges
        ColorAction edges = new ColorAction("graph.edges", VisualItem.STROKECOLOR, ColorLib.gray(200));

        // create an action list containing all color assignments
        ActionList color = new ActionList();
        color.add(fill);
        color.add(text);
        color.add(edges);

        // create an action list with an animated layout
        // the INFINITY parameter tells the action list to run indefinitely
        ActionList layout = new ActionList(Activity.INFINITY);
        ForceDirectedLayout forceLayout = new ForceDirectedLayout("graph");
        layout.add(fill);
        layout.add(forceLayout);
        layout.add(new RepaintAction());

        // add the actions to the visualization
        this.putAction("color", color);
        this.putAction("layout", layout);

        this.runAfter("color", "layout");
    }
}
