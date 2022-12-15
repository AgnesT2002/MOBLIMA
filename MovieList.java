import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.io.*;
/**
 * @author Agnes
 * @version 1.0
 * 2022-11-08
 */
public class MovieList {

    private Movie[] movies;
    private int n;

    public MovieList() throws IOException {
        // read the file and create an array of movie objects
        String fileName = "MovieList.txt";
        // String fileName = "MovieList.txt";
        Path path = Paths.get(fileName);
        n = (int) Files.lines(path).count() - 1;
        movies = new Movie[n]; // minus header
        // ArrayList<Movie> movies = new ArrayList<Movie>();

        Scanner read = new Scanner(new File(fileName));
        read.useDelimiter("|");

        String title, synopsis, director, status, type, casts, reviews, ratings;
        double duration, totalSales, avgRating;

        // Creating the list of movies from txt file
        ArrayList stringArray = (ArrayList) read(fileName);

        for (int i = 1; i < stringArray.size(); i++) // 0 is header
        {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, "|"); // pass in the string to the string tokenizer using
            // delimiter "|"
            title = star.nextToken().trim();
            // System.out.println(title);
            synopsis = star.nextToken().trim();
            // System.out.println(synopsis);
            director = star.nextToken().trim();
            // casts = (star.nextToken().trim()).split(",");
            casts = star.nextToken().trim();
            status = star.nextToken().trim();
            type = star.nextToken().trim();
            duration = Double.parseDouble(star.nextToken().trim());
            totalSales = Double.parseDouble(star.nextToken().trim());
            avgRating = Double.parseDouble(star.nextToken().trim());
            reviews = star.nextToken().trim();
            ratings = star.nextToken().trim();

            movies[i - 1] = new MovieEdit(title, synopsis, director, casts, duration, status, type, totalSales,
                    avgRating, reviews, ratings);
        }
    }

    /**
     * @return Movie[]
     */
    public Movie[] getMovies() {
        return movies;
    }

    /**
     * @return int
     */
    public int getLength() {
        return n;
    }

    public void displayAll() {
        for (int i = 0; i < n; i++) {
            System.out.printf("(%d) %s \n", i + 1, movies[i].getMovieTitle());
        }
    }

    /**
     * @param choice
     * @return String
     */
    public String getMovieName(int choice) {
        // String[] nowShowingList= new String[movies.length];
        int z = 0;
        for (int i = 0; i < movies.length; i++) {
            if (movies[i].getStatus().equals("ENDSHOW") || movies[i].getStatus().equals("COMINGSOON")) {
                continue;
            } else {
                if (choice == z) {
                    // System.out.printf("%d", choice);
                    return movies[i].getMovieTitle();
                } else
                    z++;
            }
        }
        return null;
    }

    public void displayAllLessNotShowing() {
        // System.out.printf("%s", movies[5].getStatus().compareTo("ENDSHOW"));
        int z = 1;
        for (int i = 0; i < n; i++) {
            if (movies[i].getStatus().equals("ENDSHOW") || movies[i].getStatus().equals("COMINGSOON")) {
                continue;
            } else
                System.out.println(z + ". " + movies[i].getMovieTitle());
            z++;
        }
    }

    /**
     * @param title
     */
    public void displayOne(String title) {
        for (int i = 0; i < n; i++) {
            if (title.compareTo(movies[i].getMovieTitle()) == 0) {
                movies[i].display();
                return;
            }
        }
        System.out.println("Movie not found!");
    }

    /**
     * @throws IOException
     */
    public void rankbyTS() throws IOException {
        boolean allow = viewRank("Total Sales");
        if (!allow) {
            System.out.println("Sorry this service is currently unavailable");
            return;
        }

        int i, j;
        Movie t;
        for (i = n - 2; i >= 0; i--) {
            for (j = 0; j <= i; j++) {
                if (movies[j].gettotalSales() < movies[j + 1].gettotalSales()) {
                    t = movies[j];
                    movies[j] = movies[j + 1];
                    movies[j + 1] = t;
                }
            }
        }
        System.out.println("Top 5 movies with the most ticket sales:");
        for (int x = 1; x <= 5; x++) {
            System.out.println(x + ". " + movies[x - 1].getMovieTitle());
        }
    }

    /**
     * @throws IOException
     */
    public void rankbyAR() throws IOException {
        boolean allow = viewRank("Average Rating");
        if (!allow) {
            System.out.println("Sorry this service is currently unavailable");
            return;
        }

        int i, j;
        Movie t;
        for (i = n - 2; i >= 0; i--) {
            for (j = 0; j <= i; j++) {
                if (movies[j].getavgRating() < movies[j + 1].getavgRating()) {
                    t = movies[j];
                    movies[j] = movies[j + 1];
                    movies[j + 1] = t;
                }
            }
        }
        System.out.println("Top 5 movies with the highest average rating:");
        for (int x = 1; x <= 5; x++) {
            System.out.println(x + ". " + movies[x - 1].getMovieTitle());
        }
    }

    /**
     * @param by
     * @return boolean
     * @throws IOException
     */
    // for admin to change setting for ranking
    public boolean viewRank(String by) throws IOException {
        String fileName = "RankAvail.txt";

        ArrayList stringArray = (ArrayList) read(fileName);
        String st = (String) stringArray.get(1);
        StringTokenizer star = new StringTokenizer(st, "|");

        String ts = star.nextToken().trim();
        boolean TS = Boolean.parseBoolean(ts);
        String ar = star.nextToken().trim();
        boolean AR = Boolean.parseBoolean(ar);

        if (by.compareTo("Total Sales") == 0) {
            return TS;
        } else {
            return AR;
        }
    }

    /**
     * @param replace
     * @throws IOException
     */
    public void editRank(String replace) throws IOException {
        String fileName = "RankAvail.txt";
        Path path = Paths.get(fileName);
        int n = (int) Files.lines(path).count();

        String[] lines = new String[n];
        lines[0] = "TotalSales|AvgRating";

        ArrayList stringArray = (ArrayList) read(fileName);
        String st = (String) stringArray.get(1);
        StringTokenizer star = new StringTokenizer(st, "|");
        // String TS = star.nextToken().trim();
        // String AR = star.nextToken().trim();
        lines[1] = replace;
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