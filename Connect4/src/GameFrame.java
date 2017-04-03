import javax.swing.*;
import java.awt.*;

/**
 * Created by aslak on 03.04.17.
 */
public class GameFrame extends JFrame {

    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel gridPanel = new JPanel();

    JButton button1 = new JButton("Button");

    /**
     * This is the Game Frame, which should contain all the game elements
     * such as the game log/history, game grid (6x7), power-ups ect.
     */
    public GameFrame() {

        //JFrame setup
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.blue);
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        GridBagConstraints gbc = new GridBagConstraints();

        //This is the left panel

        leftPanel.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 1;
        gbc.gridheight = 2;
        leftPanel.setBackground(Color.blue);
        this.add(leftPanel, gbc);

        //This is the right panel

        rightPanel.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 1;
        gbc.gridheight = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        rightPanel.setBackground(Color.YELLOW);
        this.add(rightPanel, gbc);


        //This is the center panel

        centerPanel.setLayout(new GridLayout(1, 1));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.8;
        gbc.weighty = 0.8;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        centerPanel.setBackground(Color.orange);
        this.add(centerPanel, gbc);


        //This is the top panel

        topPanel.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.weighty = 0.2;
        gbc.gridheight = 1;
        topPanel.setBackground(Color.cyan);
        topPanel.add(button1);
        this.add(topPanel, gbc);

        //This is the center panel 6x7 gridlayout setup

        gridPanel.setLayout(new GridLayout(6, 7));
        centerPanel.add(gridPanel);
        for (int i = 1; i < 7; i++) {
            gridPanel.add(new Button("" + i));
            for (int j = 0; j < 7; j++) {
                gridPanel.add(new Button("" + i));

            }
        }

        this.setVisible(true);
    }
}
