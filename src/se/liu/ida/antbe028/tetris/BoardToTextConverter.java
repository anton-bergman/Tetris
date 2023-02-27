package se.liu.ida.antbe028.tetris;

public class BoardToTextConverter
{

    public String convertToText(Board board) {

        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < board.getHeightMargin(); y++) {
            for (int x = 0; x < board.getWidthMargin(); x++) {

		SquareType currentSquare = board.getSquareAt(x, y);

                switch (currentSquare) {
		    case EMPTY:
		        builder.append("#");
		        break;
		    case OUTSIDE:
		        builder.append("-");
		        break;
		    case I:
		        builder.append("I");
		        break;
		    case O:
				builder.append("O");
				break;
		    case T:
				builder.append("T");
				break;
		    case S:
				builder.append("S");
				break;
		    case Z:
				builder.append("Z");
				break;
		    case J:
				builder.append("J");
				break;
		    case L:
				builder.append("L");
				break;
		    default:
		        System.out.println("Something went wrong!");
		        break;
		}
		if (x == board.getWidthMargin()-1) {
		    builder.append("\n");
		}
	    }
	}
        String result = builder.toString();
        return result;
    }

}
