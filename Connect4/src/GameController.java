import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aslak on 03.04.17.
 */
public class GameController implements ActionListener {

    GameGridModel gameGridModel;
    GameFrame gameFrame;
    GameGridPanel gameGridPanel;

    public GameController() {

        gameGridModel = new GameGridModel();
        gameFrame = new GameFrame(this);

        // Initiating a GameGridPanel Object
        gameGridPanel = new GameGridPanel(gameGridModel);

        // Adding the GameGridPanel to gameFrame's centerPanel.
        gameFrame.centerPanel.add(gameGridPanel);
        gameFrame.centerPanel.revalidate();
        gameFrame.centerPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Place piece
        placePiece(e);

        // Check 4-in-Row - Vertical
        checkWinVertical(GameGridModel.player.PLAYER_1);
        checkWinVertical(GameGridModel.player.PLAYER_2);

        // Check 4-in-Row - Horizontal
        checkWinHorizontal(GameGridModel.player.PLAYER_1);
        checkWinHorizontal(GameGridModel.player.PLAYER_2);

        // Check 4-in-Row - Ascending Diagonal
        checkWinAscendingDiagonal(GameGridModel.player.PLAYER_1);
        checkWinAscendingDiagonal(GameGridModel.player.PLAYER_2);

        // Check 4-in-Row - Descending Diagonal
        // todo: fix this shiet
        int max_y = GameGridModel.numRow - GameGridModel.winInRow;
        int max_x = GameGridModel.numCol - GameGridModel.winInRow;

        // PHASE 1: Check upwards starting on (0,0)
        for (int i = 0; i <= max_y; i++) {

            int counter = 0;

            for (int j = 0; (j+i) < GameGridModel.numRow; j++) {
                if (gameGridModel.listJPanelGameBoardSlots.get(j).get(j+i).getOwner().equals(player)) {
                    counter++;
                    if (counter >= GameGridModel.winInRow) {
                        System.out.println(player + " Won! - Ascending Diagonally starting on (0,"+i+")");
                        break;
                    }
                } else counter = 0;

            }
        }

        // PHASE 2: Check rightward starting on (1,0)
        for (int i = 1; i <= max_x; i++) {

            int counter = 0;

            for (int j = 0; (j+i) < GameGridModel.numCol; j++) {
                if (gameGridModel.listJPanelGameBoardSlots.get(j+i).get(j).getOwner().equals(player)) {
                    counter++;
                    if (counter >= GameGridModel.winInRow) {
                        System.out.println(player + " Won! - Ascending Diagonally starting on ("+i+",0)");
                        break;
                    }
                } else counter = 0;

            }
        }

    }


    // TODO: Move this function over to model when done here with all win - conditions
    // TODO: Consider change it from void -> GameGridModel.player. May open more flexibility.
    void checkWinVertical(GameGridModel.player player) {

        // Check through all columns.
        for (int i = 0; i < GameGridModel.numCol; i++) {

            int counter = 0;

            for (int j = 0; j < GameGridModel.numRow; j++) {
                if (gameGridModel.listJPanelGameBoardSlots.get(i).get(j).getOwner().equals(player)) {
                    counter++;
                    if (counter >= GameGridModel.winInRow) {
                        System.out.println(player + " Won! - Vertically on COL: " + i);
                        break;
                    }
                } else counter = 0;
            }
        }
    }

    void checkWinHorizontal(GameGridModel.player player) {
        // Check 4-in-Row - Horizontal
        for (int i = 0; i < GameGridModel.numRow; i++) {

            int counter = 0;

            for (int j = 0; j < GameGridModel.numCol; j++) {
                if (gameGridModel.listJPanelGameBoardSlots.get(j).get(i).getOwner().equals(player)) {
                    counter++;
                    if (counter >= GameGridModel.winInRow) {
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
    void checkWinAscendingDiagonal(GameGridModel.player player){
        int max_y = GameGridModel.numRow - GameGridModel.winInRow;
        int max_x = GameGridModel.numCol - GameGridModel.winInRow;

        // PHASE 1: Check upwards starting on (0,0)
        for (int i = 0; i <= max_y; i++) {

            int counter = 0;

            for (int j = 0; (j+i) < GameGridModel.numRow; j++) {
                if (gameGridModel.listJPanelGameBoardSlots.get(j).get(j+i).getOwner().equals(player)) {
                    counter++;
                    if (counter >= GameGridModel.winInRow) {
                        System.out.println(player + " Won! - Ascending Diagonally starting on (0,"+i+")");
                        break;
                    }
                } else counter = 0;

            }
        }

        // PHASE 2: Check rightward starting on (1,0)
        for (int i = 1; i <= max_x; i++) {

            int counter = 0;

            for (int j = 0; (j+i) < GameGridModel.numCol; j++) {
                if (gameGridModel.listJPanelGameBoardSlots.get(j+i).get(j).getOwner().equals(player)) {
                    counter++;
                    if (counter >= GameGridModel.winInRow) {
                        System.out.println(player + " Won! - Ascending Diagonally starting on ("+i+",0)");
                        break;
                    }
                } else counter = 0;

            }
        }
    }


    // todo: make current player as parameter, so it doesn't need to check every time the current player.
    void placePiece(ActionEvent e) {

        // Check the number on the clicked button
        int chosenCol = Integer.parseInt(e.getActionCommand());

        // Find first index of the slot that is not occupied in the chosen column
//        int indexOfNotOccupied = gameGridModel.listBoolOccupiedSlots.get(chosenCol).indexOf(GameGridModel.player.PLAYER_NONE);

        // Find first index of the slot that is not occupied in the chosen column
        int indexOfNotOccupied = -1;

        for (int i = 0; i < GameGridModel.numRow; i++) {
            if (!gameGridModel.listJPanelGameBoardSlots.get(chosenCol).get(i).getOccupied()) {
                indexOfNotOccupied = i;
                break;
            }
        }

        // Set current player name on piece
        gameGridModel.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).picture.setText("" + gameGridModel.currentPlayer);

        // Set current player color on piece
        if (gameGridModel.currentPlayer.equals(GameGridModel.player.PLAYER_1)) {
            gameGridModel.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).picture.setBackground(gameGridModel.colorPlayer1);
        } else {
            gameGridModel.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).picture.setBackground(gameGridModel.colorPlayer2);
        }

        // Set enabled on piece
        gameGridModel.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).picture.setEnabled(true);

        // Tick on the occupancy status
//        gameGridModel.listBoolOccupiedSlots.get(chosenCol).set(indexOfNotOccupied, gameGridModel.currentPlayer);
        gameGridModel.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).setOccupied(true);

        // Set owner on piece
        gameGridModel.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).owner = gameGridModel.currentPlayer;

        // Switch Current Player
        if (gameGridModel.currentPlayer.equals(GameGridModel.player.PLAYER_1)) {
            gameGridModel.currentPlayer = GameGridModel.player.PLAYER_2;
        } else gameGridModel.currentPlayer = GameGridModel.player.PLAYER_1;

    }

}