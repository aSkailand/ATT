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
public class GamePieceSlot extends JPanel {

    // Coordinate of this slot
    private int x;
    private int y;

    // Holders for button info
    private JButton pieceUnit;  // Info about current unit on this site (null if no unit)

    private JButton pieceMagic; // Temporary button for Battle Phase.

    private JButton empty;      // Empty slot info
    // Pre-load all pieces here
    private GamePiecePeasant peasantPiece;

    private GamePieceAssassin assassinPiece;
    private GamePieceKnight knightPiece;
    private GamePieceMagic magicPiece;

    // If this slot is part of a winning row
    boolean win_part = false;

    // todo add ImageBuffering here

    GameBoardController gameBoardController;

    GamePieceSlot(GameBoardController gameBoardController){

        this.gameBoardController = gameBoardController;

        // JPanel Setup
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(2, 2, 2, 2));

        // Preload Pieces
        peasantPiece = new GamePiecePeasant(this);
        assassinPiece = new GamePieceAssassin(this);
        knightPiece = new GamePieceKnight(this);
        magicPiece = new GamePieceMagic(this);




        //todo: preload color?

    }

    // Initializer Methods

    /**
     * Initializes the coordinates of this slot.
     * @param x: the x-coordinate.
     * @param y: the y-coordinate.
     */
    void setCoordinates(int x, int y){

        this.x = x;
        this.y = y;

        peasantPiece.x = x;
        peasantPiece.y = y;

        assassinPiece.x = x;
        assassinPiece.y = y;

        knightPiece.x = x;
        knightPiece.y = y;



    }

    /**
     * Initialize an empty pieceUnit, making it ready for use when needed.
     */
    void initializeEmpty(){

        empty = new JButton();
        empty.setPreferredSize(new Dimension(50,50));
        empty.setMargin(new Insets(0,0,0,0)); // Make text fit button better
        empty.setBorderPainted(false);
        empty.setForeground(Color.WHITE);

        empty.setText("( " + this.x + " , " + this.y + " )");

        empty.setEnabled(false);
    }
    // Piece Action Methods

    /**
     * Make the slot empty.
     */
    void switchToEmpty(){
        this.removeAll();
        this.add(empty);
    }

    void switchToMagic(){
        this.removeAll();
        this.add(pieceMagic);
    }

    void switchToUnit(){
        this.removeAll();
        this.add(pieceUnit);
    }

    /**
     * Attach the slot with the given pieceUnit.
     * @param pieceInfo: Info of given pieceUnit.
     */
    void setPieceUnit(PieceInfo pieceInfo){

        this.removeAll();
        this.pieceUnit = null;

        if (pieceInfo.getOwner().equals(GameBoardModel.player.PLAYER_NONE)) {
            this.switchToEmpty();
            return;
        }

        Color ownerColor;
        if(pieceInfo.getOwner() == GameBoardModel.player.PLAYER_1) ownerColor = Color.RED;
        else ownerColor = Color.BLUE;

        switch (pieceInfo.getPieceType()){
            case Peasant:{
                this.pieceUnit = this.peasantPiece;
                this.pieceUnit.setBackground(ownerColor);
                this.pieceUnit.setEnabled(pieceInfo.isEnabled());
                break;
            }
            case Assassin:{
                this.pieceUnit = this.assassinPiece;
                this.pieceUnit.setBackground(ownerColor);
                this.pieceUnit.setEnabled(pieceInfo.isEnabled());
                break;
            }
            case Knight:{
                this.pieceUnit = this.knightPiece;
                this.pieceUnit.setBackground(ownerColor);
                this.pieceUnit.setEnabled(pieceInfo.isEnabled());
                break;
            }
            default:{
                System.out.println("setPieceUnit Error!");
                break;
            }
        }

        magicPiece = new GamePieceMagic(this);
        this.magicPiece.x = x;
        this.magicPiece.y = y;

        this.magicPiece.setText(pieceUnit.getText() + "- Magic");
        this.magicPiece.ownerColor = ownerColor;
        this.magicPiece.setBackground(ownerColor);
        this.pieceMagic = this.magicPiece;

        this.add(pieceUnit);
        this.revalidate();
        this.repaint();

    }

    JButton getPieceUnit() {
        return pieceUnit;
    }

    public JButton getPieceMagic() {
        return this.pieceMagic;
    }

    /**
     * Set the color of the pieceUnit.
     * @param color: the color to color the pieceUnit with.
     */
    void setPieceColor(Color color) {
        pieceUnit.setBackground(color);
    }

}
