
/**
 * Created by Trong on 25/04/2017.
 */
public class BoardWinController {

    GameBoardController gameBoardController;
    GameBoardModel gameBoardModel;
    GameBoardPanel gameBoardPanel;

    BoardWinController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
        gameBoardModel = gameBoardController.gameBoardModel;
        gameBoardPanel = gameBoardController.gameBoardPanel;
    }

    boolean RUN() {

        gameBoardController.cleanAllWinParts();

        if (gameBoardController.checkWinAllConditions(GameBoardModel.player.PLAYER_1) | gameBoardController.checkWinAllConditions(GameBoardModel.player.PLAYER_2)) {
            gameBoardController.killWinPieces();
            gameBoardPanel.revalidate();
            gameBoardPanel.repaint();
            return true;
        } else {
            System.out.println("DONE!!!");
            return false;
        }

    }

}
