import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by aslak on 03.04.17.
 */
public class GameBodyFrame extends JFrame {

    // Temporary text
    JLabel labelCurrentPlayerText = new JLabel();


    // SUPER GBC
    GridBagConstraints gbc = new GridBagConstraints();

    // CENTER SHIT
    JPanel centerPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel playersPanel = new JPanel();
    JPanel timerPanel = new JPanel();
    JPanel centerStatusPanel = new JPanel();

    JLabel labelStatus = new JLabel("NOTIFICATIONS");

    // LEFT PLAYER SHIT
    JPanel leftPanel = new JPanel();
    JPanel leftPlayerPanel = new JPanel();
    JPanel leftPlayerAvatarPanel = new JPanel();
    JPanel leftPlayerGoldPanel = new JPanel();
    JPanel leftPlayerUnitPanel = new JPanel();

    JLabel leftPlayerGoldLabel = new JLabel("   x 0 ");
    JLabel leftPlayerHP = new JLabel("HP: 20/20");

    JButton leftUnitButton1 = new JButton("Button1");
    JButton leftUnitButton2 = new JButton("Button2");
    JButton leftUnitButton3 = new JButton("Button3");
    JButton leftPlayerHPButton;


    // RIGHT PLAYER SHIT
    JPanel rightPanel = new JPanel();
    JPanel rightPlayerPanel = new JPanel();
    JPanel rightPlayerAvatarPanel = new JPanel();
    JPanel rightPlayerGoldPanel = new JPanel();
    JPanel rightPlayerUnitPanel = new JPanel();

    JButton rightUnitButton1 = new JButton("Button1");
    JButton rightUnitButton2 = new JButton("Button2");
    JButton rightUnitButton3 = new JButton("Button3");

    JLabel rightPlayerGoldLabel = new JLabel("   x 0 ");
    JLabel rightPlayerHP = new JLabel("HP:20/20");


    // LABELS
    JButton timer = new JButton("30");

    //JLabel totalTimer = new JLabel("Total time: 0:0");

    GameBodyModel gameBodyModel;

    /**
     * This is the Game Frame, which contains all in-game elements.
     * such as the game log/history, game grid (6x7), power-ups ect.
     */
    public GameBodyFrame() {

        gameBodyModel = new GameBodyModel();

        // JFrame setup
        this.setTitle("Connect4");
        this.setSize(1366, 768);
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.blue);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // ************************************************************* //
        // *********************** CENTER SHIT************************** //
        // ************************************************************* //

        // Center panel
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.white);
        gbcCenterPanel();
        this.add(centerPanel, gbc);

        // Players panel
        playersPanel.setLayout(new GridBagLayout());
        gbcPlayersPanel();
        this.add(playersPanel, gbc);

        // Timer panel
        timerPanel.setLayout(new GridBagLayout());
        timerPanel.setBackground(Color.GRAY);
        gbcTimerPanel();

        timer.setBorderPainted(false);
        timer.setBackground(Color.LIGHT_GRAY);
        timer.setFont(new Font("Consolas", Font.BOLD, 40));
        playersPanel.add(timerPanel, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        timerPanel.add(timer, gbc);
        gbc.gridy = 0;
        gbc.gridx = 0;
        //todo: this is the totalTimer, do we need it?
        //timerPanel.add(totalTimer, gbc);

        // Center status panel
        centerStatusPanel.setLayout(new GridBagLayout());
        centerStatusPanel.setBackground(Color.LIGHT_GRAY);
        gbcCenterStatusPanel();
        playersPanel.add(centerStatusPanel, gbc);
        centerStatusPanel.add(labelStatus);

        // Top panel
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.white);
        gbcTopPanel();
        this.add(topPanel, gbc);

        // ************************************************************* //
        // *********************** LEFT PLAYER ************************* //
        // ************************************************************* //

        // Left panel
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setBackground(Color.WHITE);
        leftPanel.add(labelCurrentPlayerText);
        gbcLeftPanel();
        this.add(leftPanel, gbc);

        // Left player avatar
        leftPlayerAvatarPanel.setLayout(new GridBagLayout());
        leftPlayerAvatarPanel.setBackground(Color.RED);
        gbcLeftPlayerAvatarPanel();
        leftPanel.add(leftPlayerAvatarPanel, gbc);
        leftPlayerAvatarPanel.add(gameBodyModel.playerOneLabel);

        // Left player gold panel
        leftPlayerGoldPanel.setLayout(new GridBagLayout());
        leftPlayerGoldPanel.setBackground(Color.RED);
        gbcLeftPlayerGoldPanel();
        leftPanel.add(leftPlayerGoldPanel, gbc);
        leftPlayerGoldLabel.setForeground(Color.white);
        leftPlayerGoldPanel.add(gameBodyModel.redGoldLabel);
        leftPlayerGoldPanel.add(leftPlayerGoldLabel);

        // Left player unit panel
        leftPlayerUnitPanel.setLayout(new GridBagLayout());
        leftPlayerUnitPanel.setBackground(Color.RED);
        gbcLeftPlayerUnitPanel();
        leftPanel.add(leftPlayerUnitPanel, gbc);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 0;

        leftPlayerUnitPanel.add(leftUnitButton1, gbc);
        gbc.gridy = 1;

        leftPlayerUnitPanel.add(leftUnitButton2, gbc);
        gbc.gridy = 2;

        leftPlayerUnitPanel.add(leftUnitButton3, gbc);

        // Left player top panel
        leftPlayerPanel.setLayout(new GridBagLayout());
        leftPlayerPanel.setBackground(Color.RED);
        gbcLeftPlayerPanel();
        playersPanel.add(leftPlayerPanel, gbc);

        // HP FONT
        leftPlayerHP.setFont(new Font(null, Font.BOLD, 20));
        leftPlayerHP.setForeground(Color.white);

        gbc.gridy = 0;
        gbc.gridwidth = 4;
        leftPlayerPanel.add(leftPlayerHP, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;

        // Adding 5 hp bars to the left top panel
        for (int i = 0; i < 5; i++) {

            gbc.weighty = 0;
            gbc.gridx = i;
            leftPlayerPanel.add(leftPlayerHPButton = new JButton(), gbc);
            leftPlayerHPButton.setBackground(Color.green);
        }

        // ************************************************************* //
        // *********************** RIGHT PLAYER ************************ //
        // ************************************************************* //

        // Right panel
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        gbcRightPanel();
        this.add(rightPanel, gbc);

        // Right player avatar panel
        rightPlayerAvatarPanel.setLayout(new GridBagLayout());
        rightPlayerAvatarPanel.setBackground(Color.BLUE);
        gbcRightPlayerAvatarPanel();
        rightPanel.add(rightPlayerAvatarPanel, gbc);
        rightPlayerAvatarPanel.add(gameBodyModel.playerTwoLabel);


        // Right player gold panel
        rightPlayerGoldPanel.setLayout(new GridBagLayout());
        rightPlayerGoldPanel.setBackground(Color.BLUE);
        gbcRightPlayerGoldPanel();
        rightPanel.add(rightPlayerGoldPanel, gbc);
        rightPlayerGoldLabel.setForeground(Color.WHITE);
        rightPlayerGoldPanel.add(gameBodyModel.blueGoldLabel);
        rightPlayerGoldPanel.add(rightPlayerGoldLabel);


        // Right player unit panel
        rightPlayerUnitPanel.setLayout(new GridBagLayout());
        rightPlayerUnitPanel.setBackground(Color.BLUE);
        gbcRightPlayerUnitPanel();
        rightPanel.add(rightPlayerUnitPanel, gbc);
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 0;
        rightPlayerUnitPanel.add(rightUnitButton1, gbc);
        gbc.gridy = 1;
        rightPlayerUnitPanel.add(rightUnitButton2, gbc);
        gbc.gridy = 2;
        rightPlayerUnitPanel.add(rightUnitButton3, gbc);

        // Right player top panel
        rightPlayerPanel.setLayout(new GridBagLayout());
        rightPlayerPanel.setBackground(Color.BLUE);
        gbcRightPlayerPanel();
        playersPanel.add(rightPlayerPanel, gbc);

        //HP FONT
        rightPlayerHP.setFont(new Font(null, Font.BOLD, 20));
        rightPlayerHP.setForeground(Color.white);

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 4;
        rightPlayerPanel.add(rightPlayerHP, gbc);

        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        // Adding 5 hp bars to the right top panel
        for (int i = 0; i < 5; i++) {

            gbc.weighty = 0;
            gbc.gridx = i;
            rightPlayerPanel.add(leftPlayerHPButton = new JButton(), gbc);
            leftPlayerHPButton.setBackground(Color.green);
        }


        // Set visibility
        this.setVisible(true);
    }

    void gbcLeftPanel() {

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 3;

        gbc.weightx = 0.3;
        gbc.weighty = 1;

        gbc.insets = new Insets(1,1,1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcLeftPlayerAvatarPanel() {
        gbc.gridx = 1;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 0.3;
        gbc.weighty = 0.26;

        gbc.insets = new Insets(1,1,1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcLeftPlayerGoldPanel() {

        gbc.gridx = 1;
        gbc.gridy = 1;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 0.3;
        gbc.weighty = 0.1;

        gbc.insets = new Insets(1,1,1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;

    }

    void gbcLeftPlayerUnitPanel() {
        gbc.gridx = 1;
        gbc.gridy = 2;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 0.3;
        gbc.weighty = 0.7;

        gbc.insets = new Insets(1,1,1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcRightPanel() {

        gbc.gridx = 2;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 3;

        gbc.weightx = 0.3;
        gbc.weighty = 1;

        gbc.insets = new Insets(1,1,1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcRightPlayerAvatarPanel() {

        gbc.gridx = 1;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 0.3;
        gbc.weighty = 0.26;

        gbc.insets = new Insets(1,1,1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcRightPlayerGoldPanel() {
        gbc.gridx = 1;
        gbc.gridy = 1;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 0.3;
        gbc.weighty = 0.1;

        gbc.insets = new Insets(1,1,1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcRightPlayerUnitPanel() {

        gbc.gridx = 1;
        gbc.gridy = 2;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 0.3;
        gbc.weighty = 0.7;

        gbc.insets = new Insets(1,1,1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcCenterPanel() {

        gbc.gridx = 1;
        gbc.gridy = 2;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 0.5;
        gbc.weighty = 0.7;

        gbc.insets = new Insets(1,1,1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;

    }

    void gbcPlayersPanel() {

        gbc.gridx = 1;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 0.05;
        gbc.weighty = 0.3;

        gbc.insets = new Insets(1,1,1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcCenterStatusPanel() {

        gbc.gridx = 0;
        gbc.gridy = 1;

        gbc.gridwidth = 3;
        gbc.gridheight = 1;

        gbc.weightx = 0.05;
        gbc.weighty = 0.4;

        gbc.insets = new Insets(1,1,1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcTopPanel() {

        gbc.gridx = 1;
        gbc.gridy = 1;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 0.05;
        gbc.weighty = 0.005;

        gbc.insets = new Insets(1,1,1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcLeftPlayerPanel() {

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 0.6;
        gbc.weighty = 1;

        gbc.insets = new Insets(1,1,1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcRightPlayerPanel() {

        gbc.gridx = 2;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 0.6;
        gbc.weighty = 1;

        gbc.insets = new Insets(1,1,1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcTimerPanel() {
        gbc.gridx = 1;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 0.333;
        gbc.weighty = 1;

        gbc.insets = new Insets(1,1,1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;

    }
}
