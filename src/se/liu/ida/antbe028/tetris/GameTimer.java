package se.liu.ida.antbe028.tetris;

import javax.swing.*;

public class GameTimer {

    private Timer mainTimer;
    private Timer clockTimer;
    private final static int MINIMUM_DELAY = 200;
    private final static int START_DELAY = 500;
    private final static int TIME_BETWEEN_DELAY_CHANGING = 30000;
    private final static int REDUCE_DELAY = 30;
    
    public GameTimer(Board board) {
        
        Action doOneStep = new StepMaker(board);
        Timer clockTimer = new Timer(START_DELAY, doOneStep);
        this.clockTimer = clockTimer;

        Action changeDelay = new ChangeDelay(clockTimer, REDUCE_DELAY);
        Timer mainTimer = new Timer(TIME_BETWEEN_DELAY_CHANGING, changeDelay);
        this.mainTimer = mainTimer;
    }

    public static int getMinimumDelay() {
        return MINIMUM_DELAY;
    }

    public void startTimer() {
        mainTimer.setCoalesce(true);
        mainTimer.start();
        clockTimer.setCoalesce(true);
        clockTimer.start();
    }

    public void pauseTimer() {
        mainTimer.stop();
        clockTimer.stop();
    }

    public void resumeTimer() {
        mainTimer.start();
        clockTimer.start();
    }

    public void restartTimer() {
        mainTimer.restart();
        clockTimer.restart();
    }

}
