import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * @author Alan
 * @version 1.0
 * 2022-11-08
 */
//Reading and writing of the text file
public class Seatings {
    private Cinema_Seat[] seats;
    private String showtime;
    private String Cinema_ID;
    private static int occupiedSeats = 0;
    private int TotalNumOfSeat;
    private int Type;

    public Seatings(Date Showtime, int type, String arrangement, int currentCinemaID) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        showtime = dateFormat.format(Showtime);
        this.Type = type;
        if (type == 0) {
            this.TotalNumOfSeat = 50;
        } else if (type == 1) {
            this.TotalNumOfSeat = 20;
        } else {
            this.TotalNumOfSeat = 8;
        }
        seats = new Cinema_Seat[this.TotalNumOfSeat];
        for (int i = 0; i < this.TotalNumOfSeat; i++) {
            if (arrangement.charAt(i) == '0')
                seats[i] = new Cinema_Seat(i, false);
            else
                seats[i] = new Cinema_Seat(i, true);
        }
        this.Cinema_ID = Integer.toString(currentCinemaID);
    }

    /**
     * @return String
     */
    public String getShowtime() {
        return this.showtime;
    }

    /**
     * @return int
     */
    public int getTotalNumofSeats() {
        return this.TotalNumOfSeat;
    }

    /**
     * @return int
     */
    public int getType() {
        return this.Type;
    }

    // Show the row and column (Alphabets and Numbers, e.g. A1 for 00 seat)
    public void showSeats() {
        char[] alphabet = "ABCDE".toCharArray();
        int row = 0;
        System.out.println();
        if (Type == 0) {
            int leftStairs = 3;
            int rightStairs = 7;
            System.out.println("  1    4    7   10");
            System.out.print(alphabet[row] + " ");
            for (int i = 0; i < TotalNumOfSeat; i++) {
                if (i == leftStairs || i == rightStairs) {
                    System.out.print("   ");
                }
                if (seats[i].isOccupied())
                    System.out.print("X");
                else
                    System.out.print("O");
                if (i % 10 == 9 && i < TotalNumOfSeat - 1) {
                    leftStairs += 10;
                    rightStairs += 10;
                    row++;
                    System.out.print("\n" + alphabet[row] + " ");
                }
            }
        } else if (Type == 1) {
            int Stairs = 5;
            System.out.println("  1    5 6   10");
            System.out.print(alphabet[row] + " ");
            for (int i = 0; i < TotalNumOfSeat; i++) {
                if (i == Stairs) {
                    System.out.print("   ");
                }
                if (seats[i].isOccupied())
                    System.out.print("X");
                else
                    System.out.print("O");
                if (i == 9) {
                    Stairs += 10;
                    row++;
                    System.out.print("\n" + alphabet[row] + " ");
                }
            }
        } else {
            int Stairs = 2;
            System.out.println("  1 2 3 4");
            System.out.print(alphabet[row] + " ");
            for (int i = 0; i < TotalNumOfSeat; i++) {
                if (i == Stairs) {
                    System.out.print("   ");
                }
                if (seats[i].isOccupied())
                    System.out.print("X");
                else
                    System.out.print("O");
                if (i == 3) {
                    Stairs += 4;
                    row++;
                    System.out.print("\n" + alphabet[row] + " ");
                }
            }
        }
        System.out.println();
    }

    /**
     * @return int
     */
    public int EmptySeats() {
        int spaces = TotalNumOfSeat;
        for (int i = 0; i < TotalNumOfSeat; i++) {
            if (seats[i].isOccupied())
                spaces--;
        }
        return spaces;
    }

    /**
     * @param seating
     */
    public void reserveSeats(Cinema_Seat seating) {
        if (seating.isOccupied())
            System.out.println("Seat already reserved.");
        else {
            seating.assign(occupiedSeats);
            // File update here
            occupiedSeats++;
        }
    }

    /**
     * @param seating
     */
    public void unreserveSeats(Cinema_Seat seating) {
        seating.unAssign();
        occupiedSeats--;
    }

    /**
     * @param input
     * @return Cinema_Seat
     */
    public Cinema_Seat findSeats(String input) {
        char row = input.charAt(0);
        char[] alphabet = "ABCDE".toCharArray();
        int column = Integer.parseInt(input.substring(1, input.length()));
        System.out.printf("Selected seat is in Row %c and Column %d\n", row, column);
        int seatposition = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if (this.Type != 0 && alphabet[i] == 'C') {
                System.out.println("Invalid seat selected.");
                return null;
            }
            if (row == alphabet[i])
                break;
            seatposition++;
        }
        if (this.Type == 2) {
            if (column > 4) {
                System.out.println("Invalid seat selected.");
                return null;
            }
            seatposition *= 4;
        } else {
            seatposition *= 10;
        }
        seatposition += column - 1;
        // System.out.println(seats[seatposition].getSeatID());
        return this.seats[seatposition];
    }

    /**
     * @param seat
     * @throws IOException
     */
    public void bookSeat(Cinema_Seat seat) throws IOException {
        // format of name is A1
        // String ST = day + ", " + time;
        int index = seat.getSeatID();

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
            if (Cinema_ID.compareTo(CD) == 0 & showtime.compareTo(ST) == 0) {
                char[] replace = check[2].toCharArray();
                replace[index] = '1';
                check[2] = String.valueOf(replace);
            }
            lines[i] = String.join("|", check);
        }
        write(fileName, lines);
        seats[index].assign(seats[index].getSeatID());
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
