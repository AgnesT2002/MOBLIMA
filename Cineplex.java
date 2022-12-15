/**
 * @author Alan
 * @version 1.0
 * 2022-11-08
 */

public class Cineplex {
    private static int TotalNumOfCineplex = 0;
    private int Cineplex_ID;
    private String Location;
    private String Location_short;
    private Cinema[] CinemaHalls;

    public Cineplex(String place, Cinema[] halls, String shortform) {
        this.Cineplex_ID = TotalNumOfCineplex;
        TotalNumOfCineplex++;
        this.Location = place;
        this.CinemaHalls = halls;
        this.Location_short = shortform;
    }

    /**
     * @return int
     */
    // Need to decide order of initialization,
    // thinking to init cinema first then cineplex
    // Would array of Cinema halls just be IDS or actual cinema object?

    public int getCineplexID() {
        return Cineplex_ID;
    }

    /**
     * @return String
     */
    public String getLocation() {
        return Location;
    }

    /**
     * @return String
     */
    public String getLocation_short() {
        return Location_short;
    }

    /**
     * @return Cinema[]
     */
    public Cinema[] getHalls() {
        return CinemaHalls;
    }

    /**
     * @return Movie[]
     */
    public Movie[] getMovies() {
        Cinema[] halls = this.CinemaHalls;
        Movie[] movies = new Movie[halls.length];
        for (int i = 0; i < halls.length; i++) {
            movies[i] = halls[i].getMovie();
        }
        return movies;
    }

    /**
     * @param shortform
     */
    public void setLocation_short(String shortform) {
        this.Location_short = shortform;
    }

    public void displayMovies() {
        Cinema[] halls = this.CinemaHalls;
        Movie[] movies = new Movie[halls.length];
        System.out.printf("List of movies within %s: \n", this.Location);
        for (int i = 0; i < halls.length; i++) {
            Movie current = halls[i].getMovie();
            if (current != null)
                System.out.print(current.getMovieTitle() + "\n");
            movies[i] = halls[i].getMovie();
        }
        System.out.println();
    }
}