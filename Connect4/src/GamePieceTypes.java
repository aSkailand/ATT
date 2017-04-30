/**
 * Created by Trong on 26/04/2017.
 */
public enum  GamePieceTypes {
    Peasant('p'),
    Assassin('a'),
    Knight('k'),
    Swap('s'),
    None('-');

    private char aChar;

    GamePieceTypes(char aChar) {
        this.aChar = aChar;
    }

    public char getCharCode() {
        return aChar;
    }
}
