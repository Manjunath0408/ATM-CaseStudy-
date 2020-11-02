public class Admin {
	private static int loginID;
	private static int passWord;
	Admin(int loginID,int passWord){
		Admin.loginID = loginID;
		Admin.passWord = passWord;
	    display();
	}
	private void display() {
		System.out.println("Enter the LoginID: ");
		int login = ATM.sc.nextInt();
		System.out.println("Enter the password: ");
		int pass = ATM.sc.nextInt();
		if(login == loginID && pass == passWord) {
			System.out.println("1. Get Session Details");
			System.out.println("2. Cash and Dominations");
			int op = ATM.sc.nextInt();
			if(op==1) {
				if(!ATM.Sessions.isEmpty()) {
					for(Session s:ATM.Sessions) {
						s.getDetails();
					}
				}
				else {
					System.out.println("No Transactions");
				}
				System.out.println("-----------------------");
			}
			else {
				ATMSystem.getCashAndDenominations();
			}
		}
		else {
			System.out.println("Wrong Credentials!");
		}
	}
}
