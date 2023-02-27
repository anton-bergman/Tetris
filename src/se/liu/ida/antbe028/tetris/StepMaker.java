package se.liu.ida.antbe028.tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StepMaker extends AbstractAction {
    private Board board;

    public StepMaker(final Board board) {
        this.board = board;
    }

    public void actionPerformed(ActionEvent e) {
	    // Move one step in the game!
        board.tick();
    }

    @Override public final StepMaker clone() throws CloneNotSupportedException {
	return (StepMaker) super.clone();
    }
}



