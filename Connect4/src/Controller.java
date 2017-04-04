import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aslak on 03.04.17.
 */
public class Controller implements ActionListener{
    GameFrame gameFrame;


    public Controller(){
        gameFrame = new GameFrame(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int button_num = Integer.parseInt(e.getActionCommand());

        int indexOfNotOccupied = gameFrame.OccupiedSlots.get(button_num).indexOf(false);
        System.out.print(e.getActionCommand()+ " - ");
        System.out.println(indexOfNotOccupied);

        JButton GamePiece = new JButton("ASD");

//        gameFrame.LIST[indexOfNotOccupied][button_num].removeAll();
//        gameFrame.LIST[indexOfNotOccupied][button_num].add(GamePiece);
//        gameFrame.LIST[indexOfNotOccupied][button_num].repaint();
//        gameFrame.LIST[indexOfNotOccupied][button_num].revalidate();

        gameFrame.GameBoardSlots.get(button_num).get(indexOfNotOccupied).removeAll();
        gameFrame.GameBoardSlots.get(button_num).get(indexOfNotOccupied).add(new JButton("APP"));
        gameFrame.GameBoardSlots.get(button_num).get(indexOfNotOccupied).repaint();
        gameFrame.GameBoardSlots.get(button_num).get(indexOfNotOccupied).revalidate();

        gameFrame.OccupiedSlots.get(button_num).set(indexOfNotOccupied,true);



    }
}
