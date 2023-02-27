package se.liu.ida.antbe028.tetris;

import java.awt.*;

import javax.swing.*;

public class MenuBar implements BoardListener {

    private static final int MENUBAR_HEIGHT = 20;
    private static final int MENUBAR_WIDTH = 70; // Width of the settingsbutton

    private static final String POWER_UP_TEXT = "  Power-Up: ";
    private static final String SCORE_TEXT = "Score: ";

    private Board board;
    private JLabel score;
    private JLabel powerUp;

    public MenuBar(JFrame frame, Board board, GameTimer clockTimer) {
        this.board = board;
        final JMenuBar bar = new JMenuBar();

        final JMenu file = new JMenu("Settings");

        // Fields in the settings drop-down menu
        JMenuItem pause = new JMenuItem("Pause");
        JMenuItem resume = new JMenuItem("Resume");
        JMenuItem restart = new JMenuItem("Restart");

        JMenuItem quit = new JMenuItem("Quit");

        file.add(pause);
        file.add(resume);

        file.addSeparator(); 

        file.add(restart);

        file.addSeparator(); 

        file.add(quit);

        pause.addActionListener(new PauseOrResumeAction(clockTimer, true));
        resume.addActionListener(new PauseOrResumeAction(clockTimer, false));
        quit.addActionListener(new QuitAction(frame, 0, clockTimer));

        file.addSeparator();  // Separator

        file.setPreferredSize(new Dimension(MENUBAR_WIDTH, MENUBAR_HEIGHT));

        bar.add(file);

        // Adds the Power-Up field to the menubar
        PowerUp currentPowerUp = board.getCurrentPowerUp();
        JLabel powerUp = new JLabel(POWER_UP_TEXT + currentPowerUp);
        this.powerUp = powerUp;
        bar.add(powerUp);

        // Adds the score to the menubar
        int points = board.getPoints();
        String pointsText = points + "  ";
        String scoreField = SCORE_TEXT + pointsText;
        JLabel score = new JLabel(scoreField);
        this.score = score;
        bar.add(Box.createHorizontalGlue());
        bar.add(score);

        frame.setJMenuBar(bar);

    }

    public void boardChanged() {
        // Updates the powerUp-field and the score-field on the menubar
        // every time the board changes
        int points = board.getPoints();
        String pointsText = points + "  ";
        String scoreField = SCORE_TEXT + pointsText;
        score.setText(scoreField);

        PowerUp currentPowerUp = board.getCurrentPowerUp();
        powerUp.setText(POWER_UP_TEXT + currentPowerUp);
    }

    public static int getHeight() {
        return MENUBAR_HEIGHT;
    }

    public static int getWidth() {
        return MENUBAR_WIDTH;
    }
}
