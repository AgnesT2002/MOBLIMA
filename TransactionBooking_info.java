import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * @author Delaney
 * @version 1.0
 * 2022-11-08
 */
public class TransactionBooking_info {
    protected String personEmail;
    protected int personMobileNumber;
    protected String transactionID;
    protected String chosenMovie;
    protected int totalNumOfTickets;
    protected int noOfStandard;
    protected int noOfSeniors;
    protected int noOfStudent;
    protected double totalBookingPrice;

    public TransactionBooking_info(String personEmail, int personMobileNumber, String transactionID, String chosenMovie,
            int totalNumOfTickets, int noOfStandard, int noOfSeniors, int noOfStudent, double totalBookingPrice) {
        this.personEmail = personEmail;
        this.personMobileNumber = personMobileNumber;
        this.transactionID = transactionID;
        this.chosenMovie = chosenMovie;
        this.totalNumOfTickets = totalNumOfTickets;
        this.noOfStandard = noOfStandard;
        this.noOfSeniors = noOfSeniors;
        this.noOfStudent = noOfStudent;
        this.totalBookingPrice = totalBookingPrice;
    }

    /**
     * @return String
     */
    public String getPersonEmail() {
        return personEmail;
    }

    /**
     * @return int
     */
    public int getPersonMobileNumber() {
        return personMobileNumber;
    }

    /**
     * @return String
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * @return String
     */
    public String getChosenMovie() {
        return chosenMovie;
    }

    /**
     * @return int
     */
    public int getTotalNumOfTickets() {
        return totalNumOfTickets;
    }

    /**
     * @return int
     */
    public int getNoOfStandard() {
        return noOfStandard;
    }

    /**
     * @return int
     */
    public int getNoOfSeniors() {
        return noOfSeniors;
    }

    /**
     * @return int
     */
    public int getNoOfStudent() {
        return noOfStudent;
    }

    /**
     * @return double
     */
    public double getTotalBookingPrice() {
        return totalBookingPrice;
    }

    public void displayTransaction() {

        System.out.println("Email: " + personEmail);
        System.out.println("Phone Number: " + personMobileNumber);
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Movie: " + chosenMovie);
        System.out.println("Num of Tickets: " + totalNumOfTickets);
        System.out.println("Standard: " + noOfStandard);
        System.out.println("Senior: " + noOfSeniors);
        System.out.println("Student: " + noOfStudent);
        System.out.println("Total Booking Price: " + totalBookingPrice);
        System.out.println("=============================================");
    }

    /**
     * @throws IOException
     */
    public void addTransaction() throws IOException {
        String filename = "PastTransactions.txt";
        FileWriter fstream = new FileWriter(filename, true);
        BufferedWriter out = new BufferedWriter(fstream);

        String append = personEmail + "|" + personMobileNumber + "|" + transactionID + "|" + chosenMovie + "|" +
                totalNumOfTickets + "|" + noOfStandard + "|" + noOfSeniors + "|" + noOfStudent + "|"
                + totalBookingPrice;

        out.write(append);
        out.newLine();

        // close buffer writer
        out.close();
        System.out.println("Added new transaction");
    }

    // adding transaction so that can search next time

}