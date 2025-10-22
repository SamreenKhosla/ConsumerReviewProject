public class ReviewRunner {
    public static void main(String[] args) {
        double total1 = Review.totalSentiment("LabubuReview.txt");
        double total2 = Review.totalSentiment("LabubuReview2.txt");

        System.out.println("Walmart: " + total1);
        System.out.println("Amazon: " + total2);
    }
}
