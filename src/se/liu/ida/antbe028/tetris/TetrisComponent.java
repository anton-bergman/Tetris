package se.liu.ida.antbe028.tetris;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.EnumMap;

public class TetrisComponent extends JComponent implements BoardListener
{
    private Board board;
    private EnumMap<SquareType, Color> SQUARE_COLORS_MAP = SquareType.createColorMap();
	private final static int START_SQUARE_SIZE = 30;

    private static final String MOVE_LEFT = "move_left";
    private static final String MOVE_RIGHT = "move_right";
    private static final String ROTATE_LEFT = "rotate_left";
    private static final String ROTATE_RIGHT = "rotate_right";
    private static final String QUIT = "quit";

    public TetrisComponent(final JFrame frame, final Board board) {
	this.board = board;

	JComponent pane = frame.getRootPane();

	final InputMap in = pane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	in.put(KeyStroke.getKeyStroke("LEFT"), MOVE_LEFT);
	in.put(KeyStroke.getKeyStroke("RIGHT"), MOVE_RIGHT);

	in.put(KeyStroke.getKeyStroke("UP"), ROTATE_RIGHT);
	in.put(KeyStroke.getKeyStroke("DOWN"), ROTATE_LEFT);

	in.put(KeyStroke.getKeyStroke("ESCAPE"), QUIT);

	final ActionMap act = pane.getActionMap();

	boolean isRotation = true;
	boolean notRotation = false;

	act.put(MOVE_LEFT, new MoveOrRotateAction(Direction.LEFT, notRotation));
	act.put(MOVE_RIGHT, new MoveOrRotateAction(Direction.RIGHT, notRotation));

	act.put(ROTATE_RIGHT, new MoveOrRotateAction(Direction.RIGHT, isRotation));
	act.put(ROTATE_LEFT, new MoveOrRotateAction(Direction.LEFT, isRotation));

	act.put(QUIT, new QuitAction(frame, 0, null));
    }

    public Dimension getPreferredSize() {
		// Calculates the dimensions depending on the start square size.
		int width = board.getWidth()*START_SQUARE_SIZE;
		int height = board.getHeight()*START_SQUARE_SIZE;
        Dimension dimension = new Dimension(width, height);
        return dimension;
	}

	public int getSquareSize() {
		// Calculates the square-size dependent on the window-size
		double xValueOfComp = this.getSize().getWidth();
		int squareSize = (int) (xValueOfComp) / board.getWidth();
		return squareSize;
	}


    public void boardChanged() {
		this.repaint();
    }

    @Override protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		final Graphics2D g2d = (Graphics2D) g;
		
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {

				if (board.getSquareAt(x+2, y+3) != SquareType.OUTSIDE) {
					SquareType currentSquare = board.getSquareAt(x+2, y+3);
					g2d.setColor(Color.BLACK);
					g2d.drawRect(x*getSquareSize(), y*getSquareSize(), getSquareSize(), getSquareSize());
					g2d.setColor(SQUARE_COLORS_MAP.get(currentSquare));
					g2d.fillRect(x*getSquareSize()+2, y*getSquareSize()+2, getSquareSize()-2, getSquareSize()-2);
				}
			}
		}
    }

    private class MoveOrRotateAction extends AbstractAction {
        private Direction direction;
        private boolean isRotation;

        public MoveOrRotateAction(Direction direction, boolean isRotation) {
            this.direction = direction;
            this.isRotation = isRotation;
		}

		@Override public void actionPerformed(final ActionEvent e) {
			if (isRotation) {
				board.rotateDirection(direction);
			}
			else {
				board.moveLeftOrRight(direction);
			}
		}

	@Override public final MoveOrRotateAction clone() throws CloneNotSupportedException {
	    return (MoveOrRotateAction) super.clone();
	}
    }
}