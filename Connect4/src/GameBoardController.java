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

    GameBoardController(GameBodyFrame gbFrame) {

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

        // Turn Info
        System.out.println("\nMOVE: " + gameBoardModel.getNumMove());
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



            /* HIERARCHICAL AI
             - Follow a hierarchical scheme, if higher priority can't be followed, proceed to lower, and repeat.
             - All in all, has only an 1 step look-ahead.
             */

            // 1. IF CAN WIN, DO SO
            boolean AI_havePlaced = AI_checkIfCanWin();

            // 2. IF CAN DENY OPPONENT'S WIN IN CURRENT ROUND, DO SO
            if (!AI_havePlaced) AI_havePlaced = AI_checkIfOpponentCanWin();

            // 3. PLACE RANDOMLY WITH 1 DEPTH LOOK-AHEAD
            if (!AI_havePlaced) AI_randomPlace();


        }

        /* COMMON METHODS */

        // Remove all WinParts
        cleanAllWinParts();

        // Check if there's any winners in collateral manner (forcing non-short-circuiting with only one "|")
        while (checkWinAllConditions(gameBoardModel.getCurrentPlayer()) | checkWinAllConditions(gameBoardModel.getWaitingPlayer())) {

            System.out.println("BEFORE:");
            gameBoardModel.printOccupancyList();

            // Remove win
            System.out.println("Remove all winning rows");
            killWinPieces();
            gameBoardModel.printOccupancyList();

            // Gravity pull
            System.out.println("Gravity pull");
            gravityPull();
            gameBoardModel.printOccupancyList();

            // Clean all WinParts
            cleanAllWinParts();

            // Update Board
            gameBoardPanel.revalidate();
            gameBoardPanel.repaint();

            System.out.println("AFTER:");
            gameBoardModel.printOccupancyList();

        }



        // Swap player
        alternatePlayers();

        // Add a move
        gameBoardModel.addMove();

        // Change option buttons' color to current player
        colorOptionButtons(gameBoardModel.getPlayerColor(gameBoardModel.getCurrentPlayer()));

        // Check if any columns are full
        disableFullColumns();

        // Print OccupancyList
        gameBoardModel.printOccupancyList();

        // todo: FIX! this is self trigger for AI
        // this will trigger when the human have just played (since current player switched)
        if (gameBoardModel.getStatusAI(gameBoardModel.getCurrentPlayer())) actionPerformed(e);

    }

    /* WORK IN PROGRESS METHODS */
    void gravityPull() {

        for (int x = 0; x < GameBoardModel.numCol; x++) {

            int indexOfLowestEmpty = gameBoardModel.getListOccupancy().get(x).indexOf(GameBoardModel.player.PLAYER_NONE);
            if (indexOfLowestEmpty != -1) {

                int amountOfEmpty = Collections.frequency(gameBoardModel.getListOccupancy().get(x), GameBoardModel.player.PLAYER_NONE);

                while (indexOfLowestEmpty < GameBoardModel.numRow - amountOfEmpty) {
                    for (int i = indexOfLowestEmpty; i < GameBoardModel.numRow - 1; i++) {
                        if (gameBoardModel.getSlotOccupancy(x, i + 1).equals(GameBoardModel.player.PLAYER_NONE)) {
                            gameBoardPanel.getSlot(x, i).setEmpty();
                        } else {
                            gameBoardPanel.getSlot(x, i).setPiece(gameBoardPanel.getSlot(x, i + 1).piece);
                        }
                    }

                    gameBoardPanel.getSlot(x, GameBoardModel.numRow - 1).setEmpty();

                    gameBoardModel.getListOccupancy().get(x).remove(indexOfLowestEmpty);
                    gameBoardModel.getListOccupancy().get(x).add(GameBoardModel.player.PLAYER_NONE);

                    indexOfLowestEmpty = gameBoardModel.getListOccupancy().get(x).indexOf(GameBoardModel.player.PLAYER_NONE);
                }
            }
        }
    }

    /* AI METHODS */

    /**
     * The AI place a piece randomly on a playable column.
     * This check have 1 step look-ahead.
     *
     * @return true if a piece is placed, and false if not (false shall never occur)
     */
    boolean AI_randomPlace() {
        int antiLock = 0; // Prevents from loop lock
        boolean badSlot = true;
        int random = -1;

        while (badSlot && antiLock < 10) {

            badSlot = false;

            // Force random till legal column
            random = (int) (Math.random() * GameBoardModel.numCol);
            while (!playableCol(random)) {
                random = (int) (Math.random() * GameBoardModel.numCol);
            }

            placePieceSoft(random); // AI places temporary
            alternatePlayers(); // Switch to opponent

            // Check if opponent can win
            for (int xx = 0; xx < GameBoardModel.numCol; xx++) {
                if (playableCol(xx) && checkIfWinningMove(xx)) {
                    // Don't play it!
                    antiLock++;
                    badSlot = true;
                    break;
                }
            }

            alternatePlayers(); // Switch back to AI
            removePieceSoft(random); // remove temporary piece
        }
        placePiece(random);
        return true;
    }

    /**
     * Check if AI can win on current turn. Will play the move if it can win.
     *
     * @return returns true if it has won, and false if not.
     */
    boolean AI_checkIfCanWin() {

        boolean placed = false;

        for (int x = 0; x < GameBoardModel.numCol; x++) {
            if (playableCol(x) && checkIfWinningMove(x)) {

                // Flavour Text
                System.out.println("beep boop ~~ I see win at: (" + x + "," + gameBoardModel.getListOccupancy().get(x).indexOf(GameBoardModel.player.PLAYER_NONE) + ")");
                System.out.println("beep boop ~~ me placing my piece in column " + x);
                System.out.println("beep boop ~~ I won yay me");
                System.out.println("beep boop ~~ please notice me senpai");
                System.out.println("");

                // AI play the winning move
                placePiece(x);
                placed = true;

                // break or else AI plays more if it sees it can win multiple times
                break;
            }
        }

        return placed;
    }

    /**
     * The AI switch over to opponent to see if the opponent can win next round.
     * AI will try to block the winning row if there is one in sight.
     *
     * @return true if AI places a piece, false if not.
     */
    boolean AI_checkIfOpponentCanWin() {

        alternatePlayers(); // switch over to opponent

        int threatCol = -1;

        for (int x = 0; x < GameBoardModel.numCol; x++) {
            if (playableCol(x) && checkIfWinningMove(x)) {
                threatCol = x;
                break;
            }
        }
        alternatePlayers();

        if (threatCol != -1) {
            placePiece(threatCol);
            return true;
        }
        return false;
    }

    // TODO: Move this function over to model when done here with all win - conditions
    // TODO: Consider change it from void -> GameBoardModel.player. May open more flexibility.
    /* CHECK FOR WIN (ALL) */
    boolean checkWinAllConditions(GameBoardModel.player player) {

        boolean winInSight = false;

        System.out.println("CHECK WIN: " + player);

        System.out.println("\tcheck Horizontal:");
        if(checkWinHorizontal_All(player)) winInSight = true;

        System.out.println("\tcheck Vertical:");
        if(checkWinVertical_All(player)) winInSight = true;

        System.out.println("\tcheck Descending:");
        if(checkWinDescendingDiagonal_All(player)) winInSight = true;

        System.out.println("\tcheck Ascending:");
        if(checkWinAscendingDiagonal_All(player)) winInSight = true;

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


    /* OTHER WIN CHECK METHODS */
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

        // Creating a piece
        GamePiece aGamePiece = new GamePiece();
        aGamePiece.setBackground(gameBoardModel.getPlayerColor(gameBoardModel.getCurrentPlayer()));

        // Add piece to slot
        gameBoardPanel.getSlot(chosenCol, indexOfNotOccupied).setPiece(aGamePiece);

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
            else {
                gameOptionPanel.optionList.get(i).setEnabled(true);
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