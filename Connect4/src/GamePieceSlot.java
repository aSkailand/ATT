import javax.swing.*;

/**
 * Created by TrongDT on 04/04/2017.
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
