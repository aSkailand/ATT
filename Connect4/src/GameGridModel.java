import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by aslak on 03.04.17.
 */
public class GameGridModel {

    // Number of Rows and Columns
    static int numRow = 6;
    static int numCol = 7;
    static int winInRow = 4;

    // Boolean list used to keep track of occupancy of the board.
    ArrayList<ArrayList<player>> listBoolOccupiedSlots = new ArrayList<>();

    // JPanel list holding all the JPanels on the board in sorted fashion.
    ArrayList<ArrayList<GamePieceSlot>> listJPanelGameBoardSlots = new ArrayList<>();


    // Player Color
    Color colorPlayer1 = Color.RED;
    Color colorPlayer2 = Color.BLUE;

    // An enumeration that hold two members (behaves like a boolean in this case)
    public enum player{PLAYER_1, PLAYER_2, PLAYER_NONE}

    // The variable keeping track of current player
    player currentPlayer = player.PLAYER_1;



    GameGridModel(){

        fillOccupancy();

    }

    GamePieceSlot getSlot(int x, int y){
        return listJPanelGameBoardSlots.get(x).get(y);
    }

    void fillOccupancy(){
        // Fill Occupancy List with initial values.
        for (int i = 0; i < numCol; i++) {
            listBoolOccupiedSlots.add(new ArrayList<>());
            for (int j = 0; j < numRow; j++) {
                listBoolOccupiedSlots.get(i).add(player.PLAYER_NONE);
            }
        }
    }

}
