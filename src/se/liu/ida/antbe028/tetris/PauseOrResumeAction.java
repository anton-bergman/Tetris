package se.liu.ida.antbe028.tetris;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class PauseOrResumeAction extends AbstractAction {
    private GameTimer clockTimer;
    private boolean pressedPause;

    public PauseOrResumeAction(GameTimer clockTimer, boolean pressedPause) {
        this.pressedPause = pressedPause;
        this.clockTimer = clockTimer;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (pressedPause) {
            // If the pause-button was pressed
            clockTimer.pauseTimer();
        }
        else {
            // Else means that the resume-button was pressed
            clockTimer.resumeTimer();
        }
        
    }

    @Override public final PauseOrResumeAction clone() throws CloneNotSupportedException {
	return (PauseOrResumeAction) super.clone();
    }
}
