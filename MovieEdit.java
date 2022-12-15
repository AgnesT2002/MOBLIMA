import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.io.*;
/**
 * @author Agnes
 * @version 1.0
 * 2022-11-08
 */
public class MovieEdit extends Movie {

    public MovieEdit(String movieTitle, String Synopsis, String Director, String Casts, double Duration, String Status,
            String Type, double totalSales, double avgRating, String Reviews, String Ratings) {
        super(movieTitle, Synopsis, Director, Casts, Duration, Status, Type, totalSales, avgRating, Reviews, Ratings);
    }

    /**
     * @param title
     * @throws IOException
     */
    public void setMovieTitle(String title) throws IOException {
        // edit file first
        edit(title, 0);
        movieTitle = title;

        System.out.println("Movie title updated!");
    }

    /**
     * @param synop
     * @throws IOException
     */
    public void setSynopsis(String synop) throws IOException {
        // edit file first
        edit(synop, 1);
        Synopsis = synop;

        System.out.println("Synopsis updated!");
    }

    /**
     * @param dir
     * @throws IOException
     */
    public void setDirector(String dir) throws IOException {
        // edit file first
        edit(dir, 2);
        Director = dir;

        System.out.println("Director updated!");
    }

    /**
     * @param casts
     * @throws IOException
     */
    public void setCasts(String casts) throws IOException {
        // edit file first
        // String replace = casts.toString();
        edit(casts, 3);
        Casts = casts;

        System.out.println("Casts updated!");
    }

    /**
     * @param status
     * @throws IOException
     */
    public void setStatus(String status) throws IOException {
        // edit file first
        edit(status, 4);
        Status = status;

        System.out.println("Status updated!");
    }

    /**
     * @param type
     * @throws IOException
     */
    public void setType(String type) throws IOException {
        edit(type, 5);

        System.out.println("Type updated!");
    }

    /**
     * @param dur
     * @throws IOException
     */
    public void setDuration(double dur) throws IOException {
        // edit file first
        edit(Double.toString(dur), 6);
        Duration = dur;

        System.out.println("Duration updated!");
    }

    /**
     * @param TS
     * @throws IOException
     */
    public void settotalSales(double TS) throws IOException {
        // edit file first
        edit(Double.toString(TS), 7);
        totalSales = TS;

        System.out.println("Total Sales updated!");
    }

    /**
     * @param aR
     * @throws IOException
     */
    public void setavgRating(double aR) throws IOException {
        // edit file first
        edit(Double.toString(aR), 8);
        avgRating = aR;

        System.out.println("Average Rating updated!");
    }

    /**
     * @param Rev
     * @throws IOException
     */
    // for setReviews and setRatings the string input shld be all the previous
    // inputs, not just the new one
    // have to get old one, attach new one behind and replace the whole thing
    public void setReviews(String Rev) throws IOException {
        // String replace = Rev.toString(); //same prob as setCasts
        edit(Rev, 9);
        Reviews = Reviews + Rev;

        System.out.println("Reviews updated!");
    }

    /**
     * @param rat
     * @throws IOException
     */
    public void setRatings(String rat) throws IOException {
        // String replace = Rev.toString(); //same prob as setCasts
        edit(rat, 10);
        Ratings = Ratings + rat;

        System.out.println("Ratings updated!");
    }

    /**
     * @param replace
     * @param col
     * @throws IOException
     */
    ////////////////////////////// private methods for editing the data file
    ////////////////////////////// ///////////////////////////////

    private void edit(String replace, int col) throws IOException {
        String title, synopsis, director, duration, totalSales, avgRating, casts, reviews, status, type, ratings;

        String fileName = "MovieList.txt";
        Path path = Paths.get(fileName);
        int n = (int) Files.lines(path).count();

        String[] lines = new String[n];
        lines[0] = "movieTitle|Synopsis|Director|Casts|Status|Duration|totalSales|avgRating|Rating|Reviews";
        int count = 1;

        ArrayList stringArray = (ArrayList) read(fileName);
        for (int i = 1; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            StringTokenizer star = new StringTokenizer(st, "|");

            title = star.nextToken().trim();
            synopsis = star.nextToken().trim();
            director = star.nextToken().trim();
            casts = star.nextToken().trim();
            status = star.nextToken().trim();
            type = star.nextToken().trim();
            duration = star.nextToken().trim();
            totalSales = star.nextToken().trim();
            avgRating = star.nextToken().trim();
            reviews = star.nextToken().trim();
            ratings = star.nextToken().trim();

            String[] check = { title, synopsis, director, casts, status, type, duration, totalSales, avgRating, reviews,
                    ratings };
            if (movieTitle.compareTo(check[0]) == 0) {
                // for ratings and reviews we add behind the og
                if (col == 9) {
                    check[col] = check[col] + ", " + replace;
                } else if (col == 10) {
                    check[col] = check[col] + replace;
                } else {
                    check[col] = replace;
                }
            }
            lines[count] = String.join("|", check);
            count++;
        }

        // write updated content back into file
        // for (int i =0; i<count; i++) {System.out.println(lines[i] + "\n");}
        write(fileName, lines);
    }

    /**
     * @param fileName
     * @return List
     * @throws IOException
     */
    private static List read(String fileName) throws IOException {
        List data = new ArrayList();
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } finally {
            scanner.close();
        }
        return data;
    }

    /**
     * @param fileName
     * @param lines
     * @throws IOException
     */
    private static void write(String fileName, String[] lines) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));

        try {
            for (int i = 0; i < lines.length; i++) {
                out.println(lines[i]);
            }
        } finally {
            out.close();
        }
    }
}