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
		// set the Administrator Credentials
		int adminLoginID = ATMSystem.encrypt(123); 
		int adminPassWord = ATMSystem.encrypt(123);
		new ATMSystem(denominations,13.5);
		// Create Accounts a/c numbers and passwords are integers from [1e5,1e6);
		// A joint account of 3 members // names and passwords at corresponding indices are mapped
		Account A1 = new Account();
		String[] names = new String[3];
		int [] passwords = new int[3];
		names[0]="DFS";
		names[1]="BFS";
		names[2]="DP";
		passwords[0]=ATMSystem.encrypt(12345);
		passwords[1]=ATMSystem.encrypt(12344);
		passwords[2]=ATMSystem.encrypt(12343);
		A1.set(11111,passwords,"StateBank",names);
		ATMSystem.Ac.add(A1);
		Account A2 = new Account();
		names = new String[1];
		names[0] = "DIJKSTRA";
		passwords = new int[1];
		passwords[0] = ATMSystem.encrypt(12342);
		A2.set(11114,passwords,"UnionBank",names);
		ATMSystem.Ac.add(A2);

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
			System.out.println("-----------------------");
			System.out.println("Continue:? [Y/N]");
			char c = sc.next().charAt(0);
			if(c=='Y') end = false;
			else end =  true;
		}
		sc.close();
	}
}

