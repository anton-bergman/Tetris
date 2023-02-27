package se.liu.ida.antbe028.tetris;

public class DefaultFallHandler extends AbstractCollisionHandler {

    @Override 
    public boolean ifMethod(Poly poly, Board board, SquareType typeInPolyArray, int x, int y) {
        if (typeInPolyArray != SquareType.EMPTY && 
            board.getSquares(x, y) != SquareType.EMPTY) {
            return true;
        }
        return false;
    }
}
