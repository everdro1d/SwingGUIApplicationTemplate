/*
 * dro1dDev 2025 - SwingGUI Templates - MainWindow.java
 */

package main.com.everdro1d.swingtemplate.ui;

import com.everdro1d.libs.swing.SwingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Map;
import java.util.TreeMap;

import static main.com.everdro1d.swingtemplate.core.MainWorker.*;

public class MainWindow extends JFrame {
    // Variables ------------------------------------------------------------------------------------------------------|

    // Swing components - Follow tab hierarchy for organization -----------|
    public static JFrame topFrame;
        private JPanel mainPanel;
            private JPanel centerPanel;
                private JLabel exampleLabel;
                private JButton darkModeButton;

    // End of Swing components --------------------------------------------|

    // UI Text Defaults ---------------------------------------------------|
    // TODO 1: add to locale default methods
    public static String titleText = "SwingGUI Application Template";
    public static String darkModeButtonText = "Dark Mode Switch";
    // End of UI Text Defaults --------------------------------------------|

    // NOTE: font name and size for the application
    public static String fontName = "Tahoma";
    public static int fontSize = 16;
    public static final Font font = new Font(fontName, Font.PLAIN, fontSize);
    public static final Font boldFont = new Font(fontName, Font.BOLD, fontSize);
    public static final Font smallFont = new Font(fontName, Font.PLAIN, (fontSize - 2));

    private final int WINDOW_WIDTH = 600;
    private final int EDGE_PADDING = 15;
    private final int WINDOW_HEIGHT = 400;


    // End of variables -----------------------------------------------------------------------------------------------|

    public MainWindow() {
        // if the locale does not contain the class, add it and it's components
        if (!localeManager.getClassesInLocaleMap().contains("MainWindow")) {
            addClassToLocale();
        }
        useLocale();

        initializeWindowProperties();
        initializeGUIComponents();

        topFrame.setVisible(true);

        SwingGUI.setHandCursorToClickableComponents(topFrame);
    }

    // TODO 1: add any UI Text Defaults to these locale classes
    private void addClassToLocale() {
        Map<String, Map<String, String>> map = new TreeMap<>();
        map.put("Main", new TreeMap<>());
        Map<String, String> mainMap = map.get("Main");
        mainMap.put("titleText", titleText);
        mainMap.put("darkModeButtonText", darkModeButtonText);

        localeManager.addClassSpecificMap("MainWindow", map);
    }

    private void useLocale() {
        Map<String, String> varMap = localeManager.getAllVariablesWithinClassSpecificMap("MainWindow");
        titleText = varMap.getOrDefault("titleText", titleText);
        darkModeButtonText = varMap.getOrDefault("darkModeButtonText", darkModeButtonText);
    }

    /**
     * Initialize the window.
     */
    private void initializeWindowProperties() {
        topFrame = this;
        topFrame.setTitle(titleText);
        topFrame.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topFrame.setResizable(false); // TODO: resizeable?
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
            // TODO: add components here
            centerPanel = new JPanel();
            centerPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.weighty = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(4, 4, 4, 4);
            mainPanel.add(centerPanel, BorderLayout.CENTER);
            {
                exampleLabel = new JLabel(titleText);
                exampleLabel.setFont(boldFont);
                exampleLabel.setAlignmentX(SwingConstants.CENTER);
                exampleLabel.setAlignmentY(SwingConstants.CENTER);
                centerPanel.add(exampleLabel, gbc);

                gbc.gridy++; // vertical position between components

                darkModeButton = new JButton(darkModeButtonText);
                darkModeButton.setFont(boldFont);
                darkModeButton.setAlignmentX(SwingConstants.CENTER);
                darkModeButton.setAlignmentY(SwingConstants.CENTER);
                centerPanel.add(darkModeButton, gbc);

                darkModeButton.addActionListener(e -> {
                    darkMode = !darkMode;
                    SwingGUI.switchLightOrDarkMode(darkMode, windowFrameArray);
                    SwingUtilities.updateComponentTreeUI(topFrame);
                });
            }
        }
    }
}
