import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Trong on 25/04/2017.
 */
public class BoardWinController {

    GameBoardModel gameBoardModel;
    GameBoardPanel gameBoardPanel;

    BoardWinController(GameBoardModel gameBoardModel, GameBoardPanel gameBoardPanel, GameBoardController gameBoardController) {
        this.gameBoardModel = gameBoardModel;
        this.gameBoardPanel = gameBoardPanel;

    }

    boolean RUN() {

        cleanAllWinParts();

        if (checkWinAllConditions(GameBoardModel.player.PLAYER_1) | checkWinAllConditions(GameBoardModel.player.PLAYER_2)) {
            killWinPieces();
            gameBoardPanel.revalidate();
            gameBoardPanel.repaint();
            return true;
        } else {
            System.out.println("DONE!!!");
            return false;
        }
    }

    boolean checkWinAllConditions(GameBoardModel.player player) {

        boolean winInSight = false;

        System.out.println("CHECK WIN: " + player);

        System.out.println("\tcheck Horizontal:");
        if (checkWinHorizontal_All(player)) winInSight = true;

        System.out.println("\tcheck Vertical:");
        if (checkWinVertical_All(player)) winInSight = true;

        System.out.println("\tcheck Descending:");
        if (checkWinDescendingDiagonal_All(player)) winInSight = true;

        System.out.println("\tcheck Ascending:");
        if (checkWinAscendingDiagonal_All(player)) winInSight = true;

        System.out.println("");

        return winInSight;
    }

    boolean checkWinVertical_All(GameBoardModel.player player) {
        boolean foundWin = false;

        for (int i = 0; i < GameBoardModel.numCol; i++) {
            if (checkWinVertical_Single(i, 0, player)) foundWin = true;
        }

        return foundWin;
    }

    boolean checkWinHorizontal_All(GameBoardModel.player player) {

        boolean foundWin = false;

        for (int i = 0; i < GameBoardModel.numRow; i++) {
            if (checkWinHorizontal_Single(0, i, player)) foundWin = true;
        }

        return foundWin;

    }

    boolean checkWinAscendingDiagonal_All(GameBoardModel.player player) {

        boolean foundWin = false;

        int init_x = 0;
        int init_y = GameBoardModel.numRow - GameBoardModel.winInRow;

        int last_x = GameBoardModel.numCol - GameBoardModel.winInRow;
        int last_y = 0;

        while (init_x <= last_x) {

            int cur_x = init_x;
            int cur_y = init_y;

            if (checkWinAscendingDiagonal_Single(cur_x, cur_y, player)) foundWin = true;

            // Moving PLAYER_1 step further
            if (init_y != last_y) init_y--;
            else init_x++;

        }

        return foundWin;

    }

    boolean checkWinDescendingDiagonal_All(GameBoardModel.player player) {

        boolean foundWin = false;

        // Declare early variables
        int init_x = 0;
        int init_y = GameBoardModel.winInRow - 1;

        int last_x = GameBoardModel.numCol - GameBoardModel.winInRow;
        int last_y = GameBoardModel.numRow - 1;

        while (init_x <= last_x) {

            int cur_x = init_x;
            int cur_y = init_y;

            if (checkWinDescendingDiagonal_Single(cur_x, cur_y, player)) foundWin = true;

            // Moving PLAYER_1 step further
            if (init_y != last_y) init_y++;
            else init_x++;

        }

        return foundWin;

    }

    /* CHECK FOR WIN (SINGLE) */
    boolean checkIfWinningMove(int x, GameBoardModel.player player) {

        placePieceSoft(x, player);

        int indexOfUpper = gameBoardModel.getListOccupancy().get(x).lastIndexOf(player);

        if (checkWinVertical_Single(x, indexOfUpper, player)) {
            removePieceSoft(x);
            return true;
        }
        if (checkWinHorizontal_Single(x, indexOfUpper, player)) {
            removePieceSoft(x);
            return true;
        }
        if (checkWinAscendingDiagonal_Single(x, indexOfUpper, player)) {
            removePieceSoft(x);
            return true;
        }
        if (checkWinDescendingDiagonal_Single(x, indexOfUpper, player)) {
            removePieceSoft(x);
            return true;
        }

        removePieceSoft(x);
        return false;
    }

    /**
     * SINGLE WIN CHECKS
     * All single winChecks works in similar fashion.
     * They need a coordinate (x,y), and the player that they will look for.
     * From there, they will translate the row to an ArrayList lister that will contain 1's and 0's.
     * A 1 for each piece that correspond with given player, and a 0 for each that does not.
     *
     * @param x:      the x coordinate to start from.
     * @param y:      the y coordinate to start from.
     * @param player: the player the methods shall focus on.
     * @return true if a win occurs, and false if not.
     */
    boolean checkWinVertical_Single(int x, int y, GameBoardModel.player player) {

        // Instantiating list
        ArrayList<Integer> lister = new ArrayList<>();  // 1D binary list of current row
        for (int j = 0; j < GameBoardModel.numRow; j++) {
            if (gameBoardModel.getSlotOccupancy(x, j).equals(player)) lister.add(1);
            else lister.add(0);
        }

        // Find win
        return searchWinningRow(x, 0, 0, 1, lister);
    }

    boolean checkWinHorizontal_Single(int x, int y, GameBoardModel.player player) {

        // Instantiating list
        ArrayList<Integer> lister = new ArrayList<>();  // 1D binary list of current row
        for (int j = 0; j < GameBoardModel.numCol; j++) {
            if (gameBoardModel.getSlotOccupancy(j, y).equals(player)) lister.add(1);
            else lister.add(0);
        }

        // Find win
        return searchWinningRow(0, y, 1, 0, lister);
    }

    boolean checkWinAscendingDiagonal_Single(int x, int y, GameBoardModel.player player) {

        // Find initial values
        while (0 < x && 0 < y) {
            x--;
            y--;
        }

        // Declare initial values
        int init_x = x;
        int init_y = y;

        // Instantiating list
        ArrayList<Integer> lister = new ArrayList<>();  // 1D binary list of current row

        // Check if x or y hit their max limit
        while (x < GameBoardModel.numCol && y < GameBoardModel.numRow) {

            // Check current tile's owner
            if (gameBoardModel.getSlotOccupancy(x, y).equals(player)) lister.add(1);
            else lister.add(0);

            // Increments both, simulating ascending rightwards.
            x++;
            y++;
        }

        // Find win
        return searchWinningRow(init_x, init_y, 1, 1, lister);
    }

    boolean checkWinDescendingDiagonal_Single(int x, int y, GameBoardModel.player player) {

        // Find initial values
        while (0 < x && y < GameBoardModel.numRow - 1) {
            x--;
            y++;
        }

        // Declare initial values
        int init_x = x;
        int init_y = y;

        // Instantiating list
        ArrayList<Integer> lister = new ArrayList<>();  // 1D binary list of current row

        // Check if x or y hit their max limit
        while (x < GameBoardModel.numCol && 0 <= y) {

            // Check current tile's owner
            if (gameBoardModel.getSlotOccupancy(x, y).equals(player)) lister.add(1);
            else lister.add(0);

            // Increments both, simulating ascending rightwards.
            x++;
            y--;
        }

        return searchWinningRow(init_x, init_y, 1, -1, lister);

    }



    /* OTHER WIN CHECK METHODS */

    // todo: make it so searchWinningRow returns winning rows?
    // todo: maybe return ArrayList<Integer[]> where each Integer[] = {init_x, init_y, end_x, end_y}

    /**
     * Check the given ArrayList lister. Check for how many 1's in row. Mark the corresponding slots.
     *
     * @param init_x:      the initial x-coordinate of lister's row.
     * @param init_y:      the initial y-coordinate of lister's row.
     * @param increment_x: how x is incremented, used to simulate row (vertical, horizontal, ascending or descending).
     * @param increment_y: how y is incremented, used to simulate row (vertical, horizontal, ascending or descending).
     * @param lister:      the ArrayList forming the translated view of the current row.
     * @return true if there's win, false if not.
     */
    boolean searchWinningRow(int init_x, int init_y, int increment_x, int increment_y, ArrayList<Integer> lister) {

        // Prematurely return if not enough same pieces
        if (!(Collections.frequency(lister, 1) >= GameBoardModel.winInRow)) {
            return false;
        }

        // Return value
        boolean winInSight = false;

        // Initial values
        int x = init_x;
        int y = init_y;

        int counter = 0;

        // Check given list
        for (int i = 0; i < lister.size(); i++) {


            if (lister.get(i) == 1) {
                // Update anchor coordinates
                if (counter == 0) {
                    x = init_x + increment_x * i;
                    y = init_y + increment_y * i;
                }
                // Count up
                counter++;
            }


            if (lister.get(i) == 0 || i == lister.size() - 1) {
                // Check if long enough row exist
                if (counter >= GameBoardModel.winInRow) {

                    System.out.println(lister);

                    // Tick win
                    winInSight = true;

                    int anchor_x = x;
                    int anchor_y = y;

                    // Color win
                    for (int j = 0; j < counter; j++) {

                        gameBoardPanel.getSlot(x, y).win_part = true;
                        x += increment_x;
                        y += increment_y;
                    }

                    // Print out win message
                    System.out.println(
                            "\t\t> from (" + anchor_x + "," + anchor_y + ") " +
                                    "to (" + (x - increment_x) + "," + (y - increment_y) + "), " +
                                    "length: " + counter);
                }

                counter = 0;
            }

        }

        return winInSight;

    }

    void placePieceSoft(int chosenCol, GameBoardModel.player player) {

        // Find available slot in chosen column
        int indexOfNotOccupied = gameBoardModel.getListOccupancy().get(chosenCol).indexOf(GameBoardModel.player.PLAYER_NONE);

        // Abort if not found
        if (indexOfNotOccupied == -1) {
            System.out.println("No empty slots in this column, terminating method.");
            return; // Never supposed to happen. If it happens, we got a bug somewhere.
        }

        // Tick occupancy list
        gameBoardModel.getListOccupancy().get(chosenCol).set(indexOfNotOccupied, player);
    }

    void removePieceSoft(int chosenCol) {

        // Find available slot in chosen column
        int index1 = gameBoardModel.getListOccupancy().get(chosenCol).lastIndexOf(gameBoardModel.getCurrentPlayer());
        int index2 = gameBoardModel.getListOccupancy().get(chosenCol).lastIndexOf(gameBoardModel.getWaitingPlayer());
        int indexOfUpper;

        if (index1 > index2) indexOfUpper = index1;
        else indexOfUpper = index2;

        // Tick occupancy list
        gameBoardModel.getListOccupancy().get(chosenCol).set(indexOfUpper, GameBoardModel.player.PLAYER_NONE);
    }

    void colorWinPieces() {

        for (int x = 0; x < GameBoardModel.numCol; x++) {
            for (int y = 0; y < GameBoardModel.numRow; y++) {
                if (gameBoardPanel.getSlot(x, y).win_part) {
                    Color WinColor = gameBoardModel.getPlayerWinColor(gameBoardModel.getSlotOccupancy(x, y));
                    gameBoardPanel.getSlot(x, y).piece.setBackground(WinColor);
                }
            }
        }

    }

    void killWinPieces() {

        for (int x = 0; x < GameBoardModel.numCol; x++) {
            for (int y = 0; y < GameBoardModel.numRow; y++) {
                if (gameBoardPanel.getSlot(x, y).win_part) {
                    gameBoardPanel.getSlot(x, y).setEmpty();
//                    if(gameBoardModel.getSlotOccupancy(x,y).equals(GameBoardModel.player.PLAYER_1)) HP_player_2--;
//                    if(gameBoardModel.getSlotOccupancy(x,y).equals(GameBoardModel.player.PLAYER_2)) HP_player_1--;

                    gameBoardModel.getListOccupancy().get(x).set(y, GameBoardModel.player.PLAYER_NONE);
                }
            }
        }

    }

    /**
     * This method will make all win_part = false.
     * Needed because checking for win leaves behind residues of win_parts,
     * which later will be mistaken later by additional win checks.
     */
    void cleanAllWinParts() {
        // Clean all win_parts
        for (int i = 0; i < GameBoardModel.numCol; i++) {
            for (int j = 0; j < GameBoardModel.numRow; j++) {
                gameBoardPanel.getSlot(i, j).win_part = false;
            }
        }
    }

}
