package se.liu.ida.antbe028.tetris;

import javax.swing.*;
import java.awt.*;

public class TetrisViewerOld
{

    private Board gameBoard;

    public TetrisViewerOld(final Board gameBoard) {
	this.gameBoard = gameBoard;
    }

    public JTextArea show() {
	// Skapar en spelruta
        JFrame frame = new JFrame("TETRIS");
        // Skapar en txt-ruta och storleken på rutan
        JTextArea textArea = new JTextArea(gameBoard.getHeight()*2, gameBoard.getWidth()*4);

        // Converterar spelbrädet från en två-dim array av squareTypes, till strings och sen applicerar strängen på textarean
        BoardToTextConverter converter = new BoardToTextConverter();
        textArea.setText(converter.convertToText(gameBoard));

        // Skapar en ny layout och lägger till textarean i mitten på layouten
        frame.setLayout(new BorderLayout());
        frame.add(textArea, BorderLayout.CENTER);

        // Sätter fonten
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));

        //Utför fönstret
        frame.pack();
        //Visar fönstret
	frame.setVisible(true);

	return textArea;

    }

}
