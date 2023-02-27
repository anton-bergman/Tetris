package se.liu.ida.antbe028.tetris;

import javax.swing.*;

import java.awt.event.ActionEvent;

public class ChangeDelay extends AbstractAction{

    private Timer clockTimer;
    private int reduceDelayWith;

    public ChangeDelay(Timer clockTimer, int reduceDelayWith) {
        this.clockTimer = clockTimer;
        this.reduceDelayWith = reduceDelayWith;
    }

    public void actionPerformed(ActionEvent e) {
        int oldDelay = clockTimer.getDelay();
        int newDelay = oldDelay-reduceDelayWith;

        if (GameTimer.getMinimumDelay() <= newDelay) {
            clockTimer.setDelay(newDelay);
        }
    }

    @Override public final ChangeDelay clone() throws CloneNotSupportedException {
	return (ChangeDelay) super.clone();
    }
}
