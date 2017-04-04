import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by TrongDT on 03/04/2017.
 */
public class GameBoard extends JFrame{


    // TEST HELLO

    int numRow = 6;
    int numCol = 7;
    GamePieceSlot[][] LIST = new GamePieceSlot[numRow][numCol];


    GameBoard(GameController GC){
        this.setSize(1000,1000);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel button_holder = new JPanel();
        button_holder.setLayout(new GridBagLayout());
        button_holder.setBackground(Color.RED);

        for (int i = 0; i < 7; i++) {

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(5,5,5,5);
            gbc.weightx = 1;
            gbc.weighty = 10/7;
            gbc.gridx = i;
            gbc.gridy = 0;

            JButton B = new JButton(""+i);
            B.setActionCommand(""+i);
            B.addActionListener(GC);
            button_holder.add(B, gbc);
        }

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0,0,0,0);
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 0;

        this.add(button_holder, gbc);

        JPanel panelBoard = new JPanel();
        panelBoard.setLayout(new GridLayout(6,7,1,1));


        // Put a pane for every grid square.
        for (int i = 0; i < numCol; i++) {
            for (int j = 0; j < numRow; j++) {
                GamePieceSlot gamePieceSlot = new GamePieceSlot();
                gamePieceSlot.setToolTipText("COL:"+i+",ROW:"+j);
                gamePieceSlot.setBackground(Color.gray);

                panelBoard.add(gamePieceSlot);
                LIST[i][j] = gamePieceSlot;
            }
        }




        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,5,5,5);
        gbc.weightx = 1;
        gbc.weighty = 0.8;
        gbc.gridx = 0;
        gbc.gridy = 1;

        this.add(panelBoard, gbc);
        this.setVisible(true);

    }
}
