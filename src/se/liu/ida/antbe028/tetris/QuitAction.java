package se.liu.ida.antbe028.tetris;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

public class QuitAction extends AbstractAction {
	private final int exitCode;
	private JFrame frame;
	private GameTimer clockTimer;

	public QuitAction(JFrame frame, final int exitCode, GameTimer clockTimer) {
		this.exitCode = exitCode;
		this.frame = frame;
		this.clockTimer = clockTimer;
	}

	@Override public void actionPerformed(final ActionEvent e) {

		if (clockTimer == null) {
			frame.dispose();
			System.exit(exitCode);
		}
		else {

			// If the pop-up is shown, pause the game
			PauseOrResumeAction pause = new PauseOrResumeAction(clockTimer, true);
			pause.actionPerformed(e);

			Object[] options = {"No", "Yes"};
			int optionChosen = JOptionPane.showOptionDialog(
				frame,"Are you sure you want to Quit?",
					null, JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, null, options, null);

			if (optionChosen == 1) {
				// Yes alternative was pressed
				frame.dispose();
				System.exit(exitCode);
			}
			else {
				// Resumes the game
				PauseOrResumeAction resume = new PauseOrResumeAction(clockTimer, false);
				resume.actionPerformed(e);
			}
		}
	}

    @Override public final QuitAction clone() throws CloneNotSupportedException {
	return (QuitAction) super.clone();
    }
}
