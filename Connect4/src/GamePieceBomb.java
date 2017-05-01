import java.awt.event.ActionListener;

/**
 * Created by Trong on 01/05/2017.
 */
public class GamePieceBomb extends GamePiece{

    int x;
    int y;

    private GameBoardController gameBoardController;

    public GamePieceBomb(GamePieceSlot gamePieceSlot) {
        gameBoardController = gamePieceSlot.gameBoardController;

        // JButton Setup
        this.setText("Bomb");
        this.setEnabled(false);
    }
}
