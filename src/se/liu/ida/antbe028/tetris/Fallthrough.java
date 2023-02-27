package se.liu.ida.antbe028.tetris;

public class Fallthrough extends AbstractCollisionHandler {

    @Override
    public boolean ifMethod(Poly poly, Board board, SquareType typeInPolyArray, int x, int y) {
        if (typeInPolyArray != SquareType.EMPTY && 
            board.getSquares(x, y) == SquareType.OUTSIDE) {
            return true;
        }
        return false;
    }
}
