import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by aslak on 03.04.17.
 */
public class GameFrame extends JFrame {

    // Number of Rows and Columns
    private int numRow = 6;
    private int numCol = 7;

//    boolean[][] OccupiedSlots = new boolean[numCol][numRow];
    ArrayList<ArrayList<Boolean>> OccupiedSlots = new ArrayList<>();
    ArrayList<ArrayList<JPanel>> GameBoardSlots = new ArrayList<>();


    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel gridPanel = new JPanel();

    JLabel labelCurrentPlayerText = new JLabel();

    // Array of gridsystem ( NameOfArray[Row][Col] )
    JPanel[][] LIST = new JPanel[numRow][numCol];

    Color colorPlayer1 = Color.RED;
    Color colorPlayer2 = Color.BLUE;

    public enum player{PLAYER_1, PLAYER_2}

    player currentPlayer = player.PLAYER_1;

    /**
     * This is the Game Frame, which should contain all the game elements
     * such as the game log/history, game grid (6x7), power-ups ect.
     */
    public GameFrame(Controller C) {



        // JFrame setup
        this.setTitle("Connect4");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBackground(Color.blue);

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

        labelCurrentPlayerText.setText("CURRENT PLAYER: "+currentPlayer);
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

        // Add buttons to top panel.
        for (int i = 0; i < numCol; i++) {
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = i;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.gridheight = 1;

            JButton button1 = new JButton("["+i+"]");
            button1.setActionCommand(""+i);
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

        // Center panel
        // Adding a grid panel to center panel
        gridPanel.setLayout(new GridLayout(numRow, numCol));




        ArrayList<JPanel> tempy = new ArrayList<>();
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {

                JButton tempButton = new JButton("( "+j+" , "+(numRow-i-1)+" )");
                tempButton.setEnabled(false);

                JPanel panelTemp = new JPanel(new GridLayout(1,1));
                panelTemp.add(tempButton);
                tempy.add(panelTemp);
            }
        }

        for (int i = 0; i < numRow*numCol; i++) {
            gridPanel.add(tempy.get(i));
        }
        centerPanel.add(gridPanel);

        // Creating the ArrayLists for ArrayList of panels.
        for (int i = 0; i < numCol; i++) {
            GameBoardSlots.add(new ArrayList<JPanel>());
            for (int j = 0; j < numRow; j++) {
                GameBoardSlots.get(i).add(tempy.get((35+i)-7*j));
            }
        }

        // Creating the ArrayLists for ArrayList of Occupied Slots.
        for (int i = 0; i < numCol; i++) {
            OccupiedSlots.add(new ArrayList<Boolean>());
            for (int j = 0; j < numRow; j++) {
                OccupiedSlots.get(i).add(false);
            }
        }



        this.setVisible(true);


    }
}
