import java.awt.event.ActionEvent;

/**
 * Created by Trong on 25/04/2017.
 */
class BoardSummonController {

    private GameBoardController gameBoardController;
    private GameBoardModel gameBoardModel;
    private GameBoardPanel gameBoardPanel;

    GoldController goldController;

    BoardSummonController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
        gameBoardModel = gameBoardController.gameBoardModel;
        gameBoardPanel = gameBoardController.gameBoardPanel;

        goldController = gameBoardController.gameBodyController.goldController;
    }

    /* SUMMON METHODS */

    /**
     * Summon piece, checks for AI or HUMAN.
     *
     * @param e: Column to be placed on. Only for HUMAN.
     */
    void doSummon(ActionEvent e) {
        // IF HUMAN
        if (!gameBoardModel.getStatusAI(gameBoardModel.getCurrentPlayer())) {
            // Check selected column
            int chosenCol = Integer.parseInt(e.getActionCommand());
            summonPiece(chosenCol);
        }
        // IF AI
        else {
            summonPieceAI();
        }
    }

    /**
     * Summon for Human.
     *
     * @param x: Column to be summoned on.
     */
    private void summonPiece(int x) {
        placePiece(x, gameBoardModel.currentSelectedPiece, gameBoardModel.getCurrentPlayer());
    }

    /**
     * Summon for AI.
     */
    private void summonPieceAI() {

        // AI PLAY

            /* HIERARCHICAL AI
             - Follow a hierarchical scheme, if higher priority can't be followed, proceed to lower, and repeat.
             - All in all, has only an 1 step look-ahead.
             */


        // 1. IF CAN WIN, DO SO
        System.out.println("AI CHECKS IF SHE CAN WIN");
        boolean AI_havePlaced = AI_checkIfCanWin();

        // 2. IF CAN DENY OPPONENT'S WIN IN CURRENT ROUND, DO SO
        System.out.println("AI CHECKS IF OPPONENT CAN WIN");
        if (!AI_havePlaced) AI_havePlaced = AI_checkIfOpponentCanWin();

        // 3. PLACE RANDOMLY WITH 1 DEPTH LOOK-AHEAD
        System.out.println("AI PLAYS RANDOM");
        if (!AI_havePlaced) AI_randomPlace();
    }

    /**
     * Place a piece on top of board, readying it for gravity.
     * Used as a lesser method for rest of methods.
     *
     * @param chosenCol: The column the piece will be on.
     * @param player:    The owner of piece that will be placed.
     */
    private void placePiece(int chosenCol, GamePieceTypes currentPieceType, GameBoardModel.player player) {

        // Find available slot in chosen column
        int indexOfNotOccupied = gameBoardModel.getListOccupancy().get(chosenCol).indexOf(GameBoardModel.player.PLAYER_NONE);

        // Abort if not found
        if (indexOfNotOccupied == -1) {
            System.out.println("No empty slots in this column, terminating method.");
            return; // Never supposed to happen. If it happens, we got a bug somewhere.
        }

        // Reference copy
        PieceInfo currentPieceInfo = gameBoardModel.getSlotCombined(chosenCol,GameBoardModel.numRow-1);

        // Update Chosen Column
        gameBoardModel.currentPlayedColumn = chosenCol;

        // Tick list
        currentPieceInfo.setInfo(player, currentPieceType);

        // Add piece to slot
        gameBoardPanel.getSlot(chosenCol, GameBoardModel.numRow - 1).setPiece(currentPieceInfo);

        // Cost
        goldController.UpdateGoldValue(-getCost(currentPieceType), gameBoardModel.getCurrentPlayer());

    }

    int getCost(GamePieceTypes currentPieceType){

        switch (currentPieceType){
            case Peasant: return 1;
            case Knight: return 3;
            default:{
                System.out.println("COST ERROR!");
                return 0;
            }
        }
    }


    /* AI METHODS */

    /**
     * Check if AI can win on current turn. Will play the move if it can win.
     *
     * @return returns true if it has won, and false if not.
     */
    private boolean AI_checkIfCanWin() {

        boolean placed = false;

        for (int x = 0; x < GameBoardModel.numCol; x++) {
            if (gameBoardController.playableCol(x) && gameBoardController.checkIfWinningMove(x, gameBoardModel.getCurrentPlayer())) {

                // Flavour Text
                System.out.println("beep boop ~~ I see win at: (" + x + "," + gameBoardModel.getListOccupancy().get(x).indexOf(GameBoardModel.player.PLAYER_NONE) + ")");
                System.out.println("beep boop ~~ me placing my piece in column " + x);
                System.out.println("beep boop ~~ I won yay me");
                System.out.println("beep boop ~~ please notice me senpai");
                System.out.println("");

                // AI play the winning move
                placePiece(x, gameBoardModel.currentSelectedPiece ,gameBoardModel.getCurrentPlayer());
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
    private boolean AI_checkIfOpponentCanWin() {

        for (int x = 0; x < GameBoardModel.numCol; x++) {
            if (gameBoardController.playableCol(x) && gameBoardController.checkIfWinningMove(x, gameBoardModel.getWaitingPlayer())) {
                placePiece(x, gameBoardModel.currentSelectedPiece, gameBoardModel.getCurrentPlayer());
                return true;
            }
        }

        return false;
    }

    /**
     * The AI place a piece randomly on a playable column.
     * This check have 1 step look-ahead.
     *
     * @return true if a piece is placed, and false if not (false shall never occur)
     */
    private boolean AI_randomPlace() {

        GameBoardModel.player AI = gameBoardModel.getCurrentPlayer();
        GameBoardModel.player Opponent = gameBoardModel.getWaitingPlayer();

        int antiLock = 0; // Prevents from loop lock
        int random = -1;

        boolean badSlot = true;
        while (badSlot && antiLock < 10) {
            badSlot = false;

            // Force random till legal column
            random = (int) (Math.random() * GameBoardModel.numCol);
            while (!gameBoardController.playableCol(random)) {
                random = (int) (Math.random() * GameBoardModel.numCol);
            }

            gameBoardController.placePieceSoft(random, AI); // AI places temporary

            // Check if opponent can win
            for (int xx = 0; xx < GameBoardModel.numCol; xx++) {
                if (gameBoardController.playableCol(xx) && gameBoardController.checkIfWinningMove(xx, Opponent)) {
                    // Don't play it!
                    antiLock++;
                    badSlot = true;
                    break;
                }
            }

            gameBoardController.removePieceSoft(random); // remove temporary piece

        }
        placePiece(random, gameBoardModel.currentSelectedPiece, AI);
        return true;
    }


}
