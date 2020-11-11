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
		if(ATMSystem.isValid(accountNumber,(passWord))) {
		   int OTP = ATMSystem.getOTP();
		   System.out.println("OTP has been sent to the Registered Phone Number");
		   System.out.println("OTP: "+OTP);
		   System.out.println("Please Enter the OTP: ");
		   int receivedOTP = ATM.sc.nextInt();
		   if(OTP == receivedOTP) {
			   this.successful = true;
			   System.out.println("-----------------------");
			   System.out.println("Hey, "+ATMSystem.getName(accountNumber,passWord));
			   System.out.println("1.Transfer Funds");
			   System.out.println("2.Add Funds");
			   System.out.println("3.Withdraw Funds");
			   System.out.println("4.View Balance");
			   System.out.println("5.Get MiniStatement");
			   System.out.println("6.Change PIN");
			   int op = ATM.sc.nextInt();
			   switch(op) {
			   	 case 1:{
			   		T = new TransferFunds(accountNumber);
			   		ATMSystem.addTransactions(accountNumber, T.getInfo(),passWord);
			   		System.out.println(T.getInfo());
			   		 break;
			   	 }
			   	 case 2:{
			   		T = new AddFunds(accountNumber);
			   		ATMSystem.addTransactions(accountNumber, T.getInfo(),passWord);
			   		System.out.println(T.getInfo());
			   		 break;
			   	 }
			   	 case 3:{
			   		T = new WithdrawFunds(accountNumber);
			   		ATMSystem.addTransactions(accountNumber, T.getInfo(),passWord);
			   		System.out.println(T.getInfo());
			   		
			   		 break;
			   	 }
			   	 case 4:{
			   		T = new ViewBalance(accountNumber);
			   		System.out.println(T.getInfo());
			   		 break;
			   	 }
			   	 case 5:{
			   		T = new MiniStatement(accountNumber);
			   		break;
			   	 }
			   	 case 6:{
			   		 T = new changePassWord(accountNumber,passWord);
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
			   System.out.println("Incorrect OTP!!");
		   }
		}
		else {
			System.out.println("No User found!!");
		}
	}
	void getDetails() {
		if(!successful) {
			System.out.println("[Failed] Invalid Details!!");
		}
		else {
			System.out.println(T.accountNumber +": "+T.getInfo());
		}
	}
}
