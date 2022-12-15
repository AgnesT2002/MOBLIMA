/**
 * Represents the Menus that the Users will see.
 * 
 * @author Zhi Yong
 * @version 1.0
 *          2022-11-08
 */
public class Menu_User implements Menu_Interface {
    public Menu_User() {
    };

    /**
     * Prints the homepage menu.
     */
    public void Menu() {
        System.out.println("Homepage Menu");
        System.out.println("(1) List Movies");
        System.out.println("(2) Search Movie");
        System.out.println("(3) Buy Tickets");
        System.out.println("(4) List Top 5 Movies");
        System.out.println("(5) View Booking History");
        System.out.println("(0) Exit Homepage Menu");
        System.out.println("Please enter your choice: ");
    }

    /**
     * Prints the search menu.
     */
    public void BuyMenu() {
        System.out.println("Search Menu");
        System.out.println("(1) View movie details");
        System.out.println("(2) Check movie availability");
        System.out.println("(3) Buy tickets");
        System.out.println("(0) Exit Search Menu");
        System.out.println("Please enter your choice: ");
    }

}