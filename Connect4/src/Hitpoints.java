import javax.swing.*;
import java.awt.*;

/**
 * Created by aslak on 25.04.17.
 */
public class Hitpoints {
    GameBodyFrame gameBodyFrame;
    HitpointModel hitpointModel;
    public Hitpoints(GameBodyFrame gbFrame) {
        gameBodyFrame = gbFrame;
        hitpointModel = gbFrame.hitpointModel;
    }

    /**
     * This is the method for calculating the hitpoints of the players and updates the GameBodyFrame view
     *
     * @param change
     * @param currentPlayer
     */
    public void HitpointsPercentage(int change, GameBoardModel.player currentPlayer) {

        if (currentPlayer.equals(GameBoardModel.player.PLAYER_1)) {

            double player_1HPCurrent = (double) hitpointModel.getPlayer_1Hitpoints();
            double player_1HPTotal = (double) hitpointModel.getPlayersHitpoints();
            int percentagePlayer_1HP;

            player_1HPCurrent += change;
            percentagePlayer_1HP = (int) (Math.floor(player_1HPCurrent / player_1HPTotal * 100));

            int player_1HPCurrent_view = (int) player_1HPCurrent;

            UpdateHeartsView(player_1HPCurrent_view, currentPlayer);

            System.out.println("" + percentagePlayer_1HP + "%");
            hitpointModel.setPlayer_1Hitpoints(player_1HPCurrent_view);
            gameBodyFrame.leftPlayerHP.setText("HP:" + player_1HPCurrent_view + "/" + hitpointModel.getPlayersHitpoints());

        } else if (currentPlayer.equals(GameBoardModel.player.PLAYER_2)) {

            double player_2HPCurrent = (double) hitpointModel.getPlayer_2Hitpoints();
            double player_2HPTotal = (double) hitpointModel.getPlayersHitpoints();
            int percentagePlayer_2HP;

            player_2HPCurrent += change;
            percentagePlayer_2HP = (int) (Math.floor(player_2HPCurrent / player_2HPTotal * 100));

            int player_2HPCurrent_view = (int) player_2HPCurrent;

            UpdateHeartsView(player_2HPCurrent_view, currentPlayer);

            System.out.println("" + percentagePlayer_2HP + "%");
            hitpointModel.setPlayer_2Hitpoints(player_2HPCurrent_view);
            gameBodyFrame.rightPlayerHP.setText("HP:" + player_2HPCurrent_view + "/" + hitpointModel.getPlayersHitpoints());

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
                    hitpointModel.heartsPlayer1.get(i).setIcon(hitpointModel.heartIcon);
                }
            } else if (60 < percentage) {
                for (int i = 0; i < 4; i++) {
                    hitpointModel.heartsPlayer1.get(i).setIcon(hitpointModel.heartIcon);
                }
                hitpointModel.heartsPlayer1.get(4).setIcon(hitpointModel.deadHeartIcon);
            } else if (40 < percentage) {
                for (int i = 0; i < 3; i++) {
                    hitpointModel.heartsPlayer1.get(i).setIcon(hitpointModel.heartIcon);
                }
                for (int i = 3; i < 4; i++) {
                    hitpointModel.heartsPlayer1.get(i).setIcon(hitpointModel.deadHeartIcon);
                }
            } else if (20 < percentage) {
                for (int i = 0; i < 2; i++) {
                    hitpointModel.heartsPlayer1.get(i).setIcon(hitpointModel.heartIcon);
                }
                for (int i = 2; i < 4; i++) {
                    hitpointModel.heartsPlayer1.get(i).setIcon(hitpointModel.deadHeartIcon);
                }
            } else if (0 < percentage) {
                hitpointModel.heartsPlayer1.get(0).setIcon(hitpointModel.heartIcon);
                for (int i = 1; i < 4; i++) {
                    hitpointModel.heartsPlayer1.get(i).setIcon(hitpointModel.deadHeartIcon);
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    hitpointModel.heartsPlayer1.get(i).setIcon(hitpointModel.deadHeartIcon);
                }
            }
        }
        if (currentPlayer.equals(GameBoardModel.player.PLAYER_2)) {
            if (80 < percentage) {
                for (int i = 0; i < 5; i++) {
                    hitpointModel.heartsPlayer2.get(i).setIcon(hitpointModel.heartIcon);
                }
            } else if (60 < percentage) {
                for (int i = 4; i > 0; i--) {
                    hitpointModel.heartsPlayer2.get(i).setIcon(hitpointModel.heartIcon);
                }
                hitpointModel.heartsPlayer2.get(0).setIcon(hitpointModel.deadHeartIcon);
            } else if (40 < percentage) {
                for (int i = 4; i > 1; i--) {
                    hitpointModel.heartsPlayer2.get(i).setIcon(hitpointModel.heartIcon);
                }
                for (int i = 0; i < 2; i++) {
                    hitpointModel.heartsPlayer2.get(i).setIcon(hitpointModel.deadHeartIcon);
                }
            } else if (20 < percentage) {
                for (int i = 4; i > 2; i--) {
                    hitpointModel.heartsPlayer2.get(i).setIcon(hitpointModel.heartIcon);
                }
                for (int i = 0; i < 3; i++) {
                    hitpointModel.heartsPlayer2.get(i).setIcon(hitpointModel.deadHeartIcon);
                }
            } else if (0 < percentage) {
                hitpointModel.heartsPlayer2.get(4).setIcon(hitpointModel.heartIcon);
                for (int i = 4; i > 1; i--) {
                    hitpointModel.heartsPlayer2.get(i).setIcon(hitpointModel.heartIcon);
                }
                for (int i = 0; i < 4; i++) {
                    hitpointModel.heartsPlayer2.get(i).setIcon(hitpointModel.deadHeartIcon);
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    hitpointModel.heartsPlayer2.get(i).setIcon(hitpointModel.deadHeartIcon);
                }
            }
        }
    }

    // 5 images of hearts that is placed on a JPanel and then added to the GameBodyFrame
    public JPanel HeartPlayer1() {
        JPanel heartsPanelPlayer1 = new JPanel();
        heartsPanelPlayer1.setLayout(new GridLayout());
        heartsPanelPlayer1.setBackground(Color.red);
        for (int i = 0; i < 5; i++) {
            heartsPanelPlayer1.add(hitpointModel.heartListPlayer1.get(i));
        }
        return heartsPanelPlayer1;
    }

    // 5 images of hearts that is placed on a JPanel and then added to the GameBodyFrame
    public JPanel HeartPlayer2() {
        JPanel heartsPanelPlayer2 = new JPanel();
        heartsPanelPlayer2.setBackground(Color.BLUE);
        heartsPanelPlayer2.setLayout(new GridLayout());
        for (int i = 0; i < 5; i++) {
            heartsPanelPlayer2.add(hitpointModel.heartListPlayer2.get(i));
        }
        return heartsPanelPlayer2;
    }
}
