import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by aslak on 03.04.17.
 */

/**
 * Manages all board-related actions. The initiator for all the board/piece-related controllers.
 * <p>
 * ILLUSTRATION:
 * <p>
 * (START) GameBoardController -> BoardSummonController -> BoardGravityController
 * ^          |
 * |          v
 * BoardWinController  -->  GameBoardController (END)
 * <p>
 * NOTES:
 * GameBoardController:    Start Process
 * BoardSummonController:  Places a piece, both Human and AI
 * BoardGravityController: Drag down pieces if possible
 * BoardWinController:     Check for win, claim them and runs Gravity again, or end if no win exists.
 * GameBoardController:    End process
 */
public class GameBoardController implements ActionListener {

    GameBodyController gameBodyController;
    GameBodyFrame gameBodyFrame;

    GameBoardModel gameBoardModel;
    GameBoardPanel gameBoardPanel;

    GameOptionPanel gameOptionPanel;

    BoardSummonController boardSummonController;
    BoardGravityController boardGravityController;
    BoardWinController boardWinController;
    BoardSpellCastController boardSpellCastController;

    HitPointsController hitPointsController;


    GameBoardController(GameBodyController gameBodyController) {

        hitPointsController = gameBodyController.hitPointsController;

        // Declare upper MVC
        this.gameBodyController = gameBodyController;
        gameBodyFrame = gameBodyController.gameBodyFrame;

        // Declare current MVC
        gameBoardModel = new GameBoardModel();
        gameBoardPanel = new GameBoardPanel(this);
        gameBodyFrame.centerPanel.add(gameBoardPanel);

        boardSummonController = new BoardSummonController(this);
        boardWinController = new BoardWinController(this);
        boardGravityController = new BoardGravityController(this);
        boardSpellCastController = new BoardSpellCastController(this);

        // todo: send this gameBodyController
        gameOptionPanel = new GameOptionPanel(this);
        colorOptionButtons(gameBoardModel.getPlayerColor(gameBoardModel.getCurrentPlayer()));
        gameBodyFrame.topPanel.add(gameOptionPanel);

        gameBoardModel.printCombinedList();

        gameBodyFrame.revalidate();
        gameBodyFrame.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Run common intro
        roundIntro();

        // Place piece on top of board, checks for human and AI
        gameBoardModel.loadOccupancyListFromCombinedList();
        boardSummonController.doSummon(e);
        gameBoardModel.printCombinedList();

        // Lock all option buttons
        disableAllColumns();

        // Initiate gravity
        gameBoardModel.loadOccupancyListFromCombinedList();
        boardGravityController.runGravity();

    }

    /* ROUND CONTROL METHODS */

    // Run ONCE
    void roundInitialRun() {

        gameBodyController.gameTimerController.timer.setEnabled(false);

        gameBodyController.unitButtonController.disableButtons(gameBoardModel.getWaitingPlayer());

        disableAllColumns();

        // AI will wake up if it is her turn
        wakeupAI();
    }

    void roundIntro() {


        // Turn Info
        System.out.println("\nMOVE: " + gameBoardModel.getNumMove());
        System.out.println("**********");

        // Player Info
        System.out.println("Current Player: " + gameBoardModel.getCurrentPlayer());
        System.out.println("AI status: " + gameBoardModel.getStatusAI(gameBoardModel.getCurrentPlayer()));
    }

    void roundEnd() {

        // Close Unit Buttons
        gameBodyController.unitButtonController.disableButtons(gameBoardModel.getCurrentPlayer());

        // Disable all pieces
        gameBoardModel.loadOccupancyListFromCombinedList();
        for (int x = 0; x < GameBoardModel.numCol; x++) {
            for (int y = 0; y < GameBoardModel.numRow; y++) {
                if (!gameBoardModel.getSlotOccupancy(x, y).equals(GameBoardModel.player.PLAYER_NONE)) {
                    gameBoardModel.getSlotCombined(x, y).setEnabled(false);
//                    gameBoardPanel.getSlot(x, y).getPieceUnit().setEnabled(false);
                }
            }
        }
        gameBoardModel.loadSlotListFromCombinedList(gameBoardPanel);

        // TODO: PHASE_1 = true was here!

//        gameBoardModel.phase_1 = true;

        gameBodyController.unitButtonController.setPlayer1button1(false);
        gameBodyController.unitButtonController.setPlayer1button3(false);
        gameBodyController.unitButtonController.setPlayer1button5(false);
        gameBodyController.unitButtonController.setPlayer2button1(false);
        gameBodyController.unitButtonController.setPlayer2button3(false);
        gameBodyController.unitButtonController.setPlayer2button5(false);

        gameBodyController.gameTimerController.timer.setEnabled(false);

        // Swap player
        alternatePlayers();


        // Add a move
        gameBoardModel.addMove();

        // Print OccupancyList
        gameBoardModel.printCombinedList();
        gameBoardModel.printOccupancyList();
        gameBoardModel.printUnitList();

        // Change option buttons' color to current player
        colorOptionButtons(gameBoardModel.getPlayerColor(gameBoardModel.getCurrentPlayer()));

        // Check if any columns are full
        openPlayableColumns();

        // Get gold
        gameBodyController.goldController.UpdateGoldValue(1, gameBoardModel.getCurrentPlayer());

        // count peasants and reward gold
        int peasantCount = 0;
        for (int x = 0; x < GameBoardModel.numCol; x++) {
            for (int y = 0; y < GameBoardModel.numRow; y++) {
                if (gameBoardModel.getSlotCombined(x, y).checkIf(gameBoardModel.getCurrentPlayer(), GamePieceTypes.Peasant, false)) {
                    peasantCount++;
                }
            }
        }
        gameBodyController.goldController.UpdateGoldValue(peasantCount, gameBoardModel.getCurrentPlayer());



        // Mute de-ticker
        gameBoardModel.loadOccupancyListFromCombinedList();
        System.out.println("Check for mute:");
        for (int x = 0; x < GameBoardModel.numCol; x++) {
            for (int y = 0; y < GameBoardModel.numRow; y++) {
                if (gameBoardModel.getSlotOccupancy(x, y).equals(GameBoardModel.player.PLAYER_NEUTRAL)
                        && !gameBoardModel.getSlotCombined(x,y).getPieceType().equals(GamePieceTypes.Bomb)) {
                    if (gameBoardModel.getSlotCombined(x, y).isMuted()) {
                        gameBoardModel.getSlotCombined(x, y).setMuted(false);
                    }
                    else{
                        gameBoardModel.getSlotCombined(x,y).setMuteStatus(false);
                    }
                }
            }
        }
        gameBoardModel.loadSlotListFromCombinedList(gameBoardPanel);

        gameBoardModel.loadOccupancyListFromCombinedList();
        boardWinController.runWinCheck();

        gameBoardModel.phase_1 = true;


        // Disable buttons for Phase 1
        gameBodyController.unitButtonController.phaseDisableButtons(
                gameBoardModel.getCurrentPlayer(),
                true);

        // Checks unit affordability
        gameBodyController.unitButtonController.checkUnitsAffordability(gameBoardModel.getCurrentPlayer());
        // Checks magic affordability
        gameBodyController.unitButtonController.checkMagicAffordability(gameBoardModel.getCurrentPlayer(), gameBoardModel.phase_1);

        gameBoardModel.currentSelectedAction = null;


        autoSwitchActionPanel();

        disableAllColumns();

        // Wake up the AI if it's her turn
        wakeupAI();
    }

    void wakeupAI() {
        if (gameBoardModel.getStatusAI(gameBoardModel.getCurrentPlayer())) actionPerformed(gameBoardModel.Food4AI);
    }


    // BOARD ACTIONS

    // Check if given column is playable
    boolean playableCol(int chosenCol) {
        return gameBoardModel.getListOccupancy().get(chosenCol).indexOf(GameBoardModel.player.PLAYER_NONE) != -1;
    }

    // Disable all unplayable columns
    void openPlayableColumns() {
        for (int i = 0; i < GameBoardModel.numCol; i++) {
            if (!playableCol(i)) {
                gameOptionPanel.optionList.get(i).setEnabled(false);
            } else {
                gameOptionPanel.optionList.get(i).setEnabled(true);
            }
        }
    }

    // Disable all unplayable columns
    void disableAllColumns() {
        for (int i = 0; i < GameBoardModel.numCol; i++) {
            gameOptionPanel.optionList.get(i).setEnabled(false);
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


    // TODO: Move this function over to model when done here with all win - conditions
    // TODO: Consider change it from void -> GameBoardModel.player. May open more flexibility.
    /* CHECK FOR WIN (ALL) */

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
                    gameBoardPanel.getSlot(x, y).setPieceColor(WinColor);
                }
            }
        }

    }

    void resetColorWinPieces() {

        for (int x = 0; x < GameBoardModel.numCol; x++) {
            for (int y = 0; y < GameBoardModel.numRow; y++) {
                if (gameBoardPanel.getSlot(x, y).win_part) {
                    Color Color = gameBoardModel.getPlayerColor(gameBoardModel.getSlotOccupancy(x, y));
                    gameBoardPanel.getSlot(x, y).setPieceColor(Color);
                }
            }
        }

    }

    void killWinPieces() {
        for (int x = 0; x < GameBoardModel.numCol; x++) {
            for (int y = 0; y < GameBoardModel.numRow; y++) {
                if (gameBoardPanel.getSlot(x, y).win_part) {
                    gameBoardPanel.getSlot(x, y).switchToEmpty();

                    if (gameBoardModel.getSlotOccupancy(x, y).equals(GameBoardModel.player.PLAYER_1))
                        hitPointsController.HitpointsPercentage(-5, GameBoardModel.player.PLAYER_2);
                    if (gameBoardModel.getSlotOccupancy(x, y).equals(GameBoardModel.player.PLAYER_2))
                        hitPointsController.HitpointsPercentage(-5, GameBoardModel.player.PLAYER_1);

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

    void autoSwitchActionPanel() {
        if (gameBoardModel.currentSelectedAction == null) {
            gameOptionPanel.switchToIdlePanel();
        } else if (gameBoardModel.phase_1) {
            gameOptionPanel.switchToOptionPanel();
        } else {
            gameOptionPanel.switchToCastSpellPanel();
        }

        gameOptionPanel.revalidate();
        gameOptionPanel.repaint();
    }


}