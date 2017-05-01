import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Trong on 01/05/2017.
 */
public class BoardSpellCastController implements ActionListener {

    GameBoardController gameBoardController;
    GameBoardModel gameBoardModel;

    public BoardSpellCastController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
        this.gameBoardModel = gameBoardController.gameBoardModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("SPELL CAST");

        switch (gameBoardModel.currentSelectedAction) {
            case Swap: {

                int x1 = -1, y1 = -1;
                int x2 = -1, y2 = -1;

                boolean firstSelectedFound = false;

                for (int x = 0; x < GameBoardModel.numCol; x++) {
                    for (int y = 0; y < GameBoardModel.numRow; y++) {
                        if (!gameBoardModel.getSlotOccupancy(x, y).equals(GameBoardModel.player.PLAYER_NONE))
                            if (gameBoardController.gameBoardPanel.getSlot(x, y).getPieceMagic().isSelected()) {
                                if (!firstSelectedFound) {
                                    x1 = x;
                                    y1 = y;
                                    firstSelectedFound = true;
                                } else {
                                    x2 = x;
                                    y2 = y;
                                    break;
                                }
                            }
                    }
                }


                System.out.println("x1: "+x1+", y1: "+y1 );
                System.out.println("x2: "+x2+", y2: "+y2 );

                PieceInfo tempy = new PieceInfo();
                tempy.copyInfo(gameBoardModel.getSlotCombined(x1, y1));
                gameBoardModel.getSlotCombined(x1, y1).copyInfo(gameBoardModel.getSlotCombined(x2, y2));
                gameBoardModel.getSlotCombined(x2, y2).copyInfo(tempy);

                gameBoardModel.loadSlotListFromCombinedList(gameBoardController.gameBoardPanel);
                gameBoardModel.printCombinedList();

                gameBoardModel.currentSelectedAction = null;
                gameBoardModel.numSelected = 0;
                gameBoardController.gameOptionPanel.castSpellButton.setEnabled(false);
                gameBoardController.gameOptionPanel.switchToIdlePanel();


                break;
            }
        }
    }
}
