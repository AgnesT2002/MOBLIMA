import java.io.*;
/**
 * @author Agnes
 * @version 1.0
 * 2022-11-08
 */
public class Movie {
    // public enum showStatus {COMINGSOON, PREVIEW, SHOWING, ENDSHOW};

    protected String movieTitle;
    protected String Synopsis;
    protected String Director;
    protected String Casts;
    protected double Duration;
    // private showStatus Status;
    protected String Status;
    protected String Type;
    protected double totalSales;
    protected double avgRating;
    protected String Reviews;
    protected String Ratings;

    public Movie(String movieTitle, String Synopsis, String Director, String Casts,
            double Duration, String Status, String Type, double totalSales, double avgRating, String Reviews,
            String Ratings) {
        this.movieTitle = movieTitle;
        this.Synopsis = Synopsis;
        this.Director = Director;
        this.Casts = Casts;
        this.Duration = Duration;
        this.Status = Status;
        this.Type = Type;
        this.totalSales = totalSales;
        this.avgRating = avgRating;
        this.Reviews = Reviews;
        this.Ratings = Ratings;
    }

    /**
     * @return String
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * @return String
     */
    public String getSynopsis() {
        return Synopsis;
    }

    /**
     * @return String
     */
    public String getDirector() {
        return Director;
    }

    /**
     * @return String
     */
    public String getCasts() {
        return Casts;
    }

    /**
     * @return String
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @return String
     */
    public String getType() {
        return Type;
    }

    /**
     * @return double
     */
    public double getDuration() {
        return Duration;
    }

    /**
     * @return double
     */
    public double gettotalSales() {
        return totalSales;
    }

    /**
     * @return String
     */
    public String getReviews() {
        return Reviews;
    }

    /**
     * @return String
     */
    public String getRatings() {
        return Ratings;
    }

    /**
     * @return double
     */
    public double getavgRating() {
        avgRating = calculateAvgRating(Ratings);
        return avgRating;
    }

    /**
     * @param numberList
     * @return double
     */
    public double calculateAvgRating(String numberList) {
        // System.out.println(numberList);
        int n = numberList.length();
        // System.out.println("in cal, n: " + n);
        if (n <= 1) {
            return -1;
        }

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Character.getNumericValue(numberList.charAt(i));
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }

        return Math.round((sum / n) * 10.0) / 10.0;
    }

    /**
     * @throws IOException
     */
    public void Create() throws IOException {
        FileWriter fstream = new FileWriter("MovieList.txt", true);
        BufferedWriter out = new BufferedWriter(fstream);

        String append = movieTitle + "|" + Synopsis + "|" + Director + "|" + Casts + "|" + Status + "|" +
                Type + "|" + Duration + "|" + totalSales + "|" + avgRating + "|" + Reviews + "|" + Ratings;
        out.write(append);
        out.newLine();

        // close buffer writer
        out.close();

        System.out.println("Added new movie");
    }

    public void display() {
        System.out.println(movieTitle + " " + Status + "\n"
                + "Running Time: " + Duration + "\n"
                + "Type: " + Type + "\n"
                + "Synopsis: " + Synopsis + "\n"
                + "Main Cast: " + Casts + "\n"
                + "Reviews: " + Reviews + "\n"
                + "Ratings: " + Ratings + "\n");
    }
}
