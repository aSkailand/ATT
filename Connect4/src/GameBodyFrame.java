import javax.swing.*;
import java.awt.*;

/**
 * Created by aslak on 03.04.17.
 */
public class GameBodyFrame extends JFrame {

    GameBodyModel gameBodyModel;

    // SUPER GBC
    GridBagConstraints gbc = new GridBagConstraints();

    // Center setup
    JPanel centerPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel timerPanel = new JPanel();

    JPanel centerStatusPanel = new JPanel();

    JLabel labelNotifications = new JLabel("NOTIFICATIONS");

    // Left player setup
    JPanel leftPlayerPanel = new JPanel();
    JPanel leftPlayerAvatarPanel = new JPanel();
    JPanel leftPlayerGoldPanel = new JPanel();
    JPanel leftPlayerUnitPanel = new JPanel();
    JPanel leftPlayerHPPanel = new JPanel();
    JPanel leftPlayerGoldGrid = new JPanel();

    JLabel leftPlayerHP = new JLabel("");

    // Right player setup
    JPanel rightPlayerPanel = new JPanel();
    JPanel rightPlayerAvatarPanel = new JPanel();
    JPanel rightPlayerGoldPanel = new JPanel();
    JPanel rightPlayerUnitPanel = new JPanel();
    JPanel rightPlayerHPPanel = new JPanel();
    JPanel rightPlayerGoldGrid = new JPanel();

    JLabel rightPlayerHP = new JLabel();

    /**
     * This is the Game Frame, which contains all in-game elements.
     * such as the game log/history, game grid (6x7), power-ups ect.
     */
    public GameBodyFrame(GameBodyController gameBodyController) {

        // Declare MVC
        gameBodyModel = gameBodyController.gameBodyModel;

        // JFrame setup
        this.setTitle("Connect4");
        this.setSize(1200, 720);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.blue);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        /* ***********************************************************
        *********************** CENTER STUFF**************************
        *********************************************************** */

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        this.add(mainPanel);

        // Center panel
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.white);
        gbcCenterPanel();
        mainPanel.add(centerPanel, gbc);


        // Timer panel
        timerPanel.setLayout(new BorderLayout());
        timerPanel.setBackground(Color.GRAY);
        gbcTimerPanel();
        mainPanel.add(timerPanel, gbc);

        // Center status panel
        centerStatusPanel.setLayout(new GridBagLayout());
        centerStatusPanel.setBackground(Color.LIGHT_GRAY);
        labelNotifications.setForeground(Color.WHITE);
        centerStatusPanel.add(labelNotifications);
        gbcCenterStatusPanel();
        mainPanel.add(centerStatusPanel, gbc);

        // Top panel
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.white);
        gbcTopPanel();
        mainPanel.add(topPanel, gbc);

        /* **********************************************************
        *********************** LEFT PLAYER *************************
        ********************************************************** */

        // Left player avatar
        leftPlayerAvatarPanel.setLayout(new BorderLayout());
        leftPlayerAvatarPanel.setPreferredSize(new Dimension(100,100));
        leftPlayerAvatarPanel.setBackground(Color.RED);
        leftPlayerAvatarPanel.add(gameBodyModel.playerOneAvatarButton);
        gbcLeftPlayerAvatarPanel();
        mainPanel.add(leftPlayerAvatarPanel, gbc);


        // Left player gold panel
        leftPlayerGoldPanel.setLayout(new GridBagLayout());
        leftPlayerGoldPanel.setBackground(Color.RED.darker());
        gbcLeftPlayerGoldPanel();
        mainPanel.add(leftPlayerGoldPanel, gbc);
        leftPlayerGoldGrid.setBackground(Color.RED.darker());
        leftPlayerGoldGrid.setLayout(new GridLayout(1,2));
        leftPlayerGoldPanel.add(leftPlayerGoldGrid);


        // Left player unit panel
        leftPlayerUnitPanel.setLayout(new BorderLayout());
        leftPlayerUnitPanel.setBackground(Color.RED);
        gbcLeftPlayerUnitPanel();
        mainPanel.add(leftPlayerUnitPanel, gbc);

        // Left player top panel
        leftPlayerPanel.setLayout(new GridBagLayout());
        leftPlayerPanel.setBackground(Color.RED.darker());
        gbcLeftPlayerPanel();
        mainPanel.add(leftPlayerPanel, gbc);

        // HP FONT
        leftPlayerHP.setHorizontalAlignment(SwingConstants.CENTER);
        leftPlayerHP.setFont(gameBodyModel.loadFontHP());
        leftPlayerHP.setForeground(Color.white);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 0;

        leftPlayerPanel.add(leftPlayerHP, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 0;
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.BOTH;


        leftPlayerHPPanel.setBackground(Color.RED.darker());
        leftPlayerHPPanel.setLayout(new FlowLayout());
        leftPlayerPanel.add(leftPlayerHPPanel, gbc);



        /* *********************************************************
        *********************** RIGHT PLAYER ***********************
        ********************************************************* */


        // Right player avatar panel
        rightPlayerAvatarPanel.setLayout(new BorderLayout());
        rightPlayerAvatarPanel.setPreferredSize(new Dimension(100,100));
        rightPlayerAvatarPanel.setBackground(Color.BLUE.darker());
        gbcRightPlayerAvatarPanel();
        mainPanel.add(rightPlayerAvatarPanel, gbc);
        rightPlayerAvatarPanel.add(gameBodyModel.playerTwoAvatarButton);


        // Right player gold panel
        rightPlayerGoldPanel.setLayout(new GridBagLayout());
        rightPlayerGoldPanel.setBackground(Color.BLUE.darker());
        gbcRightPlayerGoldPanel();
        mainPanel.add(rightPlayerGoldPanel, gbc);
        rightPlayerGoldGrid.setLayout(new GridLayout(1,2));
        rightPlayerGoldGrid.setBackground(Color.BLUE.darker());
        rightPlayerGoldPanel.add(rightPlayerGoldGrid);

        // Right player unit panel
        rightPlayerUnitPanel.setLayout(new BorderLayout());
        rightPlayerUnitPanel.setBackground(Color.BLUE.darker().darker());
        gbcRightPlayerUnitPanel();
        mainPanel.add(rightPlayerUnitPanel, gbc);

        // Right player top panel
        rightPlayerPanel.setLayout(new GridBagLayout());
        rightPlayerPanel.setBackground(Color.BLUE.darker());
        gbcRightPlayerPanel();
        mainPanel.add(rightPlayerPanel, gbc);

        //HP FONT
        rightPlayerHP.setFont(gameBodyModel.loadFontHP());
        rightPlayerHP.setHorizontalAlignment(SwingConstants.CENTER);
        rightPlayerHP.setForeground(Color.white);


        gbc.gridx = 3;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        rightPlayerPanel.add(rightPlayerHP, gbc);

        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weighty=0;

        rightPlayerHPPanel.setBackground(Color.BLUE.darker());
        rightPlayerHPPanel.setLayout(new FlowLayout());
        rightPlayerPanel.add(rightPlayerHPPanel,gbc);

        // Set visibility
        this.setVisible(false);
    }

    // Center GBC's
    void gbcMainPanel() {

        gbc.gridx = 0;
        gbc.gridy = 2;

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

    void gbcCenterPanel() {

        gbc.gridx = 1;
        gbc.gridy = 3;

        gbc.gridwidth = 3;
        gbc.gridheight = 1;

        gbc.weightx = 0.5;
        gbc.weighty = 1;

        gbc.insets = new Insets(1, 1, 1, 1);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;

    }

    void gbcCenterStatusPanel() {

        gbc.gridx = 1;
        gbc.gridy = 1;

        gbc.gridwidth = 3;
        gbc.gridheight = 1;

        gbc.weightx = 0.5;
        gbc.weighty = 0.05;

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcTimerPanel() {
        gbc.gridx = 2;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 1;
        gbc.weighty = 0.5;

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;

    }

    void gbcTopPanel() {

        gbc.gridx = 1;
        gbc.gridy = 2;

        gbc.gridwidth = 3;
        gbc.gridheight = 1;

        gbc.weightx = 0.5;
        gbc.weighty = 0.05;

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    // Left GBC's
    void gbcLeftPlayerAvatarPanel() {
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 2;

        gbc.weightx = 1;
        gbc.weighty = 0.5;

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcLeftPlayerGoldPanel() {

        gbc.gridx = 0;
        gbc.gridy = 2;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 1;
        gbc.weighty = 0.005;

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;

    }

    void gbcLeftPlayerUnitPanel() {
        gbc.gridx = 0;
        gbc.gridy = 3;

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

    void gbcLeftPlayerPanel() {

        gbc.gridx = 1;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 0.5;
        gbc.weighty = 0.333;

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    // Right GBC's
    void gbcRightPlayerAvatarPanel() {

        gbc.gridx = 4;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 2;

        gbc.weightx = 1;
        gbc.weighty = 0.333;

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcRightPlayerGoldPanel() {
        gbc.gridx = 4;
        gbc.gridy = 2;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 1;
        gbc.weighty = 0.05;

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }

    void gbcRightPlayerUnitPanel() {

        gbc.gridx = 4;
        gbc.gridy = 3;

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

    void gbcRightPlayerPanel() {

        gbc.gridx = 3;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 0.5;
        gbc.weighty = 0.333;

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;
    }
}
