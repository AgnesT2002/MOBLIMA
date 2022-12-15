/**
 * Represents the Menus that the Admin will see.
 * 
 * @author Zhi Yong
 * @version 1.0
 *          2022-11-08
 */
public class Menu_Admin implements Menu_Interface {
    /**
     * Prints the admin homepage menu.
     */
    public void Menu() {
        System.out.println("Admin Menu");
        System.out.println("(1) Edit Movie listings");
        System.out.println("(2) Edit Cinema settings");
        System.out.println("(3) Configure System settings");
        System.out.println("(0) Exit Admin Menu");
        System.out.println("Please enter your choice: ");
    }

    /**
     * Prints the edit movie menu.
     */
    public void EditMovieMenu() {
        System.out.println("Edit Movie Menu");
        System.out.println("(1) Create Movie listings");
        System.out.println("(2) Update Movie listings");
        System.out.println("(3) Remove Movie listings");
        System.out.println("(0) Exit Edit Movie Menu");
        System.out.println("Please enter your choice: ");
    }

    /**
     * Prints the edit movie details.
     */
    public void EditMovieDetails() {
        System.out.println("(1) Change Movie Title");
        System.out.println("(2) Change Synopsis");
        System.out.println("(3) Change Director");
        System.out.println("(4) Change Casts");
        System.out.println("(5) Change Duration");
        System.out.println("(6) Change Status");
        System.out.println("(7) Change Type");
        System.out.println("(8) Change Total Sales");
        System.out.println("(9) Update Average Rating");
        System.out.println("(10) Reviews");
        System.out.println("(11) Ratings");
        System.out.println("(0) Exit Edit Movie Menu");
        System.out.println("Please enter your choice: ");
    }

    /**
     * Prints the edit cinema menu.
     */
    public void EditCinemaMenu() {
        // Jurong West Cineplex Hall 4 - Movie Title - Showtime 1100 1400 1800
        System.out.println("Edit Cinema Menu");
        System.out.println("(1) Select Cinema");
        System.out.println("(2) Add Cinema Showtimes");
        System.out.println("(3) Edit Cinema Showtimes");
        System.out.println("(4) Remove Cinema Showtimes");
        System.out.println("(5) Assign Cinema a movie");
        System.out.println("(0) Exit Edit Cinema Menu");
        System.out.println("Please enter your choice: ");
    }

    /**
     * Prints the edit system menu.
     */
    public void EditSystemMenu() {
        System.out.println("Edit System Menu");
        System.out.println("(1) Edit Ticket Pricing");
        System.out.println("(2) Edit Holidays");
        System.out.println("(3) Toggle movie ranking");
        System.out.println("(0) Exit Edit System Menu");
        System.out.println("Please enter your choice: ");
    }

    /**
     * Prints the edit ticket price menu.
     */
    public void EditTicketPrice() {
        System.out.println("Edit Ticket Price Menu");
        System.out.println("(1) Edit Public Holiday Pricing");
        System.out.println("(2) Edit Standard Pricing");
        System.out.println("(3) Edit Senior Citizen Pricing");
        System.out.println("(4) Edit Student Pricing");
        System.out.println("(5) Edit Normal Seating Pricing");
        System.out.println("(6) Edit Gold Class Pricing");
        System.out.println("(7) Edit Platinum Class Pricing");
        System.out.println("(8) Edit Preview Pricing");
        System.out.println("(9) Edit Now Showing Pricing");
        System.out.println("(10) Edit 2D Pricing");
        System.out.println("(11) Edit 3D Pricing");
        System.out.println("(0) Exit Edit Ticket Price Menu");
        System.out.println("Please enter your choice: ");
    }

    /**
     * Prints the toggle system menu.
     */
    public void ToggleSystemMenu() {
        System.out.println("Toggle Ranking Movie");
        System.out.println("(1) Hide Total Sales Ranking, Hide Average Rating Ranking");
        System.out.println("(2) Hide Total Sales Ranking, Show Average Rating Ranking");
        System.out.println("(3) Show Total Sales Ranking, Hide Average Rating Ranking");
        System.out.println("(4) Show Total Sales Ranking, Show Average Rating Ranking");
        System.out.println("(0) Exit toggling");
        System.out.println("Please enter your choice: ");
    }

}