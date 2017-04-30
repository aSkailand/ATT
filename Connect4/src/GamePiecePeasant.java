import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Trong on 26/04/2017.
 */
public class GamePiecePeasant extends GamePiece implements ActionListener{

    int x;
    int y;

    GameBoardController gameBoardController;

    GamePiecePeasant(GamePieceSlot gamePieceSlot){

        // Button Setup
        this.setText("Peasant");
        this.setEnabled(false);
        this.addActionListener(this);

        gameBoardController = gamePieceSlot.gameBoardController;
    }

    // Peasant Action
    @Override
    public void actionPerformed(ActionEvent e) {
        this.setEnabled(false);

        GameBoardModel.player ownerOfPiece = gameBoardController.gameBoardModel.getSlotCombined(x,y).getOwner();

        gameBoardController.gameBodyController.goldController.UpdateGoldValue(5, ownerOfPiece);
        gameBoardController.gameBodyController.hitPointsController.HitpointsPercentage(-5, ownerOfPiece);
    }
}
