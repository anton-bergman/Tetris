package se.liu.ida.antbe028.tetris;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ImageComponent extends JComponent implements BoardListener {

    private JFrame frame;
    private TetrisViewer viewer;
    private JLabel label = new JLabel();
    private ImageIcon icon = null;
    private Board board;
    private HighScoreList highScoreList;

    private final static boolean RESTARTGAME_BOOL = false;
    private final static boolean STARTGAME_BOOL = true;

    private final static String START_GAME = "start_game";
    private boolean actionNeverBeenUsed = true;

    private boolean hasEnteredPlayerName = false;
    private String playerName = null;
    private int points;

    public ImageComponent(JFrame frame, Board board, TetrisViewer viewer) {
        this.frame = frame;
        this.board = board;
        this.viewer = viewer;

    }

    public void keyMapping(boolean startOrResumeGame) {
        // Map the start/resume/restart action to the space key and calls on start or
        // restart depending on the boolean input, startgame: True, restartgame: false
        JComponent pane = frame.getRootPane();

        final InputMap in = pane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        in.put(KeyStroke.getKeyStroke("SPACE"), START_GAME);

        final ActionMap act = pane.getActionMap();
        act.put(START_GAME, new StartOrRestartGameAction(startOrResumeGame));
    }

    public void showStartImage() {
        // Displays the start image
        actionNeverBeenUsed = true;
        String path = "resources/images/tetris_startimage.jpg";
        ImageIcon icon = new ImageIcon(path);
        label.setIcon(icon);
        frame.add(label);

        keyMapping(STARTGAME_BOOL);

        // Creates the highscore-list
        HighScoreList highScoreList = new HighScoreList();
        this.highScoreList = highScoreList;

        // Tries to read from the highscore-list
        while (true) {
            try {
                highScoreList.readFromHighScoreList();
                break;
            } catch (IOException ignored) {
                String errorMessage = "Error: Could not read from highscore file.";

                Object[] options = { "Cancel", "Retry" };
                int optionChosen = JOptionPane.showOptionDialog(
                        frame, errorMessage, "Error Message", JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE, null, options, options[1]);

                if (optionChosen != 1) {
                    // If user did not press "Retry"
                    break;
                }
            }
        }

    }

    public void showGameOverImage() {
        // Displays the game-over image
        actionNeverBeenUsed = true;
        String path = "resources/images/tetris_gameoverimage.jpg";
        ImageIcon icon = new ImageIcon(path);
        label.setIcon(icon);

        keyMapping(RESTARTGAME_BOOL);
    }

    public void boardChanged() {
        // If the gamestatus game-over is true, show game-over image

        if (board.getGameOverStatus()) {
            showGameOverImage();
            if (!hasEnteredPlayerName) {
                // Get the player name
                String input = JOptionPane.showInputDialog("Enter player name:");
                playerName = input;
                HighScore score = new HighScore();
                score.setNameAndPoints(playerName, board.getPoints());

                // Try to add score to the highscore list
                while (true) {
                    try {
                        highScoreList.addScore(score);
                        break;
                    } catch (IOException ignored) {
                        String errorMessage = "Error: Could not write highscore to textfile.";
                        Object[] options = { "Cancel", "Retry" };
                        int optionChosen = JOptionPane.showOptionDialog(
                                frame, errorMessage, "Error Message", JOptionPane.YES_NO_OPTION,
                                JOptionPane.ERROR_MESSAGE, null, options, options[1]);

                        if (optionChosen != 1) {
                            // If user did not press "Retry"
                            break;
                        }
                    }
                }

                hasEnteredPlayerName = true;

                // Draws out the Highscore-list
                JOptionPane.showMessageDialog(frame,
                        highScoreList.drawHighScoreList(), "Highscore List", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public void paintComponent(final Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        icon.paintIcon(this, g, 0, 0);
    }

    private class StartOrRestartGameAction extends AbstractAction {
        private boolean startOrRestartGame;

        public StartOrRestartGameAction(boolean startOrRestartGame) {
            this.startOrRestartGame = startOrRestartGame;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            // This action can only run once
            if (actionNeverBeenUsed) {
                if (startOrRestartGame) {
                    // Remove start image
                    label.setIcon(null);
                    // Run the game
                    viewer.show(frame);
                }

                else {
                    // Remove Game over image
                    label.setIcon(null);
                    // Restart the game
                    viewer.restartGame();
                    // When game is restarted enter a new playername
                    hasEnteredPlayerName = false;
                }
                actionNeverBeenUsed = false;
            }
        }

        @Override
        public final StartOrRestartGameAction clone() throws CloneNotSupportedException {
            return (StartOrRestartGameAction) super.clone();
        }
    }
}
