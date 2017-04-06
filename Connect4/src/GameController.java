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
        } else {
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
        checkWinVertical();

        // Check 4-in-Row - Horizontal
        // Check through all Rows.
        for (int i = 0; i < GameModel.numRow; i++) {

            // Counter = 4 -> Player 1 wins, if Counter = -4 -> Player 2 wins
            int counter = 0;

            for (int j = 0; j < GameModel.numCol; j++) {
                if (gameModel.listBoolOccupiedSlots.get(j).get(i).equals(GameModel.player.PLAYER_1)) {
                    if (counter < 0) counter = 0;
                    counter++;
                    if (counter >= GameModel.winInRow) {
                        System.out.println("Player 1 Won! - Vertically on COL: " + i);
                        break;
                    }
                } else {
                    if (counter > 0) counter = 0;
                    counter--;
                    if (counter <= -GameModel.winInRow) {
                        System.out.println("Player 2 Won! - Vertically on COL: " + i);
                        break;
                    }
                }
            }
        }
    }


    // TODO: Move this function over to model when done here with all win - conditions
    // TODO: Consider change it from void -> GameModel.player. May open more flexibility.
    void checkWinVertical() {

        // Check through all columns.
        for (int i = 0; i < GameModel.numCol; i++) {

            // Only check if there is low enough empty slots
            if (GameModel.numRow - GameModel.winInRow >= Collections.frequency(gameModel.listBoolOccupiedSlots.get(i), GameModel.player.PLAYER_NONE)) {

                // Counter = 4 -> Player 1 wins, if Counter = -4 -> Player 2 wins
                int counter = 0;

                for (int j = 0; j < GameModel.numRow; j++) {
                    if (gameModel.listBoolOccupiedSlots.get(i).get(j).equals(GameModel.player.PLAYER_1)) {
                        if (counter < 0) counter = 0;
                        counter++;
                        if (counter >= GameModel.winInRow) {
                            System.out.println("Player 1 Won! - Vertically on COL: " + i);
                            break;
                        }
                    } else {
                        if (counter > 0) counter = 0;
                        counter--;
                        if (counter <= -GameModel.winInRow) {
                            System.out.println("Player 2 Won! - Vertically on COL: " + i);
                            break;
                        }
                    }
                }
            }
        }

    }


}