import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Trong on 30/04/2017.
 */
public class GamePieceMagic extends GamePiece implements ActionListener {

    int x;
    int y;

    private GameBoardController gameBoardController;
    private GameBoardModel gameBoardModel;

    Color ownerColor;


    boolean selected = false;

    public GamePieceMagic(GamePieceSlot gamePieceSlot) {
        this.gameBoardController = gamePieceSlot.gameBoardController;
        this.gameBoardModel = gameBoardController.gameBoardModel;

        this.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (gameBoardModel.currentSelectedAction) {
            case Swap: {
                System.out.println("Swap Select");
                runSwap();
                break;
            }
        }

    }

    void runSwap(){
        gameBoardModel.loadOccupancyListFromCombinedList();

        // IF CLICKED PIECE IS NOT SELECTED
        if (!selected) {
            selected = true;
            gameBoardModel.numSelected++;
            this.setBackground(ownerColor.darker().darker());


        }
        // IF CLICKED PIECE IS ALREADY SELECTED
        else {
            selected = false;
            gameBoardModel.numSelected--;
            this.setBackground(ownerColor);

        }

        // LOCK MANAGEMENT
        if (gameBoardModel.numSelected == 0) {

            // Update Selected Player
            gameBoardModel.selectedPlayer = null;

            // Open all except legal pieces to swap on
            lockMagicPieces(gameBoardModel.getWaitingPlayer());
            openMagicPieces(gameBoardModel.getCurrentPlayer());
            openMagicPieces(GameBoardModel.player.PLAYER_NEUTRAL);

            // Lock Cast Spell Button
            gameBoardController.gameOptionPanel.castSpellButton.setText("Please Select 2 Pieces");
            gameBoardController.gameOptionPanel.castSpellButton.setEnabled(false);

        }else if (gameBoardModel.numSelected == 1) {

            // Update Selected Player
            gameBoardModel.selectedPlayer = gameBoardModel.getSlotOccupancy(x, y);

            // Lock all except selected team
            lockMagicPieces(gameBoardModel.getWaitingPlayer());
            lockMagicPieces(gameBoardModel.getCurrentPlayer());
            lockMagicPieces(GameBoardModel.player.PLAYER_NEUTRAL);
            openMagicPieces(gameBoardModel.selectedPlayer);

            // Lock Cast Spell Button
            gameBoardController.gameOptionPanel.castSpellButton.setText("Please Select 1 more Piece");
            gameBoardController.gameOptionPanel.castSpellButton.setEnabled(false);

        } else if (gameBoardModel.numSelected == 2) {

            // Lock all except selected pieces
            lockMagicPieces(gameBoardModel.getWaitingPlayer());
            lockMagicPieces(gameBoardModel.getCurrentPlayer());
            lockMagicPieces(GameBoardModel.player.PLAYER_NEUTRAL);
            openSelectedMagicPieces();

            // Open Cast Spell Button
            gameBoardController.gameOptionPanel.castSpellButton.setText("CAST SPELL");
            gameBoardController.gameOptionPanel.castSpellButton.setEnabled(true);
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

    void openSelectedMagicPieces(){
        // Lock all except selected players
        for (int x = 0; x < GameBoardModel.numCol; x++) {
            for (int y = 0; y < GameBoardModel.numRow; y++) {
                if (!gameBoardModel.getSlotOccupancy(x, y).equals(GameBoardModel.player.PLAYER_NONE))
                    if(gameBoardController.gameBoardPanel.getSlot(x, y).getPieceMagic().isSelected())
                        gameBoardController.gameBoardPanel.getSlot(x, y).getPieceMagic().setEnabled(true);
            }
        }
    }

    void openMagicPieces(GameBoardModel.player openPlayer) {
        // Lock all except selected players
        for (int x = 0; x < GameBoardModel.numCol; x++) {
            for (int y = 0; y < GameBoardModel.numRow; y++) {
                if (!gameBoardModel.getSlotOccupancy(x, y).equals(GameBoardModel.player.PLAYER_NONE)) {
                    if(gameBoardModel.getSlotOccupancy(x, y).equals(openPlayer)){
                        gameBoardController.gameBoardPanel.getSlot(x, y).getPieceMagic().setEnabled(true);
                    }
                }
            }
        }
    }


    @Override
    public boolean isSelected() {
        return selected;
    }
}

