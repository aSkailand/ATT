import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Trong on 30/04/2017.
 */
public class GamePieceMagic extends GamePiece implements ActionListener{

    int x;
    int y;

    private GameBoardController gameBoardController;

    Color ownerColor;

    boolean selected = false;

    public GamePieceMagic(GamePieceSlot gamePieceSlot) {
        this.gameBoardController = gamePieceSlot.gameBoardController;
        this.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (gameBoardController.gameBoardModel.currentSelectedAction){
            case Swap:{
                System.out.println("Swap Select");
                if (!selected) {
                    this.setBackground(ownerColor.darker().darker());
                    selected = true;
                }
                else{
                    this.setBackground(ownerColor);
                    selected = false;
                }
                break;
            }
        }
    }
}
