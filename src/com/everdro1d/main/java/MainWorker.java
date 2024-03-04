/* dro1dDev SwingGUI Templates - MainWorker.java
 *
 */

package com.everdro1d.main.java;

import com.everdro1d.libs.core.*;
import com.everdro1d.libs.swing.*;
import javax.swing.*;
import java.awt.*;
import java.util.prefs.Preferences;

public class MainWorker {
    // Variables ------------------------------------------------------------------------------------------------------|
    public static boolean debug = false;
    // Boolean to enable debug logging output all 'sout' statements must be wrapped in 'if (debug)'

    static final Preferences prefs = Preferences.userNodeForPackage(MainWorker.class);
    // Preferences object for saving and loading user settings

    public static int[] windowPosition = {0, 0, 0};
    // Default window position

    // End of variables -----------------------------------------------------------------------------------------------|

    public static void main(String[] args) {
        ApplicationCore.checkCLIArgs(args);
        checkOSCompatibility(debug);
        SwingGUI.setLookAndFeel(true, false, debug);
        loadPreferences();

        SwingGUI.lightOrDarkMode(false, new JFrame[]{MainWindow.topFrame});
        SwingGUI.uiSetup(false, MainWindow.fontName, MainWindow.fontSize);

        startMainWindow();

    }

    /**
     * Detects the OS to determine compat with application and dependencies.
     * @param debug whether to print debug information
     * @see #executeOSSpecificCode(String, boolean)
     */
    public static void checkOSCompatibility(boolean debug) {
        String detectedOS = ApplicationCore.detectOS(debug);
        executeOSSpecificCode(detectedOS, debug);
    }

    /**
     * Execute OS specific code.
     * @param detectedOS the detected OS
     * @param debug whether to print debug information
     * @see #checkOSCompatibility(boolean)
     */
    public static void executeOSSpecificCode(String detectedOS, boolean debug) {
        switch (detectedOS) {
            case "Windows" -> {
                System.out.println("Windows OS detected.");
            }
            case "macOS" -> {
                System.out.println("macOS detected.");
            }
            case "Unix" -> {
                System.out.println("Unix OS detected.");
            }
            default -> {
                System.out.println("Unknown OS detected. Exiting.");
                System.exit(1);
            }
        }
    }

    /**
     * Load the user settings from the preferences. And save the settings on exit.
     */
    private static void loadPreferences() {
        loadWindowPosition();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Save the user settings on exit
        }));
    }

    /**
     * Load the window position from the preferences. And save the position on exit.
     */
    private static void loadWindowPosition() {
        windowPosition[0] = prefs.getInt("framePosX", 0);
        windowPosition[1] = prefs.getInt("framePosY", 0);
        windowPosition[2] = prefs.getInt("activeMonitor", 0);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            prefs.putInt("framePosX", windowPosition[0]);
            prefs.putInt("framePosY", windowPosition[1]);
            prefs.putInt("activeMonitor", windowPosition[2]);
        }));
    }

    /**
     * Start the com.everdro1d.main window.
     */
    private static void startMainWindow() {
        EventQueue.invokeLater(() -> {
            try {
                new MainWindow();
                SwingGUI.setFramePosition(
                        MainWindow.topFrame,
                        windowPosition[0], windowPosition[1], windowPosition[2]
                );
            } catch (Exception ex) {
                if (debug) ex.printStackTrace(System.err);
                System.err.println("Failed to start com.everdro1d.main window.");
            }
        });
    }
}
