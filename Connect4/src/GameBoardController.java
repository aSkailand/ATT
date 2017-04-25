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

    BoardPlacePiece boardPlacePiece;
    BoardWinController boardWinController;

    gravityTimer gravityTimer;

    // todo: insert HP here
    int HP_player_1 = 30;
    int HP_player_2 = 30;

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

        boardPlacePiece = new BoardPlacePiece(this);
        boardWinController = new BoardWinController(gameBoardModel, gameBoardPanel, this);
        gravityTimer = new gravityTimer(gameBoardPanel, gameBoardModel, this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {



        // Turn Info
        System.out.println("\nMOVE: " + gameBoardModel.getNumMove());
        System.out.println("**********");

        // Player Info
        System.out.println("Current Player: " + gameBoardModel.getCurrentPlayer());
        System.out.println("AI status: " + gameBoardModel.getStatusAI(gameBoardModel.getCurrentPlayer()));


        // Check the number on the clicked button
        int chosenCol = Integer.parseInt(e.getActionCommand());

        // HANDLE AI OR HUMAN PLAY IN HERE
        boardPlacePiece.placePieceREAL(chosenCol);

        disableAllColumns();

        gravityTimer.startGravityTimer();


//        /* COMMON METHODS */
//
//        // Remove all WinParts
//        boardWinController.cleanAllWinParts();
//
//
//        // CHECKWIN
//        boardWinController.checkWinAllConditions(gameBoardModel.getCurrentPlayer());
//        boardWinController.checkWinAllConditions(gameBoardModel.getWaitingPlayer());
//        boardWinController.killWinPieces();
//
//        gameBoardPanel.revalidate();
//        gameBoardPanel.repaint();
//
//        // GRAVITYPULL
//        gravityTimer.startGravityTimer();
//        gameBoardPanel.revalidate();
//        gameBoardPanel.repaint();

//        // Check if there's any winners in collateral manner (forcing non-short-circuiting with only one "|")
//        while (checkWinAllConditions(gameBoardModel.getCurrentPlayer()) | checkWinAllConditions(gameBoardModel.getWaitingPlayer())) {
//
//            System.out.println("BEFORE:");
//            gameBoardModel.printOccupancyList();
//
//            // Remove win
//            System.out.println("Remove all winning rows");
//            killWinPieces();
//            gameBoardModel.printOccupancyList();
//
//            // Gravity pull
//            System.out.println("Gravity pull");
//            gravityPull();
//            gameBoardModel.printOccupancyList();
//
//            // Clean all WinParts
//            cleanAllWinParts();
//
//            // Update Board
//            gameBoardPanel.revalidate();
//            gameBoardPanel.repaint();
//
//            System.out.println("AFTER:");
//            gameBoardModel.printOccupancyList();
//
//        }
//
//        System.out.println("Player 1 HP: "+HP_player_1);
//        System.out.println("Player 2 HP: "+HP_player_2);

        

//        // todo: FIX! this is self trigger for AI
//        // this will trigger when the human have just played (since current player switched)
//        if (gameBoardModel.getStatusAI(gameBoardModel.getCurrentPlayer())) actionPerformed(e);

    }

    void endRound(){
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
//    boolean AI_randomPlace() {
//
//        GameBoardModel.player AI = gameBoardModel.getCurrentPlayer();
//        GameBoardModel.player Opponent = gameBoardModel.getWaitingPlayer();
//
//        int antiLock = 0; // Prevents from loop lock
//        int random = -1;
//
//        boolean badSlot = true;
//        while (badSlot && antiLock < 10) {
//            badSlot = false;
//
//            // Force random till legal column
//            random = (int) (Math.random() * GameBoardModel.numCol);
//            while (!playableCol(random)) {
//                random = (int) (Math.random() * GameBoardModel.numCol);
//            }
//
//            placePieceSoft(random, AI); // AI places temporary
//
//            // Check if opponent can win
//            for (int xx = 0; xx < GameBoardModel.numCol; xx++) {
//                if (playableCol(xx) && checkIfWinningMove(xx, Opponent)) {
//                    // Don't play it!
//                    antiLock++;
//                    badSlot = true;
//                    break;
//                }
//            }
//
//            removePieceSoft(random); // remove temporary piece
//
//        }
//        placePiece(random, AI);
//        return true;
//    }

    /**
     * Check if AI can win on current turn. Will play the move if it can win.
     *
     * @return returns true if it has won, and false if not.
     */
//    boolean AI_checkIfCanWin() {
//
//        boolean placed = false;
//
//        for (int x = 0; x < GameBoardModel.numCol; x++) {
//            if (playableCol(x) && checkIfWinningMove(x, gameBoardModel.getCurrentPlayer())) {
//
//                // Flavour Text
//                System.out.println("beep boop ~~ I see win at: (" + x + "," + gameBoardModel.getListOccupancy().get(x).indexOf(GameBoardModel.player.PLAYER_NONE) + ")");
//                System.out.println("beep boop ~~ me placing my piece in column " + x);
//                System.out.println("beep boop ~~ I won yay me");
//                System.out.println("beep boop ~~ please notice me senpai");
//                System.out.println("");
//
//                // AI play the winning move
//                placePiece(x, gameBoardModel.getCurrentPlayer());
//                placed = true;
//
//                // break or else AI plays more if it sees it can win multiple times
//                break;
//            }
//        }
//
//        return placed;
//    }

    /**
     * The AI switch over to opponent to see if the opponent can win next round.
     * AI will try to block the winning row if there is one in sight.
     *
     * @return true if AI places a piece, false if not.
     */
//    boolean AI_checkIfOpponentCanWin() {
//
//        for (int x = 0; x < GameBoardModel.numCol; x++) {
//            if (playableCol(x) && checkIfWinningMove(x, gameBoardModel.getWaitingPlayer())) {
//                placePiece(x, gameBoardModel.getCurrentPlayer());
//                return true;
//            }
//        }
//
//        return false;
//    }

    // TODO: Move this function over to model when done here with all win - conditions
    // TODO: Consider change it from void -> GameBoardModel.player. May open more flexibility.
    /* CHECK FOR WIN (ALL) */


    // BOARD ACTIONS

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


    // GETTERS


}