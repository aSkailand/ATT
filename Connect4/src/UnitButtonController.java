import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aslak on 27.04.17.
 */
public class UnitButtonController implements ActionListener {

    GameBodyFrame gameBodyFrame;
    UnitButtonView unitButtonView;
    UnitButtonModel unitButtonModel;
    GoldModel goldModel;
    GameBoardModel gameBoardModel;
    GoldController goldController;

    public UnitButtonController(GameBodyController gameBodyController) {


        // Declare MVC
        goldModel = gameBodyController.goldController.goldModel;
        goldController = gameBodyController.goldController;

        gameBodyFrame = gameBodyController.gameBodyFrame;
        unitButtonModel = new UnitButtonModel();
        unitButtonView = new UnitButtonView();

        gameBoardModel = gameBodyController.gameBoardController.gameBoardModel;



        // Adds buttons to the left player unit panel
        gameBodyFrame.leftPlayerUnitPanel.add(unitButtonView.CreateUnitButtonView(
                this,
                unitButtonModel.unitButtonsPlayer1,
                unitButtonModel.unitLabelPlayer1,
                unitButtonModel.testImg,
                Color.red,
                Color.GREEN));

        // adds buttons to the right player unit panel
        gameBodyFrame.rightPlayerUnitPanel.add(unitButtonView.CreateUnitButtonView(
                this,
                unitButtonModel.unitButtonsPlayer2,
                unitButtonModel.unitLabelPlayer2, unitButtonModel.testImg,
                Color.blue,
                Color.GREEN));

        gameBodyFrame.revalidate();
        gameBodyFrame.repaint();

    }


    /**
     * This alternates the unit buttons for each player, disables the buttons of the opposing player when its the current players turn.
     *
     * @param currentPlayer
     */
    public void disableButtons(GameBoardModel.player currentPlayer) {

        if (currentPlayer.equals(GameBoardModel.player.PLAYER_1)) {
            for (int i = 0; i < 6; i++) {

                gameBodyFrame.gameBodyModel.playerTwoAvatarButton.setEnabled(false);
                gameBodyFrame.gameBodyModel.playerOneAvatarButton.setEnabled(true);



                unitButtonModel.unitButtonsPlayer1.get(i).setEnabled(true);
                unitButtonModel.unitButtonsPlayer2.get(i).setEnabled(false);



                checkUnitsAffordability(GameBoardModel.player.PLAYER_1, goldModel.getPlayer1Gold(), goldModel.getPlayer2Gold());
                checkMagicAffordability(GameBoardModel.player.PLAYER_1, goldModel.getPlayer1Gold(), goldModel.getPlayer2Gold());

                gameBodyFrame.labelNotifications.setText(setPlayerTurnNotificationText(currentPlayer));
                gameBodyFrame.centerStatusPanel.setBackground(Color.RED);

            }
            for (int i = 0; i < 6; i++) {
                unitButtonModel.unitButtonsPlayer2.get(i).setContentAreaFilled(false);

            }

        } else if (currentPlayer.equals(GameBoardModel.player.PLAYER_2)) {
            for (int i = 0; i < 6; i++) {


                gameBodyFrame.gameBodyModel.playerTwoAvatarButton.setEnabled(true);
                gameBodyFrame.gameBodyModel.playerOneAvatarButton.setEnabled(false);

                unitButtonModel.unitButtonsPlayer2.get(i).setEnabled(true);
                unitButtonModel.unitButtonsPlayer1.get(i).setEnabled(false);

                checkUnitsAffordability(GameBoardModel.player.PLAYER_2, goldModel.getPlayer1Gold(), goldModel.getPlayer2Gold());
                checkMagicAffordability(GameBoardModel.player.PLAYER_2, goldModel.getPlayer1Gold(), goldModel.getPlayer2Gold());

                gameBodyFrame.labelNotifications.setText(setPlayerTurnNotificationText(currentPlayer));
                gameBodyFrame.centerStatusPanel.setBackground(Color.BLUE);

            }
            for (int i = 0; i < 6; i++) {
                unitButtonModel.unitButtonsPlayer1.get(i).setContentAreaFilled(false);
            }
        }
    }

    /**
     * This method checks if the current player can afford the different units
     *
     * @param currentPlayer: player turn
     * @param player_1Gold:  player 1 gold
     * @param player_2Gold:  player 2 gold
     */
    public void checkUnitsAffordability(GameBoardModel.player currentPlayer, int player_1Gold, int player_2Gold) {

        int p1Gold = player_1Gold;
        int p2Gold = player_2Gold;

        if (currentPlayer.equals(GameBoardModel.player.PLAYER_1)) {
            if (p1Gold >= unitButtonModel.getUnit3Cost()) {
                unitButtonModel.unitButtonsPlayer1.get(0).setEnabled(true);
                unitButtonModel.unitButtonsPlayer1.get(2).setEnabled(true);
                unitButtonModel.unitButtonsPlayer1.get(4).setEnabled(true);
            }
            if (p1Gold < unitButtonModel.getUnit3Cost()) {

                unitButtonModel.unitButtonsPlayer1.get(0).setEnabled(true);
                unitButtonModel.unitButtonsPlayer1.get(2).setEnabled(true);
                unitButtonModel.unitButtonsPlayer1.get(4).setEnabled(false);

            }
            if (p1Gold < unitButtonModel.getUnit2Cost()) {
                unitButtonModel.unitButtonsPlayer1.get(0).setEnabled(true);
                unitButtonModel.unitButtonsPlayer1.get(2).setEnabled(false);
                unitButtonModel.unitButtonsPlayer1.get(4).setEnabled(false);

            }
            if (p1Gold < unitButtonModel.getUnit1Cost()) {
                unitButtonModel.unitButtonsPlayer1.get(0).setEnabled(false);
                unitButtonModel.unitButtonsPlayer1.get(2).setEnabled(false);
                unitButtonModel.unitButtonsPlayer1.get(4).setEnabled(false);

            }

        }
        if (currentPlayer.equals(GameBoardModel.player.PLAYER_2)) {
            if (p2Gold >= unitButtonModel.getUnit3Cost()) {
                unitButtonModel.unitButtonsPlayer2.get(0).setEnabled(true);
                unitButtonModel.unitButtonsPlayer2.get(2).setEnabled(true);
                unitButtonModel.unitButtonsPlayer2.get(4).setEnabled(true);
            }
            if (p2Gold < unitButtonModel.getUnit3Cost()) {

                unitButtonModel.unitButtonsPlayer2.get(0).setEnabled(true);
                unitButtonModel.unitButtonsPlayer2.get(2).setEnabled(true);
                unitButtonModel.unitButtonsPlayer2.get(4).setEnabled(false);

            }
            if (p2Gold < unitButtonModel.getUnit2Cost()) {
                unitButtonModel.unitButtonsPlayer2.get(0).setEnabled(true);
                unitButtonModel.unitButtonsPlayer2.get(2).setEnabled(false);
                unitButtonModel.unitButtonsPlayer2.get(4).setEnabled(false);

            }
            if (p2Gold < unitButtonModel.getUnit1Cost()) {
                unitButtonModel.unitButtonsPlayer2.get(0).setEnabled(false);
                unitButtonModel.unitButtonsPlayer2.get(2).setEnabled(false);
                unitButtonModel.unitButtonsPlayer2.get(4).setEnabled(false);

            }

        }
        gameBodyFrame.revalidate();
        gameBodyFrame.repaint();
    }

    /**
     * This metod checks if the current player can afford the different magic spells
     *
     * @param currentPlayer: player turn
     * @param player_1Gold:  player 1 gold
     * @param player_2Gold:  player 2 gold
     */
    public void checkMagicAffordability(GameBoardModel.player currentPlayer, int player_1Gold, int player_2Gold) {

        int p1Gold = player_1Gold;
        int p2Gold = player_2Gold;

        if (currentPlayer.equals(GameBoardModel.player.PLAYER_1)) {
            if (p1Gold >= unitButtonModel.getMagic3Cost()) {
                unitButtonModel.unitButtonsPlayer1.get(1).setEnabled(true);
                unitButtonModel.unitButtonsPlayer1.get(3).setEnabled(true);
                unitButtonModel.unitButtonsPlayer1.get(5).setEnabled(true);
            }
            if (p1Gold < unitButtonModel.getMagic3Cost()) {

                unitButtonModel.unitButtonsPlayer1.get(1).setEnabled(true);
                unitButtonModel.unitButtonsPlayer1.get(3).setEnabled(true);
                unitButtonModel.unitButtonsPlayer1.get(5).setEnabled(false);

            }
            if (p1Gold < unitButtonModel.getMagic2Cost()) {
                unitButtonModel.unitButtonsPlayer1.get(1).setEnabled(true);
                unitButtonModel.unitButtonsPlayer1.get(3).setEnabled(false);
                unitButtonModel.unitButtonsPlayer1.get(5).setEnabled(false);

            }
            if (p1Gold < unitButtonModel.getMagic1Cost()) {
                unitButtonModel.unitButtonsPlayer1.get(1).setEnabled(false);
                unitButtonModel.unitButtonsPlayer1.get(3).setEnabled(false);
                unitButtonModel.unitButtonsPlayer1.get(5).setEnabled(false);

            }

        }
        if (currentPlayer.equals(GameBoardModel.player.PLAYER_2)) {
            if (p2Gold >= unitButtonModel.getMagic3Cost()) {
                unitButtonModel.unitButtonsPlayer2.get(1).setEnabled(true);
                unitButtonModel.unitButtonsPlayer2.get(3).setEnabled(true);
                unitButtonModel.unitButtonsPlayer2.get(5).setEnabled(true);
            }
            if (p2Gold < unitButtonModel.getMagic3Cost()) {

                unitButtonModel.unitButtonsPlayer2.get(1).setEnabled(true);
                unitButtonModel.unitButtonsPlayer2.get(3).setEnabled(true);
                unitButtonModel.unitButtonsPlayer2.get(5).setEnabled(false);

            }
            if (p2Gold < unitButtonModel.getMagic2Cost()) {
                unitButtonModel.unitButtonsPlayer2.get(1).setEnabled(true);
                unitButtonModel.unitButtonsPlayer2.get(3).setEnabled(false);
                unitButtonModel.unitButtonsPlayer2.get(5).setEnabled(false);

            }
            if (p2Gold < unitButtonModel.getMagic1Cost()) {
                unitButtonModel.unitButtonsPlayer2.get(1).setEnabled(false);
                unitButtonModel.unitButtonsPlayer2.get(3).setEnabled(false);
                unitButtonModel.unitButtonsPlayer2.get(5).setEnabled(false);

            }
        }

    }

    void selectedButton(int buttonPressed, GameBoardModel.player currentPlayer) {
        if (currentPlayer.equals(GameBoardModel.player.PLAYER_1)) {
            for (int i = 0; i < 6; i++) {
                unitButtonModel.unitButtonsPlayer1.get(i).setContentAreaFilled(false);

            }
            unitButtonModel.unitButtonsPlayer1.get(buttonPressed).setContentAreaFilled(true);
        } else if (currentPlayer.equals(GameBoardModel.player.PLAYER_2)) {
            for (int i = 0; i < 6; i++) {
                unitButtonModel.unitButtonsPlayer2.get(i).setContentAreaFilled(false);
            }
            unitButtonModel.unitButtonsPlayer2.get(buttonPressed).setContentAreaFilled(true);
        }
    }


    public String setPlayerTurnNotificationText(GameBoardModel.player currentPlayer ) {
        String currentPlayerText = "";

        if (currentPlayer.equals(GameBoardModel.player.PLAYER_1)) {
            currentPlayerText = "Player 1 turn!";
        }
        if (currentPlayer.equals(GameBoardModel.player.PLAYER_2)) {
            currentPlayerText = "Player 2 turn!";
        }
        return currentPlayerText;
    }


    // Action listener
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "button0": {
                selectedButton(0, gameBoardModel.getCurrentPlayer());


                System.out.println("button0");
                break;
            }
            case "button1": {

                selectedButton(1, gameBoardModel.getCurrentPlayer());

                System.out.println("button1");
                break;
            }
            case "button2": {
                selectedButton(2, gameBoardModel.getCurrentPlayer());
                System.out.println("button2");
                break;
            }
            case "button3": {
                selectedButton(3, gameBoardModel.getCurrentPlayer());
                System.out.println("button3");
                break;
            }
            case "button4": {
                selectedButton(4, gameBoardModel.getCurrentPlayer());

                System.out.println("button4");
                break;
            }
            case "button5": {
                selectedButton(5, gameBoardModel.getCurrentPlayer());

                System.out.println("button5");
                break;
            }
            case "button6": {
                selectedButton(6, gameBoardModel.getCurrentPlayer());

                System.out.println("button6");
                break;
            }
        }
    }
}
