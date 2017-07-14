/**
 * 
 */
package net.luisalbertogh.jnumbers;

import javax.swing.JFrame;

import net.luisalbertogh.jnumbers.gui.GraphPanel;

/**
 * @author lagarcia
 */
public class JNumbers {

    /**
     * @param args
     */
    public static void main(String[] args) {
        new JNumbers().run();
    }

    /**
     * Run.
     */
    public void run() {
        try {
            // create a new window to hold the visualization
            JFrame frame = new JFrame("JNumbers");
            // ensure application exits when window is closed
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Load panel
            GraphPanel panel = new GraphPanel(frame);
            frame.add(panel);

            frame.pack(); // layout components in window
            frame.setVisible(true); // show the window

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
