/**
 * Created by Trong on 27/04/2017.
 */

class PieceInfo {

    private GameBoardModel.player owner;
    private GamePieceTypes gamePieceType;
    private boolean enabled;

    private boolean muted = false;
    private GameBoardModel.player backupOwner;

    private boolean bomber = false;

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
        this.backupOwner = sourcePiece.backupOwner;
        this.muted = sourcePiece.muted;
        this.bomber = sourcePiece.bomber;
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
        this.backupOwner = null;
        this.muted = false;
        this.bomber = false;
    }

    void setBombStatus(boolean turnOnBomb){
        this.bomber = turnOnBomb;
    }

    void setMuteStatus(boolean muteTick){
        if(muteTick) {
            this.muted = true;
            this.backupOwner = this.owner;
            this.owner = GameBoardModel.player.PLAYER_NEUTRAL;
        }
        else{
            this.muted = false;
            this.owner = this.backupOwner;
            this.backupOwner = null;
        }
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

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public boolean isMuted() {
        return muted;
    }

    public GameBoardModel.player getBackupOwner() {
        return backupOwner;
    }

    public boolean isBomber() {
        return bomber;
    }
}

