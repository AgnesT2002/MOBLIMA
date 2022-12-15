import java.nio.file.*;
import java.util.*;
import java.io.*;
import java.io.*;
/**
 * @author Agnes
 * @version 1.0
 * 2022-11-08
 */

public class MovieApp {

  /**
   * @param args
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void main(String[] args) throws FileNotFoundException, IOException {
    MovieList ML = new MovieList();
    Movie[] movies = ML.getMovies();

    // search 1
    ML.displayAll();

    // search 2
    System.out.println("\n Displaying movie details \n");
    ML.displayOne("Smile");

    // List top 5
    System.out.println("\n After changing the rank availability \n");
    ML.rankbyAR();
    ML.rankbyTS();

    // editing rank availability
    ML.editRank("TRUE|FALSE"); // disallow viweing rank by avg rating
    ML.rankbyAR();
    ML.rankbyTS();

    // Testing editing the movie details
    int length = ML.getLength();
    for (int i = 0; i < length; i++) {
      if (movies[i].getMovieTitle().compareTo("Smile") == 0) {
        MovieEdit EditMovie = (MovieEdit) movies[i]; // downcast to MovieEdit object to access all the mutators

        // update movie title
        EditMovie.setMovieTitle("Smile2");
        // System.out.println(movies[n-2].getMovieTitle());
        // update synopsis
        EditMovie.setSynopsis("Empty");
        // update director
        EditMovie.setDirector("Parker Finnn");
        // update casts
        String newcasts = "Sosie Bacon, Jessie T, Kyle Gallner";
        EditMovie.setCasts(newcasts);
        // update status
        EditMovie.setStatus("ENDSHOW");
        // update type
        EditMovie.setType("3D");
        // update duration
        EditMovie.setDuration(120);
        // update total sales
        EditMovie.settotalSales(50);
        // update reviews
        String newrev = "Good show, Wonderful, Meh, A scary movie, Horrible & terrible, Scaryy";
        EditMovie.setReviews(newrev);
        // update ratings
        String newrat = "5,4,2,0,1";
        EditMovie.setRatings(newrat);
        // update avgRating based on newrat
        double AR = EditMovie.calculateAvgRating(EditMovie.getRatings());
        // System.out.println(AR);
        EditMovie.setavgRating(AR);

        break;
      }
      System.out.println("Movie not found!");
    }

    // Ctrl + / to uncomment
    // Creating a new movie
    // String title, synopsis, director, casts, status, type, reviews, ratings;
    // double duration, totalSales, avgRating;
    // title = "Frozen"; synopsis = "girl with ice magic"; director = "Mr Tan";
    // casts="Elsa, Anna";
    // status = "SHOWING"; type = "2D"; duration = 109; totalSales = 77; avgRating =
    // 4.6;
    // reviews = "Magical movie, Interesting plot!"; ratings = "4,5";
    // Movie newMov = new Movie(title, synopsis, director, casts, duration, status,
    // type, totalSales, avgRating, reviews, ratings);
    // newMov.Create();
  }

  /**
   * @param fileName
   * @return List
   * @throws IOException
   */
  ///////////////////////////// helper functions /////////////////////////////////
  public static List read(String fileName) throws IOException {
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

}
