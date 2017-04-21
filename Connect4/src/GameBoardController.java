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

        System.out.println("");
        gameBoardModel.printOccupancyList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        boolean end_game = false;

        // Turn Info
        System.out.println("\nTURN: " + gameBoardModel.getNumMove());
        System.out.println("**********");

        // Player Info
        System.out.println("Current Player: " + gameBoardModel.getCurrentPlayer());
        System.out.println("AI status: " + gameBoardModel.getStatusAI(gameBoardModel.getCurrentPlayer()));

        // HUMAN PLAY
        if (!gameBoardModel.getStatusAI(gameBoardModel.getCurrentPlayer())) {

            // Check the number on the clicked button
            int chosenCol = Integer.parseInt(e.getActionCommand());

            // Place piece
            placePiece(chosenCol);

        }
        // AI PLAY
        else {

            boolean won = false;

            // check if there is any winning play
            for (int x = 0; x < GameBoardModel.numCol; x++) {
                if (playableCol(x) && checkIfWinningMove(x)) {

                    // AI play the winning move
                    placePiece(x);
                    won = true;

                    // Flavour Text
                    System.out.println("beep boop ~~ I see win at: (" + x + "," + gameBoardModel.getListOccupancy().get(x).indexOf(GameBoardModel.player.PLAYER_NONE) + ")");
                    System.out.println("beep boop ~~ me placing my piece in column " + x);
                    System.out.println("beep boop ~~ I won yay me");
                    System.out.println("beep boop ~~ please notice me senpai");
                    System.out.println("");

                    // break or else AI plays more if it sees it can win multiple times
                    break;
                }
            }

            if (!won) {

                // Put random
                boolean playable = false;
                while (!playable) {
                    int random = (int) (Math.random() * GameBoardModel.numCol);
                    if (playableCol(random)) {
                        playable = true;
                        placePiece(random);
                    }

                }
            }
        }

        // COMMON METHODS

        // Check if there's any winners
        checkWinAllConditions(gameBoardModel.getCurrentPlayer());
        checkWinAllConditions(gameBoardModel.getWaitingPlayer());
        colorWinPieces();

        // Swap player
        alternatePlayers();

        // add a move
        gameBoardModel.addMove();

        // todo: fix draw
        // Check if Draw
        if (gameBoardModel.getNumMove() == GameBoardModel.numRow * GameBoardModel.numCol) {
            System.out.println("DRAW?");
            end_game = true;
        }

        // Change option buttons' color to current player
        colorOptionButtons(gameBoardModel.getPlayerColor(gameBoardModel.getCurrentPlayer()));

        // Check if any columns are full
        disableFullColumns();

        // Print OccupancyList
        gameBoardModel.printOccupancyList();

        // todo: FIX! this is self trigger for AI
        // this will trigger when the human have just played (since current player switched)
        if (gameBoardModel.getStatusAI(gameBoardModel.getCurrentPlayer()) && !end_game) actionPerformed(e);

    }

    // TODO: Move this function over to model when done here with all win - conditions
    // TODO: Consider change it from void -> GameBoardModel.player. May open more flexibility.
    // CHECK WIN CONDITIONS

    // CHECK ALL
    void checkWinAllConditions(GameBoardModel.player player) {
        System.out.println("CHECK WIN: " + player);

        System.out.println("\tcheck Horizontal:");
        checkWinHorizontal_All(player);

        System.out.println("\tcheck Vertical:");
        checkWinVertical_All(player);

        System.out.println("\tcheck Descending:");
        checkWinDescendingDiagonal_All(player);

        System.out.println("\tcheck Ascending:");
        checkWinAscendingDiagonal_All(player);

        System.out.println("");
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


    // CHECK SINGLE
    boolean checkIfWinningMove(int x) {

        placePieceSoft(x);

        int indexOfUpper = gameBoardModel.getListOccupancy().get(x).lastIndexOf(gameBoardModel.getCurrentPlayer());

        if (checkWinVertical_Single(x, indexOfUpper, gameBoardModel.getCurrentPlayer())) {
            removePieceSoft(x);
            return true;
        }
        if (checkWinHorizontal_Single(x, indexOfUpper, gameBoardModel.getCurrentPlayer())) {
            removePieceSoft(x);
            return true;
        }
        if (checkWinAscendingDiagonal_Single(x, indexOfUpper, gameBoardModel.getCurrentPlayer())) {
            removePieceSoft(x);
            return true;
        }
        if (checkWinDescendingDiagonal_Single(x, indexOfUpper, gameBoardModel.getCurrentPlayer())) {
            removePieceSoft(x);
            return true;
        }

        removePieceSoft(x);
        return false;
    }

    // todo: remove int y parameter?
    boolean checkWinVertical_Single(int x, int y, GameBoardModel.player player) {

        // Instantiating list
        ArrayList<Integer> lister = new ArrayList<>();  // 1D binary list of current row
        for (int j = 0; j < GameBoardModel.numRow; j++) {
            if (gameBoardModel.getSlotOccupancy(x, j).equals(player)) lister.add(1);
            else lister.add(0);
        }

        // Prematurely return if not enough same pieces
        if (!(Collections.frequency(lister, 1) >= GameBoardModel.winInRow)) {
            return false;
        }

        // Find win
        return searchWinningRow(x, 0, 0, 1, lister, player);
    }

    // todo: remove int x parameter?
    boolean checkWinHorizontal_Single(int x, int y, GameBoardModel.player player) {

        // Instantiating list
        ArrayList<Integer> lister = new ArrayList<>();  // 1D binary list of current row
        for (int j = 0; j < GameBoardModel.numCol; j++) {
            if (gameBoardModel.getSlotOccupancy(j, y).equals(player)) lister.add(1);
            else lister.add(0);
        }

        // Prematurely return if not enough same pieces
        if (!(Collections.frequency(lister, 1) >= GameBoardModel.winInRow)) {
            return false;
        }

        // Find win
        return searchWinningRow(0, y, 1, 0, lister, player);
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

        // Prematurely return if not enough same pieces
        if (!(Collections.frequency(lister, 1) >= GameBoardModel.winInRow)) {
            return false;
        }

        // Find win
        return searchWinningRow(init_x, init_y, 1, 1, lister, player);
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

        // Prematurely return if not enough same pieces
        if (!(Collections.frequency(lister, 1) >= GameBoardModel.winInRow)) {
            return false;
        }

        return searchWinningRow(init_x, init_y, 1, -1, lister, player);

    }


    boolean searchWinningRow(int init_x, int init_y, int increment_x, int increment_y, ArrayList<Integer> lister, GameBoardModel.player player) {

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

    // BOARD ACTIONS

    void placePieceSoft(int chosenCol) {

        // Find available slot in chosen column
        int indexOfNotOccupied = gameBoardModel.getListOccupancy().get(chosenCol).indexOf(GameBoardModel.player.PLAYER_NONE);

        // Abort if not found
        if (indexOfNotOccupied == -1) {
            System.out.println("No empty slots in this column, terminating method.");
            return; // Never supposed to happen. If it happens, we got a bug somewhere.
        }

        // Tick occupancy list
        gameBoardModel.getListOccupancy().get(chosenCol).set(indexOfNotOccupied, gameBoardModel.getCurrentPlayer());
    }

    void removePieceSoft(int chosenCol) {

        // Find available slot in chosen column
        int indexOfUpper = gameBoardModel.getListOccupancy().get(chosenCol).lastIndexOf(gameBoardModel.getCurrentPlayer());

        // Tick occupancy list
        gameBoardModel.getListOccupancy().get(chosenCol).set(indexOfUpper, GameBoardModel.player.PLAYER_NONE);
    }

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

        // Tick occupancy list
        gameBoardModel.getListOccupancy().get(chosenCol).set(indexOfNotOccupied, gameBoardModel.getCurrentPlayer());

    }

    // Check if given column is playable
    boolean playableCol(int chosenCol) {
        return gameBoardModel.getListOccupancy().get(chosenCol).indexOf(GameBoardModel.player.PLAYER_NONE) != -1;
    }

    // Disable all unplayable columns
    void disableFullColumns() {
        for (int i = 0; i < GameBoardModel.numCol; i++) {
            if (!playableCol(i)) {
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