# MOBLIMA
Object-Oriented Design &amp; Programming Assignment

Group Members: 

	1. https://github.com/delaneyquek
	2. https://github.com/alanwalker23
	3. https://github.com/notmymainlzy
	4. https://github.com/keithajy

The assignment for your group will be to design and develop a Console-based application
(non-Graphical UI): MOvie Booking and LIsting Management Application (MOBLIMA)

MOBLIMA is an application to computerize the processes of making online booking and purchase of movie tickets, listing of movies and sale reporting. It will be used by the moviegoers and cinema staff.

The following are information about the application:

• The application acts as a centralized ‘location’ for making bookings for all the Cineplexes in different locations managed by the vendor.

• Each Cineplex will have 3 or more cinemas.

• The movie ticket price can be charged according to the following type :

	a. type of movie (3D, Blockbuster, etc.),
	b. class of cinema (e.g. Platinum Movie Suites)
	c. age of movie-goer (e.g. adult, senior citizen, child)
	d. day of the week or public holiday.

• Movie listings and showtimes can be queried and booking can be made.

• From the movie listings, movie-goer can also view the information about the movie.

Information like:

	a. Movie title
	b. Showing status [Coming Soon, Preview, Now Showing]
	c. SYNOPSIS – movie abstract
	d. Director
	e. Cast (at least 2)
	f. Overall reviewer rating (1 – 5 [best])
	g. Past reviews and reviewers’ ratings

• Movie-goer can also enter his/her review and rating. [The overall rating is the average (to 1 decimal place) of the all individual ratings for the particular movie

• A layout of each cinema will be presented for seat selections upon booking. [the layout will contain aisle and main stairway. Refer to Appendix A for reference]

• Upon booking, the application will capture the movie-goer’s name, mobile number and email address. Each payment will have a transaction id (TID). The TID is of the format XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m : minutes, XXX : cinema code in letters). [ you will decide on the cinema code]

• Movie-goer can check their history of bookings. 


• For cinema staff only :

	a. Cinema staff needs to login to access its functions
	b. Cinema staff can configure the system settings (e.g., ticket prices, holidays, etc.)
	c. Cinema staff can enter the forthcoming movies, its type (Blockbuster/3D, etc.), movie rating (e.g. PG), show times, the cinema, showing status (Coming Soon, Preview, Now Showing, End Of Showing), etc.
	d. Cinema staff can also update the details of the movies or remove the movie by changing the status to ‘End of Showing’.
	e. Cinema staff can list the current top 5 ranking movies by
		i. Ticket sales (display the movie title and total sales)
		ii. Overall reviewers’ rating (display the movie title and overall rating)
		
The application is to be developed as a Console-based application (non-Graphical UI).

Data should be stored in flat file format, either in text or binary

Assumptions :

	- This is a single-user application and there is no need to consider concurrent access.
	- THREE cineplexes will be created for the demonstration.
	- The currency will be in Singapore Dollar (SGD) and inclusive of Good and Services Tax (GST).
	- A simple login for cinema staff is sufficed.
	- Payment will always be successful.
	- There is no need to interface with external system, e.g. Payment, printer, etc., but you can consider it in your design.
	- Senior citizen can be purchased online without validation of identity or age. The validation will be done upon entering the cinema.
