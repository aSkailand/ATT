import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by TrongDT on 04/04/2017.
 */

/**
 *  WHAT:
 *      GamePieceSlot is a JPanel that has additional information linked to it.
 *      In each grid-slot in GameBoard resides a GamePieceSlot.
 *  USAGE:
 *      Used for simplifying accessing the slot's content.
 */
public class GamePieceSlot extends JPanel{
    GameGridModel.player owner = GameGridModel.player.PLAYER_NONE;

    JButton piece;

    GamePieceSlot(){


        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        piece = new JButton();
//        piece.setBorderPainted(false);
        piece.setForeground(Color.WHITE);
        piece.setText("Hey");

        this.add(piece);
    }

    public GameGridModel.player getOwner(){
        return owner;
    }

}
