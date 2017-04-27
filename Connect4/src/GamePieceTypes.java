/**
 * Created by Trong on 26/04/2017.
 */
public enum  GamePieceTypes {
    Peasant('p'), Knight('k'), None('-');


    private char aChar;

    GamePieceTypes(char aChar) {
        this.aChar = aChar;
    }

    public char getaChar() {
        return aChar;
    }
}

//public enum unit {
//
//    peasant('a'),
//    knight('b'),
//    bomb('c');
//
//    private char numVal;
//
//    unit(char numVal) {
//        this.numVal = numVal;
//    }
//
//    public char getNumVal() {
//        return numVal;
//    }
//}