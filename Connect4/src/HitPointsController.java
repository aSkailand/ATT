import java.awt.*;

/**
 * Created by aslak on 25.04.17.
 */
public class HitPointsController {
    GameBodyFrame gameBodyFrame;
    HitPointsModel hitPointsModel;
    HitPointsView hitPointsView;

    public HitPointsController(GameBodyController gameBodyController) {

        // Declare MVC
        gameBodyFrame = gameBodyController.gameBodyFrame;
        hitPointsModel = new HitPointsModel();
        hitPointsView = new HitPointsView();

        // Panel Setup
        gameBodyFrame.leftPlayerHPPanel.add(hitPointsView.createAHeartJPanel(hitPointsModel.heartsLabelPlayer1, Color.red));
        gameBodyFrame.rightPlayerHPPanel.add(hitPointsView.createAHeartJPanel(hitPointsModel.heartsLabelPlayer2, Color.blue));

        gameBodyFrame.leftPlayerHP.setText("HP:"+ hitPointsModel.getPlayer_1Hitpoints()+"/"+ hitPointsModel.getPlayersHitpoints());
        gameBodyFrame.rightPlayerHP.setText("HP:"+ hitPointsModel.getPlayer_2Hitpoints()+"/"+ hitPointsModel.getPlayersHitpoints());

    }

    /**
     * This is the method for calculating the hit points of the players and updates the GameBodyFrame view
     *
     * @param change
     * @param currentPlayer
     */
    public void HitpointsPercentage(int change, GameBoardModel.player currentPlayer) {

        if (currentPlayer.equals(GameBoardModel.player.PLAYER_1)) {

            double player_1HPCurrent = (double) hitPointsModel.getPlayer_1Hitpoints();
            double player_1HPTotal = (double) hitPointsModel.getPlayersHitpoints();
            int percentagePlayer_1HP;

            player_1HPCurrent += change;
            percentagePlayer_1HP = (int) (Math.floor(player_1HPCurrent / player_1HPTotal * 100));

            int player_1HPCurrent_view = (int) player_1HPCurrent;

            UpdateHeartsView(player_1HPCurrent_view, currentPlayer);

            System.out.println("" + percentagePlayer_1HP + "%");
            hitPointsModel.setPlayer_1Hitpoints(player_1HPCurrent_view);
            gameBodyFrame.leftPlayerHP.setText("HP:" + player_1HPCurrent_view + "/" + hitPointsModel.getPlayersHitpoints());

        } else if (currentPlayer.equals(GameBoardModel.player.PLAYER_2)) {

            double player_2HPCurrent = (double) hitPointsModel.getPlayer_2Hitpoints();
            double player_2HPTotal = (double) hitPointsModel.getPlayersHitpoints();
            int percentagePlayer_2HP;

            player_2HPCurrent += change;
            percentagePlayer_2HP = (int) (Math.floor(player_2HPCurrent / player_2HPTotal * 100));

            int player_2HPCurrent_view = (int) player_2HPCurrent;

            UpdateHeartsView(player_2HPCurrent_view, currentPlayer);

            System.out.println("" + percentagePlayer_2HP + "%");
            hitPointsModel.setPlayer_2Hitpoints(player_2HPCurrent_view);
            gameBodyFrame.rightPlayerHP.setText("HP:" + player_2HPCurrent_view + "/" + hitPointsModel.getPlayersHitpoints());

        } else {
            System.out.println("Error: no player found!");
        }
    }

    /**
     * This method updates the hearts for each player
     *
     * @param percentage
     * @param currentPlayer
     */
    public void UpdateHeartsView(int percentage, GameBoardModel.player currentPlayer) {
        if (currentPlayer.equals(GameBoardModel.player.PLAYER_1)) {
            if (80 < percentage) {
                for (int i = 0; i < 5; i++) {
                    hitPointsModel.heartsLabelPlayer1.get(i).setIcon(hitPointsModel.heartAliveIcon);
                }
            } else if (60 < percentage) {
                for (int i = 0; i < 4; i++) {
                    hitPointsModel.heartsLabelPlayer1.get(i).setIcon(hitPointsModel.heartAliveIcon);
                }
                hitPointsModel.heartsLabelPlayer1.get(4).setIcon(hitPointsModel.heartDeadIcon);
            } else if (40 < percentage) {
                for (int i = 0; i < 3; i++) {
                    hitPointsModel.heartsLabelPlayer1.get(i).setIcon(hitPointsModel.heartAliveIcon);
                }
                for (int i = 3; i < 4; i++) {
                    hitPointsModel.heartsLabelPlayer1.get(i).setIcon(hitPointsModel.heartDeadIcon);
                }
            } else if (20 < percentage) {
                for (int i = 0; i < 2; i++) {
                    hitPointsModel.heartsLabelPlayer1.get(i).setIcon(hitPointsModel.heartAliveIcon);
                }
                for (int i = 2; i < 4; i++) {
                    hitPointsModel.heartsLabelPlayer1.get(i).setIcon(hitPointsModel.heartDeadIcon);
                }
            } else if (0 < percentage) {
                hitPointsModel.heartsLabelPlayer1.get(0).setIcon(hitPointsModel.heartAliveIcon);
                for (int i = 1; i < 4; i++) {
                    hitPointsModel.heartsLabelPlayer1.get(i).setIcon(hitPointsModel.heartDeadIcon);
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    hitPointsModel.heartsLabelPlayer1.get(i).setIcon(hitPointsModel.heartDeadIcon);
                }
            }
        }
        if (currentPlayer.equals(GameBoardModel.player.PLAYER_2)) {
            if (80 < percentage) {
                for (int i = 0; i < 5; i++) {
                    hitPointsModel.heartsLabelPlayer2.get(i).setIcon(hitPointsModel.heartAliveIcon);
                }
            } else if (60 < percentage) {
                for (int i = 4; i > 0; i--) {
                    hitPointsModel.heartsLabelPlayer2.get(i).setIcon(hitPointsModel.heartAliveIcon);
                }
                hitPointsModel.heartsLabelPlayer2.get(0).setIcon(hitPointsModel.heartDeadIcon);
            } else if (40 < percentage) {
                for (int i = 4; i > 1; i--) {
                    hitPointsModel.heartsLabelPlayer2.get(i).setIcon(hitPointsModel.heartAliveIcon);
                }
                for (int i = 0; i < 2; i++) {
                    hitPointsModel.heartsLabelPlayer2.get(i).setIcon(hitPointsModel.heartDeadIcon);
                }
            } else if (20 < percentage) {
                for (int i = 4; i > 2; i--) {
                    hitPointsModel.heartsLabelPlayer2.get(i).setIcon(hitPointsModel.heartAliveIcon);
                }
                for (int i = 0; i < 3; i++) {
                    hitPointsModel.heartsLabelPlayer2.get(i).setIcon(hitPointsModel.heartDeadIcon);
                }
            } else if (0 < percentage) {
                hitPointsModel.heartsLabelPlayer2.get(4).setIcon(hitPointsModel.heartAliveIcon);
                for (int i = 4; i > 1; i--) {
                    hitPointsModel.heartsLabelPlayer2.get(i).setIcon(hitPointsModel.heartAliveIcon);
                }
                for (int i = 0; i < 4; i++) {
                    hitPointsModel.heartsLabelPlayer2.get(i).setIcon(hitPointsModel.heartDeadIcon);
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    hitPointsModel.heartsLabelPlayer2.get(i).setIcon(hitPointsModel.heartDeadIcon);
                }
            }
        }
    }

}
