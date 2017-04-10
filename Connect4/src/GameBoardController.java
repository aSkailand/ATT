import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by aslak on 03.04.17.
 */
public class GameBoardController implements ActionListener {

    GameBoardModel gameBoardModel;
    GameBodyController gameBodyController;
    GameBodyFrame gameBodyFrame;
    GameBoardPanel gameBoardPanel;

    public GameBoardController() {

        gameBoardModel = new GameBoardModel();
        gameBodyFrame = new GameBodyFrame(this);
        gameBodyController = new GameBodyController(gameBodyFrame);
        // Initiating a GameBoardPanel Object
        gameBoardPanel = new GameBoardPanel(gameBoardModel);

        // Adding the GameBoardPanel to gameBodyFrame's centerPanel.
        gameBodyFrame.centerPanel.add(gameBoardPanel);
        gameBodyFrame.centerPanel.revalidate();
        gameBodyFrame.centerPanel.repaint();


        // todo: add this to GameBodyFrame
        //JMenu action listeners
        gameBodyFrame.mainMenu.addActionListener(gameBodyController);
        gameBodyFrame.saveGame.addActionListener(gameBodyController);
        gameBodyFrame.openGame.addActionListener(gameBodyController);
        gameBodyFrame.pauseGame.addActionListener(gameBodyController);
        gameBodyFrame.restartGame.addActionListener(gameBodyController);
        gameBodyFrame.highscore.addActionListener(gameBodyController);

        // todo: add this to GameBodyFrame
        //Window listener
        gameBodyFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        int option = 0;

                        //Promts a JOptionPane, "Yes" = 1, "No" = 0
                        Object[] options = {"No", "Yes"};
                        option = JOptionPane.showOptionDialog(
                                null,
                                "Quit game?",
                                "Quit game?",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[1]);

                        //If yes close game, else continue game
                        if (option == 1) {
                            System.out.println("Yes, closing game...");
                            gameBodyFrame.dispose();
                        } else {
                            System.out.println("No, continue game... ");
                        }

                    }
                });
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        // Place piece
        placePiece(e);

        // Check 4-in-Row - Vertical
        checkWinVertical(GameBoardModel.player.PLAYER_1);
        checkWinVertical(GameBoardModel.player.PLAYER_2);

        // Check 4-in-Row - Horizontal
        checkWinHorizontal(GameBoardModel.player.PLAYER_1);
        checkWinHorizontal(GameBoardModel.player.PLAYER_2);

        // Check 4-in-Row - Ascending Diagonal
        checkWinAscendingDiagonal(GameBoardModel.player.PLAYER_1);
        checkWinAscendingDiagonal(GameBoardModel.player.PLAYER_2);

        // Check 4-in-Row - Descending Diagonal
        checkWinDescendingDiagonal(GameBoardModel.player.PLAYER_1);
        checkWinDescendingDiagonal(GameBoardModel.player.PLAYER_2);



    }


    // TODO: Move this function over to model when done here with all win - conditions
    // TODO: Consider change it from void -> GameBoardModel.player. May open more flexibility.
    void checkWinVertical(GameBoardModel.player player) {

        // Check through all columns.
        for (int i = 0; i < GameBoardModel.numCol; i++) {

            int counter = 0;

            for (int j = 0; j < GameBoardModel.numRow; j++) {
                if (gameBoardModel.listJPanelGameBoardSlots.get(i).get(j).getOwner().equals(player)) {
                    counter++;
                    if (counter >= GameBoardModel.winInRow) {
                        System.out.println(player + " Won! - Vertically on COL: " + i);
                        break;
                    }
                } else counter = 0;
            }
        }
    }

    void checkWinHorizontal(GameBoardModel.player player) {
        // Check 4-in-Row - Horizontal
        for (int i = 0; i < GameBoardModel.numRow; i++) {

            int counter = 0;

            for (int j = 0; j < GameBoardModel.numCol; j++) {
                if (gameBoardModel.listJPanelGameBoardSlots.get(j).get(i).getOwner().equals(player)) {
                    counter++;
                    if (counter >= GameBoardModel.winInRow) {
                        System.out.println(player + " Won! - Horizontal on Row: " + i);
                        break;
                    }
                } else counter = 0;
            }
        }
    }

    /**
     *      Ascending Diagonal - Order of Check:
     *
     *      - Phase 1: step 0,1,2
     *      - Phase 2: step 3,4,5
     *
     *                            max_x
     *                              |
     *                              v
     *              5   | == == ==  2  1  0  3 |
     *              4   | == ==  2  1  0  3  4 |
     *              3   | ==  2  1  0  3  4  5 |
     *              2   |  2  1  0  3  4  5 == | <- max_y
     *              1   |  1  0  3  4  5 == == |
     *              0   |  0  3  4  5 == == == |
     *
     *                    0  1  2  3  4  5  6
     * @param player
     */
    void checkWinAscendingDiagonal(GameBoardModel.player player){
        int max_y = GameBoardModel.numRow - GameBoardModel.winInRow;
        int max_x = GameBoardModel.numCol - GameBoardModel.winInRow;

        // PHASE 1: Check upwards starting on (0,0)
        for (int i = 0; i <= max_y; i++) {

            int counter = 0;

            for (int j = 0; (j+i) < GameBoardModel.numRow; j++) {
                if (gameBoardModel.listJPanelGameBoardSlots.get(j).get(j+i).getOwner().equals(player)) {
                    counter++;
                    if (counter >= GameBoardModel.winInRow) {
                        System.out.println(player + " Won! - Ascending Diagonally starting on (0,"+i+")");
                        break;
                    }
                } else counter = 0;

            }
        }

        // PHASE 2: Check rightward starting on (1,0)
        for (int i = 1; i <= max_x; i++) {

            int counter = 0;

            for (int j = 0; (j+i) < GameBoardModel.numCol; j++) {
                if (gameBoardModel.listJPanelGameBoardSlots.get(j+i).get(j).getOwner().equals(player)) {
                    counter++;
                    if (counter >= GameBoardModel.winInRow) {
                        System.out.println(player + " Won! - Ascending Diagonally starting on ("+i+",0)");
                        break;
                    }
                } else counter = 0;

            }
        }
    }

    void checkWinDescendingDiagonal(GameBoardModel.player player){
        // todo: fix this shiet
        int min_y = GameBoardModel.winInRow-1;
        int max_x = GameBoardModel.winInRow-1;

        // PHASE 1: Check Downwards starting on (0,5)

        // int i = y-coordinate
        for (int i = GameBoardModel.numRow - 1; i >= min_y; i--) {

            int counter = 0;

            // int j loops to lowest row (0)
            for (int j = 0; (i-j) >= 0; j++) {
                if (gameBoardModel.listJPanelGameBoardSlots.get(j).get(i-j).getOwner().equals(player)) {
                    counter++;
                    if (counter >= GameBoardModel.winInRow) {
                        System.out.println(player + " Won! - Descending Diagonally starting on (0,"+i+")");
                        break;
                    }
                } else counter = 0;

            }
        }

        // PHASE 2: Check rightward starting on (1,5)
        for (int i = 1; i <= max_x; i++) {

            int counter = 0;

            for (int j = 0; (j+i) < GameBoardModel.numCol; j++) {
                if (gameBoardModel.listJPanelGameBoardSlots.get(j+i).get(GameBoardModel.numRow-1-j).getOwner().equals(player)) {
                    counter++;
                    if (counter >= GameBoardModel.winInRow) {
                        System.out.println(player + " Won! - Ascending Diagonally starting on ("+i+","+(GameBoardModel.numRow-1)+")");
                        break;
                    }
                } else counter = 0;

            }
        }
    }

    // todo: make current player as parameter, so it doesn't need to check every time the current player.
    // todo: make the chosen column as a parameter, so that the ActionEvent e is not needed as a parameter.
    void placePiece(ActionEvent e) {

        // Check the number on the clicked button
        int chosenCol = Integer.parseInt(e.getActionCommand());

        // Find first index of the slot that is not occupied in the chosen column
//        int indexOfNotOccupied = gameBoardModel.listBoolOccupiedSlots.get(chosenCol).indexOf(GameBoardModel.player.PLAYER_NONE);

        // Find first index of the slot that is not occupied in the chosen column
        int indexOfNotOccupied = -1;

        for (int i = 0; i < GameBoardModel.numRow; i++) {
            if (gameBoardModel.listJPanelGameBoardSlots.get(chosenCol).get(i).getOwner().equals(GameBoardModel.player.PLAYER_NONE)) {
                indexOfNotOccupied = i;
                break;
            }
        }

        // todo: activate this again or delete in the future!
        // Set current player name on piece
//        gameBoardModel.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).piece.setText("" + gameBoardModel.currentPlayer);

        // Set current player color on piece
        if (gameBoardModel.currentPlayer.equals(GameBoardModel.player.PLAYER_1)) {
            gameBoardModel.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).piece.setBackground(gameBoardModel.colorPlayer1);
        } else {
            gameBoardModel.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).piece.setBackground(gameBoardModel.colorPlayer2);
        }

        // Set enabled on piece
        gameBoardModel.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).piece.setEnabled(true);

        // Set owner on piece
        gameBoardModel.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).owner = gameBoardModel.currentPlayer;

        // Switch Current Player
        if (gameBoardModel.currentPlayer.equals(GameBoardModel.player.PLAYER_1)) {
            gameBoardModel.currentPlayer = GameBoardModel.player.PLAYER_2;
        } else gameBoardModel.currentPlayer = GameBoardModel.player.PLAYER_1;

    }

}