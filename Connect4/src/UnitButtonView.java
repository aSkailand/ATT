import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by aslak on 27.04.17.
 */

class UnitButtonView {

    private GridBagConstraints gbc = new GridBagConstraints();

    /**
     * This method returns a JPanel with GridBagLayout and buttons and labels attached to it
     *
     * @param unitButtonController: controller
     * @param buttons:              a array list of JButtons
     * @param labels:               a array list of JLabels
     * @param imageIcon:            test image icon // todo: remove this and add unique pictures for each button
     * @param color:                color for the background
     * @param colorbutton:          color for the buttons
     * @return: returns a JPanel
     */
    JPanel CreateUnitButtonView(UnitButtonController unitButtonController, ArrayList<JButton> buttons, ArrayList<JLabel> labels, ImageIcon imageIcon, Color color, Color colorbutton) {

        JPanel unitButtonView = new JPanel();

        // JPanel setup
        unitButtonView.setLayout(new GridBagLayout());
        unitButtonView.setBackground(color);

        //GBC

        gbc();

        // Adds look and action commands to the buttons
        for (int i = 0; i < 6; i++) {
            // Button setup
            buttons.get(i).setVerticalAlignment(SwingConstants.BOTTOM);
            buttons.get(i).setContentAreaFilled(false);
            buttons.get(i).setBorderPainted(false);
            buttons.get(i).setFocusPainted(true);
            buttons.get(i).setIcon(imageIcon);
            buttons.get(i).setBackground(colorbutton);
            buttons.get(i).addActionListener(unitButtonController);
            buttons.get(i).setActionCommand("button" + i);
        }
        // Adds look for the JLabel text
        for (int i = 0; i < 8; i++) {
            // Label setup
            labels.get(i).setText("" + i);
            labels.get(i).setHorizontalAlignment(SwingConstants.CENTER);
            labels.get(i).setVerticalAlignment(SwingConstants.TOP);
            labels.get(i).setForeground(Color.WHITE);
        }


        // This is the how the JPanel will look like
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.SOUTH;

        gbc.gridy = 0;
        gbc.gridx = 0;

        gbc.weighty = 0.0625;

        // Top labels
        labels.get(0).setVerticalAlignment(SwingConstants.BOTTOM);
        labels.get(1).setVerticalAlignment(SwingConstants.BOTTOM);
        labels.get(0).setText("Units");
        labels.get(1).setText("Magic");

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

    void gbc() {

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
    }
}