import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * @author Alan
 * @version 1.0
 * 2022-11-08
 */

//Creation of objects and reading file
public class Cinema {
    private int Cinema_ID;
    private int Type;
    private Date[] Showtime;
    private Seatings[] Seating;
    private Movie movie; // read name from csv then convert to movie object
    // {0 1 2} only 3 possible values
    // 0 = standard = 50 (3 4 3)x5
    // 1 = gold = 20 (5 5)x2
    // 2 = platinum = 8 (2 2) x2

    public Cinema(int hallID) throws IOException, ParseException {
        // read the file and create an array of movie objects
        // DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cinema_ID = hallID;
        ArrayList<Date> Showtime_arrayList = new ArrayList<Date>();
        ArrayList<Seatings> Seatings_arrayList = new ArrayList<Seatings>();
        String fileName = "SeatsAvail.txt";
        Path path = Paths.get(fileName);
        int n = (int) Files.lines(path).count();
        ArrayList stringArray = (ArrayList) read(fileName);
        // this.seats = new String[n - 1];
        for (int i = 1; i < n; i++) {
            String st = (String) stringArray.get(i);
            StringTokenizer star = new StringTokenizer(st, "|");
            String ID = star.nextToken().trim();
            String type = star.nextToken().trim();
            String arr = star.nextToken().trim();
            String ST = star.nextToken().trim();
            String[] check = { ID, type, arr, ST };
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date newDate = formatter.parse(ST);
            // System.out.printf("%s \t %s\n",ST,newDate);
            // System.out.println("CineID is " + (current + 1));
            // System.out.println("Check[0] is " + check[0]);
            if (check[0].compareTo(Integer.toString(hallID)) == 0) {
                Showtime_arrayList.add(newDate);
                Seatings session = new Seatings(newDate, Integer.parseInt(type), arr, hallID);
                Seatings_arrayList.add(session);
                this.Type = Integer.parseInt(type);
            }
        }
        Date[] showtimes = new Date[Showtime_arrayList.size()];
        showtimes = Showtime_arrayList.toArray(showtimes);
        Seatings[] seatingArrangements = new Seatings[Seatings_arrayList.size()];
        seatingArrangements = Seatings_arrayList.toArray(seatingArrangements);
        this.Showtime = showtimes;
        this.Seating = seatingArrangements;
    }

    /**
     * @return int
     */

    public int getCinemaID() {
        return Cinema_ID;
    }

    /**
     * @return Date[]
     */
    public Date[] getShowtimes() {
        return Showtime;
    }

    /**
     * @return int
     */
    public int getType() {
        return this.Type;
    }

    /**
     * @return Movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * @return Seatings[]
     */
    public Seatings[] getSeatings() {
        return Seating;
    }

    public void displayShowtimes() {
        SimpleDateFormat _24HourSDF = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        if (this.movie == null || Showtime == null)
            System.out.println("No movie showing.");
        else {
            System.out.printf("Showtimes for %s are:\n", this.movie.movieTitle);
            if (this.Showtime == null)
                System.out.println("Currently no showtimes available for this movie.");
            else {
                for (int i = 0; i < Showtime.length; i++) {
                    if (Showtime[i] != null)
                        System.out.println("(" + (i + 1) + ") " + _24HourSDF.format(Showtime[i]));
                }
            }
        }
    }

    /**
     * @param input
     * @throws IOException
     */
    public void addShowtimes(String input) throws IOException {
        SimpleDateFormat _24HourSDF = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        // SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
        try {
            Date _24HourDt = _24HourSDF.parse(input);
            // DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            String strDate = _24HourSDF.format(_24HourDt);
            if (Showtime != null) {
                // System.out.println("Showtime not null!");
                ArrayList<Date> arrayList = new ArrayList<Date>(Arrays.asList(this.Showtime));
                arrayList.add(_24HourDt);
                this.Showtime = arrayList.toArray(Showtime);
            } else {
                // System.out.println("Showtime null!");
                Date[] timings = new Date[1];
                timings[0] = _24HourDt;
                this.Showtime = timings;
            }
            // edit file
            String fileName = "SeatsAvail.txt";
            Path path = Paths.get(fileName);
            int n = (int) Files.lines(path).count();

            String[] lines = new String[n + 1]; // overwrite with this
            lines[0] = "CinemaID, Type, Arrangement, Showtime";

            ArrayList stringArray = (ArrayList) read(fileName);
            for (int i = 1; i < n; i++) {
                String st = (String) stringArray.get(i);
                lines[i] = st;
            }
            String seats;
            if (Type == 0)
                seats = "|00000000000000000000000000000000000000000000000000|";
            else if (Type == 1)
                seats = "|00000000000000000000|";
            else
                seats = "|00000000|";
            lines[n] = Integer.toString(Cinema_ID) + "|" + Integer.toString(Type)
                    + seats + strDate;
            write(fileName, lines);

            // System.out.println("Showtime " + _12HourSDF.format(_24HourDt) + " added.");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param input
     * @param choice
     * @throws IOException
     */
    public void editShowtimes(String input, int choice) throws IOException {
        SimpleDateFormat _24HourSDF = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date _24HourDt = _24HourSDF.parse(input); // new value
            String newDate = input;
            String oldDate = _24HourSDF.format(this.Showtime[choice]);
            System.out.println("Showtime changed to " + _24HourSDF.format(_24HourDt) + ".");
            this.Showtime[choice] = _24HourDt;

            // edit file
            String fileName = "SeatsAvail.txt";
            Path path = Paths.get(fileName);
            int n = (int) Files.lines(path).count();

            String[] lines = new String[n]; // overwrite with this
            lines[0] = "CinemaID, Type, Arrangement, Showtime";

            ArrayList stringArray = (ArrayList) read(fileName);
            for (int i = 1; i < n; i++) {
                String st = (String) stringArray.get(i);
                StringTokenizer star = new StringTokenizer(st, "|");
                String CD = star.nextToken().trim();
                String type = star.nextToken().trim();
                String arr = star.nextToken().trim();
                String ST = star.nextToken().trim();

                String[] check = { CD, type, arr, ST };
                if (Integer.toString(Cinema_ID).compareTo(CD) == 0 & oldDate.compareTo(ST) == 0) {

                    check[3] = newDate;
                }
                lines[i] = String.join("|", check);
            }
            write(fileName, lines);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param choice
     * @throws IOException
     */
    public void removeShowtimes(int choice) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String strDate = dateFormat.format(this.Showtime[choice]);
        // System.out.println(strDate);
        ArrayList<Date> arrayList = new ArrayList<Date>(Arrays.asList(this.Showtime));
        arrayList.remove(choice);
        // System.out.println("Showtime removed.");
        this.Showtime = arrayList.toArray(Showtime);

        // edit file
        String fileName = "SeatsAvail.txt";
        Path path = Paths.get(fileName);
        int n = (int) Files.lines(path).count();

        String[] lines = new String[n - 1]; // overwrite with this
        lines[0] = "CinemaID, Type, Arrangement, Showtime";
        int count = 1;

        ArrayList stringArray = (ArrayList) read(fileName);
        for (int i = 1; i < n; i++) {
            String st = (String) stringArray.get(i);
            StringTokenizer star = new StringTokenizer(st, "|");
            String CD = star.nextToken().trim();
            String type = star.nextToken().trim();
            String arr = star.nextToken().trim();
            String ST = star.nextToken().trim();

            String[] check = { CD, type, arr, ST };
            if (Integer.toString(Cinema_ID).compareTo(CD) == 0 & strDate.compareTo(ST) == 0) {
                continue;
            } else {
                lines[count] = String.join("|", check);
                count++;
            }
        }
        write(fileName, lines);
    }

    /**
     * @param mov
     */
    public void assignMovie(Movie mov) {
        this.movie = mov;
    }

    /**
     * @param fileName
     * @return List
     * @throws IOException
     */
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

    /**
     * @param fileName
     * @param lines
     * @throws IOException
     */
    public static void write(String fileName, String[] lines) throws IOException {
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
