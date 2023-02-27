package se.liu.ida.antbe028.tetris;

import java.util.Comparator;

public class ScoreComparator implements Comparator<HighScore> {

    @Override
    public int compare(HighScore o1, HighScore o2) {
        // TODO Auto-generated method stub
	return Integer.compare(o2.getPoints(), o1.getPoints());
    }
    
}
