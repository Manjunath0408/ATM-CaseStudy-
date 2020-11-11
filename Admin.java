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
		if(ATMSystem.encrypt(login) == loginID && ATMSystem.encrypt(pass) == passWord) {
			System.out.println("1. Get Session Details");
			System.out.println("2. Cash and Dominations");
			int op = ATM.sc.nextInt();
			if(op==1) {
				if(!ATM.Sessions.isEmpty()) {
					int cnt=1;
					for(Session s:ATM.Sessions) {
						System.out.print(cnt+". ");
						s.getDetails();
						cnt++;
					}
				}
				else {
					System.out.println("No Transactions");
				}
				
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
