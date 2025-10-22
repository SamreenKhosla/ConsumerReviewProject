public class ReviewRunner {
    public static void main(String[] args) {
        double total = Review.totalSentiment("LabubuReview.txt");
        System.out.println("Total Sentiment Value for Labubu Reviews: " + total);
    }
}
