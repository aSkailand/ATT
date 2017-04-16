import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

        colorOptionButtons(gameBoardModel.getPlayerColor(gameBoardModel.currentPlayer));

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        // Check the number on the clicked button
        int chosenCol = Integer.parseInt(e.getActionCommand());

        // Place piece
        placePiece(chosenCol);

        // Check if there's any winners
        checkWinAllConditions(gameBoardModel.currentPlayer);
        checkWinAllConditions(gameBoardModel.waitingPlayer);

        // Swap player
        swapPlayer();

        // Change option buttons' color to current player
        colorOptionButtons(gameBoardModel.getPlayerColor(gameBoardModel.currentPlayer));

        // Check if any columns are full
        disableFullColumns();
    }

    // TODO: Move this function over to model when done here with all win - conditions
    // TODO: Consider change it from void -> GameBoardModel.player. May open more flexibility.
    // CHECK WIN CONDITIONS
    void checkWinAllConditions(GameBoardModel.player player) {
        checkWinHorizontal(player);
        checkWinVertical(player);
        checkWinDescendingDiagonal(player);
        checkWinAscendingDiagonal(player);
    }

    void checkWinHorizontal(GameBoardModel.player player) {
        // Check 4-in-Row - Horizontal
        for (int i = 0; i < GameBoardModel.numRow; i++) {

            boolean winStorage = false;
            int counter = 0;

            for (int j = 0; j < GameBoardModel.numCol; j++) {
                if (getSlot(j, i).getOwner().equals(player)){
                    counter++;
                    System.out.println(counter);
                    if(counter >= GameBoardModel.winInRow){
                        winStorage = true;
                    }
                }
                else {
                    if(winStorage){

                        System.out.println(player + " Won! - Horizontal on Row: " + i);
                        colorWinningRow(j-counter, i, 1, 0, counter, player);

                    }

                    counter = 0;

                }

            }
        }
    }

    void checkWinVertical(GameBoardModel.player player) {

        // Check through all columns.
        for (int i = 0; i < GameBoardModel.numCol; i++) {

            int counter = 0;

            for (int j = 0; j < GameBoardModel.numRow; j++) {
                if (getSlot(i, j).getOwner().equals(player)) {
                    counter++;
                    if (counter >= GameBoardModel.winInRow) {
                        System.out.println(player + " Won! - Vertically on COL: " + i);

                        break;
                    }
                } else counter = 0;
            }
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
            int counter = 0;
            int cur_x = init_x;
            int cur_y = init_y;

            // check if x or y hit their max limit
            while (cur_x <= GameBoardModel.numCol - 1 && cur_y <= GameBoardModel.numRow - 1) {

                // Check current tile's owner
                if (getSlot(cur_x, cur_y).getOwner().equals(player)) {
                    counter++;
                    if (counter >= GameBoardModel.winInRow) {
                        System.out.println(player + " Won! - Ascending Diagonally starting on (" + init_x + "," + init_y + ")");
                        break;
                    }
                } else counter = 0;

                // increments both, simulating ascending rightwards.
                cur_x++;
                cur_y++;
            }

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
            int counter = 0;
            int cur_x = init_x;
            int cur_y = init_y;

            // check if x or y hit their max limit
            while (cur_x < GameBoardModel.numCol && cur_y >= 0) {

                // Check current tile's owner
                if (getSlot(cur_x, cur_y).getOwner().equals(player)) {
                    counter++;
                    if (counter >= GameBoardModel.winInRow) {
                        System.out.println(player + " Won! - Descending Diagonally starting on (" + init_x + "," + init_y + ")");
                        break;
                    }
                } else counter = 0;

                // increments both, simulating descending rightwards.
                cur_x++;
                cur_y--;
            }

            // Moving one step further
            if (init_y != last_y) init_y++;
            else init_x++;

        }
    }

    // todo: finish this when got time.
    void colorWinningRow(int init_x, int init_y, int increment_x, int increment_y, int count, GameBoardModel.player player) {

        int x = init_x;
        int y = init_y;

        Color WinColor;

        if (player.equals(GameBoardModel.player.PLAYER_1)) WinColor = gameBoardModel.colorWin1;
        else WinColor = gameBoardModel.colorWin2;

        for (int i = 0; i < count; i++) {
            gameBoardPanel.listJPanelGameBoardSlots.get(x).get(y).piece.setBackground(WinColor);
            x += increment_x;
            y += increment_y;
        }

    }


    // BOARD ACTIONS

    // Place piece at chosen column
    void placePiece(int chosenCol) {

        // Find available slot in chosen column
        int indexOfNotOccupied = gameBoardModel.listOccupancyGameBoardSlots.get(chosenCol).indexOf(GameBoardModel.player.PLAYER_NONE);

        // Abort if not found
        if (indexOfNotOccupied == -1) {
            System.out.println("No empty slots in this column, terminating method.");
            return; // Never supposed to happen. If it happens, we got a bug somewhere.
        }

        // Set current player color on piece
        getSlot(chosenCol, indexOfNotOccupied).piece.setBackground(gameBoardModel.getPlayerColor(gameBoardModel.currentPlayer));

        // Set enabled on piece
        getSlot(chosenCol, indexOfNotOccupied).piece.setEnabled(true);

        // Set owner on piece
        getSlot(chosenCol, indexOfNotOccupied).owner = gameBoardModel.currentPlayer;

        // Tick occupancy list
        gameBoardModel.listOccupancyGameBoardSlots.get(chosenCol).set(indexOfNotOccupied, gameBoardModel.currentPlayer);

    }

    // Make it impossible to place piece in full columns
    void disableFullColumns() {
        for (int i = 0; i < GameBoardModel.numCol; i++) {
            if (gameBoardModel.listOccupancyGameBoardSlots.get(i).indexOf(GameBoardModel.player.PLAYER_NONE) == -1) {
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
    void swapPlayer() {
        // Swap Current Player
        GameBoardModel.player tempPlayer = gameBoardModel.currentPlayer;
        gameBoardModel.currentPlayer = gameBoardModel.waitingPlayer;
        gameBoardModel.waitingPlayer = tempPlayer;
    }


    // GETTERS

    // Getter-Wrapper for the slots inside GameBoardPanel
    GamePieceSlot getSlot(int x, int y) {
        return gameBoardPanel.listJPanelGameBoardSlots.get(x).get(y);
    }
}