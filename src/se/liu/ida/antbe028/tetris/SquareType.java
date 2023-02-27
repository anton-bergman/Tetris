package se.liu.ida.antbe028.tetris;

import java.awt.*;
import java.util.EnumMap;
import java.util.Random;

public enum SquareType
{
    OUTSIDE, EMPTY, I, O, T, S, Z, J, L;

    public static EnumMap<SquareType, Color> createColorMap(){
        EnumMap<SquareType, Color> squareColors = new EnumMap<>(SquareType.class);

        Color purple = new Color(128,0,128);

        for (SquareType element : SquareType.values()) {
            switch (element) {
                case I:
                    squareColors.put(I, Color.CYAN);
                    break;
                case O:
                    squareColors.put(O, Color.YELLOW);
                    break;
                case T:
                    squareColors.put(T, purple);
                    break;
                case S:
                    squareColors.put(S, Color.GREEN);
                    break;
                case Z:
                    squareColors.put(Z, Color.RED);
                    break;
                case J:
                    squareColors.put(J, Color.BLUE);
                    break;
                case L:
                    squareColors.put(L, Color.ORANGE);
                    break;
                case EMPTY:
                    squareColors.put(EMPTY, Color.BLACK);
                    break;
                case OUTSIDE:
                    break;
                default:
                    throw new IllegalArgumentException("Error: SquareType does not correspond to any Color!");

            }
        }

        return squareColors;
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        SquareType[] squareTypeArray = SquareType.values();
        for (int i = 0; i < 25; i++) {
            System.out.println("Random int: " + rnd.nextInt(100));
            System.out.println("Random SquareType: " + squareTypeArray[rnd.nextInt(8)]);
        }
    }
}
