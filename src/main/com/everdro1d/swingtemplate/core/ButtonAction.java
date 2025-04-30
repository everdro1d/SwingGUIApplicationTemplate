package main.com.everdro1d.swingtemplate.core;

import com.everdro1d.libs.swing.windows.settings.BasicSettingsWindow;
import main.com.everdro1d.swingtemplate.ui.MainWindow;
import main.com.everdro1d.swingtemplate.ui.panels.GeneralSettingsPanel;

import static main.com.everdro1d.swingtemplate.core.MainWorker.*;
import static main.com.everdro1d.swingtemplate.ui.MainWindow.topFrame;

public class ButtonAction {

    // Variables ------------------------------------------------------------------------------------------------------|

    // NOTE: settings window for locale changes & basic settings
    public static BasicSettingsWindow settingsWindow;

    public static void showSettingsWindow() {
        if (debug) System.out.println("Showing settings window.");
        if (settingsWindow == null) {
            settingsWindow = new BasicSettingsWindow(
                    topFrame, MainWindow.fontName, MainWindow.fontSize,
                    prefs, debug, localeManager, new GeneralSettingsPanel()
            ) {
                @Override
                public void applySettings() {
                    localeManager.reloadLocaleInProgram(prefs.get("currentLocale", localeManager.getCurrentLocale()));
                    currentLocale = localeManager.getCurrentLocale();

                    if (debug) {
                        showDebugConsole();
                        if (debug) System.out.println("Loaded locale: " + currentLocale);
                        System.out.println("Active: " + MainWindow.titleText + " v" + currentVersion);
                        System.out.println("Detected OS: " + MainWorker.detectedOS);
                    } else if (debugConsoleWindow != null) {
                        debugConsoleWindow.dispose();
                        debugConsoleWindow = null;
                    }
                }
            };
            windowFrameArray[2] = settingsWindow;
        } else {
            settingsWindow.setVisible(true);
            settingsWindow.requestFocus();
            settingsWindow.toFront();
        }

    }
}
