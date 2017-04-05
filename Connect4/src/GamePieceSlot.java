import javax.swing.*;

/**
 * Created by TrongDT on 04/04/2017.
 */

/**
 * NOTE FROM TRONG: UNUSED AT THE MOMENT, MAY BE USED IN FUTURE OR NOT!
 */
public class GamePieceSlot extends JPanel{
    boolean occupied = false;

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean getOccupied(boolean occupied) {
        return occupied;
    }
}
