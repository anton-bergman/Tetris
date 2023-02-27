package se.liu.ida.antbe028.tetris;

public abstract class AbstractCollisionHandler implements FallHandler {

    public boolean hasCollision(Poly poly, Board board) {
        // Checks if falling has collided with anything

        int lenFalling = poly.getLengthPolyArray();
        int xFalling = board.getXFalling();
        int yFalling = board.getYFalling();

        for (int y = 0; y < lenFalling; y++) {
            for (int x = 0; x < lenFalling; x++) {
                SquareType typeInPolyArray = poly.getSquareTypeAt(x, y);
                
                boolean isCollision = ifMethod(poly, board, typeInPolyArray, x+xFalling, y+yFalling);
                if (isCollision) {
                    return true;
                }
            }
        }
        return false;
    }
}
