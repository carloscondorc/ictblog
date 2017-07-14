package net.luisalbertogh.jnumbers.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import net.luisalbertogh.jnumbers.data.GraphMLConverter;
import net.luisalbertogh.jnumbers.visualization.VisBrowser;
import prefuse.action.ActionList;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.controls.FocusControl;
import prefuse.data.Graph;
import prefuse.data.io.GraphMLReader;
import prefuse.util.force.ForceSimulator;
import prefuse.util.ui.JForcePanel;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;

/**
 * GraphPanel.
 * 
 * @author lagarcia
 */
public class GraphPanel extends JPanel {

    private GraphMLConverter converter;
    private VisBrowser vis;
    private DisplayBrowser display;

    private JSplitPane splitPane;
    private JTabbedPane tabbedPane = new JTabbedPane();

    /**
     * TODO Description
     * 
     * @param parentFrame
     */
    public GraphPanel(final JFrame parentFrame) {
        super();

        // Layout
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);

        // Tabbed pane
        add(tabbedPane, BorderLayout.CENTER);

        initDisplay();
        updateUI();
    }

    private void initDisplay() {
        try {
            // Split panel
            splitPane = new JSplitPane();
            splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
            splitPane.setOneTouchExpandable(true);
            splitPane.setDividerLocation(100);

            // Create GraphML
            converter = new GraphMLConverter();
            converter.getGraphMLFile(100);

            // Load graph
            Graph graph = new GraphMLReader().readGraph("data/output.xml");
            vis = new VisBrowser(graph);
            display = new DisplayBrowser(vis);

            // Customized controls
            display.addControlListener(new NodeActionControl());
            splitPane.setRightComponent(display);
            display.getVisualization().run("color"); // assign the colors
            display.getVisualization().run("layout"); // start up the animated layout

            // Create radial graph
            /**
             * RadialWebGraph radial = new RadialWebGraph("data/output.xml"); tabbedPane.addTab("Radial", radial);
             * radial.runGraph();
             */

            // Force simulator
            ForceSimulator fs = ((ForceDirectedLayout) ((ActionList) vis.getAction("layout")).get(1))
                    .getForceSimulator();
            splitPane.setLeftComponent(new JForcePanel(fs));

            tabbedPane.add("Graph", splitPane);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO Description
     * 
     * @author lagarcia
     */
    public class NodeActionControl extends FocusControl {

        /**
         * @see prefuse.controls.FocusControl#itemClicked(prefuse.visual.VisualItem, java.awt.event.MouseEvent)
         */
        @Override
        public void itemClicked(VisualItem item, java.awt.event.MouseEvent e) {
            // Single click
            if (item instanceof NodeItem && e.getClickCount() == 1) {
            }
        }

        /**
         * @see prefuse.controls.FocusControl#itemEntered(prefuse.visual.VisualItem, java.awt.event.MouseEvent)
         */
        @Override
        public void itemEntered(VisualItem item, java.awt.event.MouseEvent e) {
            if (item instanceof NodeItem) {
            }
        }

        /**
         * @see prefuse.controls.FocusControl#itemExited(prefuse.visual.VisualItem, java.awt.event.MouseEvent)
         */
        @Override
        public void itemExited(VisualItem item, java.awt.event.MouseEvent e) {

        }
    }
}
