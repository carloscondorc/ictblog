/**
 * Jwire is an advanced GUI for the crawler WIRE. It launches, manages, and configures WIRE for its use.
 * It also includes an advanced toolset of data visualization tools based on the most common and well-known
 * visualization techniques.
 * Copyright (C) 2010 Luis Alberto Garcia Hernandez
 */
/**
 * This file is part of Jwire.

 Jwire is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Jwire is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Jwire. If not, see <http://www.gnu.org/licenses/>.
 */
package net.luisalbertogh.jnumbers.visualization;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.luisalbertogh.jnumbers.actions.NodeColorAction;
import net.luisalbertogh.jnumbers.actions.TreeRootAction;
import net.luisalbertogh.jnumbers.renderer.MyColorFiller;
import net.luisalbertogh.jnumbers.renderer.MyNodeRenderer;
import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.animate.ColorAnimator;
import prefuse.action.animate.PolarLocationAnimator;
import prefuse.action.animate.QualityControlAnimator;
import prefuse.action.animate.VisibilityAnimator;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.FontAction;
import prefuse.action.layout.CollapsedSubtreeLayout;
import prefuse.action.layout.graph.RadialTreeLayout;
import prefuse.activity.SlowInSlowOutPacer;
import prefuse.controls.ControlAdapter;
import prefuse.controls.DragControl;
import prefuse.controls.FocusControl;
import prefuse.controls.HoverActionControl;
import prefuse.controls.PanControl;
import prefuse.controls.WheelZoomControl;
import prefuse.controls.ZoomControl;
import prefuse.controls.ZoomToFitControl;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Table;
import prefuse.data.Tuple;
import prefuse.data.event.TupleSetListener;
import prefuse.data.io.GraphMLReader;
import prefuse.data.query.SearchQueryBinding;
import prefuse.data.search.PrefixSearchTupleSet;
import prefuse.data.search.SearchTupleSet;
import prefuse.data.tuple.DefaultTupleSet;
import prefuse.data.tuple.TupleSet;
import prefuse.render.AbstractShapeRenderer;
import prefuse.render.DefaultRendererFactory;
import prefuse.util.ColorLib;
import prefuse.util.FontLib;
import prefuse.util.ui.JFastLabel;
import prefuse.util.ui.JSearchPanel;
import prefuse.visual.VisualItem;
import prefuse.visual.sort.TreeDepthItemSorter;

/**
 * This class implements a graph for displaying a web structure using "prefuse".
 * 
 * @date April 2010
 * @author Luis Alberto Garcia Hernandez
 */
@SuppressWarnings("serial")
public class RadialWebGraph extends JPanel {

    // Names
    protected static final String GRAPH = "graph";
    protected static final String GRAPHNODES = "graph.nodes";
    protected static final String GRAPHEDGES = "graph.edges";
    protected static final String LINEAR = "linear";

    // Web graph
    protected Graph webgraph;
    // Display
    protected Display display;
    // Visualization
    protected Visualization m_vis;

    /**
     * TODO Description
     * 
     * @param filepath
     */
    public RadialWebGraph(String filepath) {
        super();

        setLayout(new BorderLayout());

        m_vis = new Visualization();
        display = new Display(m_vis);
        display.setBackground(Color.GRAY);

        loadXMLData(filepath);
        initGraph();
    }

    /**
     * Load a GraphXML data file as the graph dataset.
     * 
     * @param filepath String Path to the file.
     */
    public void loadXMLData(String filepath) {
        try {
            // Read the data file and initiate the graph
            webgraph = new GraphMLReader().readGraph(filepath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Init the graph.
     */
    public void initGraph() {
        // Set up visualization
        setupVisualization();
        // Set up renderers
        setRenderers();
        // Process the graph actions
        processGraph();
        // Set up interactivity
        setInteractiveControls();
        // Add additional controls
        setAdditionalControls();
    }

    /**
     * Add the data to the visualization as a data group.
     */
    protected void setupVisualization() {
        // add the graph to the visualization as the data group "graph"
        // nodes and edges are accessible as "graph.nodes" and "graph.edges"
        m_vis.add(GRAPH, webgraph);
    }

    /**
     * Set up the renderers to use.
     */
    protected void setRenderers() {
        // draw the "name" label for NodeItems
        MyNodeRenderer lr = new MyNodeRenderer("name");
        lr.setRenderType(AbstractShapeRenderer.RENDER_TYPE_FILL);
        lr.setHorizontalAlignment(Constants.CENTER);
        lr.setRoundedCorner(8, 8); // round the corners

        // create a new default renderer factory
        // includes straight line edges for EdgeItems by default
        DefaultRendererFactory rf = new DefaultRendererFactory();
        rf.setDefaultRenderer(lr); // Default renderer is shape
        m_vis.setRendererFactory(rf);
    }

    /**
     * Process the graph setting the color, display and other parameters.
     */
    protected void processGraph() {
        // Add shaped nodes
        m_vis.setValue(GRAPHNODES, null, VisualItem.SHAPE, new Integer(Constants.SHAPE_ELLIPSE));

        // Colors
        ColorAction nodeColor = new NodeColorAction(GRAPHNODES, VisualItem.STROKECOLOR);
        ColorAction textColor = new ColorAction(GRAPHNODES, VisualItem.TEXTCOLOR, ColorLib.gray(0));
        ColorAction textBgColor = new MyColorFiller(GRAPHNODES, VisualItem.FILLCOLOR, "type");
        textBgColor.add(VisualItem.FIXED, ColorLib.rgb(255, 100, 100));
        ColorAction edgeColor = new ColorAction(GRAPHEDGES, VisualItem.STROKECOLOR, ColorLib.gray(100));

        // Fonts
        FontAction fonts = new FontAction(GRAPHNODES, FontLib.getFont("Arial", 10));
        fonts.add("ingroup('_focus_')", FontLib.getFont("Arial", 12));

        // create an action list containing all color assignments
        ActionList color = new ActionList();
        color.add(nodeColor);
        color.add(textColor);
        color.add(textBgColor);
        color.add(edgeColor);
        m_vis.putAction("recolor", color);

        // repaint
        ActionList repaint = new ActionList();
        repaint.add(color);
        repaint.add(new RepaintAction());
        m_vis.putAction("repaint", repaint);

        // animate paint change
        ActionList animatePaint = new ActionList(400);
        animatePaint.add(new ColorAnimator(GRAPHNODES));
        animatePaint.add(new RepaintAction());
        m_vis.putAction("animatePaint", animatePaint);

        // create the tree layout action
        RadialTreeLayout treeLayout = new RadialTreeLayout(GRAPH);
        // treeLayout.setAngularBounds(-Math.PI/2, Math.PI);
        m_vis.putAction("treeLayout", treeLayout);

        CollapsedSubtreeLayout subLayout = new CollapsedSubtreeLayout(GRAPH);
        m_vis.putAction("subLayout", subLayout);

        // create the filtering and layout
        ActionList filter = new ActionList();
        filter.add(new TreeRootAction(GRAPH)); // Action to replace focused node from the center
        filter.add(treeLayout);
        filter.add(subLayout);
        filter.add(fonts);
        filter.add(color);
        m_vis.putAction("filter", filter);

        // animated transition
        ActionList animate = new ActionList(1250);
        animate.setPacingFunction(new SlowInSlowOutPacer());
        animate.add(new QualityControlAnimator());
        animate.add(new VisibilityAnimator(GRAPH));
        animate.add(new PolarLocationAnimator(GRAPHNODES, LINEAR));
        animate.add(new ColorAnimator(GRAPHNODES));
        animate.add(new RepaintAction());
        m_vis.putAction("animate", animate);
        m_vis.alwaysRunAfter("filter", "animate");
    }

    /**
     * Set the controls to interact with the graph.
     */
    protected void setInteractiveControls() {
        display.setSize(600, 400);
        display.pan(display.getWidth() / 2, display.getHeight() / 2); // Set initial point

        display.setItemSorter(new TreeDepthItemSorter());

        display.addControlListener(new DragControl()); // drag items around
        display.addControlListener(new PanControl()); // pan with background left-drag
        display.addControlListener(new ZoomControl()); // zoom with vertical right-drag
        display.addControlListener(new WheelZoomControl()); // zoom with mouse wheel
        display.addControlListener(new ZoomToFitControl());
        display.addControlListener(new FocusControl(1, "filter"));
        display.addControlListener(new HoverActionControl("repaint"));
    }

    /**
     * Add additional controls to the graph, like the search control.
     */
    protected void setAdditionalControls() {
        // For the search
        SearchTupleSet search = new PrefixSearchTupleSet();
        m_vis.addFocusGroup(Visualization.SEARCH_ITEMS, search);
        search.addTupleSetListener(new TupleSetListener() {
            @Override
            public void tupleSetChanged(TupleSet t, Tuple[] add, Tuple[] rem) {
                m_vis.cancel("animatePaint");
                m_vis.run("recolor");
                m_vis.run("animatePaint");
            }
        });

        // create a search panel for the tree map
        SearchQueryBinding sq = new SearchQueryBinding((Table) m_vis.getGroup(GRAPHNODES), "name",
                (SearchTupleSet) m_vis.getGroup(Visualization.SEARCH_ITEMS));
        JSearchPanel searchPanel = sq.createSearchPanel();
        searchPanel.setShowResultCount(true);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 4, 0));
        searchPanel.setFont(FontLib.getFont("Arial", Font.PLAIN, 12));
        searchPanel.setBackground(Color.GRAY);

        final JFastLabel title = new JFastLabel("                 ");
        title.setPreferredSize(new Dimension(350, 20));
        title.setVerticalAlignment(SwingConstants.BOTTOM);
        title.setFont(FontLib.getFont("Arial", Font.BOLD, 14));
        title.setBackground(Color.GRAY);
        display.addControlListener(new ControlAdapter() {
            @Override
            public void itemEntered(VisualItem item, MouseEvent e) {
                if (item.canGetString("name")) {
                    title.setText(item.getString("name"));
                } else if (item.canGetFloat("weight")) {
                    title.setText(Integer.toString((int) item.getFloat("weight")));
                }
            }

            @Override
            public void itemExited(VisualItem item, MouseEvent e) {
                title.setText(null);
            }
        });

        add(display, BorderLayout.CENTER);
        Box box = Box.createHorizontalBox();
        box.add(title);
        box.add(searchPanel);
        add(box, BorderLayout.SOUTH);
    }

    /**
     * Run the graph.
     */
    public void runGraph() {
        m_vis.run("filter");

        // maintain a set of items that should be interpolated linearly
        // this isn't absolutely necessary, but makes the animations nicer
        // the PolarLocationAnimator should read this set and act accordingly
        m_vis.addFocusGroup(LINEAR, new DefaultTupleSet());
        m_vis.getGroup(Visualization.FOCUS_ITEMS).addTupleSetListener(new TupleSetListener() {
            @Override
            public void tupleSetChanged(TupleSet t, Tuple[] add, Tuple[] rem) {
                TupleSet linearInterp = m_vis.getGroup(LINEAR);
                if (add.length < 1) {
                    return;
                }
                linearInterp.clear();
                for (Node n = (Node) add[0]; n != null; n = n.getParent()) {
                    linearInterp.addTuple(n);
                }
            }
        });
    }
}
