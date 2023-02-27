package se.liu.ida.antbe028.tddd78.lab1;

import javax.swing.JOptionPane;

public class Exercise8 {
    public static void main(String[] args) {
        boolean run = true;

        while (run) {
            String question = "Har du grönt hår?";
            System.out.println(askUser(question));

            // Illustrering på skillnaden mellan '&&' och '&'.
            // '&' kommer alltid att köra båda askUser funktionerna.
            // '&&' kommer kör båda beroende på returnvärdet av första askUser.
            if (askUser("Quit?") && askUser("Really?")) {
                run = false;
            }
        }

    }

    public static boolean askUser(String question) {
        // Translates a yes or no input from user to true or false
        boolean result = JOptionPane.showConfirmDialog(null, question, "",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        return result;

    }
}
