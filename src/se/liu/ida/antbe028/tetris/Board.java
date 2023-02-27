package se.liu.ida.antbe028.tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Board {
    
    private SquareType[][] squares;
    private int width;
    private int height;
    private final static Random RND = new Random();
    private Poly falling = null;
    private int xCoordFalling;
    private int yCoordFalling;

    private List<List<Integer>> coordFallingList = new ArrayList<>();

    private final static int MARGIN = 2;
    private final static int DOUBLE_MARGIN = 2 * MARGIN;

    private boolean gameOver = false;

    private int points = 0;
    private Map<Integer, Integer> pointMap;

    private List<BoardListener> boardListenerList = new ArrayList<>();
    private Tetrominomaker makeFalling = new Tetrominomaker();
    private FallHandler collisionHandler = new DefaultFallHandler();

    // Count for setting powerUps on falling
    private int numOfFalling = 0;


    public Board(final int width, final int height) {
        this.width = width;
        this.height = height;

        Map<Integer, Integer> pointMap = Map.of(1, 100, 2, 300, 3, 500, 4, 800);
        this.pointMap = pointMap;
        
        squares = new SquareType[height+DOUBLE_MARGIN][width+DOUBLE_MARGIN];

        for (int y = 0; y < height+DOUBLE_MARGIN; y++) {
            for (int x = 0; x < width+DOUBLE_MARGIN; x++) {

                if (y >= MARGIN && y < height+MARGIN) {
                    if (x < MARGIN || x >= width+MARGIN) {
                        squares[y][x] = SquareType.OUTSIDE;
                    }
                    else {
                        squares[y][x] = SquareType.EMPTY;
                    }
                }
                else {
                    Arrays.fill(squares[y], SquareType.OUTSIDE);
                }
            }
        }
    }

    public SquareType getSquares(int width, int height) {
        SquareType currentSquare = squares[height][width];
        return currentSquare;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMargin() {
        return MARGIN;
    }

    public int getWidthMargin() {
        return width + DOUBLE_MARGIN;
    }

    public int getHeightMargin() {
        return height + DOUBLE_MARGIN;
    }

    public int getPoints() {
        return points;
    }

    public boolean getGameOverStatus() {
        return gameOver;
    }

    public int getXFalling() {
        return xCoordFalling;
    }

    public int getYFalling() {
        return yCoordFalling;
    }

    public List<Integer> getCoordFalling() {
        List<Integer> newcoordFalling = Arrays.asList(xCoordFalling, yCoordFalling);
        return newcoordFalling;
    }

    public PowerUp getCurrentPowerUp() {
        if (falling == null) {
            return PowerUp.DEFAULT;
        }
        PowerUp powerUp = falling.getPowerUp();
        return powerUp;
    }

    public List<List<Integer>> getFallingList() {
        return coordFallingList;
    }


    public void moveDownColumn(int x, int row) {
        // Moves a given column down one step
        for (int y = row; y < height + MARGIN; y++) {
            if (getSquares(x, y) != SquareType.EMPTY &&
                getSquares(x, y+1) == SquareType.EMPTY) {
                // If position in column is not empty and the position below
                // in the column is empty
                for (int newY = y+1; newY > row; newY--) {
                    squares[newY][x] = squares[newY-1][x];
                    squares[newY-1][x] = SquareType.EMPTY;
                }
                break;
            }
        }
    }


    public int findMiddleBoard() {
        // Finds the middle of the board
        if (getWidthMargin() % 2 == 0) {
            return getWidthMargin()/2 - 1;
        }
        else {
            return getWidthMargin()/2;
        }
    }

    
    public void setCollisionHandler() {
        // Sets the collision-handler depending on the powerup of the falling poly
        PowerUp powerUp = falling.getPowerUp();
        switch (powerUp) {
            case DEFAULT:
                collisionHandler = new DefaultFallHandler();
                break;
            case FALLTHROUGH:
                collisionHandler = new Fallthrough();
                break;
            case HEAVY_POLY:
                collisionHandler = new HeavyPoly();
                break;
            default:
                throw new IllegalArgumentException("Error: Given power-up does not have a valid collision handler.");
        }
    }


    public void setFalling(Poly falling) {
        // Places falling in the middle of the board.
        this.falling = falling;
        int xMiddlePoly = findMiddleBoard() - falling.findMiddlePoly();
        this.yCoordFalling = MARGIN;
        this.xCoordFalling = xMiddlePoly;
    }

    public void moveFalling(Poly falling) {
        // Moves falling one step down.
        this.falling = falling;
        this.yCoordFalling +=1;
    }

    public void moveLeftOrRight(Direction direction) {
        // Moves falling left or right
        if (direction == Direction.LEFT && falling != null) {
            this.xCoordFalling -= 1;
            if (collisionHandler.hasCollision(falling, this)) {
                this.xCoordFalling += 1;
            }
        }
        else if (direction == Direction.RIGHT && falling != null){
            this.xCoordFalling += 1;
            if (collisionHandler.hasCollision(falling, this)) {
                this.xCoordFalling -= 1;
            }
        }
        notifyListeners();
    }


    public boolean tryRotate(Direction direction) {
        // Tries to rotate a copy of the falling poly and looks for collisions
        if (direction == Direction.LEFT && falling != null) {
            Poly copyFalling = falling.rotateLeft();
            return !collisionHandler.hasCollision(copyFalling, this);
        }
        else if (direction == Direction.RIGHT && falling != null) {
            Poly copyFalling = falling.rotateRight();
            return !collisionHandler.hasCollision(copyFalling, this);
        }
        // Code will never get here
        return false;
    }

    public void rotateDirection(Direction direction) {
        // Rotates the falling poly in a given direction
        if (tryRotate(direction) && direction == Direction.LEFT) {
            falling = falling.rotateLeft();
        }
        else if (tryRotate(direction) && direction == Direction.RIGHT) {
            falling = falling.rotateRight();
        }
        notifyListeners();
    }


    public boolean hasMovedStraightDown() {
        boolean movedStraightDown = false;

        if (coordFallingList.size() == 2) {
            int currentXfalling = coordFallingList.get(1).get(0);
            int oldXfalling = coordFallingList.get(0).get(0);

            if (currentXfalling == oldXfalling) {
                movedStraightDown = true;
            }
        }
        return movedStraightDown;
    }


    public void calculatePoints(int numOfFullRows) {
        // Calculates the ammount of points that should be added
        if (1 <= numOfFullRows && numOfFullRows <= 4) {
            int newPoints = pointMap.get(numOfFullRows);
            points += newPoints;
        }

        else if (numOfFullRows > 4) {
            int newPoints = pointMap.get(4);
            points += newPoints;
        }
    }


    public boolean thisRowFull(int y) {
        // Checks if a specific row on the board is full
        boolean rowIsFull = true;
        for (int x = MARGIN; x < width+MARGIN; x++) {
            SquareType block = getSquares(x, y);
            if (block == SquareType.EMPTY) {
                rowIsFull = false;
            }
        }
        return rowIsFull;
    }

    public boolean rowIsEmpty(int y) {
        // Checks if a specific row on the board is empty
        boolean rowIsEmpty = true;
        for (int x = MARGIN; x < width + MARGIN; x++) {
            SquareType block = getSquares(x, y);
            if (block != SquareType.EMPTY) {
                rowIsEmpty = false;
            }
        }
        return rowIsEmpty;
    }

    public void moveDownRowsAbove(int column) {
        // Takes the index of a column and moves everything above down one step
        for (int y = column; y >= MARGIN; y--) {
            int indexColumnAbove = y - 1;
            if (rowIsEmpty(indexColumnAbove)) {
                for (int x = MARGIN; x < width+MARGIN; x++) {
                    squares[y][x] = squares[y-1][x];
                }
                break;
            }
            for (int x = MARGIN; x < width+MARGIN; x++) {
                squares[y][x] = squares[y-1][x];
            }
        }
    }


    public void removeRow() {
        // Removes all rows in the board that are full
        int numOfFullRows = 0;

        for (int y = MARGIN; y <= height-1+MARGIN; y++) {
            if (thisRowFull(y)) {
                moveDownRowsAbove(y);
                numOfFullRows += 1;

            }
        }
        calculatePoints(numOfFullRows);
    }

    public SquareType getSquareAt(int x, int y) {
        // Returnes the SquareType on a given square (includes falling)

        if (falling == null) {
            return squares[y][x];
        }

        // Calculates the last x and y coordinate of falling in board
        int lastXcoordFalling = falling.getLengthPolyArray()-1 + xCoordFalling;
        int lastYcoordFalling = falling.getLengthPolyArray()-1 + yCoordFalling;

        // Calculates the x and y coordinate inside the falling polyarray
        int xCoordInFalling = x - xCoordFalling;
        int yCoordInFalling = y - yCoordFalling;

        if ((x >= xCoordFalling && x <= lastXcoordFalling) &&
            (y >= yCoordFalling && y <= lastYcoordFalling) &&
            falling.getSquareTypeAt(xCoordInFalling, yCoordInFalling) != SquareType.EMPTY) {
            SquareType squareTypeInFalling = falling.getSquareTypeAt(xCoordInFalling, yCoordInFalling);
            /* If x and y is inside the span of fallings coordinates
            and the SquareType in the falling polyarray isn't empty, return the SquareType */
            return squareTypeInFalling;

        }

        // Else return the value that squares hold at the input coordinates
        return squares[y][x];
    }

    public void randomizeSquaresArray() {
        // Generates a random SquareType at every position in the board
        SquareType[] squareTypeValues = SquareType.values();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                squares[y][x] = squareTypeValues[RND.nextInt(squareTypeValues.length)];
            }
        }
        notifyListeners();
    }

    public void addBoardChanged(BoardListener bl) {
        // Adds a BoardListner to the list of BoardListners
        boardListenerList.add(bl);
    }

    private void notifyListeners() {
        // Notifies every BoardListner in the list of BoardListners
        for (BoardListener listner : boardListenerList) {
            listner.boardChanged();
        }
    }

    public void placePolyOnBoard(Poly poly, int xCoordPoly, int yCoordPoly) {
        //Places a Poly on the board
        int lenPoly = poly.getLengthPolyArray();
        for (int y = 0; y < lenPoly; y++) {
            for (int x = 0; x < lenPoly; x++) {
                if (getSquareAt(xCoordPoly+x, yCoordFalling+y) != SquareType.EMPTY &&
                    getSquareAt(xCoordPoly+x, yCoordFalling+y) != SquareType.OUTSIDE) {
                    squares[yCoordPoly + y][xCoordPoly + x] = getSquareAt(xCoordPoly + x, yCoordPoly + y);
                }
            }
        }
    }

    public void activateFallingPowerUps() {
        // Decides when to activate fallings powerUps, checks if they're
        // activated and activates them

        numOfFalling += 1;
        if (numOfFalling % 3 == 0) {
            // Every third falling that has been created
            //falling.setPowerUp(PowerUp.FALLTHROUGH);
            if (numOfFalling % 2 == 0) {
                falling.setPowerUp(PowerUp.HEAVY_POLY);
            }
            else {
                falling.setPowerUp(PowerUp.FALLTHROUGH);
            }
        }
        else {
            falling.setPowerUp(PowerUp.DEFAULT);
        }
        setCollisionHandler();
    }

    public void updateCoordFallingList() {
        // Update coordfallinglist so that att any given time it contains two
        // pairs of falling coords, the current and the previous coords.
        coordFallingList = getFallingList();
		coordFallingList.add(getCoordFalling());
        if (coordFallingList.size() >= 3) {
            // At any given time, there are two coord-pairs in the list
            // (the current and the previous coord-pairs)
            coordFallingList.remove(0);
		}
    }

    public void clearBoard() {
        // Clears the board and resets the score
        for (int y = MARGIN; y < height+MARGIN; y++) {
            for (int x = MARGIN; x < width+MARGIN; x++) {
                squares[y][x] = SquareType.EMPTY;
            }
        }
        points = 0;
        gameOver = false;
        numOfFalling = 0;
    }

    public void tick() {

        updateCoordFallingList();

        if (gameOver) {
            System.out.println("Game Over!");
        }

        if (falling != null) {
            // If there's a falling, move it down one step
            moveFalling(falling);

            if (collisionHandler.hasCollision(falling, this)) {
                // If falling has a collision, move the block back and place it on the board.
                yCoordFalling -= 1;
                placePolyOnBoard(falling, xCoordFalling, yCoordFalling);
                falling = null;
                removeRow();
            }
        }

        else {
            // If there's no falling, create a new one
            int randomSquareTypeIndex = RND.nextInt(makeFalling.getNumberOfTypes());
            falling = makeFalling.getPoly(randomSquareTypeIndex);

            setFalling(falling);

            activateFallingPowerUps();

            if (collisionHandler.hasCollision(falling, this)) {
                // If the creation led to a collision it means it's GAME OVER
                gameOver = true;
                this.falling = null;
            }

        }
        notifyListeners();
    }
}
