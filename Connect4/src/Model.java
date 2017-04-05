import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by aslak on 03.04.17.
 */
public class Model {

    // Number of Rows and Columns
    static int numRow = 10;
    static int numCol = 10;

    // Boolean list used to keep track of occupancy of the board.
    ArrayList<ArrayList<Boolean>> listBoolOccupiedSlots = new ArrayList<>();

    // JPanel list holding all the JPanels on the board in sorted fashion.
    ArrayList<ArrayList<JPanel>> listJPanelGameBoardSlots = new ArrayList<>();

    // Player Color
    Color colorPlayer1 = Color.RED;
    Color colorPlayer2 = Color.BLUE;

    // An enumeration that hold two members (behaves like a boolean in this case)
    public enum player{PLAYER_1, PLAYER_2}

    // The variable keeping track of current player
    player currentPlayer = player.PLAYER_1;



    Model(){
        // Fill Occupancy List with initial values.
        for (int i = 0; i < numCol; i++) {
            listBoolOccupiedSlots.add(new ArrayList<>());
            for (int j = 0; j < numRow; j++) {
                listBoolOccupiedSlots.get(i).add(false);
            }
        }
    }

}
