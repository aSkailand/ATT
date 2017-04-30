import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Trong on 30/04/2017.
 */
public class GamePieceMagic extends GamePiece implements ActionListener {

    int x;
    int y;

    private GameBoardController gameBoardController;
    private GameBoardModel gameBoardModel;

    Color ownerColor;




    boolean selected = false;

    public GamePieceMagic(GamePieceSlot gamePieceSlot) {
        this.gameBoardController = gamePieceSlot.gameBoardController;
        this.gameBoardModel = gameBoardController.gameBoardModel;
        this.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (gameBoardModel.currentSelectedAction) {
            case Swap: {
                System.out.println("Swap Select");
                if (!selected) {
                    selected = true;
                    gameBoardModel.numSelected++;
                    this.setBackground(ownerColor.darker().darker());
                } else {
                    selected = false;
                    gameBoardModel.numSelected--;
                    this.setBackground(ownerColor);
                }
                break;
            }
        }

        if (gameBoardModel.numSelected == 2) {
            gameBoardModel.loadOccupancyListFromCombinedList();
            for (int x = 0; x < GameBoardModel.numCol; x++) {
                for (int y = 0; y < GameBoardModel.numRow; y++) {
                    if (!gameBoardModel.getSlotOccupancy(x, y).equals(GameBoardModel.player.PLAYER_NONE)) {
                        if (!gameBoardController.gameBoardPanel.getSlot(x, y).getPieceMagic().isSelected()) {
                            gameBoardController.gameBoardPanel.getSlot(x, y).getPieceMagic().setEnabled(false);
                        }
                    }
                }
            }
        }
        else if(gameBoardModel.numSelected == 1){
            gameBoardModel.loadOccupancyListFromCombinedList();
            for (int x = 0; x < GameBoardModel.numCol; x++) {
                for (int y = 0; y < GameBoardModel.numRow; y++) {
                    if (!gameBoardModel.getSlotOccupancy(x, y).equals(GameBoardModel.player.PLAYER_NONE)) {
                        if (gameBoardController.gameBoardPanel.getSlot(x, y).getPieceMagic().isSelected()) {
                            gameBoardController.gameBoardPanel.getSlot(x, y).getPieceMagic().setEnabled(false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isSelected() {
        return selected;
    }
}

