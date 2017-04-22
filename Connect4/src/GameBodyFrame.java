import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by aslak on 03.04.17.
 */
public class GameBodyFrame extends JFrame {

    // Temporary text
    JLabel labelCurrentPlayerText = new JLabel();

    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel playersPanel = new JPanel();
    JPanel timerPanel = new JPanel();
    JPanel leftPlayerPanel = new JPanel();
    JPanel rightPlayerPanel = new JPanel();

    JLabel timer = new JLabel("Time: 30");
    JLabel totalTimer = new JLabel("Total time: 0:0");

    GridBagConstraints gbc = new GridBagConstraints();

    GameBodyModel gameBodyModel;

    /**
     * This is the Game Frame, which contains all in-game elements.
     * such as the game log/history, game grid (6x7), power-ups ect.
     */
    public GameBodyFrame() {

        gameBodyModel = new GameBodyModel();

        // JFrame setup
        this.setTitle("Connect4");
        this.setSize(1280, 720);
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.blue);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // Left panel
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setBackground(Color.darkGray);
        leftPanel.add(labelCurrentPlayerText);
        gbcLeftPanel();
        this.add(leftPanel, gbc);

        // Right panel
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setBackground(Color.gray);
        gbcRightPanel();
        this.add(rightPanel, gbc);

        // Center panel
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.white);
        gbcCenterPanel();
        this.add(centerPanel, gbc);

        // Players panel
        playersPanel.setLayout(new GridBagLayout());
        playersPanel.setBackground(Color.green);
        gbcPlayersPanel();
        this.add(playersPanel, gbc);

        // Left player panel
        leftPlayerPanel.setLayout(new GridBagLayout());
        leftPlayerPanel.setBackground(Color.RED);
        gbcLeftPlayerPanel();
        playersPanel.add(leftPlayerPanel, gbc);
        leftPlayerPanel.add(gameBodyModel.playerOneLabel);

        // Timer panel
        timerPanel.setLayout(new GridBagLayout());
        timerPanel.setBackground(Color.GRAY);
        gbcTimerPanel();
        playersPanel.add(timerPanel,gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        timerPanel.add(timer,gbc);
        gbc.gridy = 0;
        gbc.gridx = 0;
        timerPanel.add(totalTimer,gbc);


        // Right player panel
        rightPlayerPanel.setLayout(new GridBagLayout());
        rightPlayerPanel.setBackground(Color.BLUE);
        gbcRightPlayerPanel();
        playersPanel.add(rightPlayerPanel, gbc);
        rightPlayerPanel.add(gameBodyModel.playerTwoLabel);


        // Top panel
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.white);
        gbcTopPanel();
        this.add(topPanel, gbc);


        // Set visibility
        this.setVisible(true);
    }

    void gbcLeftPanel() {

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 3;

        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.insets = new Insets(0, 0, 0, 0);
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
        gbc.gridy = 2;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 0.05;
        gbc.weighty = 0.2;

        gbc.insets = new Insets(0, 0, 0, 0);
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
        gbc.weighty = 0.9;

        gbc.insets = new Insets(0, 0, 0, 0);
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

        gbc.insets = new Insets(0, 0, 0, 0);
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

        gbc.weightx = 0.333;
        gbc.weighty = 1;

        gbc.insets = new Insets(0, 0, 0, 0);
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

        gbc.weightx = 0.333;
        gbc.weighty = 1;

        gbc.insets = new Insets(0, 0, 0, 0);
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

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;

    }
}
