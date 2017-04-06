import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Created by aslak on 03.04.17.
 */
public class GameFrame extends JFrame {


    // Temporary text
    JLabel labelCurrentPlayerText = new JLabel();

    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel topPanel = new JPanel();

    //JMenu items
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem mainMenu = new JMenuItem("Main menu");
    JMenuItem highscore = new JMenuItem("Highscore");
    JMenuItem saveGame = new JMenuItem("Save game");
    JMenuItem openGame = new JMenuItem("Open game");
    JMenuItem pauseGame = new JMenuItem("Pause game");
    JMenuItem restartGame = new JMenuItem("Restart game");


    /**
     * This is the Game Frame, which contains all in-game elements.
     * such as the game log/history, game grid (6x7), power-ups ect.
     */
    public GameFrame(Controller C) {

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

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Left panel
        leftPanel.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 1;
        gbc.gridheight = 2;
        leftPanel.setBackground(Color.blue);

        labelCurrentPlayerText.setText("Hello");
        leftPanel.add(labelCurrentPlayerText);


        this.add(leftPanel, gbc);


        //Right panel
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


        // Center panel
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


        // Top panel

        topPanel.setLayout(new GridBagLayout());
        topPanel.setBackground(Color.cyan);


        // todo: Make the amount of buttons here depend on common Col num!
        // Add buttons to top panel.
        for (int i = 0; i < Model.numCol; i++) {
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = i;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.gridheight = 1;

            JButton button1 = new JButton("[" + i + "]");
            button1.setActionCommand("" + i);
            button1.addActionListener(C);
            topPanel.add(button1, gbc);
        }

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.weighty = 0.05;
        gbc.gridheight = 1;

        this.add(topPanel, gbc);

        this.setVisible(true);


    }
}
