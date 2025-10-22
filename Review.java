import java.util.Scanner;
import java.io.File;
import java.util.HashMap;


public class Review {

    private static HashMap<String, Double> sentiment = new HashMap<String, Double>();

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

    public static double sentimentVal(String word) {
        try {
            return sentiment.get(word.toLowerCase());
        } catch (Exception e) {
            return 0;
        }
    }

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

    public static double totalSentiment(String fileName) {
        String reviewText = textToString(fileName);
        String[] words = reviewText.split("[^a-zA-Z]+"); 
        double total = 0.0;

        for (String word : words) {
            if (word.length() > 0 && sentimentVal(word) != 0) {
                total += sentimentVal(word);
            }
        }

        return total;
    }
}
