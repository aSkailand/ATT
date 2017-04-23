import java.awt.*;
import java.util.ArrayList;

/**
 * Created by aslak on 03.04.17.
 */
public class GameBoardModel {

    // BOARD CONTROL
    static int numRow = 6;      // Default: 6
    static int numCol = 7;      // Default: 7
            static int winInRow = 4;    // Default: 4

    // GAME OPTIONS
    private int numMove = 0;

    // PLAYER_1 list that keeps track on which slots belong to whom
    private ArrayList<ArrayList<player>> listOccupancyGameBoardSlots = new ArrayList<>();

    // PLAYER VARIABLES
    // An enumeration that hold player states (behaves like a boolean in this case)
    public enum player {

        PLAYER_1(1),
        PLAYER_2(2),
        PLAYER_NONE(0);

        private int numVal;

        player(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }
    }

    private player firstPlayer = player.PLAYER_NONE;
    private player currentPlayer;
    private player waitingPlayer;

    private boolean AI_player_1 = true;
    private boolean AI_player_2 = false;

    boolean getStatusAI(player player) {
        switch (player) {
            case PLAYER_1:
                return AI_player_1;
            case PLAYER_2:
                return AI_player_2;
            default:
                return false;
        }
    }


    // COLORS
    private Color colorPlayer1 = Color.RED;
    private Color colorPlayer2 = Color.BLUE;
    private Color colorWin1 = new Color(255, 119, 134);
    private Color colorWin2 = new Color(127, 167, 255);


    GameBoardModel() {

        // Set current + waiting player
        initializePlayers();

        // Load list of occupancy
        initializeOccupancyList();

    }

    // CONSTRUCTOR METHODS

    private void initializePlayers() {
        if (firstPlayer == player.PLAYER_1) {
            currentPlayer = player.PLAYER_1;
            waitingPlayer = player.PLAYER_2;
        } else if (firstPlayer == player.PLAYER_2) {
            currentPlayer = player.PLAYER_2;
            waitingPlayer = player.PLAYER_1;
        } else {
            firstPlayer = player.PLAYER_1;
            currentPlayer = player.PLAYER_1;
            waitingPlayer = player.PLAYER_2;
        }
    }

    private void initializeOccupancyList() {
        for (int i = 0; i < numCol; i++) {
            listOccupancyGameBoardSlots.add(new ArrayList<>());
            for (int j = 0; j < numRow; j++) {
                listOccupancyGameBoardSlots.get(i).add(player.PLAYER_NONE);
            }
        }
    }


    // GETTERS AND SETTERS

    // Prints out OccupancyList in terminal
    void printOccupancyList() {
        for (int i = GameBoardModel.numRow - 1; i >= 0; i--) {
            System.out.print("[");
            for (int j = 0; j < GameBoardModel.numCol; j++) {
                if (this.getListOccupancy().get(j).get(i).equals(GameBoardModel.player.PLAYER_NONE))
                    System.out.print(" - ");
                else
                    System.out.print(" " + this.getListOccupancy().get(j).get(i).getNumVal() + " ");
            }
            System.out.print("]\n");
        }
    }

    player getCurrentPlayer() {
        return currentPlayer;
    }

    player getWaitingPlayer() {
        return waitingPlayer;
    }

    void setCurrentPlayer(player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    void setWaitingPlayer(player waitingPlayer) {
        this.waitingPlayer = waitingPlayer;
    }

    // Get color of given player
    Color getPlayerColor(player checkPlayer) {
        if (checkPlayer.equals(player.PLAYER_1)) {
            return colorPlayer1;
        } else return colorPlayer2;
    }

    // Get win color of given player
    Color getPlayerWinColor(player checkPlayer) {
        if (checkPlayer.equals(player.PLAYER_1)) {
            return colorWin1;
        } else return colorWin2;
    }


    ArrayList<ArrayList<player>> getListOccupancy() {
        return this.listOccupancyGameBoardSlots;
    }


    int getNumMove() {
        return numMove;
    }

    player getSlotOccupancy(int x, int y) {
        return this.listOccupancyGameBoardSlots.get(x).get(y);
    }

    void addMove() {
        this.numMove++;
    }


}
