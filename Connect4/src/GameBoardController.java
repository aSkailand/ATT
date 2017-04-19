import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by aslak on 03.04.17.
 */
public class GameBoardController implements ActionListener {

    GameBodyFrame gameBodyFrame;

    GameBoardModel gameBoardModel;
    GameBoardPanel gameBoardPanel;

    GameOptionPanel gameOptionPanel;



    public GameBoardController(GameBodyFrame gbFrame) {



        gameBodyFrame = gbFrame;

        gameBoardModel = new GameBoardModel();
        gameBoardPanel = new GameBoardPanel();
        gameOptionPanel = new GameOptionPanel(this);

        gameBodyFrame.centerPanel.add(gameBoardPanel);
        gameBodyFrame.topPanel.add(gameOptionPanel);

        gameBodyFrame.revalidate();
        gameBodyFrame.repaint();

        colorOptionButtons(gameBoardModel.getPlayerColor(gameBoardModel.getCurrentPlayer()));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("\nTURN: "+ gameBoardModel.getTurn());
        System.out.println("**********");

        // Check the number on the clicked button
        int chosenCol = Integer.parseInt(e.getActionCommand());

        // Place piece
        placePiece(chosenCol);

        // Check if there's any winners
        checkWinAllConditions(gameBoardModel.getCurrentPlayer());
        checkWinAllConditions(gameBoardModel.getWaitingPlayer());

        // Swap player
        alternatePlayers();

        // Proceed turn
        gameBoardModel.proceedTurn();

        // Change option buttons' color to current player
        colorOptionButtons(gameBoardModel.getPlayerColor(gameBoardModel.getCurrentPlayer()));

        // Check if any columns are full
        disableFullColumns();
    }

    // TODO: Move this function over to model when done here with all win - conditions
    // TODO: Consider change it from void -> GameBoardModel.player. May open more flexibility.
    // CHECK WIN CONDITIONS
    void checkWinAllConditions(GameBoardModel.player player) {
        System.out.println("CHECK WIN: " + player);

        System.out.println("\tcheck Horizontal:");
        checkWinHorizontal(player);

        System.out.println("\tcheck Vertical:");
        checkWinVertical(player);

        System.out.println("\tcheck Descending:");
        checkWinDescendingDiagonal(player);

        System.out.println("\tcheck Ascending:");
        checkWinAscendingDiagonal(player);

        System.out.println("");
    }

    void checkWinHorizontal(GameBoardModel.player player) {

        // Check through all rows
        for (int i = 0; i < GameBoardModel.numRow; i++) {

            // Instantiating list
            ArrayList<Integer> lister = new ArrayList<>();  // 1D binary list of current row

            for (int j = 0; j < GameBoardModel.numCol; j++) {
                if (gameBoardPanel.getSlot(j, i).getOwner().equals(player)) lister.add(1);
                else lister.add(0);
            }

            // Find win
            colorWinningRow(0, i, 1, 0, lister, player);

        }
    }

    void checkWinVertical(GameBoardModel.player player) {

        // Check through all columns
        for (int i = 0; i < GameBoardModel.numCol; i++) {

            // Instantiating list
            ArrayList<Integer> lister = new ArrayList<>();  // 1D binary list of current row

            for (int j = 0; j < GameBoardModel.numRow; j++) {
                if (gameBoardPanel.getSlot(i, j).getOwner().equals(player)) lister.add(1);
                else lister.add(0);
            }

            // Find win
            colorWinningRow(i, 0, 0, 1, lister, player);

        }
    }

    void checkWinAscendingDiagonal(GameBoardModel.player player) {

        // Declare early variables
        int init_x = 0;
        int init_y = GameBoardModel.numRow - GameBoardModel.winInRow;

        int last_x = GameBoardModel.numCol - GameBoardModel.winInRow;
        int last_y = 0;

        // Run until x has reached its end
        while (init_x <= last_x) {

            // Reset variables
            int cur_x = init_x;
            int cur_y = init_y;

            // Instantiating list
            ArrayList<Integer> lister = new ArrayList<>();  // 1D binary list of current row

            // Check if x or y hit their max limit
            while (cur_x < GameBoardModel.numCol && cur_y < GameBoardModel.numRow) {

                // Check current tile's owner
                if (gameBoardPanel.getSlot(cur_x, cur_y).getOwner().equals(player)) lister.add(1);
                else lister.add(0);

                // Increments both, simulating ascending rightwards.
                cur_x++;
                cur_y++;
            }

            // Find win
            colorWinningRow(init_x, init_y, 1, 1, lister, player);

            // Moving one step further
            if (init_y != last_y) init_y--;
            else init_x++;

        }
    }

    void checkWinDescendingDiagonal(GameBoardModel.player player) {

        // Declare early variables
        int init_x = 0;
        int init_y = GameBoardModel.winInRow - 1;

        int last_x = GameBoardModel.numCol - GameBoardModel.winInRow;
        int last_y = GameBoardModel.numRow - 1;

        // Run until x has reached its end
        while (init_x <= last_x) {

            // Reset variables
            int cur_x = init_x;
            int cur_y = init_y;

            // Instantiating list
            ArrayList<Integer> lister = new ArrayList<>();  // 1D binary list of current row

            // Check if x or y hit their max limit
            while (cur_x < GameBoardModel.numCol && cur_y >= 0) {

                // Check current tile's owner
                if (gameBoardPanel.getSlot(cur_x, cur_y).getOwner().equals(player)) lister.add(1);
                else lister.add(0);

                // Increments both, simulating ascending rightwards.
                cur_x++;
                cur_y--;
            }

            // Find win
            colorWinningRow(init_x, init_y, 1, -1, lister, player);

            // Moving one step further
            if (init_y != last_y) init_y++;
            else init_x++;


        }
    }

    void colorWinningRow(int init_x, int init_y, int increment_x, int increment_y, ArrayList<Integer> lister, GameBoardModel.player player) {

        // Prematurely return if not enough same pieces
        if (!(Collections.frequency(lister, 1) >= GameBoardModel.winInRow)){
            return;
        }

        int x = init_x;
        int y = init_y;

        // Pick correct color
        Color WinColor = gameBoardModel.getPlayerWinColor(player);

        int counter = 0;

        // Check given list
        for (int i = 0; i < lister.size(); i++) {

            if (lister.get(i) == 1) {
                counter++;
            }

            if (lister.get(i) == 0 || i == lister.size() - 1) {

                // Check if long enough row exist
                if (counter >= GameBoardModel.winInRow) {

                    // Add win-events here (things that happens if one user wins)
                    // HERE
                    // HERE
                    // HERE

                    System.out.println("\t\t> x: " + x + ", y: " + y + ", length: " + counter);

                    for (int j = 0; j < counter; j++) {
                        gameBoardPanel.getSlot(x,y).piece.setBackground(WinColor);
                        x += increment_x;
                        y += increment_y;
                    }
                } else {
                    x += increment_x;
                    y += increment_y;
                }
                counter = 0;
            }

        }

    }


    // BOARD ACTIONS

    // Place piece at chosen column
    void placePiece(int chosenCol) {

        // Find available slot in chosen column
        int indexOfNotOccupied = gameBoardModel.getListOccupancy().get(chosenCol).indexOf(GameBoardModel.player.PLAYER_NONE);

        // Abort if not found
        if (indexOfNotOccupied == -1) {
            System.out.println("No empty slots in this column, terminating method.");
            return; // Never supposed to happen. If it happens, we got a bug somewhere.
        }

        // Set current player color on piece
        gameBoardPanel.getSlot(chosenCol, indexOfNotOccupied).piece.setBackground(gameBoardModel.getPlayerColor(gameBoardModel.getCurrentPlayer()));

        // Set enabled on piece
        gameBoardPanel.getSlot(chosenCol, indexOfNotOccupied).piece.setEnabled(true);

        // Set owner on piece
        gameBoardPanel.getSlot(chosenCol, indexOfNotOccupied).owner = gameBoardModel.getCurrentPlayer();

        // Tick occupancy list
        gameBoardModel.getListOccupancy().get(chosenCol).set(indexOfNotOccupied, gameBoardModel.getCurrentPlayer());

    }

    // Make it impossible to place piece in full columns
    void disableFullColumns() {
        for (int i = 0; i < GameBoardModel.numCol; i++) {
            if (gameBoardModel.getListOccupancy().get(i).indexOf(GameBoardModel.player.PLAYER_NONE) == -1) {
                gameOptionPanel.optionList.get(i).setEnabled(false);
            }
        }
    }

    // Colorize the option button colors
    void colorOptionButtons(Color playerColor) {
        for (int i = 0; i < GameBoardModel.numCol; i++) {
            gameOptionPanel.optionList.get(i).setBackground(playerColor);
            gameOptionPanel.optionList.get(i).setForeground(Color.white);
        }
    }

    // Switch current player with waiting player, and vice versa
    void alternatePlayers() {
        // Swap Current Player
        GameBoardModel.player tempPlayer = gameBoardModel.getCurrentPlayer();
        gameBoardModel.setCurrentPlayer(gameBoardModel.getWaitingPlayer());
        gameBoardModel.setWaitingPlayer(tempPlayer);
    }


    // GETTERS


}