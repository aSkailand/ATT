import javax.swing.*;
import java.awt.*;
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
    GameOptionPanel gameOptionPanel;

    public GameBoardController() {

        gameBoardModel = new GameBoardModel();
        gameBodyFrame = new GameBodyFrame(this);
        gameBodyController = new GameBodyController(gameBodyFrame);
        // Initiating a GameBoardPanel Object
        gameBoardPanel = new GameBoardPanel(gameBoardModel);
        gameOptionPanel = new GameOptionPanel(this);

        // Adding the GameBoardPanel to gameBodyFrame's centerPanel.
        gameBodyFrame.centerPanel.add(gameBoardPanel);
        gameBodyFrame.topPanel.add(gameOptionPanel);
        gameBodyFrame.revalidate();
        gameBodyFrame.repaint();


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
                if (gameBoardModel.listJPanelGameBoardSlots.get(cur_x).get(cur_y).getOwner().equals(player)) {
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
                if (gameBoardModel.listJPanelGameBoardSlots.get(cur_x).get(cur_y).getOwner().equals(player)) {
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

    void colorWinningRow(int init_x, int init_y, int increment_x, int increment_y, int count, GameBoardModel.player player) {

        int x = init_x;
        int y = init_y;

        Color WinColor;

        if (player.equals(GameBoardModel.player.PLAYER_1)) WinColor = gameBoardModel.colorWin1;
        else WinColor = gameBoardModel.colorWin2;

        for (int i = 0; i < count; i++) {
            gameBoardModel.listJPanelGameBoardSlots.get(x).get(y).piece.setBackground(WinColor);
            x += increment_x;
            y += increment_y;
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