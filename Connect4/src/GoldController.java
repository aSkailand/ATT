import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by aslak on 27.04.17.
 */
public class GoldController {

    private GameBodyFrame gameBodyFrame;
    GoldModel goldModel;
    private GoldView goldView;


    public GoldController(GameBodyController gameBodyController) {

        // Declare MVC
        gameBodyFrame = gameBodyController.gameBodyFrame;
        goldModel = new GoldModel();
        goldView = new GoldView(this);

        Image goldImage = goldModel.loadGoldIcon(new File("gold.png"), 30, 30);

        JPanel goldIconPanelPlayer1 = goldView.makeGoldIcon(goldImage);
        JPanel goldIconPanelPlayer2 = goldView.makeGoldIcon(goldImage);

        // Adds gold icon and a text with how much each player starts with

        // Player 1
        gameBodyFrame.leftPlayerGoldGrid.add(goldIconPanelPlayer1);
        goldView.player1GoldLabel.setText("X" + goldModel.getPlayer1Gold());
        gameBodyFrame.leftPlayerGoldGrid.add(goldView.player1GoldLabel);

        // Player 2
        gameBodyFrame.rightPlayerGoldGrid.add(goldIconPanelPlayer2);
        goldView.player2GoldLabel.setText("X" + goldModel.getPlayer2Gold());
        gameBodyFrame.rightPlayerGoldGrid.add(goldView.player2GoldLabel);

        gameBodyFrame.revalidate();
        gameBodyFrame.repaint();
    }

    /**
     * This method updates the players gold value, and changes the view and set the new value in the model.
     *
     * @param gold:          this is the change in gold (+-).
     * @param currentPlayer: which players gold is updated.
     */
    public void UpdateGoldValue(int gold, GameBoardModel.player currentPlayer) {

        if (currentPlayer.equals(GameBoardModel.player.PLAYER_1)) {

            int player_1Gold = goldModel.getPlayer1Gold();
            player_1Gold += gold;
            goldModel.setPlayer1Gold(player_1Gold);
            goldView.player1GoldLabel.setFont(goldModel.loadFontGold());
            goldView.player1GoldLabel.setText("x " + goldModel.getPlayer1Gold());
            System.out.println("player 1");

        } else if (currentPlayer.equals(GameBoardModel.player.PLAYER_2)) {

            int player_2Gold = goldModel.getPlayer2Gold();
            player_2Gold += gold;
            goldModel.setPlayer2Gold(player_2Gold);
            goldView.player2GoldLabel.setFont(goldModel.loadFontGold());
            goldView.player2GoldLabel.setText("x " + goldModel.getPlayer2Gold());
            System.out.println("player 2");
        }
    }
}
