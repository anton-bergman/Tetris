package se.liu.ida.antbe028.tetris;

public enum PowerUp {
    
    DEFAULT, FALLTHROUGH, HEAVY_POLY;

    public String toString() {
        switch (this) {
            case DEFAULT:
                return "None";
            case FALLTHROUGH:
                return "Fall Through";
            case HEAVY_POLY:
                return "Heavy block";
            default:
                throw new IllegalArgumentException("Error: Given power-up does not exist.");
        }
    }
}
