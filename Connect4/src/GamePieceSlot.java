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

    private int x;
    private int y;

    // A JButton will behave as the slot piece for now
    JButton piece;
    JButton empty;

    boolean win_part = false;

    GamePieceSlot(){

        // JPanel Setup
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(2, 2, 2, 2));

    }

    void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    void initializeEmpty(){

        empty = new JButton();
        empty.setPreferredSize(new Dimension(50,50));
        empty.setMargin(new Insets(0,0,0,0)); // Make text fit button better
        empty.setBorderPainted(false);
        empty.setForeground(Color.WHITE);

        empty.setText("( " + this.x + " , " + this.y + " )");

        empty.setEnabled(false);
    }

    void setEmpty(){
        this.removeAll();

        this.add(empty);
    }

    void setPiece(JButton aPiece){
        this.removeAll();

        this.piece = aPiece;
        this.add(piece);

        piece.setText("( " + this.x + " , " + this.y + " )");

        this.revalidate();
        this.repaint();
    }

}
