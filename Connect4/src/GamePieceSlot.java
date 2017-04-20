import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by TrongDT on 04/04/2017.
 */

/**
 *  ROLE:
 *      View + Controller
 *  WHAT:
 *      GamePieceSlot is a JPanel that has additional information linked to it.
 *      In each grid-slot in GameBoard resides a GamePieceSlot.
 *  USAGE:
 *      Used for simplifying accessing the slot's content.
 */
public class GamePieceSlot extends JPanel{

    // Owner of this slot
    GameBoardModel.player owner = GameBoardModel.player.PLAYER_NONE;

    // PLAYER_1 JButton will behave as the slot piece for now
    JButton piece;

    GamePieceSlot(){

        // JPanel Setup
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(2, 2, 2, 2));

        // JButton Setup
        piece = new JButton();
        piece.setBorderPainted(false);
        piece.setForeground(Color.WHITE);
        piece.setText("Hey");
        piece.setPreferredSize(new Dimension(50,50));

        this.add(piece);
    }

    public GameBoardModel.player getOwner(){
        return owner;
    }

}
