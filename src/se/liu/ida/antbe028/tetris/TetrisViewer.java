package se.liu.ida.antbe028.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class TetrisViewer {

    private Board board;
    private GameTimer gameTimer = null;
    private JFrame frame = null;

    public TetrisViewer(final Board board) {
        this.board = board;
    }

    public double calcAspectRatio(Board board) {
        // Calculates the aspectratio of the board
        int height = board.getHeight();
        int width = board.getWidth();

        int aspectRatio = height / width;

        return aspectRatio;
    }

    public void showStartWindow() {
        // Creates the start-window
        JFrame startFrame = new JFrame("TETRIS");
        this.frame = startFrame;

        // Creates the TetrisComponent and the StartImageComponent
        TetrisComponent comp = new TetrisComponent(startFrame, board);
        ImageComponent startComp = new ImageComponent(startFrame, board, this);
        board.addBoardChanged(startComp);

        // Displays the start image
        startComp.showStartImage();

        startFrame.setLayout(new GridLayout(1, 1));

        ComponentListener compListener = new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Only makes it possible to resize the window in the given
                // start aspect ratio

                // Window has an aspect ratio of 1:2 + 20 pixels from the Menubar
                double aspectRatio = calcAspectRatio(board);
                Rectangle b = e.getComponent().getBounds();
                int aspectRatioWidth = (int) Math.round(aspectRatio * b.width + MenuBar.getHeight());
                e.getComponent().setBounds(b.x, b.y, b.width, aspectRatioWidth);

            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        };

        startFrame.addComponentListener(compListener);

        // Same size as the game window
        startFrame.setSize(comp.getPreferredSize());
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startFrame.setVisible(true);
    }

    public void restartGame() {
        // Restarts the game after GAME-OVER
        gameTimer.restartTimer();
        board.clearBoard();
        frame.setVisible(true);

    }

    public void show(JFrame frame) {
        // Creates a timer
        GameTimer gameTimer = new GameTimer(board);
        this.gameTimer = gameTimer;

        TetrisComponent comp = new TetrisComponent(frame, board);
        board.addBoardChanged(comp);

        // Creates a new layout for the frame
        frame.setLayout(new BorderLayout());

        // Adds the menu to the frame, must be created after comp
        MenuBar menuComp = new MenuBar(frame, board, gameTimer);
        board.addBoardChanged(menuComp);

        // Add the TetrisCompontet object and the frame layout to the frame
        frame.add(comp, BorderLayout.CENTER);
        frame.setSize(comp.getPreferredSize());

        // Shows the frame
        frame.setVisible(true);

        // startTimer();
        gameTimer.startTimer();
    }

}
