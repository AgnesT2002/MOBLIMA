import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
/**
 * @author Delaney
 * @version 1.0
 * 2022-11-08
 */
public class TransactionBooking {

    private Movie selectedMovie; // movie that person wants to book
    private Cinema Hall;
    private Cineplex selectedCineplex; // location where person wants to watch the movie
    private int totalNumOfTickets; // get from array of seatIds;
    private String[] seatIds; // seats that are booked
    private int noOfStandard; // get number of standard tickets
    private int noOfStudent; // get number of student tickets
    private int noOfSeniors; // get total number of senior citizen tickets
    private Ticket[] ticket; // = new Ticket[seatIds.length]; //array of tickets
    private Movie_Goer bookingPerson; // get email and phone to book in transaction array
    private String transactionID;
    private String date;

    private double totalBookingPrice = 0;

    public TransactionBooking(Movie selectedMovie, Cinema Hall, Cineplex selectedCineplex, String[] seatsIds,
            int totalNumOfTickets,
            int noOfStandard, int noOfStudent, int noOfSeniors, Movie_Goer bookingPerson, String date) {
        this.totalNumOfTickets = seatsIds.length;
        this.selectedMovie = selectedMovie;
        this.Hall = Hall;
        this.selectedCineplex = selectedCineplex;
        seatIds = new String[totalNumOfTickets]; // initialise array
        this.seatIds = seatIds; // store the seatIds here
        for (int i = 0; i < seatsIds.length; i++) {
            this.seatIds[i] = seatsIds[i];
        }
        this.noOfStandard = noOfStandard; // to calculate price
        this.noOfSeniors = noOfSeniors; // to calculate price
        this.noOfStudent = noOfStudent; // to calculate price
        this.bookingPerson = bookingPerson;
        this.date = date;
    }

    /**
     * @throws IOException
     * @throws ParseException
     */
    public void createTickets() throws IOException, ParseException {
        // get the price, store into the array
        // Date date = null;
        Price prices = new Price();
        prices.setchosen_MovieType(selectedMovie.getType());
        prices.setchosen_Moviestatus(selectedMovie.getStatus());
        // prices.setchosen_CheckPH(false);
        // prices.setchosen_GetDayOfWeek(1);
        prices.setchosen_Cinemaclass(Hall.getType());
        prices.setDate_PH_dayOfWeek(date);
        // Price prices = new Price(selectedMovie.getType(), selectedMovie.getType(),
        // Hall.getType() , Calender.getDayOfWeek(date));
        double[] prices_age = prices.setFinalPrice();
        // System.out.println("Prices");
        // for (int i = 0; i < prices_age.length; i++) {
        // System.out.println("Price " + i + "= " + prices_age[i]);
        // }
        double priceOfStandard = prices_age[0];
        double priceOfSeniorCitizen = prices_age[1];
        double priceOfStudent = prices_age[2];

        int tickets = 0;
        this.ticket = new Ticket[totalNumOfTickets];

        // pass in information to ticket class

        for (int i = 0; i < noOfStandard; i++) {
            ticket[tickets] = new Ticket(selectedMovie.getStatus(), Hall.getType(), selectedMovie.getType(), 0,
                    seatIds[tickets], Hall.getCinemaID(), priceOfStandard);
            // System.out.println(ticket[tickets].getTicketPrice());
            tickets++;
        }
        // for senior citizen
        for (int i = 0; i < noOfSeniors; i++) {
            ticket[tickets] = new Ticket(selectedMovie.getStatus(), Hall.getType(), selectedMovie.getType(), 1,
                    seatIds[tickets], Hall.getCinemaID(), priceOfSeniorCitizen);
            tickets++;
        }
        // for student
        for (int i = 0; i < noOfStudent; i++) {
            ticket[tickets] = new Ticket(selectedMovie.getStatus(), Hall.getType(), selectedMovie.getType(), 2,
                    seatIds[tickets], Hall.getCinemaID(), priceOfStudent);
            tickets++;
        }
        settotalBookingPrice(this.ticket);
        setTransactionID();
        storeTransaction();
    }

    /**
     * @throws IOException
     */
    public void storeTransaction() throws IOException {
        TransactionBooking_info ti = new TransactionBooking_info(bookingPerson.getEmail(),
                bookingPerson.getMobile_number(), transactionID, selectedMovie.getMovieTitle(), totalNumOfTickets,
                noOfStandard, noOfSeniors, noOfStudent, totalBookingPrice);
        ti.addTransaction();
    }

    public void setTransactionID() {
        // Each payment will have a transaction id (TID). The TID is of the
        // format XXX YYYY MM DD hh mm (Y : year, M : month, D : day, h : hour, m :
        // minutes, XXX : cinema code in letters).
        // Each payment will have a transaction id (TID). The TID is of the
        // format XXX YYYY MM DD hh mm (Y : year, M : month, D : day, h : hour, m :
        // minutes, XXX : cinema code in letters).
        // getting the year month time date now
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        // System.out.printf("%d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
        String ID = String.format("%s%d%d%02d%02d%02d%02d", selectedCineplex.getLocation_short(), Hall.getCinemaID(),
                year, month, day, hour, minute);
        this.transactionID = ID.strip();

    }

    /**
     * @return String
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * @param ticket
     */
    public void settotalBookingPrice(Ticket[] ticket) {

        for (int j = 0; j < ticket.length; j++) {
            totalBookingPrice += ticket[j].getTicketPrice();
        }
    }

    /**
     * @return double
     */
    public double getTotalBookingPrice() {
        return totalBookingPrice;
    }

    /**
     * @throws IOException
     */
    // printing receipt
    public void printReceipt() throws IOException {
        Calender calendar = new Calender();
        System.out.println("========================================================");
        System.out.println("Pigs Fly Cineplex");
        System.out.println("Receipt");
        System.out.println("TransactionID: " + transactionID);
        // check PH
        if (calendar.checkPH(date)) {
            System.out.println("Public Holiday Rate");
        }
        System.out.println("----------- Movie Details -----------");
        System.out.println("Movie: " + selectedMovie.getMovieTitle());
        if (Hall.getType() == 0) {
            System.out.println("Movie Class: Standard");
        } else if (Hall.getType() == 1) {
            System.out.println("Movie Class: Gold");
        } else if (Hall.getType() == 2) {
            System.out.println("Movie Class: Platinum");
        }

        if (selectedMovie.getType().equals("2D")) {
            System.out.println("Movie Type 2D");
        } else {
            System.out.println("Movie Type 3D");
        }
        System.out.println("----------- Ticket Details -----------");
        System.out.println("No. of Tickets: " + seatIds.length);
        System.out.println("No. of Standard:  " + noOfStandard);
        System.out.println("No. of Senior Citizen:  " + noOfSeniors);
        System.out.println("No. of Student:  " + noOfStudent);

        System.out.println("----------- Seat Allocation -----------");
        for (int i = 0; i < seatIds.length; i++) {
            System.out.print(seatIds[i] + " ");
        }
        System.out.println();
        System.out.println("Total price: $" + totalBookingPrice);
        System.out.println("Thank you for choosing Pigs Fly Cineplex. See you again!");
        System.out.println("========================================================");

    }

}