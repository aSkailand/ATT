/**
 * Created by Trong on 25/04/2017.
 */
public class BoardPlacePiece {

    GameBoardModel gameBoardModel;
    GameBoardPanel gameBoardPanel;
    gravityTimer gravityTimer;

    BoardPlacePiece(GameBoardController gameBoardController) {

        this.gameBoardModel = gameBoardController.gameBoardModel;
        this.gameBoardPanel = gameBoardController.gameBoardPanel;



    }

    void placePieceREAL(int x){
        // HUMAN PLAY
        if (!gameBoardModel.getStatusAI(gameBoardModel.getCurrentPlayer())) {

            // Place piece
            placePiece(x, gameBoardModel.getCurrentPlayer());

        }


        //        // AI PLAY
//        else {
//
//            /* HIERARCHICAL AI
//             - Follow a hierarchical scheme, if higher priority can't be followed, proceed to lower, and repeat.
//             - All in all, has only an 1 step look-ahead.
//             */
//
//            // 1. IF CAN WIN, DO SO
//            boolean AI_havePlaced = AI_checkIfCanWin();
//
//            // 2. IF CAN DENY OPPONENT'S WIN IN CURRENT ROUND, DO SO
//            if (!AI_havePlaced) AI_havePlaced = AI_checkIfOpponentCanWin();
//
//            // 3. PLACE RANDOMLY WITH 1 DEPTH LOOK-AHEAD
//            if (!AI_havePlaced) AI_randomPlace();
//
//
//        }
    }
    // Place piece at chosen column
    void placePiece(int chosenCol, GameBoardModel.player player) {

        // Find available slot in chosen column
        int indexOfNotOccupied = gameBoardModel.getListOccupancy().get(chosenCol).indexOf(GameBoardModel.player.PLAYER_NONE);

        // Abort if not found
        if (indexOfNotOccupied == -1) {
            System.out.println("No empty slots in this column, terminating method.");
            return; // Never supposed to happen. If it happens, we got a bug somewhere.
        }

        // Creating a piece
        GamePiece aGamePiece = new GamePiece();
        aGamePiece.setBackground(gameBoardModel.getPlayerColor(player));

        // Add piece to slot
        gameBoardPanel.getSlot(chosenCol, GameBoardModel.numRow-1).setPiece(aGamePiece);

        // Tick occupancy list
        gameBoardModel.getListOccupancy().get(chosenCol).set(GameBoardModel.numRow-1, player);

    }
}
