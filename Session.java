public class Session {
	String SessionID;
	boolean successful;
	Transaction T;
	Session(String SessionID){
		this.SessionID = SessionID;
		successful = false;
		T = null;
		start();
	}
	private void start() {
		int accountNumber,passWord;
		System.out.print("Enter Account Number:");
		accountNumber = ATM.sc.nextInt();
		System.out.print("Enter Password:");
		System.out.println();
		passWord = ATM.sc.nextInt();
		if(ATMSystem.isValid(accountNumber,passWord)) {
		   this.successful = true;
		   System.out.println("1.Transfer Funds");
		   System.out.println("2.Add Funds");
		   System.out.println("3.Withdraw Funds");
		   System.out.println("4.View Balance");
		   int op = ATM.sc.nextInt();
		   switch(op) {
		   	 case 1:{
		   		 T = new TransferFunds(accountNumber);
		   		System.out.println(T.getInfo());
		   		 break;
		   	 }
		   	 case 2:{
		   		T = new AddFunds(accountNumber);
		   		System.out.println(T.getInfo());
		   		 break;
		   	 }
		   	 case 3:{
		   		T = new WithdrawFunds(accountNumber);
		   		System.out.println(T.getInfo());
		   		 break;
		   	 }
		   	 case 4:{
		   		T = new ViewBalance(accountNumber);
		   		System.out.println(T.getInfo());
		   		 break;
		   	 }
		   	 default:{
		   		 System.out.println("Wrong Input!!!");
		   		 successful = false;
		   		 break;
		   	 }
		   }
		}
		else {
			System.out.println("No User found!!");
		}
	}
	void getDetails() {
		if(!successful) {
			System.out.println("Invalid Details!!");
		}
		else {
			System.out.println(T.accountNumber +": "+T.getInfo());
		}
	}
}
