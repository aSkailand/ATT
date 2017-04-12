import javax.swing.*;
import java.awt.*;

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

    //JMenu items
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem mainMenu = new JMenuItem("Main menu");
    JMenuItem highscore = new JMenuItem("Highscore");
    JMenuItem saveGame = new JMenuItem("Save game");
    JMenuItem openGame = new JMenuItem("Open game");
    JMenuItem pauseGame = new JMenuItem("Pause game");
    JMenuItem restartGame = new JMenuItem("Restart game");

    GridBagConstraints gbc = new GridBagConstraints();

    /**
     * This is the Game Frame, which contains all in-game elements.
     * such as the game log/history, game grid (6x7), power-ups ect.
     */
    public GameBodyFrame(GameBoardController Gcontroller) {

        // JFrame setup
        this.setTitle("Connect4");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setBackground(Color.blue);

        //JMenu setup
        this.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        fileMenu.add(mainMenu);
        fileMenu.addSeparator();
        fileMenu.add(highscore);
        fileMenu.addSeparator();
        fileMenu.add(saveGame);
        fileMenu.add(openGame);
        fileMenu.addSeparator();
        fileMenu.add(pauseGame);
        fileMenu.add(restartGame);

        //The main GUI look setup
        this.setLayout(new GridBagLayout());

        // Left panel
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setBackground(Color.darkGray);
        leftPanel.add(labelCurrentPlayerText);
        gbcLeftPanel();
        this.add(leftPanel, gbc);

        //Right panel
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setBackground(Color.gray);
        gbcRightPanel();
        this.add(rightPanel, gbc);

        // Center panel
        centerPanel.setLayout(new GridLayout(1, 1));
        centerPanel.setBackground(Color.white);
        gbcCenterPanel();
        this.add(centerPanel, gbc);

        // Players panel
        playersPanel.setLayout(new GridBagLayout());
        playersPanel.setBackground(Color.lightGray);
        gbcPlayersPanel();
        this.add(playersPanel, gbc);

        // Top panel
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.white);
        gbcTopPanel();
        this.add(topPanel, gbc);

        // Set visibility
        this.setVisible(true);
    }

    void gbcAddButtonsToPanel(GameBoardController C){
        // todo: Make the amount of buttons here depend on common Col num!
        // Add buttons to top panel.

        for (int i = 0; i < GameBoardModel.numCol; i++) {
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = i;
            gbc.gridy = 0;

            gbc.gridwidth = 1;
            gbc.gridheight = 1;

            gbc.weightx = 0.05;
            gbc.weighty = 0.05;

            gbc.insets = new Insets(0, 0, 0, 0);
            gbc.ipadx = 0;
            gbc.ipady = 0;

            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.PAGE_END;

            JButton button1 = new JButton("[" + i + "]");
            button1.setActionCommand("" + i);
            button1.addActionListener(C);

            topPanel.add(button1, gbc);
        }
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
}
