/**
 * Represents the start menu that lets you select user or admin.
 * 
 * @author Alan
 * @version 1.0
 *          2022-11-08
 */
public class Menu_Start implements Menu_Interface {
    public Menu_Start() {
    };

    /**
     * Prints the start menu.
     */
    public void Menu() {
        System.out.println("(1) User");
        System.out.println("(2) Admin");
        System.out.println("(0) Exit Menu");
        System.out.println("Please enter your choice: ");
    }
}