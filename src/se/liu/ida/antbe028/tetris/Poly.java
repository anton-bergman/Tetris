package se.liu.ida.antbe028.tetris;


public class Poly
{
    private SquareType[][] polyArray;

    private PowerUp currentPowerUp = PowerUp.DEFAULT;

    public Poly(final SquareType[][] polyArray) {
        this.polyArray = polyArray;
    }

    public PowerUp getPowerUp() {
        return currentPowerUp;
    }

    public void setPowerUp(PowerUp powerUp) {
        currentPowerUp = powerUp;
    }
    
    public int getLengthPolyArray() {
        return polyArray.length;
    }

    public int findMiddlePoly() {
        // Finds the middle of a poly
        if (getLengthPolyArray() % 2 == 0) {
            return getLengthPolyArray()/2 - 1;
        }
        else {
            return getLengthPolyArray()/2;
        }
    }

    public SquareType getSquareTypeAt(int x, int y) {
        return polyArray[y][x];
    }

    public Poly rotateRight() {
        // Creates a new poly and rotates it 90 degrees to the right
        int size = this.getLengthPolyArray();
        Poly newPoly = new Poly(new SquareType[size][size]);
        newPoly.setPowerUp(this.getPowerUp());

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++){
                newPoly.polyArray[c][size-1-r] = this.polyArray[r][c];
            }
        }
        return newPoly;
    }

    public Poly rotateLeft() {
        // Creates a new poly and rotates it 90 degrees to the left
        int size = this.getLengthPolyArray();
        Poly newPoly = new Poly(new SquareType[size][size]);
        newPoly.setPowerUp(this.getPowerUp());

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++){
                newPoly.polyArray[size-1-c][r] = this.polyArray[r][c];
            }
        }

        return newPoly;
    }
}
