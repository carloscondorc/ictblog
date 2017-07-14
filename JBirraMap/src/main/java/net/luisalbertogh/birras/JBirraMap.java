/**
 * © 2009-2012 Tex Toll Services, LLC
 */
package net.luisalbertogh.birras;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * JBirraMap.
 * 
 * @author lagarcia
 */
public class JBirraMap {

    /** Frame width */
    public static final int WIDTH = 800;

    /** Frame height */
    public static final int HEIGHT = 600;

    /**
     * Main method.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // PApplet.main(new String[] {"net.luisalbertogh.birras.BirraApplet" });
        JBirraMap birraMap = new JBirraMap();
        birraMap.init();
    }

    /**
     * Init GUI.
     */
    private void init() {
        try {
            /* Set Nimbus L&F */
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            /* Main frame */
            JFrame frame = new JFrame();
            frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            frame.setTitle("JBirraMap");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            /* Map applet */
            BirraApplet ba = new BirraApplet();
            ba.setMinimumSize(new Dimension(WIDTH / 2, HEIGHT));
            ba.init();

            /* Main panel */
            ControlPanel controlPanel = new ControlPanel(frame);
            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, ba, controlPanel);
            splitPane.setOneTouchExpandable(true);
            splitPane.setDividerLocation(WIDTH);
            splitPane.setContinuousLayout(true);
            frame.add(splitPane);

            /* Set icon */
            // BufferedImage bi = null;
            // try {
            // bi = ImageIO.read(new File("./pics/PadLock-icon.png"));
            // } catch (IOException e) {
            // e.printStackTrace();
            // }
            // frame.setIconImage(bi);

            /* Show frame */
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
