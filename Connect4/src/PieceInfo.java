/**
 * Created by Trong on 27/04/2017.
 */

class PieceInfo {


    private GameBoardModel.player owner;
    private GamePieceTypes gamePieceType;

    PieceInfo() {
        this.owner = GameBoardModel.player.PLAYER_NONE;
        this.gamePieceType = GamePieceTypes.None;
    }

    PieceInfo(GameBoardModel.player player, GamePieceTypes unit) {
        this.owner = player;
        this.gamePieceType = unit;
    }

    boolean checkIf(GameBoardModel.player player, GamePieceTypes unit){
        return owner.equals(player) && gamePieceType.equals(unit);
    }

    void copyInfo(PieceInfo sourcePiece) {
        this.owner = sourcePiece.getOwner();
        this.gamePieceType = sourcePiece.getPieceType();
    }

    void setInfo(GameBoardModel.player player, GamePieceTypes unit) {
        this.owner = player;
        this.gamePieceType = unit;
    }

    void clearInfo() {
        this.owner = GameBoardModel.player.PLAYER_NONE;
        this.gamePieceType = GamePieceTypes.None;
    }

    String getCode() {
        return "" + owner.getNumVal() + gamePieceType.getaChar();
    }

    public GameBoardModel.player getOwner() {
        return owner;
    }

    public GamePieceTypes getPieceType() {
        return gamePieceType;
    }
}

