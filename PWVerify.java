import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class PWVerify {
	// if hashed password is correct flip to true
	private boolean correctPW;
	private String savedHashedPW; // the main password
	private boolean isSetup; // for setupComplete method
	// I use Mac which is unix based like linux, you'll probably use a different
	// file path
	// we can figure out how to make the app recognize which OS it's downloading to,
	// or make a separate download for a few OS's
	private String passwordPath = "/Users/masonmcgerry/PWManager/.password.txt"; // .password makes it a hidden file
	private String isSetupPath = "/Users/masonmcgerry/PWManager/.isSetup.txt";

	// reads hashed password from permission protected file
	public String readHashedPW() {
		String savedHash = "";
		try {
			File file = new File(passwordPath);
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				savedHash = reader.nextLine();
				// savedHashedPW = savedHash;
				//System.out.println(savedHash);
			}

			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return savedHash;
	}

	// hashes password, verifies against hashed password, stored in specific
	// directory & hidden
	public boolean verifyPW(String hashedPW) {
		// checks if the password is correct
		if (hashedPW.equals(savedHashedPW)) {
			correctPW = true;
		}
		return correctPW;
	}

	// method to read permission protected file, parse boolean indicating if
	// this is login has been setup or not
	// this will stop someone from setting the password without using the current
	// password
	public boolean readSetupComplete() {
		String isSetupS = "";
		try {
			File file = new File(isSetupPath);
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				isSetupS = reader.nextLine();

			}

			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("First run, create account");
		}
		isSetup = Boolean.parseBoolean(isSetupS);
		return isSetup;
	}

}

/*
 * SOURCES: https://www.w3schools.com/java/java_files_read.asp
 * 
 */