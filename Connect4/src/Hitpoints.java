import javax.swing.*;
import java.awt.*;

/**
 * Created by aslak on 25.04.17.
 */
public class Hitpoints {

    GameBodyFrame gameBodyFrame;
    HitpointModel hitpointModel;

    private int HP_player_1View;
    private int HP_player_2View;

    JLabel hp;

    public Hitpoints(GameBodyFrame gbFrame) {

        hitpointModel = new HitpointModel();
        gameBodyFrame = gbFrame;

    }

    public JPanel HeartPlayer1() {
        JPanel heartsPanelPlayer1 = new JPanel();
        heartsPanelPlayer1.setLayout(new GridLayout());
        heartsPanelPlayer1.setBackground(Color.BLUE);


        for (int i = 0; i < 5; i++) {

            heartsPanelPlayer1.add(hitpointModel.heartListPlayer1.get(i));

        }

        return heartsPanelPlayer1;

    }

    public JPanel HeartPlayer2() {
        JPanel heartsPanelPlayer2 = new JPanel();
        heartsPanelPlayer2.setBackground(Color.BLUE);
        heartsPanelPlayer2.setLayout(new GridLayout());

        for (int i = 0; i < 5; i++) {

            heartsPanelPlayer2.add(hitpointModel.heartListPlayer2.get(i));
        }


        return heartsPanelPlayer2;

    }


    public int getHP_player_1View() {
        return HP_player_1View;
    }

    public void setHP_player_1View(int HP_player_1View) {

        gameBodyFrame.leftPlayerHP.setText("HP:" + HP_player_1View);

        this.HP_player_1View = HP_player_1View;
    }

    public int getHP_player_2View() {
        return HP_player_2View;
    }

    public void setHP_player_2View(int HP_player_2View) {
        gameBodyFrame.rightPlayerHP.setText("HP:" + HP_player_2View + "");
        this.HP_player_2View = HP_player_2View;
    }
}
