import java.awt.*;
import java.util.ArrayList;

/**
 * Created by aslak on 03.04.17.
 */
public class GameBoardModel {

    // Number of Rows and Columns
    static int numRow = 6;      // Default: 6
    static int numCol = 7;      // Default: 7
    static int winInRow = 4;    // Default: 4

    // A list that keeps track on which slots belong to whom
    ArrayList<ArrayList<player>> listOccupancyGameBoardSlots = new ArrayList<>();

    // Player Color
    Color colorPlayer1 = Color.RED;
    Color colorPlayer2 = Color.BLUE;
    Color colorWin1 = new Color(255, 119, 134);
    Color colorWin2 = new Color(127, 167, 255);


    // An enumeration that hold two members (behaves like a boolean in this case)
    public enum player{PLAYER_1, PLAYER_2, PLAYER_NONE}

    // The variable keeping track of current player
    player currentPlayer = player.PLAYER_1;
    player waitingPlayer = player.PLAYER_2;

    GameBoardModel(){

        // Instantiate list of occupancy
        for (int i = 0; i < numCol; i++) {
            listOccupancyGameBoardSlots.add(new ArrayList<>());
            for (int j = 0; j < numRow; j++) {
                listOccupancyGameBoardSlots.get(i).add(player.PLAYER_NONE);
            }
        }
    }



    // Get color of given parameter
    Color getPlayerColor(player checkPlayer){
        if(checkPlayer.equals(player.PLAYER_1)){
            return colorPlayer1;
        }
        else return colorPlayer2;
    }

}
