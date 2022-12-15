import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * @author Delaney
 * @version 1.0
 * 2022-11-08
 */
public class TransactionStore {
    private TransactionBooking_info[] tbs;
    private int n;

    public TransactionStore() throws IOException, FileNotFoundException {
        // read file and create an array of transactionID objects
        String fileName = "PastTransactions.txt";
        Path path = Paths.get(fileName);
        n = (int) Files.lines(path).count();

        tbs = new TransactionBooking_info[n];
        Scanner read = new Scanner(new File(fileName));
        read.useDelimiter("|");

        String personEmail, transactionID, chosenMovie;
        int personMobileNumber, noOfStandard, noOfSeniors, noOfStudent, totalNumOfTickets;
        double totalBookingPrice;

        ArrayList stringArray = (ArrayList) read(fileName);

        for (int i = 0; i < stringArray.size(); i++) // 0 is header
        {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, "|"); // pass in the string to the string tokenizer using
                                                                 // delimiter "|"

            personEmail = star.nextToken().trim();
            // System.out.println(title);
            personMobileNumber = Integer.parseInt(star.nextToken().trim());
            // System.out.println(synopsis);
            transactionID = star.nextToken().trim();
            // casts = (star.nextToken().trim()).split(",");
            chosenMovie = star.nextToken().trim();
            totalNumOfTickets = Integer.parseInt(star.nextToken().trim());
            noOfStandard = Integer.parseInt(star.nextToken().trim());
            noOfSeniors = Integer.parseInt(star.nextToken().trim());
            noOfStudent = Integer.parseInt(star.nextToken().trim());
            totalBookingPrice = Double.parseDouble(star.nextToken().trim());

            tbs[i] = new TransactionBooking_info(personEmail, personMobileNumber, transactionID, chosenMovie,
                    totalNumOfTickets, noOfStandard, noOfSeniors, noOfStudent, totalBookingPrice);
        }
    }

    /**
     * @param personMobileNumber
     */
    // displayTransactions with the cineplex
    public void displayTransactions(int personMobileNumber) {
        int noOfTransactions = 0;
        System.out.println("Past Transactions:  ");

        for (int i = 0; i < n; i++) {
            if (Integer.compare(tbs[i].getPersonMobileNumber(), personMobileNumber) == 0) {
                noOfTransactions++;
                System.out.println("=============================================");
                System.out.println("Transaction " + noOfTransactions);
                // System.out.println("Email\t\t\t Phone Number TransactionID \t\t Movie
                // TotalTicket Standard Senior Student TotalPrice");
                tbs[i].displayTransaction();
            }
        }

        if (noOfTransactions == 0) {
            System.out.println("Sorry! We have no transactions with " + personMobileNumber);
        } else {
            System.out.println("You have a total of " + noOfTransactions + " booking with us.");
        }
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