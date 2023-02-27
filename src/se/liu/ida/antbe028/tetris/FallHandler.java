package se.liu.ida.antbe028.tetris;

public interface FallHandler {
    
    public boolean hasCollision(Poly poly, Board board);
    
    public boolean ifMethod(Poly poly, Board board, SquareType typeInPolyArray, int x, int y);
}
