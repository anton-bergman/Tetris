package se.liu.ida.antbe028.tetris;

public class Run {
    public static void main(String[] args) {
        Board gameBoard = new Board(10, 20);

        // BoardToTextConverter converter = new BoardToTextConverter();

        TetrisViewer testViewer = new TetrisViewer(gameBoard);
        testViewer.showStartWindow();
    }
}
