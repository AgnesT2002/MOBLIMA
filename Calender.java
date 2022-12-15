import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @author Zhi Yong
 * @version 1.0
 * 2022-11-08
 */
public class Calender {
	private int DayOfWeek;
	private Date date;
	private Calendar cal = Calendar.getInstance();
	private Date[] ph = new Date[10];

	/**
	 * @throws IOException If there is an input/output error
	 */
	public void DisplayAll() throws IOException {
		FileReader fstream = new FileReader("PublicHoliday.txt");
		BufferedReader br = new BufferedReader(fstream);
		String line;
		while ((line = br.readLine()) != null) {
			System.out.printf("%s \n", line);
		}
	}
	// public PHList() throws IOException {
	// String fileName = "PublicHoliday.txt";
	// Path path = Paths.get(fileName);
	// n = (int) Files.lines(path).count()-1;
	// ph = new Date[n];
	// Scanner read = new Scanner(new File(fileName));
	// ArrayList<Date> stringArray = (ArrayList<Date>) read(fileName);
	// }

	public void setPublicHoliday(String date) throws IOException {
		String filename = "PublicHoliday.txt";
		FileWriter fstream = new FileWriter(filename, true);
		BufferedWriter out = new BufferedWriter(fstream);

		String append = date;
		out.write(append);
		out.newLine();

		// close buffer writer
		out.close();

		System.out.println("PH added ");
	}

	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	SimpleDateFormat hhmm = new SimpleDateFormat("HH:mm");
	SimpleDateFormat ddmmyyyy = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * @param d
	 * @return Date
	 * @throws ParseException
	 */
	public Date getdate() { // get date now
		return this.date;
	}

	/**
	 * @param d
	 * @return Date
	 * @throws ParseException
	 */
	public Date setDate(String d) throws ParseException { // set the date
		date = dateFormatter.parse(d);
		return date;
	}

	/**
	 * @param d
	 * @return Date
	 * @throws ParseException
	 */
	public Date setHHMM(String d) throws ParseException {

		date = hhmm.parse(d);
		return date;
	}

	/**
	 * @param date
	 * @return int
	 * @throws IOException
	 */
	public int getDayOfWeek(Date date) { // sun = 1
		cal.setTime(date);
		DayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return DayOfWeek;
	}

	/**
	 * @param date
	 * @throws IOException
	 */
	public void unsetPublicHoliday(String date) throws IOException {
		File inputFile = new File("PublicHoliday.txt");
		File tempFile = new File("TempFile.txt");

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String line;
		String lineToRemove = null;

		while ((line = reader.readLine()) != null) {
			if (date.equals(line)) {
				lineToRemove = reader.readLine();
				break;
			}
		}
		// if (line == null)
		// return;
		String currentLine;

		while ((currentLine = reader.readLine()) != null) {
			// trim newline when comparing with lineToRemove
			String trimmedLine = currentLine.trim();
			if (trimmedLine.equals(lineToRemove))
				continue;
			writer.write(currentLine + System.getProperty("line.separator"));
		}
		writer.close();
		reader.close();
		boolean successful = tempFile.renameTo(inputFile);

		System.out.println("PH removed");

	}

	/**
	 * @param date
	 * @return boolean
	 * @throws IOException
	 */
	public boolean checkPH(String date) throws IOException {

		FileReader fstream = new FileReader("PublicHoliday.txt");
		BufferedReader br = new BufferedReader(fstream);
		String line;
		date = date.substring(0, 10);
		// System.out.printf("%s",date);
		while ((line = br.readLine()) != null) {
			if (line.equals(date))
				return true;
		}
		return false;
	}
}