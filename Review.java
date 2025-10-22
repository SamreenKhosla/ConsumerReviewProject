import java.util.Scanner;
import java.io.File;
import java.util.HashMap;

/**
 * Class that contains helper methods for the Review Lab
 **/

public class Review {

    // stores sentiment values from cleanSentiment.csv
    private static HashMap<String, Double> sentiment = new HashMap<String, Double>();

    // static block: loads the sentiment values once
    static {
        try {
            Scanner input = new Scanner(new File("cleanSentiment.csv"));
            while (input.hasNextLine()) {
                String[] parts = input.nextLine().split(",");
                if (parts.length == 2) {
                    sentiment.put(parts[0].toLowerCase(), Double.parseDouble(parts[1]));
                }
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error loading sentiment file: " + e);
        }
    }

    // given method from starter file
    public static double sentimentVal(String word) {
        try {
            return sentiment.get(word.toLowerCase());
        } catch (Exception e) {
            return 0;
        }
    }

    // reads an entire file and returns its contents as one string
    public static String textToString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner input = new Scanner(new File(fileName));
            while (input.hasNextLine()) {
                sb.append(input.nextLine()).append(" ");
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e);
        }
        return sb.toString();
    }

    // calculates the total sentiment of all words in a review file
    public static double totalSentiment(String fileName) {
        String reviewText = textToString(fileName);
        String[] words = reviewText.split("[^a-zA-Z]+"); // split by non-letters
        double total = 0.0;

        // compound boolean: checks both word length and nonzero sentiment
        for (String word : words) {
            if (word.length() > 0 && sentimentVal(word) != 0) {
                total += sentimentVal(word);
            }
        }

        return total;
    }
}
