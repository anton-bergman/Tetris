package se.liu.ida.antbe028.tetris;

public class HeavyPoly extends AbstractCollisionHandler {

    public boolean hasHolesInColumn(Board board, int x, int yStart) {
        // Returns true if the given column has holes any EMPTY holes in it
        // further down in the column
        boolean hasHoles = false;
        int yMax = board.getHeight() + board.getMargin();

        for (int y = yStart; y < yMax; y++) {
            if (board.getSquares(x, y) == SquareType.EMPTY) {
                hasHoles = true;
            }
        }
        return hasHoles;
    }
    
    public boolean canMoveDownFurther(Poly poly, Board board) {
        // Returns true if, for all possible overlapping squaretypes,
        // there are holes further down in the same column
        boolean canMoveDownFurther = true;

        int lenFalling = poly.getLengthPolyArray();
        int xFalling = board.getXFalling();
        int yFalling = board.getYFalling();

        for (int y = 0; y < lenFalling; y++) {
            for (int x = 0; x < lenFalling; x++) {
                SquareType typeInPolyArray = poly.getSquareTypeAt(x, y);
                
                if (board.hasMovedStraightDown() && typeInPolyArray != SquareType.EMPTY &&
                    board.getSquares(xFalling+x, yFalling+y) != SquareType.OUTSIDE &&
                    board.getSquares(xFalling+x, yFalling+y) != SquareType.EMPTY) {
                    
                    if (!hasHolesInColumn(board, xFalling+x, yFalling+y)) {
                        canMoveDownFurther = false;
                    }
                }
            }
        }
        return canMoveDownFurther;
    }

    @Override
    public boolean ifMethod(Poly poly, Board board, SquareType typeInPolyArray, int x, int y) {

        if (!board.hasMovedStraightDown()) {
            // If falling has not moved straight down
            if (typeInPolyArray != SquareType.EMPTY && 
                board.getSquares(x, y) != SquareType.EMPTY) {
                // If it then collides, obey normal collision rules
                return true;
            }
        }
        else {
            if (typeInPolyArray != SquareType.EMPTY && 
                board.getSquares(x, y) == SquareType.OUTSIDE) {
                // If typ in falling is not empty and type in squares is outside
                return true;
            }
            else if (typeInPolyArray != SquareType.EMPTY && 
                board.getSquares(x, y) != SquareType.OUTSIDE &&
                board.getSquares(x, y) != SquareType.EMPTY){
                // If falling collides with a squaretype other than OUTSIDE
                if (!canMoveDownFurther(poly, board)) {
                    // If it can't move down further it's a collision
                    return true;
                }
                else {
                    // Otherwise it should move down
                    board.moveDownColumn(x, y);
                }
            }
        }
        return false;
    }
}
