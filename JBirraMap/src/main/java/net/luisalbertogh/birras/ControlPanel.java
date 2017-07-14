/**
 * © 2009-2012 Tex Toll Services, LLC
 */
package net.luisalbertogh.birras;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Control panel.
 * 
 * @author lagarcia
 */
public class ControlPanel extends JPanel {

    /** Parent frame. */
    private JFrame parent;

    /** Number of component groups */
    private final int N_ROWS = 10;

    /**
     * Constructor.
     * 
     * @param parent
     */
    public ControlPanel(JFrame parent) {
        this.parent = parent;

        /* Set layout */
        this.setLayout(new GridLayout(N_ROWS, 1));

        /* Search panel */
        ComponentPanel searchPanel = new ComponentPanel("Search", new JPanel());
        add(searchPanel);
    }

    /**
     * Component Panel
     * 
     * @author lagarcia
     */
    class ComponentPanel extends JPanel {
        public ComponentPanel(String labelStr, JPanel panelRight) {
            /* Add components */
            setBorder(new TitledBorder(labelStr));
            add(panelRight);
        }
    }
}
