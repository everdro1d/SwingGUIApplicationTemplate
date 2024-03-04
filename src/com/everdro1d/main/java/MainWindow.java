/* dro1dDev SwingGUI Templates - MainWindow.java
 *
 */

package com.everdro1d.main.java;

import com.everdro1d.libs.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static com.everdro1d.main.java.MainWorker.windowPosition;

public class MainWindow extends JFrame {
    // Variables ------------------------------------------------------------------------------------------------------|

    // Swing components - Follow tab hierarchy for organization -----------|
    public static JFrame topFrame;
        private JPanel mainPanel;

    // End of Swing components --------------------------------------------|

    public static String fontName = "Tahoma";
    // Font name for the application
    public static int fontSize = 16;

    private final int windowWidth = 600;
    private final int windowHeight = 400;


    // End of variables -----------------------------------------------------------------------------------------------|

    public MainWindow() {
        initializeWindowProperties();
        initializeGUIComponents();

        topFrame.setVisible(true);
    }

    /**
     * Initialize the window.
     */
    private void initializeWindowProperties() {
        topFrame = this;
        topFrame.setTitle("MainWindow");
        topFrame.setMinimumSize(new Dimension(windowWidth, windowHeight));
        topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topFrame.setResizable(false);
        topFrame.setLocationRelativeTo(null);

        topFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                windowPosition = SwingGUI.getFramePositionOnScreen(topFrame);
            }
        });
    }

    /**
     * Initialize the GUI components.
     */
    private void initializeGUIComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        topFrame.add(mainPanel);
        {
            // Add components here
        }
    }
}
