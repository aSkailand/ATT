/**
 * Created by Trong on 27/04/2017.
 */

class PieceInfo {
    public GameBoardModel.player getOwner() {
        return owner;
    }

    public GamePieceTypes getGamePieceType() {
        return gamePieceType;
    }

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

    void copyInfo(PieceInfo sourcePiece) {
        this.owner = sourcePiece.getOwner();
        this.gamePieceType = sourcePiece.getGamePieceType();
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
}

