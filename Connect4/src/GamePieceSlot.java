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

    // Coordinate of this slot
    private int x;
    private int y;

    // A JButton will behave as the slot piece for now
    private JButton piece;
    private JButton empty;

    // Pre-load all pieces here
    private GamePiecePeasant peasantPiece;
    private GamePieceAssassin assassinPiece;
    private GamePieceKnight knightPiece;

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
     * Initialize an empty piece, making it ready for use when needed.
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
    void setEmpty(){
        this.removeAll();
        this.add(empty);
    }
    /**
     * Attach the slot with the given piece.
     * @param pieceInfo: Info of given piece.
     */
    void setPiece(PieceInfo pieceInfo){

        this.removeAll();
        this.piece = null;

        if (pieceInfo.getOwner().equals(GameBoardModel.player.PLAYER_NONE)) {
            this.setEmpty();
            return;
        }

        Color ownerColor;
        if(pieceInfo.getOwner() == GameBoardModel.player.PLAYER_1) ownerColor = Color.RED;
        else ownerColor = Color.BLUE;

        switch (pieceInfo.getPieceType()){
            case Peasant:{
                this.piece = this.peasantPiece;
                this.piece.setBackground(ownerColor);
                this.piece.setEnabled(pieceInfo.isEnabled());
                break;
            }
            case Assassin:{
                this.piece = this.assassinPiece;
                this.piece.setBackground(ownerColor);
                this.piece.setEnabled(pieceInfo.isEnabled());
                break;
            }
            case Knight:{
                this.piece = this.knightPiece;
                this.piece.setBackground(ownerColor);
                this.piece.setEnabled(pieceInfo.isEnabled());
                break;
            }
            default:{
                System.out.println("setPiece Error!");
                break;
            }
        }



        this.add(piece);
        this.revalidate();
        this.repaint();

    }

    JButton getPiece() {
        return piece;
    }

    /**
     * Set the color of the piece.
     * @param color: the color to color the piece with.
     */
    void setPieceColor(Color color) {
        piece.setBackground(color);
    }

}
