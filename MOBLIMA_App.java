import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * @author Alan and Zhi yong
 * @version 1.0
 * 2022-11-08
 */

public class MOBLIMA_App {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// read the file and create an array of movie objects
		// Calender cal = new Calender();
		int choice = 1; // Menu option
		// Date[] showtimes = { cal.setHHMM("14:00"), cal.setHHMM("17:00") };
		MovieList ML = new MovieList();
		Movie[] movies = ML.getMovies();
		// Menu menu = new Menu();
		Cinema hall1 = new Cinema(0);
		Cinema hall2 = new Cinema(1);
		Cinema hall3 = new Cinema(2);
		Cinema hall4 = new Cinema(3);
		Cinema hall5 = new Cinema(4);
		Cinema hall6 = new Cinema(5);
		Cinema hall7 = new Cinema(6);
		Cinema hall8 = new Cinema(7);
		Cinema hall9 = new Cinema(8);

		Cinema[] jurong_halls = { hall1, hall2, hall3 };
		Cinema[] woodlands_halls = { hall4, hall5, hall6 };
		Cinema[] bedok_halls = { hall7, hall8, hall9 };
		Cineplex jurong_cineplex = new Cineplex("Jurong Cineplex", jurong_halls, "JP");
		Cineplex woodlands_cineplex = new Cineplex("Woodlands Cineplex", woodlands_halls, "WL");
		Cineplex bedok_cineplex = new Cineplex("Bedok Cineplex", bedok_halls, "BD");
		Cineplex[] allBranches = { jurong_cineplex, woodlands_cineplex, bedok_cineplex };

		// Last thing to do is assigning of movies to each object and storage
		hall1.assignMovie(movies[0]);
		hall2.assignMovie(movies[1]);
		hall3.assignMovie(movies[2]);
		hall4.assignMovie(movies[3]);
		hall5.assignMovie(movies[4]);
		hall6.assignMovie(movies[5]);
		hall7.assignMovie(movies[6]);
		hall8.assignMovie(movies[7]);
		hall9.assignMovie(movies[8]);
		Scanner sc = new Scanner(System.in);
		Menu_Start menustart = new Menu_Start();
		outer: while (choice != 0) { // when choice = 0, will end the program
			System.out.printf("---------------------------------------------\n");
			menustart.Menu();
			int moviechoice = 0; // movie choice, indexed to the movies in array
			choice = sc.nextInt();
			switch (choice) {
				case (1): // user
					Menu_User user = new Menu_User();
					int choice_user;
					do {
						user.Menu();
						choice_user = sc.nextInt();
						switch (choice_user) {
							case (1): // list Movie
								ML = new MovieList();
								ML.getMovies();
								ML.displayAll();
								System.out.print("Select movie no. to view in detail or 0 to back: ");
								moviechoice = sc.nextInt() - 1;
								if (moviechoice < 0) // option
									break;
								else if (moviechoice > ML.getLength()) {
									System.out.println("Invalid option");
									break;
								} else {
									System.out.printf("Selected movie is:%s \n",
											ML.getMovies()[moviechoice].getMovieTitle());
									ML.displayOne(ML.getMovies()[moviechoice].getMovieTitle());
								}
								break;
							case (2): // Search Movie
								ML = new MovieList();
								ML.getMovies();
								System.out.print("Enter movie name: ");
								String movieChoices = sc.nextLine();
								if (searchMovie(movieChoices, allBranches) != null)
									System.out.printf("Movie found at %s \n", searchMovie(movieChoices, allBranches));
								break;
							case (3): // buy movie option
								ML = new MovieList();
								ML.getMovies();
								System.out.println("Please select a choice a movie to watch");
								ML.displayAllLessNotShowing();
								int choice3 = sc.nextInt() - 1; // index of movie
								String movieChoice = ML.getMovieName(choice3);// get movietitle of selectedmovie
								int movieIndex = 0; // variable to get the index of the movie
								for (int i = 0; i < ML.getLength() - 1; i++) { // help to get the index of the movie
									// System.out.println(movies[i].getMovieTitle());
									if (movies[i].getMovieTitle().equals(movieChoice))
										movieIndex = i;
								}
								int choices = 1;
								while (choices != 0) {
									user.BuyMenu();
									choices = sc.nextInt();
									String location = searchMovie(movieChoice, allBranches);
									Cinema hall = getHall(movieChoice, allBranches);
									if (choices == 1) // display movie details
									{
										ML.displayOne(movieChoice);
									} else if (choices == 2) // check seat availability
									{
										// Fix this portion
										System.out.printf("Showtimes for hall %s: \n", hall.getCinemaID() + 1);
										hall.displayShowtimes();
										//////////////////
										System.out.println("Please select showtime: ");
										int showTimeChoice = sc.nextInt() - 1; // variable for showtiming
										Seatings current = hall.getSeatings()[showTimeChoice];
										current.showSeats();
									} else if (choices == 3) { // buy tickets
										int i = 0;
										System.out.printf("Showtimes for Hall%d, %s Class: \n", hall.getCinemaID() + 1,
												convertCinemaType(hall.getType()));
										System.out.println("Hall type is " + hall.getType());
										hall.displayShowtimes();
										System.out.println("Please select showtime: ");
										int showTimeChoice = sc.nextInt() - 1; // variable for showtiming
										Seatings current = hall.getSeatings()[showTimeChoice];
										current.showSeats();

										String[] seatids = confirmSeat(hall.getSeatings()[showTimeChoice]); // book seat
										for (int a = 0; a < seatids.length; a++)
											System.out.printf("%s,", seatids[a]);

										System.out.println("Please enter email: ");
										sc.nextLine();
										String email = sc.nextLine();
										System.out.println("Please enter phone number: ");
										int PhoneNumber = sc.nextInt();
										int noOfStudent = 0;
										int noOfSeniors = 0;
										Boolean check = false;
										while (!check) {
											System.out.println("How many students? ");
											noOfStudent = sc.nextInt();
											System.out.println("How many senior citizens? ");
											noOfSeniors = sc.nextInt();
											if (noOfStudent + noOfSeniors < seatids.length)
												check = true;
											System.out.println(
													"Invalid number of tickets selected!, Please select again!");
										}
										Movie_Goer bob = new Movie_Goer(PhoneNumber, email);
										for (i = 0; i < allBranches.length; i++) {
											if (allBranches[i].getLocation() == location)
												break;
										}
										// System.out.printf("%s", current.getShowtime());
										String date = current.getShowtime();
										// Calender cal2 = new Calender();
										// cal2.setDate(date);
										// System.out.printf("%s",cal2.checkPH(date));
										int noOfStandard = seatids.length - noOfStudent - noOfSeniors;
										// System.out.printf(" Type = %s, movie type = %s ", hall.getType(),
										// hall.getMovie().getType());

										TransactionBooking tb = new TransactionBooking(hall.getMovie(), hall,
												allBranches[i],
												seatids, seatids.length, noOfStandard, noOfStudent, noOfSeniors, bob,
												date);
										// tb.storeTransaction();

										tb.createTickets();
										tb.printReceipt();
									}
								}
								break;
							case (4): // list top 5
								ML.rankbyAR();
								ML.rankbyTS();
								break;

							case (5): // view booking history
								TransactionStore TS = new TransactionStore();
								System.out.println("Please enter phone number: ");
								int personMobileNumber = sc.nextInt();
								TS.displayTransactions(personMobileNumber);
								break;
						}
					} while (choice_user > 0 && choice_user <= 5);
					break;
				case 2: // admin
				{
					System.out.print("Enter password: ");
					String password = sc.next();
					if (password.equals("Admin123")) {
						adminOptions(allBranches);
					} else
						System.out.println("Invalid password.");
					break;
				}
				// menu.Homepage();
				// System.out.print("Please enter choice: ");
				// boolean done = false;
				// while (!done)
				// try {
				// choice = Integer.parseInt(sc.nextLine());
				// System.out.printf("---------------------------------------------\n");
				// done = true;
				// } catch (NumberFormatException | InputMismatchException b) {
				// System.out.println("Invalid Input! Please input a valid option! ");
				// menu.Homepage();
				// System.out.print("Please enter choice: ");
				// }
				// switch (choice) {

				// case (6): // Admin Options
				case (0):
					break outer;
				default:
					System.out.println("Invalid input!");
					break;
			}
		}
	}

	/**
	 * @param i
	 * @return String
	 */
	public static String convertCinemaType(int i) {
		if (i == 0)
			return "STANDARD";
		else if (i == 1)
			return "GOLD";
		else if (i == 2)
			return "PLATINUM";
		return null;
	}

	/**
	 * @param cineplexes
	 */
	public static void loadCineplexMovies(Cineplex[] cineplexes) {
		for (int i = 0; i < cineplexes.length; i++) {
			Movie[] cineplexMovies = cineplexes[i].getMovies();
		}
	}

	/**
	 * @param movielist
	 */
	public static void displayAllMovies(Movie[] movielist) {
		if (movielist[0] != null) {
			for (int i = 0; i < movielist.length; i++) {
				System.out.printf("%s\n", movielist[i].movieTitle);
			}
		} else
			System.out.println("No movies added to system yet.");
	}

	/**
	 * @param input
	 * @param cineplexes
	 * @return String
	 */
	public static String searchMovie(String input, Cineplex[] cineplexes) {
		if (cineplexes[0] != null) {
			for (int i = 0; i < cineplexes.length; i++) {
				Movie[] cineplexMovies = new Movie[cineplexes[i].getMovies().length];
				cineplexMovies = cineplexes[i].getMovies();
				for (int j = 0; j < cineplexMovies.length; j++) {
					if (cineplexMovies[j] != null) {
						if (cineplexMovies[j].getMovieTitle().equalsIgnoreCase(input)) {
							// System.out.println("Found at " + cineplexes[j].getLocation());
							return cineplexes[j].getLocation();
						}
					}
				}
			}
			System.out.println("No such movie in database.");
			return null;
		} else
			return "No cineplex in database yet.";
	}

	/**
	 * @param input
	 * @param cineplexes
	 * @return Cinema
	 */
	public static Cinema getHall(String input, Cineplex[] cineplexes) {
		if (cineplexes[0] != null) {
			for (int i = 0; i < cineplexes.length; i++) {
				Movie[] cineplexMovies = new Movie[cineplexes[i].getMovies().length];
				// System.out.println("No. of cineplex and movies is
				// "+cineplexes[i].getMovies().length);
				cineplexMovies = cineplexes[i].getMovies();
				for (int j = 0; j < cineplexMovies.length; j++) {
					if (cineplexMovies[j] != null) {
						if (cineplexMovies[j].getMovieTitle().equalsIgnoreCase(input)) {
							System.out.println("Found at " + cineplexes[i].getLocation());
							// System.out.println(input+"\t"+cineplexMovies[j].getMovieTitle());;
							return cineplexes[i].getHalls()[j];
						}
					}
				}
			}
			System.out.println("No such movie in database.");
		} else
			System.out.println("No cineplex in database yet.");
		return null;
	}

	/**
	 * @param session
	 * @return String[]
	 */
	public static String[] confirmSeat(Seatings session) throws IOException {
		int z = 0, i = 0;
		// int[] seatids = new int[cine.EmptySeats()];
		int counter = 0;
		Scanner obj = new Scanner(System.in);
		Cinema_Seat[] chosenSeats = new Cinema_Seat[session.EmptySeats()];
		String[] seatNames = new String[session.EmptySeats()];
		System.out.println("Booking of seats, enter '-' to end");

		while (true) { // loop to book select seats
			System.out.print("Enter seat number: ");
			String input = obj.nextLine();
			if (input.charAt(0) == '-') {
				break;
			}
			Cinema_Seat selectedseat = session.findSeats(input);
			if (selectedseat.isOccupied() == true) {
				System.out.println("Seat occupied!");
				continue;
			}

			// System.out.println("input is: " + input);
			else {
				// Cinema_Seat seat = session.findSeats(input);
				// seatids[counter] = seat.getSeatID();
				counter++;
				// System.out.println(seat.getSeatID());
				if (selectedseat != null) {

					session.reserveSeats(selectedseat);
					chosenSeats[i] = selectedseat;
					seatNames[i] = input;
					i++;
					z++;
				}
			}
		}
		z = 0;
		if (i != 0) {
			System.out.print("Chosen seats are: ");
			for (int j = 0; j < chosenSeats.length; j++) {
				if (chosenSeats[j] != null) {
					// System.out.printf("%d", chosenSeats[j].getSeatID());
					System.out.print(seatNames[j] + " ");
				}
			}
			session.showSeats();
			System.out.print("Confirm booking (Y/N): ");
			String input = obj.nextLine();
			if (input.charAt(0) == 'N') {
				for (int j = 0; j < chosenSeats.length; j++) {
					if (chosenSeats[j] != null) {
						session.unreserveSeats(chosenSeats[j]);
						z--;
					}
				}
				System.out.println("Bookings reversed.");
				session.showSeats();
				System.out.print("Continue booking (Y/N): ");
				input = obj.nextLine();
				if (input.charAt(0) == 'Y') { // put seats from array into bookseats
					for (int a = 0; a < chosenSeats.length; a++) {
						session.bookSeat(chosenSeats[a]);
					}
				}
			}
		}
		String[] seatIDs = new String[counter]; // remove all the null from the array
		for (int a = 0; a < counter; a++) {
			seatIDs[a] = seatNames[a];
		}
		return seatIDs;
	}

	/**
	 * @param branches
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	// Admin options
	public static void adminOptions(Cineplex[] branches) throws IOException, FileNotFoundException {
		Scanner obj = new Scanner(System.in);
		MovieList ML = new MovieList();
		Menu_Admin admin = new Menu_Admin();
		outer: while (true) {
			System.out.println();
			admin.Menu();
			int choice = Integer.parseInt(obj.nextLine());
			switch (choice) {
				case 0:
					break outer;
				case 1: { // Admin Editing Movie stuff
					inner: while (true) {
						System.out.println();
						admin.EditMovieMenu();
						choice = Integer.parseInt(obj.nextLine());
						if (choice == 0)
							break inner;
						adminMovieOptions(choice);
					}
					break;
				}
				case 2: { // Admin Editing Cinema Showtimes and assign movie
					Cineplex selectedCineplex = null;
					Cinema selectedCinema = null;
					inner: while (true) {
						System.out.println();
						admin.EditCinemaMenu();
						if (selectedCinema != null) {
							System.out.printf("---Current selected cinema %d at %s---\n",
									selectedCinema.getCinemaID(), selectedCineplex.getLocation());
						} else {
							System.out.println("--- Select a Cinema first to begin ---\n");
						}
						System.out.print("Enter your choice (0 to exit): ");
						choice = Integer.parseInt(obj.nextLine());
						if (choice != 1 && selectedCinema == null)
							continue;
						switch (choice) {
							case 0:
								break inner;
							case 1: {
								int count = 1;
								for (int i = 0; i < branches.length; i++) {
									System.out.printf("%s\n", branches[i].getLocation());
									for (int j = 0; j < branches[i].getHalls().length; j++) {
										System.out.printf("(%d) - Hall %d\n", count,
												branches[i].getHalls()[j].getCinemaID());
										count++;
									}
								}
								System.out.print("Select cinema: ");
								choice = Integer.parseInt(obj.nextLine());
								if (choice < count) {
									count = 1;
									for (int i = 0; i < branches.length; i++) {
										for (int j = 0; j < branches[i].getHalls().length; j++) {
											if (count == choice) {
												// System.out.println("Current is " + choice + count);
												selectedCineplex = branches[i];
												selectedCinema = branches[i].getHalls()[j];
												// System.out.println("CINEMA FOUND");
											}
											count++;
										}
									}
								} else
									System.out.println("Invalid option");
								break;
							}
							case 2: {
								// Add new showtime
								selectedCinema.displayShowtimes();
								System.out.print("Enter showtime in (dd/MM/yyyy HH:mm) [E.g. 22/03/2022 14:30]: ");
								String input = obj.nextLine();
								selectedCinema.addShowtimes(input);
								break;
							}
							case 3: {
								// Edit showtime
								Date[] showtimes = selectedCinema.getShowtimes();
								selectedCinema.displayShowtimes();
								System.out.print("Select showtime to edit: ");
								choice = Integer.parseInt(obj.nextLine()) - 1;
								if (choice != -1 && choice < showtimes.length) {
									System.out.print("Enter showtime in (dd/MM/yyyy HH:mm) [E.g. 22/03/2022 14:30]: ");
									String input = obj.nextLine();
									selectedCinema.editShowtimes(input, choice);
								}
								break;
							}
							case 4: {
								// Remove showtime
								Date[] showtimes = selectedCinema.getShowtimes();
								selectedCinema.displayShowtimes();
								System.out.print("Enter your choice: ");
								choice = Integer.parseInt(obj.nextLine()) - 1;
								if (choice != -1 && choice < showtimes.length) {
									selectedCinema.removeShowtimes(choice);
								}
								break;
							}
							case 5: {
								// Assign movie
								ML = new MovieList();
								Movie[] movielist = ML.getMovies();
								for (int i = 0; i < movielist.length; i++) {
									System.out.printf("(%d) %s\n", i + 1, movielist[i].getMovieTitle());
								}
								System.out.print("Select movie to assign: ");
								choice = Integer.parseInt(obj.nextLine()) - 1;
								if (choice == -1 || choice >= movielist.length)
									break;
								Movie selection = movielist[choice];
								selectedCinema.assignMovie(selection);
								System.out.printf("Current movie is %s for Cinema %d\n",
										movielist[choice].getMovieTitle(), selectedCinema.getCinemaID());
								break;
							}
							default:
								System.out.println("Invalid input.");
								break;
						}
						selectedCinema.displayShowtimes();
					}
					break;
				}
				case 3: { // Admin Editing System Settings (Price+Holidays+Toggle Rating)
					inner: while (true) {
						System.out.println();
						admin.EditSystemMenu();
						choice = Integer.parseInt(obj.nextLine());
						if (choice == 0)
							break;
						AdminSettingOptions(choice);
					}
					break;
				}
				default:
					break;
			}
		}
		System.out.println("Exiting admin menu...\n");
	}

	/**
	 * @param choice
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	static void AdminSettingOptions(int choice) throws IOException, FileNotFoundException {
		Scanner obj = new Scanner(System.in);
		MovieList ML = new MovieList();
		Menu_Admin admin = new Menu_Admin();
		switch (choice) {
			case 1: { // Edit pricings
				innerinner: while (true) {
					double data;
					Price pricings = new Price();
					System.out.println();
					pricings.printPrices();
					admin.EditTicketPrice();
					choice = Integer.parseInt(obj.nextLine());
					if (choice == 0)
						break innerinner;
					System.out.print("Enter new pricing: ");
					data = Double.parseDouble(obj.nextLine());
					switch (choice) {
						case 1:
							pricings.setPrice_PH(data);
							break;
						case 2:
							pricings.setPrice_standard(data);
							break;
						case 3:
							pricings.setPrice_seniorCitizen(data);
							break;
						case 4:
							pricings.setPrice_student(data);
							break;
						case 5:
							pricings.setPrice_normal(data);
							break;
						case 6:
							pricings.setPrice_gold(data);
							break;
						case 7:
							pricings.setPrice_platinum(data);
							break;
						case 8:
							pricings.setPrice_PREVIEW(data);
							break;
						case 9:
							pricings.setPrice_NOWSHOWING(data);
							break;
						case 10:
							pricings.setPrice_twoD(data);
							break;
						case 11:
							pricings.setPrice_threeD(data);
							break;
						default:
							System.out.println("Invalid input.");
							break;
					}
				}
				break;
			}
			case 2: { // Input public holiday in string fashion
				innerinner: while (true) {
					Calender newPH = new Calender();
					newPH.DisplayAll();
					System.out.println();
					System.out.println("Enter 0 to exit.");
					System.out.print("Enter new public holiday (DD/MM/YYYY): ");
					String input = obj.nextLine();
					if (input.charAt(0) == '0')
						break innerinner;
					newPH.setPublicHoliday(input);

				}
				break;
			}
			case 3: { // Toggle rating and total sales
				innerinner: while (true) {
					System.out.println();
					ML.rankbyAR();
					ML.rankbyTS();
					admin.ToggleSystemMenu();
					choice = Integer.parseInt(obj.nextLine());
					switch (choice) {
						case 0:
							break innerinner;
						case 1:
							ML.editRank("FALSE|FALSE");
							break;
						case 2:
							ML.editRank("FALSE|TRUE");
							break;
						case 3:
							ML.editRank("TRUE|FALSE");
							break;
						case 4:
							ML.editRank("TRUE|TRUE");
							break;
						default:
							System.out.println("Invalid input.");
							break;
					}
				}
				break;
			}
			default:
				System.out.println("Invalid input.");
				break;
		}
	}

	/**
	 * @param choice
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	static void adminMovieOptions(int choice) throws IOException, FileNotFoundException {
		Menu_Admin admin = new Menu_Admin();
		Scanner obj = new Scanner(System.in);
		MovieList ML = new MovieList();
		switch (choice) {
			case 1: { // Add new movie
				Movie[] movielist = ML.getMovies();
				Movie newMovie = new Movie(null, null, null, null, 0,
						null, null, 0, 0, null, null);
				System.out.println("-----   Enter new movie details   -----");
				System.out.print("Movie Title: ");
				String input = obj.nextLine();
				newMovie.movieTitle = input;
				System.out.print("Movie Synopsis: ");
				input = obj.nextLine();
				newMovie.Synopsis = input;
				System.out.print("Director name: ");
				input = obj.nextLine();
				newMovie.Director = input;
				System.out.print("Casts: ");
				input = obj.nextLine();
				newMovie.Casts = input;
				System.out.print("Duration: ");
				double data = Integer.parseInt(obj.nextLine());
				newMovie.Duration = data;
				System.out.print("Status: ");
				input = obj.nextLine();
				newMovie.Status = input;
				System.out.print("Type: ");
				input = obj.nextLine();
				newMovie.Type = input;
				System.out.print("Total Sales: ");
				data = Integer.parseInt(obj.nextLine());
				newMovie.totalSales = data;
				// Avg rating should be added based on ratings string
				// System.out.print("Average Rating: ");
				// input = obj.nextLine();
				// newMovie.movieTitle = input;
				System.out.print("Reviews: ");
				input = obj.nextLine();
				newMovie.Reviews = input;
				System.out.print("Ratings: ");
				input = obj.nextLine();
				newMovie.Ratings = input;
				newMovie.avgRating = newMovie.calculateAvgRating(newMovie.Ratings);
				ArrayList<Movie> arrayList = new ArrayList<Movie>(Arrays.asList(movielist));
				arrayList.add(newMovie);
				movielist = arrayList.toArray(movielist);
				newMovie.Create();
				// System.out.println("Movie added");
				displayAllMovies(movielist);
				break;
			}
			case 2: {
				System.out.println("-----   Edit movie details   -----");
				Movie[] movielist = ML.getMovies();
				for (int i = 0; i < movielist.length; i++) {
					System.out.printf("(%d) %s\n", i + 1, movielist[i].getMovieTitle());
				}
				System.out.println("(0) Exit editing movie");
				System.out.print("Select movie to edit: ");
				choice = Integer.parseInt(obj.nextLine()) - 1;
				if (choice == -1 || choice >= movielist.length)
					break;
				Movie selected = movielist[choice];
				selected.display();
				MovieEdit EditMovie = (MovieEdit) selected;
				admin.EditMovieDetails();
				choice = Integer.parseInt(obj.nextLine());
				switch (choice) {
					case 0:
						break;
					case 1:
						System.out.print("New movie title: ");
						String input = obj.nextLine();
						EditMovie.setMovieTitle(input);
						break;
					case 2:
						System.out.print("New synopsis: ");
						input = obj.nextLine();
						EditMovie.setSynopsis(input);
						break;
					case 3:
						System.out.print("New director: ");
						input = obj.nextLine();
						EditMovie.setDirector(input);
						break;
					case 4:
						System.out.print("New casts: ");
						input = obj.nextLine();
						EditMovie.setCasts(input);
						break;
					case 5:
						System.out.print("New duration: ");
						double data = Integer.parseInt(obj.nextLine());
						EditMovie.setDuration(data);
						break;
					case 6:
						System.out.print("New status: ");
						input = obj.nextLine();
						EditMovie.setStatus(input);
						break;
					case 7:
						System.out.print("New type: ");
						input = obj.nextLine();
						EditMovie.setType(input);
						break;
					case 8:
						System.out.print("New total sales: ");
						input = obj.nextLine();
						EditMovie.settotalSales(Double.parseDouble(input));
						break;
					case 9:
						System.out.print("Updating average rating...");
						EditMovie.setavgRating(selected.calculateAvgRating(selected.Ratings));
						break;
					case 10:
						System.out.print("Edit review in progress");
						input = obj.nextLine();
						EditMovie.setReviews(input);
						break;
					case 11:
						System.out.print("Edit ratings in progress: ");
						input = obj.nextLine();
						EditMovie.setRatings(input);
						break;
					default:
						System.out.println("Invalid input.");
						break;
				}
				// System.out.printf("Movie %s has been edited.\n", selected.getMovieTitle());
				break;
			}
			case 3: {
				// Remove movie listing
				Movie[] movielist = ML.getMovies();
				System.out.println("-----   Delete movie   -----");
				for (int i = 0; i < movielist.length; i++) {
					System.out.printf("(%d) %s\n", i + 1, movielist[i].getMovieTitle());
				}
				System.out.println("(0) Exit removing movie");
				System.out.print("Select movie to remove: ");
				choice = Integer.parseInt(obj.nextLine()) - 1;
				if (choice == -1 || choice >= movielist.length)
					break;
				Movie selected = movielist[choice];
				selected.display();
				System.out.println("Confirm delete? (Y/N): ");
				String input = obj.nextLine();
				if (input.charAt(0) == 'Y') {
					MovieEdit EditMovie = (MovieEdit) selected;
					EditMovie.setStatus("ENDSHOW");
				}
				break;
			}
			default:
				System.out.println("Invalid input.");
				break;
		}
	}
}