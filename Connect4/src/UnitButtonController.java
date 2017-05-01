import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aslak on 27.04.17.
 */
public class UnitButtonController implements ActionListener {

    GameBodyController gameBodyController;
    GameBodyFrame gameBodyFrame;
    UnitButtonView unitButtonView;
    UnitButtonModel unitButtonModel;
    GoldModel goldModel;
    GameBoardModel gameBoardModel;
    GameBoardController gameBoardController;
    GoldController goldController;


    boolean player1button1 = false;
    boolean player1button3 = false;
    boolean player1button5 = false;


    boolean player2button1 = false;
    boolean player2button3 = false;
    boolean player2button5 = false;


    public UnitButtonController(GameBodyController gameBodyController) {

        this.gameBodyController = gameBodyController;
        gameBoardController = gameBodyController.gameBoardController;
        gameBoardModel = gameBoardController.gameBoardModel;

        // Declare MVC
        goldModel = gameBodyController.goldController.goldModel;
        goldController = gameBodyController.goldController;

        gameBodyFrame = gameBodyController.gameBodyFrame;
        unitButtonModel = new UnitButtonModel();
        unitButtonView = new UnitButtonView();


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

        if (currentPlayer.equals(GameBoardModel.player.PLAYER_2)) {
            for (int i = 0; i < 6; i++) {

                gameBodyFrame.gameBodyModel.playerTwoAvatarButton.setEnabled(false);
                gameBodyFrame.gameBodyModel.playerOneAvatarButton.setEnabled(true);


                unitButtonModel.unitButtonsPlayer1.get(i).setEnabled(true);
                unitButtonModel.unitButtonsPlayer2.get(i).setEnabled(false);


                checkUnitsAffordability(GameBoardModel.player.PLAYER_1);
                checkMagicAffordability(GameBoardModel.player.PLAYER_1, false);

                gameBodyFrame.labelNotifications.setText(setPlayerTurnNotificationText(currentPlayer));
                gameBodyFrame.centerStatusPanel.setBackground(Color.RED);

            }
            for (int i = 0; i < 6; i++) {
                unitButtonModel.unitButtonsPlayer2.get(i).setContentAreaFilled(false);

            }


        } else if (currentPlayer.equals(GameBoardModel.player.PLAYER_1)) {
            for (int i = 0; i < 6; i++) {


                gameBodyFrame.gameBodyModel.playerTwoAvatarButton.setEnabled(true);
                gameBodyFrame.gameBodyModel.playerOneAvatarButton.setEnabled(false);

                unitButtonModel.unitButtonsPlayer2.get(i).setEnabled(true);
                unitButtonModel.unitButtonsPlayer1.get(i).setEnabled(false);

                checkUnitsAffordability(GameBoardModel.player.PLAYER_2);
                checkMagicAffordability(GameBoardModel.player.PLAYER_2, false);

                gameBodyFrame.labelNotifications.setText(setPlayerTurnNotificationText(currentPlayer));
                gameBodyFrame.centerStatusPanel.setBackground(Color.BLUE);

            }
            for (int i = 0; i < 6; i++) {
                unitButtonModel.unitButtonsPlayer1.get(i).setContentAreaFilled(false);
            }
        }
    }

    /**
     * This method disables buttons depending on which phase is ongoing
     *
     * @param currentPlayer: player turn
     * @param phase:         phase1 = true, phase 2 = false
     */
    public void phaseDisableButtons(GameBoardModel.player currentPlayer, boolean phase) {
        if (currentPlayer.equals(GameBoardModel.player.PLAYER_1)) {
            // Phase 1
            if (phase) {

                // Disables magic buttons when starting a new round
                unitButtonModel.unitButtonsPlayer1.get(1).setEnabled(false);
                unitButtonModel.unitButtonsPlayer1.get(3).setEnabled(false);
                unitButtonModel.unitButtonsPlayer1.get(5).setEnabled(false);
            }
            // Phase 2
            else {
                // Removes selected color on units
                unitButtonModel.unitButtonsPlayer1.get(0).setContentAreaFilled(false);
                unitButtonModel.unitButtonsPlayer1.get(2).setContentAreaFilled(false);
                unitButtonModel.unitButtonsPlayer1.get(4).setContentAreaFilled(false);

                // Disables unit buttons after phase 1
                unitButtonModel.unitButtonsPlayer1.get(0).setEnabled(false);
                unitButtonModel.unitButtonsPlayer1.get(2).setEnabled(false);
                unitButtonModel.unitButtonsPlayer1.get(4).setEnabled(false);

                // Enables magic buttons in phase 2
                unitButtonModel.unitButtonsPlayer1.get(1).setEnabled(true);
                unitButtonModel.unitButtonsPlayer1.get(3).setEnabled(true);
                unitButtonModel.unitButtonsPlayer1.get(5).setEnabled(true);
            }
        } else if (currentPlayer.equals(GameBoardModel.player.PLAYER_2)) {
            // Phase 1
            if (phase) {

                // Disables magic buttons when starting a new round
                unitButtonModel.unitButtonsPlayer2.get(1).setEnabled(false);
                unitButtonModel.unitButtonsPlayer2.get(3).setEnabled(false);
                unitButtonModel.unitButtonsPlayer2.get(5).setEnabled(false);

            }
            // Phase 2
            else {

                // Removes selected color on units
                unitButtonModel.unitButtonsPlayer2.get(0).setContentAreaFilled(false);
                unitButtonModel.unitButtonsPlayer2.get(2).setContentAreaFilled(false);
                unitButtonModel.unitButtonsPlayer2.get(4).setContentAreaFilled(false);

                // Disables unit buttons after phase 1
                unitButtonModel.unitButtonsPlayer2.get(0).setEnabled(false);
                unitButtonModel.unitButtonsPlayer2.get(2).setEnabled(false);
                unitButtonModel.unitButtonsPlayer2.get(4).setEnabled(false);

                // Enables magic buttons in phase 2
                unitButtonModel.unitButtonsPlayer2.get(1).setEnabled(true);
                unitButtonModel.unitButtonsPlayer2.get(3).setEnabled(true);
                unitButtonModel.unitButtonsPlayer2.get(5).setEnabled(true);
            }
        }
    }

    /**
     * This method checks if the current player can afford the different units
     *
     * @param currentPlayer: player turn
     */
    public void checkUnitsAffordability(GameBoardModel.player currentPlayer) {

        int p1Gold = gameBodyController.goldController.goldModel.getPlayer1Gold();
        int p2Gold = gameBodyController.goldController.goldModel.getPlayer2Gold();

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
     */
    public void checkMagicAffordability(GameBoardModel.player currentPlayer, boolean phase) {

        int p1Gold = gameBodyController.goldController.goldModel.getPlayer1Gold();
        int p2Gold = gameBodyController.goldController.goldModel.getPlayer2Gold();
        if (!phase) {
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

    void magicButtonSelectedPlayer1(int buttonPressed) {

        if(buttonPressed == 1) {
            if (!player1button1) {
                if (gameBoardModel.getCurrentPlayer().equals(GameBoardModel.player.PLAYER_1)) {
                    unitButtonModel.unitButtonsPlayer1.get(3).setEnabled(false);
                    unitButtonModel.unitButtonsPlayer1.get(5).setEnabled(false);
                }
            }
            if (player1button1) {
                checkMagicAffordability(GameBoardModel.player.PLAYER_1, false);
                unitButtonModel.unitButtonsPlayer1.get(1).setContentAreaFilled(false);
            }
            player1button1 = !player1button1;

        }
        if (buttonPressed == 3){
            if (!player1button3) {
                if (gameBoardModel.getCurrentPlayer().equals(GameBoardModel.player.PLAYER_1)) {
                    unitButtonModel.unitButtonsPlayer1.get(1).setEnabled(false);
                    unitButtonModel.unitButtonsPlayer1.get(5).setEnabled(false);
                }
            }
            if (player1button3) {
                checkMagicAffordability(GameBoardModel.player.PLAYER_1, false);
                unitButtonModel.unitButtonsPlayer1.get(3).setContentAreaFilled(false);
            }
            player1button3 = !player1button3;
        }
        if (buttonPressed == 5){
            if (!player1button5) {
                if (gameBoardModel.getCurrentPlayer().equals(GameBoardModel.player.PLAYER_1)) {
                    unitButtonModel.unitButtonsPlayer1.get(1).setEnabled(false);
                    unitButtonModel.unitButtonsPlayer1.get(3).setEnabled(false);
                }
            }
            if (player1button5) {
                checkMagicAffordability(GameBoardModel.player.PLAYER_1, false);
                unitButtonModel.unitButtonsPlayer1.get(5).setContentAreaFilled(false);
            }
            player1button5 = !player1button5;
        }
    }

    void magicButtonSelectedPlayer2(int buttonPressed) {
        if(buttonPressed == 1) {
            if (!player2button1) {
                if (gameBoardModel.getCurrentPlayer().equals(GameBoardModel.player.PLAYER_2)) {
                    unitButtonModel.unitButtonsPlayer2.get(3).setEnabled(false);
                    unitButtonModel.unitButtonsPlayer2.get(5).setEnabled(false);
                }
            }
            if (player2button1) {
                checkMagicAffordability(GameBoardModel.player.PLAYER_2, false);
                unitButtonModel.unitButtonsPlayer2.get(1).setContentAreaFilled(false);
            }
            player2button1 = !player2button1;
        }
        if (buttonPressed == 3){
            if (!player2button3) {
                if (gameBoardModel.getCurrentPlayer().equals(GameBoardModel.player.PLAYER_2)) {
                    unitButtonModel.unitButtonsPlayer2.get(1).setEnabled(false);
                    unitButtonModel.unitButtonsPlayer2.get(5).setEnabled(false);
                }
            }
            if (player2button3) {
                checkMagicAffordability(GameBoardModel.player.PLAYER_2, false);
                unitButtonModel.unitButtonsPlayer2.get(3).setContentAreaFilled(false);
            }
            player2button3 = !player2button3;
        }
        if (buttonPressed == 5){
            if (!player2button5) {
                if (gameBoardModel.getCurrentPlayer().equals(GameBoardModel.player.PLAYER_2)) {
                    unitButtonModel.unitButtonsPlayer2.get(1).setEnabled(false);
                    unitButtonModel.unitButtonsPlayer2.get(3).setEnabled(false);
                }
            }
            if (player2button5) {
                checkMagicAffordability(GameBoardModel.player.PLAYER_2, false);
                unitButtonModel.unitButtonsPlayer2.get(5).setContentAreaFilled(false);
            }
            player2button5 = !player2button5;
        }
    }

    public String setPlayerTurnNotificationText(GameBoardModel.player currentPlayer) {
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
                gameBoardModel.currentSelectedAction = GamePieceTypes.Peasant;
                gameBoardController.openPlayableColumns();
                System.out.println("button0 - Peasant is chosen.");
                break;
            }
            case "button1": {


                selectedButton(1, gameBoardModel.getCurrentPlayer());

                // this disables the buttons that are not selected
                if (GameBoardModel.player.PLAYER_1.equals(gameBoardModel.getCurrentPlayer())) {
                    magicButtonSelectedPlayer1(1);
                }
                if (GameBoardModel.player.PLAYER_2.equals(gameBoardModel.getCurrentPlayer())){
                    magicButtonSelectedPlayer2(1);
                }

                // Updating Occupancy List
                gameBoardModel.loadOccupancyListFromCombinedList();

                // Convert all pieces to magic
                convertAllToMagicPieces();

                // Enabling all except opponent + empty slots
                lockMagicPieces(gameBoardModel.getWaitingPlayer());

                // Changing current selection
                gameBoardModel.currentSelectedAction = GamePieceTypes.Swap;

                // Switch to Spell Cast Panel
                gameBoardController.autoSwitchActionPanel();

                gameBoardController.gameBoardPanel.revalidate();
                gameBoardController.gameBoardPanel.repaint();


                System.out.println("button1 - Swap is chosen");
                break;
            }
            case "button2": {
                selectedButton(2, gameBoardModel.getCurrentPlayer());
                gameBoardModel.currentSelectedAction = GamePieceTypes.Assassin;
                gameBoardController.openPlayableColumns();
                System.out.println("button2 - Assassin is chosen.");
                break;
            }
            case "button3": {

                selectedButton(3, gameBoardModel.getCurrentPlayer());


                if (GameBoardModel.player.PLAYER_1.equals(gameBoardModel.getCurrentPlayer())) {
                    magicButtonSelectedPlayer1(3);
                }
                if (GameBoardModel.player.PLAYER_2.equals(gameBoardModel.getCurrentPlayer())){
                    magicButtonSelectedPlayer2(3);
                }

                System.out.println("button3");

                convertAllToMagicPieces();

                // Enabling all except Neutral
                lockMagicPieces(GameBoardModel.player.PLAYER_NEUTRAL);

                // Changing current selection
                gameBoardModel.currentSelectedAction = GamePieceTypes.Mute;

                // Switch to Spell Cast Panel
                gameBoardController.autoSwitchActionPanel();

                gameBoardController.gameBoardPanel.revalidate();
                gameBoardController.gameBoardPanel.repaint();

                System.out.println("button3 - Mute is chosen.");

                break;
            }
            case "button4": {
                selectedButton(4, gameBoardModel.getCurrentPlayer());
                gameBoardModel.currentSelectedAction = GamePieceTypes.Knight;
                gameBoardController.openPlayableColumns();
                System.out.println("button4 - Knight is chosen.");
                break;
            }
            case "button5": {

                selectedButton(5, gameBoardModel.getCurrentPlayer());

                if (GameBoardModel.player.PLAYER_1.equals(gameBoardModel.getCurrentPlayer())) {
                    magicButtonSelectedPlayer1(5);
                }
                if (GameBoardModel.player.PLAYER_2.equals(gameBoardModel.getCurrentPlayer())){
                    magicButtonSelectedPlayer2(5);
                }

                System.out.println("button5");
                break;
            }
            case "button6": {
                selectedButton(6, gameBoardModel.getCurrentPlayer());

                System.out.println("button6");
                break;
            }
        }

        gameBoardController.autoSwitchActionPanel();


    }

    public void setPlayer1button1(boolean player1button1) {
        this.player1button1 = player1button1;
    }

    public void setPlayer1button3(boolean player1button3) {
        this.player1button3 = player1button3;
    }

    public void setPlayer1button5(boolean player1button5) {
        this.player1button5 = player1button5;
    }

    public void setPlayer2button1(boolean player2button1) {
        this.player2button1 = player2button1;
    }

    public void setPlayer2button3(boolean player2button3) {
        this.player2button3 = player2button3;
    }

    public void setPlayer2button5(boolean player2button5) {
        this.player2button5 = player2button5;
    }

    void convertAllToMagicPieces(){
        // Convert all over to magic
        for (int x = 0; x < GameBoardModel.numCol; x++) {
            for (int y = 0; y < GameBoardModel.numRow; y++) {
                if (!gameBoardModel.getSlotOccupancy(x, y).equals(GameBoardModel.player.PLAYER_NONE)) {
                    gameBoardController.gameBoardPanel.getSlot(x, y).switchToMagic();
                }
            }
        }
    }

    void lockMagicPieces(GameBoardModel.player lockPlayer) {
        // Lock all except selected players
        for (int x = 0; x < GameBoardModel.numCol; x++) {
            for (int y = 0; y < GameBoardModel.numRow; y++) {
                if (!gameBoardModel.getSlotOccupancy(x, y).equals(GameBoardModel.player.PLAYER_NONE)) {
                    if(gameBoardModel.getSlotOccupancy(x, y).equals(lockPlayer)){
                        gameBoardController.gameBoardPanel.getSlot(x, y).getPieceMagic().setEnabled(false);
                    }
                }
            }
        }
    }
}
