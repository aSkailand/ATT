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
                System.out.println("Casting Swap");
                castSwap();
                gameBoardController.gameBodyController.goldController.UpdateGoldValue(
                        -gameBoardController.gameBodyController.unitButtonController.unitButtonModel.getMagic1Cost(),
                        gameBoardModel.getCurrentPlayer());
                gameBoardController.gameBodyController.unitButtonController.buttonDisablerWrapper(1);
                break;
            }
            case Mute: {
                System.out.println("Casting Mute");
                castMute();
                gameBoardController.gameBodyController.goldController.UpdateGoldValue(
                        -gameBoardController.gameBodyController.unitButtonController.unitButtonModel.getMagic2Cost(),
                        gameBoardModel.getCurrentPlayer());
                gameBoardController.gameBodyController.unitButtonController.buttonDisablerWrapper(3);
                break;
            }
            case Bomb: {
                System.out.println("Casting Bomb");
                castBomb();
                gameBoardController.gameBodyController.goldController.UpdateGoldValue(
                        -gameBoardController.gameBodyController.unitButtonController.unitButtonModel.getMagic3Cost(),
                        gameBoardModel.getCurrentPlayer());
                gameBoardController.gameBodyController.unitButtonController.buttonDisablerWrapper(5);
                break;
            }

        }

    }

    void castBomb(){
        for (int x = 0; x < GameBoardModel.numCol; x++) {
            for (int y = 0; y < GameBoardModel.numRow; y++) {
                if (!gameBoardModel.getSlotOccupancy(x, y).equals(GameBoardModel.player.PLAYER_NONE))
                    if (gameBoardController.gameBoardPanel.getSlot(x, y).getPieceMagic().isSelected()) {
                        gameBoardModel.getSlotCombined(x, y).setInfo(GameBoardModel.player.PLAYER_NEUTRAL, GamePieceTypes.Bomb, false);
                        gameBoardModel.getSlotCombined(x, y).setBombStatus(true);
                        break;
                    }
            }
        }

        gameBoardModel.loadSlotListFromCombinedList(gameBoardController.gameBoardPanel);

        // Clean Up after cast
        gameBoardModel.currentSelectedAction = null;
        gameBoardModel.selectedPlayer = null;
        gameBoardModel.numSelected = 0;
        gameBoardController.gameOptionPanel.castSpellButton.setEnabled(false);
        gameBoardController.gameOptionPanel.switchToIdlePanel();

    }

    void castSwap() {
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


        System.out.println("x1: " + x1 + ", y1: " + y1);
        System.out.println("x2: " + x2 + ", y2: " + y2);

        // Swap Piece information
        PieceInfo tempy = new PieceInfo();
        tempy.copyInfo(gameBoardModel.getSlotCombined(x1, y1));
        gameBoardModel.getSlotCombined(x1, y1).copyInfo(gameBoardModel.getSlotCombined(x2, y2));
        gameBoardModel.getSlotCombined(x2, y2).copyInfo(tempy);

        // Print out result
        gameBoardModel.loadSlotListFromCombinedList(gameBoardController.gameBoardPanel);
        gameBoardModel.printCombinedList();

        // Clean Up after cast
        gameBoardModel.currentSelectedAction = null;
        gameBoardModel.selectedPlayer = null;
        gameBoardModel.numSelected = 0;
        gameBoardController.gameOptionPanel.castSpellButton.setEnabled(false);
        gameBoardController.gameOptionPanel.switchToIdlePanel();


    }

    void castMute() {

        for (int x = 0; x < GameBoardModel.numCol; x++) {
            for (int y = 0; y < GameBoardModel.numRow; y++) {
                if (!gameBoardModel.getSlotOccupancy(x, y).equals(GameBoardModel.player.PLAYER_NONE))
                    if (gameBoardController.gameBoardPanel.getSlot(x, y).getPieceMagic().isSelected()) {
                        gameBoardModel.getSlotCombined(x, y).setMuteStatus(true);
                    }
            }
        }

        System.out.println("After Mute:");
        gameBoardModel.printCombinedList();
        gameBoardModel.loadSlotListFromCombinedList(gameBoardController.gameBoardPanel);


        // Clean Up after cast
        gameBoardModel.currentSelectedAction = null;
        gameBoardModel.selectedPlayer = null;
        gameBoardModel.numSelected = 0;
        gameBoardController.gameOptionPanel.castSpellButton.setEnabled(false);
        gameBoardController.gameOptionPanel.switchToIdlePanel();

    }
}
