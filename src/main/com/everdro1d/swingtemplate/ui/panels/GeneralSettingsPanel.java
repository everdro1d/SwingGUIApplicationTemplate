package main.com.everdro1d.swingtemplate.ui.panels;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

import static main.com.everdro1d.swingtemplate.core.MainWorker.debug;
import static main.com.everdro1d.swingtemplate.core.MainWorker.localeManager;
import static main.com.everdro1d.swingtemplate.ui.MainWindow.fontName;
import static main.com.everdro1d.swingtemplate.ui.MainWindow.fontSize;

public class GeneralSettingsPanel extends JPanel {
    private final JLabel debugSwitchLabel;
    private final JComboBox<String> debugSwitchComboBox;
    private String debugSwitchLabelText = "Enable Debug Mode:";
    private String[] debugSwitchOptions = {"Enabled", "Disabled"};

    public GeneralSettingsPanel() {
        localeCheck();

        setLayout(new GridBagLayout());
        GridBagConstraints gbcAuto = new GridBagConstraints();
        gbcAuto.gridx = 0;
        gbcAuto.gridy = 0;
        gbcAuto.weightx = 0;
        gbcAuto.weighty = 1;
        gbcAuto.fill = GridBagConstraints.HORIZONTAL;
        gbcAuto.anchor = GridBagConstraints.NORTH;
        gbcAuto.insets = new Insets(4, 4, 4, 4);
        // row 1
        {
            debugSwitchLabel = new JLabel(debugSwitchLabelText);
            debugSwitchLabel.setFont(new Font(fontName, Font.PLAIN, fontSize));
            add(debugSwitchLabel, gbcAuto);

            gbcAuto.gridx++;
            gbcAuto.weightx = 1;
            debugSwitchComboBox = new JComboBox<>(debugSwitchOptions);
            debugSwitchComboBox.setFont(new Font(fontName, Font.PLAIN, fontSize));
            add(debugSwitchComboBox, gbcAuto);

            debugSwitchComboBox.setSelectedIndex(debug ? 0 : 1);

            debugSwitchComboBox.addActionListener(e -> {
                debug = debugSwitchComboBox.getSelectedIndex() == 0;
            });
        }
    }

    private void localeCheck() {
        if (!localeManager.getClassesInLocaleMap().contains("BasicSettingsWindow")
                || !localeManager.getComponentsInClassMap("BasicSettingsWindow")
                    .contains("GeneralSettingsPanel")
        ) {
            addGeneralSettingsPanelToLocale();
        }
        useLocale();
    }

    private void addGeneralSettingsPanelToLocale() {
        Map<String,String> map = new TreeMap<>();
        map.put("debugSwitchLabelText", debugSwitchLabelText);
        for (int i = 0; i < debugSwitchOptions.length; i++) {
            map.put("debugSwitchOptions"+i, debugSwitchOptions[i]);
        }

        if (!localeManager.getClassesInLocaleMap().contains("BasicSettingsWindow")) {
            localeManager.addClassSpecificMap("BasicSettingsWindow", new TreeMap<>());
        }

        localeManager.addComponentSpecificMap(
                "BasicSettingsWindow", "GeneralSettingsPanel", map
        );
    }

    private void useLocale() {
        Map<String,String> varMap = localeManager.getComponentSpecificMap("BasicSettingsWindow", "GeneralSettingsPanel");

        debugSwitchLabelText = varMap.get("debugSwitchLabelText");
        for (int i = 0; i < debugSwitchOptions.length; i++) {
            debugSwitchOptions[i] = varMap.get("debugSwitchOptions"+i);
        }
    }
}
