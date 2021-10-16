import javax.swing.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.io.FileWriter; // Import the FileWriter class
import java.io.File;
import java.io.IOException; // Import the IOException class to handle errors
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.lang.StringBuilder;

public class PWGUI {

	PWVerify pwverify = new PWVerify();

	private String username = "masonmcgerry"; // takes username with method
	// need to make new method for obtaining username

	private boolean enterPW; // verify if enter button pressed
	private boolean changePW; // goto change password screen
	private boolean passwordSet; // retrieves setPW indicating the password must be reset to change it not set

	private String password; // to open passwords display gui
	private String newPassword; // for resetting password

	private String setPassword1; // for initial setting of password 1
	private String setPassword2; // for initial setting of password 2

	private String resetPassword1; // for holding password input 1
	private String resetPassword2; // for holding password input 2

	private String passwordPath = "/Users/" + username + "/PWManager/";
	private String isSetupPath = "/Users/" + username + "/PWManager/.isSetup.txt";

	Border loweredbevel = BorderFactory.createLoweredBevelBorder(); // creates beveled border, used as param
	CardLayout cardLayout = new CardLayout();
	boolean p1 = false;

	// ***** run GUI METHODS *****

	// method to run window
	public void window() {

		JFrame frame = new JFrame("Password Manager"); // creating instance of JFrame
		JPanel panel = new JPanel(); // creates panel for createAccount, signIn, and resetPassword panels to sit in
		JPanel createAccount = new JPanel(); // creates createAccount
		JPanel signIn = new JPanel(); // creates signIn
		JPanel passwordList = new JPanel();
		JPanel resetPassword = new JPanel(); // creates resetPassword
		JLabel createAcct = new JLabel("Create Account"); // create label for create account panel
		createAcct.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel login = new JLabel("Log in"); // create label for log in panel
		login.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel resetPW = new JLabel("Reset Password"); // create label for reset pw panel

		// first time setup
		JLabel nPL1 = new JLabel("Enter New Password"); // new password label
		nPL1.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel nPL2 = new JLabel("Enter New Password again"); // new password label
		nPL2.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPasswordField nP1 = new JPasswordField(); // new password 1
		nP1.setMaximumSize(new Dimension(300, 30));
		nP1.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPasswordField nP2 = new JPasswordField(); // new password 2
		nP2.setMaximumSize(new Dimension(300, 30));
		nP2.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton sEnter = new JButton("Enter"); // enter button for set password
		sEnter.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel mismatched = new JLabel("Mismatched Passwords, try again");
		mismatched.setVisible(false);
		mismatched.setAlignmentX(Component.CENTER_ALIGNMENT);

		// login
		JLabel pL = new JLabel("Enter Password"); // password label
		pL.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPasswordField p = new JPasswordField(); // new password 1
		p.setMaximumSize(new Dimension(300, 30));
		p.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton enter = new JButton("Enter"); // enter button for set password
		enter.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel wrong = new JLabel("Wrong password, try again");
		wrong.setVisible(false);
		wrong.setAlignmentX(Component.CENTER_ALIGNMENT);

		// all passwords
		JLabel passList = new JLabel("All Passwords");
		passList.setAlignmentX(Component.CENTER_ALIGNMENT);
		// make add/delete on each field
		JButton addField = new JButton("Add");
		JButton removeField = new JButton("Delete");
		
		// reset password

		panel.setLayout(cardLayout);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); // center on Y Axis

		panel.setAlignmentX(Component.CENTER_ALIGNMENT); // centers component on Y Axis ^
		panel.setPreferredSize(new Dimension(500, 500)); // keeps this dimension even when frame is smaller
		panel.setMaximumSize(new Dimension(500, 500)); // keeps this dimension even when frame is larger

		// account setup screen
		createAccount.setLayout(new BoxLayout(createAccount, BoxLayout.PAGE_AXIS));
		createAccount.setAlignmentX(Component.CENTER_ALIGNMENT); // centers component on Y Axis ^
		createAccount.setPreferredSize(new Dimension(200, 200)); // keeps this dimension even when frame is smaller
		createAccount.setMaximumSize(new Dimension(200, 200)); // keeps this dimension even when frame is larger
		createAccount.setBorder(loweredbevel);
		createAccount.add(createAcct);
		createAccount.add(nPL1);
		createAccount.add(nP1);
		createAccount.add(nPL2);
		createAccount.add(nP2);
		createAccount.add(sEnter);
		createAccount.add(mismatched);

		// login screen
		signIn.setLayout(new BoxLayout(signIn, BoxLayout.PAGE_AXIS));
		signIn.setAlignmentX(Component.CENTER_ALIGNMENT); // centers component on Y Axis ^
		signIn.setPreferredSize(new Dimension(200, 200)); // keeps this dimension even when frame is smaller
		signIn.setMaximumSize(new Dimension(200, 200)); // keeps this dimension even when frame is larger
		signIn.setBorder(loweredbevel);
		signIn.add(login);
		signIn.add(pL);
		signIn.add(p);
		signIn.add(enter);
		signIn.add(wrong);

		// all passwords screen
		passwordList.setLayout(new BoxLayout(passwordList, BoxLayout.PAGE_AXIS));
		passwordList.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordList.setPreferredSize(new Dimension(200, 200)); // keeps this dimension even when frame is smaller
		passwordList.setMaximumSize(new Dimension(200, 200)); // keeps this dimension even when frame is larger
		passwordList.setBorder(loweredbevel);
		passwordList.add(passList);
		passwordList.add(addField);
		passwordList.add(removeField);

		// reset password screen
		resetPassword.setAlignmentX(Component.CENTER_ALIGNMENT); // centers component on Y Axis ^
		resetPassword.setPreferredSize(new Dimension(200, 200)); // keeps this dimension even when frame is smaller
		resetPassword.setMaximumSize(new Dimension(200, 200)); // keeps this dimension even when frame is larger
		resetPassword.setBorder(loweredbevel);
		resetPassword.add(resetPW);

		panel.add(createAccount, "link1");
		panel.add(signIn, "link2");
		panel.add(resetPassword, "link3");
		panel.add(passwordList, "link4");

		// add components to frame, *** order matters ***

		frame.getContentPane().add(panel); // add panel to the frame
		// frame.getContentPane().add(b); // add change window button to frame

		frame.setSize(450, 450); // 400 width and 500 height
		frame.setVisible(true); // making the frame visible

		// set password enter button
		sEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] input1 = nP1.getPassword();
				setPassword1 = ("" + new String(input1)); // Reuben fixed
				char[] input2 = nP2.getPassword();
				setPassword2 = ("" + new String(input2)); // Reuben fixed

				if (setPassword1.equals(setPassword2)) {
					System.out.println(setPassword1);
					System.out.println(setPassword2);// good password
					mismatched.setVisible(false);
					// passwordSucceeded.setVisible(true);
					writePW(hash(setPassword1));
					accountIsSetup(); // writes true to .isSetup.txt
					cardLayout.show(panel, "link4");
				} else {
					mismatched.setVisible(true);
				}

			}
		});

		// enter password button
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] input1 = p.getPassword();
				password = ("" + new String(input1)); // Reuben fixed

				if (hash(password).equals(pwverify.readHashedPW())) {
					System.out.println(hash(password));// good password
					System.out.println(pwverify.readHashedPW());
					wrong.setVisible(false);
					// passwordSucceeded.setVisible(true);
					cardLayout.show(panel, "link4");
				} else {
					wrong.setVisible(true);
					System.out.println(password);// good password
					System.out.println(pwverify.readHashedPW());

				}

			}
		});

		// will only show setup panel if first time setup
		if (pwverify.readSetupComplete() == false) {
			cardLayout.show(panel, "link1");
		} else {
			cardLayout.show(panel, "link2");
		}

	}

	// run reset password graphical interface
	public void runResetPassword() {
		String newPass1 = ""; // for holding password input 1
		String newPass2 = ""; // for holding password input 2

		JFrame f = new JFrame("Change Password");// creating instance of JFrame

		JLabel mismatched = new JLabel("Mismatched Passwords, try again");
		mismatched.setVisible(false);

		JLabel oPL = new JLabel("Current Password");
		oPL.setBounds(130, 70, 140, 40);
		JPasswordField cP = new JPasswordField(); // current password
		cP.setBounds(130, 100, 100, 40);
		JLabel nPL1 = new JLabel("New Password");
		JPasswordField nP1 = new JPasswordField(); // new password 1
		nP1.setBounds(130, 140, 100, 40);
		JLabel nPL2 = new JLabel("Original");
		JPasswordField nP2 = new JPasswordField(); // new password 2
		nP2.setBounds(130, 180, 100, 40);
		JButton bEnter = new JButton();

		f.add(oPL);
		f.add(cP);
		f.add(nPL1);
		f.add(nP1);
		f.add(nPL2);
		f.add(nP2);
		f.add(bEnter);

		f.setSize(400, 500);
		f.setLayout(null);
		f.setVisible(true);

		bEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (newPass1 == newPass2) {
					changePW = true; // toggles boolean to true after condition met
				} else {
					mismatched.setVisible(true);
				}
			}
		});

	}

	// ***** Getter, and Write METHODS *****

	// getter method for password
	public String getPW() {
		return password; // is private
	}

	// get passwordSet status
	public boolean getPasswordSet() {
		return passwordSet;
	}

	// getter method for getting newPassword
	public String getNewPW() {
		return newPassword;
	}

	// getter method for enterPW, which triggers Main while loop to end
	public Boolean getEnterPW() {
		return enterPW;
	}

	// getter method for changePW
	public Boolean getChangePW() {
		return changePW;
	}

	// creates hash from text input, returns string of hash
	public String hash(String toBeHashed) {
		// String hashed = (new Integer(toBeHashed.hashCode())).toString();
		String hashed = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(toBeHashed.getBytes(StandardCharsets.UTF_8));
			// Convert byte array into signum representation
			BigInteger number = new BigInteger(1, encodedhash);
			// Convert message digest into hex value
			StringBuilder hexString = new StringBuilder(number.toString(16));
			// Pad with leading zeros
			while (hexString.length() < 32) {
				hexString.insert(0, '0');
			}
			hashed = ("" + new String(hexString.toString()));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Didn't write");
		}
		return hashed;
	}

	// write new hashed password into .password file
	public void writePW(String hashedPW) {
		File f = new File(passwordPath); // for testing if path is writeable to
		if (f.exists()) {
			try {
				FileWriter writer = new FileWriter(passwordPath + ".password.txt");
				writer.write(hashedPW);
				writer.close();
				System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		} else {
			f.mkdir();
			try {
				FileWriter writer = new FileWriter(passwordPath + ".password.txt");
				writer.write(hashedPW);
				writer.close();
				System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			System.out.println("Not written");
		}

	}

	// method to write to isSetup file
	// may rewrite methods for isSetup file so it's just a string written in the
	// file, the files creation and the string indicating setup is complete
	public void accountIsSetup() {
		try {
			FileWriter writer = new FileWriter(isSetupPath);
			writer.write("true");
			writer.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	// will write another run method that displays password list for user after
	// PWVerify succeeds, may use a attempt limit of 3 or before random length every
	// higher timeouts between attempts

}

/*
 * SOURCES:
 * 
 * https://www.javatpoint.com/java-swing
 * 
 * https://www.geeksforgeeks.org/java-swing-jpasswordfield/
 * 
 * https://www.w3schools.com/java/java_files_create.asp
 * 
 * https://www.baeldung.com/sha-256-hashing-java
 * 
 * https://stackoverflow.com/questions/7357852/write-int-to-text-file-using-
 * writer
 * 
 * https://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-
 * jframe
 * 
 * https://stackoverflow.com/questions/15633692/java-io-filenotfoundexception-
 * when-opening-file-with-filewriter-in-java
 * 
 * https://stackoverflow.com/questions/6231779/how-to-check-write-permissions-of
 * -a-directory-in-java
 * 
 * https://thingnoy.medium.com/how-to-set-directory-permission-in-java-
 * 3bf8643cd64e
 * 
 * https://www.geeksforgeeks.org/sha-256-hash-in-java/
 * 
 * https://stackoverflow.com/questions/17405714/how-to-return-a-value-from-try-
 * catch-and-finally
 * 
 * https://stackoverflow.com/questions/18497982/how-do-i-dynamically-create-
 * multiple-swing-objects-and-remove-them-one-at-a-time
 * 
 */
