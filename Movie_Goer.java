/**
 * @author Zhi Yong
 * @version 1.0
 * 2022-11-08
 */
public class Movie_Goer {
	private int age;
	private String name;
	private int mobile_number;
	private String email;
	// public Transaction[] booking_history;

	public Movie_Goer(int mobile_number, String email) {
		// this.age=age;
		// this.name=name;
		this.mobile_number = mobile_number;
		this.email = email;
		// this.booking_history= new Transaction[];
	}

	/**
	 * @return int
	 */
	// method to print transaction history
	// public void Show_Transaction_History(Transaction[] booking_history) {
	// System.out.println("Booking History:");
	// System.out.printf("10%s 10%s 10%s","Transaction ID","Movie Title","Total
	// Price");
	// for (int i=0; i<booking_history.length;i++) {
	// System.out.printf("|%10d| : %10s, %10d ",booking_history[i].transaction_ID,
	// booking_history[i].movie_title,booking_history[i].booking_history.price);
	// }
	//
	// }

	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return int
	 */
	public int getMobile_number() {
		return mobile_number;
	}

	/**
	 * @param mobile_number
	 */
	public void setMobile_number(int mobile_number) {
		this.mobile_number = mobile_number;
	}

	/**
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}