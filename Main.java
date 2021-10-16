public class Main {

	public static void main(String[] args) {
		System.out.println("Start"); // beginning of program

		boolean endLogin = false; //
		boolean setupStatus = false; // flips to true when setup has been done and user has password

		PWGUI pwgui = new PWGUI();
		PWVerify pwverify = new PWVerify();

		setupStatus = pwverify.readSetupComplete();
		
		pwgui.window();
		

		// while (pwgui.getPasswordSet() == false) {
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			System.out.println("got interrupted!");
//		}
//
//		if (setupStatus == false) {
//			pwgui.runSetPassword();
//		} else {
//			pwgui.runLogin();
//		}
		// }

		// pwgui.runLogin();
		// create larger loop for going between GUI's
//		while (endLogin == false) {
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				System.out.println("got interrupted!");
//			}
//			if (pwgui.getEnterPW() == true) {
//				// evaluate hash of new password
//				if (pwverify.verifyPW(pwgui.hash(pwgui.getPW())) == true) {
//					endLogin = true;
//				}
//			}
//			if (pwgui.getChangePW() == true) {
//				pwgui.runResetPassword(); // go to change password GUI
//				endLogin = true;
//			}
//		}
		// System.out.println("Password: " + pwgui.getPW());
	}
}


/*
 * SOURCES:
 *
 * https://stackoverflow.com/questions/2663419/sleep-from-main-thread-is-
 * throwing-interruptedexception
 * https://www.quora.com/How-do-I-use-method-from-another-Java-class
 */