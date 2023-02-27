package se.liu.ida.antbe028.tetris;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class HighScoreList {
    private List<HighScore> highScoreList = new ArrayList<>();

    private final static int SHOW_LENGTH_OF_SCORE_LIST = 10;

    public HighScoreList() {
    }

    public void writeToFile(String filePath) throws IOException {
        // Writes a json-string to a text file
        File file = new File(filePath);

        PrintWriter writer = new PrintWriter(new FileWriter(file));
        String listAsJson = convertToGson();
        writer.print(listAsJson);
        writer.close();
    }

    public void safeWritingTofile() throws IOException {
        // Writes a json-string to a text file in a safe manner

        String filePath = "HighScoreList.txt";
        File file = new File(filePath);

        String tempFilePath = "TempList.txt";

        File tempFile = new File(tempFilePath);
        tempFile.createNewFile();

        try {
            writeToFile(tempFilePath);
        } catch (IOException ignored) {
            throw new IOException("Error: Could not write to temporary Highscorelist.");
        }

        file.delete();
        tempFile.renameTo(file);
    }

    public String convertToGson() {
        // Converts a highscorelist-object to a json-string
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String listAsJson = gson.toJson(this);

        return listAsJson;
    }

    public HighScoreList convertBackToGson(String listAsJson) {
        // Converts a json-string back to a highscorelist-object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HighScoreList list = gson.fromJson(listAsJson, HighScoreList.class);
        return list;
    }

    public HighScoreList readFromFile() throws IOException, FileNotFoundException {
        // Reads from the High-score list text file.

        String filePath = "HighScoreList.txt";
        File file = new File(filePath);

        Scanner reader = new Scanner(file);
        StringBuilder builder = new StringBuilder();

        while (reader.hasNextLine()) {
            String dataInFile = reader.nextLine();
            builder.append(dataInFile);
            builder.append("\n");
        }
        reader.close();

        String listAsJson = builder.toString();
        HighScoreList list = convertBackToGson(listAsJson);
        return list;
    }

    public void readFromHighScoreList() throws IOException, FileNotFoundException {
        // Reads the information from an existing text file or creates a new one

        String filePath = "HighScoreList.txt";
        File file = new File(filePath);

        if (!file.exists()) {
            // Create the file
            file.createNewFile();
        }

        else {
            // Read from the file
            HighScoreList highScoreListObj = readFromFile();
            if (highScoreListObj != null) {
                List<HighScore> list = highScoreListObj.getHighScoreList();

                for (HighScore score : list) {
                    this.addScore(score);
                }
            }
        }
    }

    public List<HighScore> getHighScoreList() {
        return highScoreList;
    }

    public void addScore(HighScore score) throws IOException {
        // Adds and sorts the score into the highscorelist-object
        highScoreList.add(score);
        sortHighScoreList();
        safeWritingTofile();
    }

    public void highScoreListToString() {
        for (HighScore score : highScoreList) {
            System.out.println("Name: " + score.getName() + ", Score: " + score.getPoints());
        }
    }

    public void sortHighScoreList() {
        // Sorts the highscorelist in descending order
        highScoreList.sort(new ScoreComparator());
    }

    public String drawHighScoreList() {
        // Creates a string with the 10 highest scores
        StringBuilder builder = new StringBuilder();

        String titleString = String.format("%-20s%-20s\n", "Name:", "Score:");
        builder.append(titleString);

        for (int index = 0; index < highScoreList.size(); index++) {
            if (index < SHOW_LENGTH_OF_SCORE_LIST) {

                HighScore score = highScoreList.get(index);
                int points = score.getPoints();
                String name = score.getName();

                String nameScoreString = String.format("%-20s%-20s\n", name, points);
                builder.append(nameScoreString);
            }
        }
        String result = builder.toString();
        return result;
    }

}
