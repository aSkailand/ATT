import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by TrongDT on 04/04/2017.
 */

/**
 * NOTE FROM TRONG: UNUSED AT THE MOMENT, MAY BE USED IN FUTURE OR NOT!
 */
public class GamePieceSlot extends JPanel{
    boolean occupied = false;
    GameGridModel.player owner = GameGridModel.player.PLAYER_NONE;

    JButton picture;

    GamePieceSlot(){


        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        picture = new JButton();
//        picture.setBorderPainted(false);
        picture.setForeground(Color.WHITE);
        picture.setText("Hey");

        this.add(picture);



    }

    public GameGridModel.player getOwner(){
        return owner;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean getOccupied() {
        return occupied;
    }
}
