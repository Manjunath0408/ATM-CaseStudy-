import java.util.*;

public class ATM {
	static ArrayList<Session> Sessions = new ArrayList<Session> (); // maybe stored in the database
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		boolean end = false;
		// Manually add denominations
		int [] denominations = new int[3];
		denominations[0] =3;
		denominations[1] =3;
		denominations[2] =3;
		// set the Admin Credentials
		int adminLoginID = 123; 
		int adminPassWord = 123;
		new ATMSystem(denominations);
		// Create Accounts a/c numbers and passwords are integers from [1e5,1e6);
		ATMSystem.Ac.add(new Account(11111,12345));
		ATMSystem.Ac.add(new Account(11112,12344));
		ATMSystem.Ac.add(new Account(11113,12343));
		ATMSystem.Ac.add(new Account(11114,12342));
		while(!end) {
			System.out.println("1. Admin");
			System.out.println("2. Customer");
			int op = sc.nextInt();
			if(op == 1) {
				new Admin(adminLoginID,adminPassWord);
			}
			else {
				Session s = new Session("123");
				Sessions.add(s);
			}
			System.out.println("Continue:? [Y/N]");
			char c = sc.next().charAt(0);
			if(c=='Y') end = false;
			else end =  true;
		}
		sc.close();
	}
}

