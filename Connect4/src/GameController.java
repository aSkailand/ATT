import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

/**
 * Created by aslak on 03.04.17.
 */
public class GameController implements ActionListener {

    GameModel gameModel;
    GameFrame gameFrame;
    GameGridPanel gameGridPanel;

    public GameController() {
        gameModel = new GameModel();
        gameFrame = new GameFrame(this);

        // Initiating a GameGridPanel Object
        gameGridPanel = new GameGridPanel(gameModel.listJPanelGameBoardSlots);

        // Adding the GameGridPanel to gameFrame's centerPanel.
        gameFrame.centerPanel.add(gameGridPanel);
        gameFrame.centerPanel.repaint();
        gameFrame.centerPanel.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Check the number on the clicked button
        int chosenCol = Integer.parseInt(e.getActionCommand());

        // Find first index of the slot that is not occupied in the chosen column
        int indexOfNotOccupied = gameModel.listBoolOccupiedSlots.get(chosenCol).indexOf(GameModel.player.PLAYER_NONE);

        // Make the game piece ready
        JButton curPiece = new JButton("" + gameModel.currentPlayer);

        // Color the piece according to current player
        curPiece.setBorderPainted(false);
        curPiece.setForeground(Color.WHITE);
        if (gameModel.currentPlayer.equals(GameModel.player.PLAYER_1)) {
            curPiece.setBackground(gameModel.colorPlayer1);
        }
        else{
            curPiece.setBackground(gameModel.colorPlayer2);
        }

        // Place a button (piece) on chosen column
        gameModel.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).removeAll();
        gameModel.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).add(curPiece);
        gameModel.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).validate();
        gameModel.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).repaint();

        // Tick on the occupancy status
        gameModel.listBoolOccupiedSlots.get(chosenCol).set(indexOfNotOccupied, gameModel.currentPlayer);

        // Switch Current Player
        if (gameModel.currentPlayer.equals(GameModel.player.PLAYER_1)) {
            gameModel.currentPlayer = GameModel.player.PLAYER_2;
        } else gameModel.currentPlayer = GameModel.player.PLAYER_1;

        // Check 4-in-Row - Vertical
        for (int i = 0; i < GameModel.numCol; i++) {
            if(4 <= Collections.frequency(gameModel.listBoolOccupiedSlots.get(i),true)){
                System.out.println("SHIET");
            }
        }

    }
}
