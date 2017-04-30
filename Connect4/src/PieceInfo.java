/**
 * Created by Trong on 27/04/2017.
 */

class PieceInfo {

    private GameBoardModel.player owner;
    private GamePieceTypes gamePieceType;

    private boolean enabled;

    PieceInfo() {
        this.owner = GameBoardModel.player.PLAYER_NONE;
        this.gamePieceType = GamePieceTypes.None;
        this.enabled = false;
    }

    PieceInfo(GameBoardModel.player player, GamePieceTypes unit, boolean enabled) {
        this.owner = player;
        this.gamePieceType = unit;
        this.enabled = enabled;
    }

    boolean checkIf(GameBoardModel.player player, GamePieceTypes unit, boolean enable){
        return owner.equals(player) && gamePieceType.equals(unit) && enabled == enable;
    }

    void copyInfo(PieceInfo sourcePiece) {
        this.owner = sourcePiece.getOwner();
        this.gamePieceType = sourcePiece.getPieceType();
        this.enabled = sourcePiece.isEnabled();
    }

    void setInfo(GameBoardModel.player player, GamePieceTypes unit, boolean isEnabled) {
        this.owner = player;
        this.gamePieceType = unit;
        this.enabled = isEnabled;
    }

    void clearInfo() {
        this.owner = GameBoardModel.player.PLAYER_NONE;
        this.gamePieceType = GamePieceTypes.None;
        this.enabled = false;
    }

    /**
     * Get string code, used during printing of listCombined.
     * @return a code of three characters: Owner+UnitType+Enabled -> ex. 1k1 ( Player 1's knight, who is still clickable)
     */
    String getCode() {
        if(enabled) return "" + owner.getNumVal() + gamePieceType.getCharCode() + 1;
        else return "" + owner.getNumVal() + gamePieceType.getCharCode() + 0;
    }

    public GameBoardModel.player getOwner() {
        return owner;
    }

    public GamePieceTypes getPieceType() {
        return gamePieceType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

