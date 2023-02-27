package se.liu.ida.antbe028.tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tetrominomaker
{

    public int getNumberOfTypes() {
	List<SquareType> squareTypeValues = new ArrayList<>(Arrays.asList(SquareType.values()));
	squareTypeValues.remove(SquareType.EMPTY);
	squareTypeValues.remove(SquareType.OUTSIDE);
	return squareTypeValues.size();
    }

    public Poly createPolyI() {

	SquareType[][] polyArray = new SquareType[4][4];

	Arrays.fill(polyArray[0], SquareType.EMPTY);
	Arrays.fill(polyArray[1], SquareType.I);
	Arrays.fill(polyArray[2], SquareType.EMPTY);
	Arrays.fill(polyArray[3], SquareType.EMPTY);

	Poly newPoly = new Poly(polyArray);
	return newPoly;
    }

    public Poly createPolyO() {
        SquareType[][] polyArray = new SquareType[2][2];

	Arrays.fill(polyArray[0], SquareType.O);
	Arrays.fill(polyArray[1], SquareType.O);

	Poly newPoly = new Poly(polyArray);
	return newPoly;
    }

    public Poly createPolyT() {
        SquareType[][] polyArray = new SquareType[3][3];

	Arrays.fill(polyArray[0], SquareType.EMPTY);
	polyArray[0][1] = SquareType.T;
	Arrays.fill(polyArray[1], SquareType.T);
	Arrays.fill(polyArray[2], SquareType.EMPTY);

	Poly newPoly = new Poly(polyArray);
	return newPoly;
    }

    public Poly createPolyS() {
        SquareType[][] polyArray = new SquareType[3][3];

	Arrays.fill(polyArray[0], SquareType.S);
	polyArray[0][0] = SquareType.EMPTY;
	Arrays.fill(polyArray[1], SquareType.S);
	polyArray[1][2] = SquareType.EMPTY;
	Arrays.fill(polyArray[2], SquareType.EMPTY);

	Poly newPoly = new Poly(polyArray);
	return newPoly;
    }

    public Poly createPolyZ() {
        SquareType[][] polyArray = new SquareType[3][3];

	Arrays.fill(polyArray[0], SquareType.Z);
	polyArray[0][2] = SquareType.EMPTY;
	Arrays.fill(polyArray[1], SquareType.Z);
	polyArray[1][0] = SquareType.EMPTY;
	Arrays.fill(polyArray[2], SquareType.EMPTY);

	Poly newPoly = new Poly(polyArray);
	return newPoly;
    }

    public Poly createPolyJ() {
        SquareType[][] polyArray = new SquareType[3][3];

	Arrays.fill(polyArray[0], SquareType.EMPTY);
	polyArray[0][0] = SquareType.J;
	Arrays.fill(polyArray[1], SquareType.J);
	Arrays.fill(polyArray[2], SquareType.EMPTY);

	Poly newPoly = new Poly(polyArray);
	return newPoly;
    }

    public Poly createPolyL() {
        SquareType[][] polyArray = new SquareType[3][3];

	Arrays.fill(polyArray[0], SquareType.EMPTY);
	polyArray[0][2] = SquareType.L;
	Arrays.fill(polyArray[1], SquareType.L);
	Arrays.fill(polyArray[2], SquareType.EMPTY);

	Poly newPoly = new Poly(polyArray);
	return newPoly;
    }

    public Poly getPoly(int n) {
	List<SquareType> squareTypeValues = new ArrayList<>(Arrays.asList(SquareType.values()));
	squareTypeValues.remove(SquareType.EMPTY);
	squareTypeValues.remove(SquareType.OUTSIDE);

        switch (squareTypeValues.get(n)) {
	    case I:
	        Poly newPolyI = createPolyI();
	        return newPolyI;
	    case O:
	        Poly newPolyO = createPolyO();
	        return newPolyO;
	    case T:
		Poly newPolyT = createPolyT();
		return newPolyT;
	    case S:
		Poly newPolyS = createPolyS();
		return newPolyS;
	    case Z:
		Poly newPolyZ = createPolyZ();
		return newPolyZ;
	    case J:
		Poly newPolyJ = createPolyJ();
		return newPolyJ;
	    case L:
		Poly newPolyL = createPolyL();
		return newPolyL;
	    default:
	        throw new IllegalArgumentException("Invalid index: " + n);

	}
    }

}
