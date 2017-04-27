import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by aslak on 27.04.17.
 */

class UnitButtonView {

    JPanel UnitButtonView(UnitButtonController unitButtonController, Boolean buttonEnabled,Color color) {


        JPanel unitButtonView = new JPanel();
        ArrayList<JButton> buttons = new ArrayList<>();
        ArrayList<JLabel> labels = new ArrayList<>();

        // JPanel setup
        unitButtonView.setSize(200, 500);
        unitButtonView.setLayout(new GridBagLayout());
        unitButtonView.setBackground(color);

        //GBC
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;

        // Adds 6 buttons to a arraylist
        for (int i = 0; i < 6; i++) {
            JButton tempButton = new JButton();
            tempButton.setEnabled(buttonEnabled);
            tempButton.addActionListener(unitButtonController);
            tempButton.setText("" + i);
            tempButton.setActionCommand("button" + i);
            buttons.add(tempButton);
        }
        // Adds 6 labes to a arraylist
        for (int i = 0; i < 8; i++) {

            JLabel tempLabel = new JLabel("test" + i);
            tempLabel.setForeground(Color.white);

            tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
            tempLabel.setBackground(new Color(0,0,0,0));
            tempLabel.setVerticalAlignment(SwingConstants.TOP);
            labels.add(tempLabel);
        }

        // This is the how the JPanel will look like

        gbc.insets = new Insets(10, 10, 0, 10);

        gbc.gridy = 0;
        gbc.gridx = 0;

        gbc.weighty = 0.0625;
        unitButtonView.add(labels.get(0), gbc);
        gbc.gridx = 2;
        unitButtonView.add(labels.get(1), gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;

        gbc.weighty = 0.25;
        unitButtonView.add(buttons.get(0), gbc);
        gbc.gridx = 2;
        unitButtonView.add(buttons.get(1), gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;

        gbc.weighty = 0.0625;
        unitButtonView.add(labels.get(2), gbc);
        gbc.gridx = 2;
        unitButtonView.add(labels.get(3), gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.weighty = 0.25;
        unitButtonView.add(buttons.get(2), gbc);
        gbc.gridx = 2;
        unitButtonView.add(buttons.get(3), gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;

        gbc.weighty = 0.0625;
        unitButtonView.add(labels.get(4), gbc);
        gbc.gridx = 2;
        unitButtonView.add(labels.get(5), gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;

        gbc.weighty = 0.25;
        unitButtonView.add(buttons.get(4), gbc);
        gbc.gridx = 2;
        unitButtonView.add(buttons.get(5), gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;

        gbc.weighty = 0.0625;
        unitButtonView.add(labels.get(6), gbc);
        gbc.gridx = 2;
        unitButtonView.add(labels.get(7), gbc);

        return unitButtonView;
    }
}
