package se.liu.ida.antbe028.tetris;

public class HighScore {

    private String name = "Unknown";
    private int points = 0;
    
    public HighScore() {}

    public void setNameAndPoints(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

}
