//1.Transfer Funds
//2.Add Funds
//3.Withdraw Funds
//4.View Balance
// Transaction class is extended by other classes
class Transaction{
	String details;
	boolean status;
	int accountNumber;
	Transaction(int accountNumber){
		details = "";
		status = false;
		this.accountNumber = accountNumber;
	}
	String getInfo() {
		return this.details;
	}
	boolean getStatus() {
		return this.status;
	}
}
class TransferFunds extends Transaction{
	private int otherAccountNumber;
	private double amount;
	TransferFunds(int accountNumber){
		super(accountNumber);
		start();
	}
	private void start() {
		System.out.println("Enter the Account Number");
		otherAccountNumber = ATM.sc.nextInt();
		if(ATMSystem.isValid(otherAccountNumber)) {
			System.out.println("Enter the Amount");
			amount = ATM.sc.nextDouble();
			double charges = 0.0;
			if(ATMSystem.getBankName(otherAccountNumber).equals(ATMSystem.getBankName(accountNumber))) {
				charges = ATMSystem.getProcessingFee();
			}
			if(ATMSystem.getBalance(this.accountNumber) >= amount+charges) {
				ATMSystem.AddFunds(otherAccountNumber, amount);
				ATMSystem.DeductFunds(this.accountNumber, amount+charges);
				this.status = true;
				this.details += "[Successful] ";
			}
			else {
				this.status = false;
				this.details += "[Failed] ";
			}
			this.details += "From: "+accountNumber + " To: "+otherAccountNumber+" Amount: "+amount;
		}
		else {
			this.status = false;
			this.details += "[Failed] ";
		}
	}
}
class AddFunds extends Transaction{
	private double amount;
	AddFunds(int accountNumber){
		super(accountNumber);
		start();
	}
	private void start() {
		System.out.println("Please Insert the Cash(As 100s,500s,2000s)");
		int [] denom = new int[3];
		for(int i=0;i<3;i++) denom[i] = ATM.sc.nextInt();
		amount = denom[0]*100 + denom[1]*500 + denom[2]*2000;
		System.out.println("1.Self");
		System.out.println("2.Other Account");
		int op = ATM.sc.nextInt();
		if(op == 1) {
			ATMSystem.AddFunds(accountNumber, amount,denom);
			this.details += "[Successful] "+"Added Rs: "+amount+ " To: "+accountNumber;
			this.status = true;
		}
		else if(op == 2) {
			System.out.println("Enter the account number");
			int otherAccountNumber = ATM.sc.nextInt();
			if(ATMSystem.isValid(otherAccountNumber)) {
				ATMSystem.AddFunds(otherAccountNumber, amount,denom);
				this.details += "[Successful] "+"Added Rs: "+amount+ " To: "+otherAccountNumber;
				this.status = true;
			}
			else {
				this.status = false;
				this.details += "[Failed] ";		
			}
		}
		else {
			this.status = false;
			this.details += "[Failed] ";
		}
	}
}
class WithdrawFunds extends Transaction{
	private double amount;
	WithdrawFunds(int accountNumber){
		super(accountNumber);
		start();
	}
	private void start() {
		System.out.println("Enter the Amount(Multiples of 100s):");
		amount = ATM.sc.nextDouble();
		if(ATMSystem.getBalance(accountNumber) >= amount && ATMSystem.getMachineBalance() >= amount && ATMSystem.drawable(amount)) {
			ATMSystem.DeductFunds(accountNumber, amount);
			System.out.println("Please take the Cash!!");
			this.details+= "[Successful] Withdrawal of Rs: "+amount;						
		}
		else {
			System.out.println("Not Enough Funds");
			this.details += "[Failed] Requested Amount: "+amount;
			this.status = false;
		}
	}
}
class ViewBalance extends Transaction{
	ViewBalance(int accountNumber){
		super(accountNumber);
		start();
	}
	private void start() {
		this.details += "Viewed Balance: "+ATMSystem.getBalance(accountNumber);
		this.status=true;
	}
}
class MiniStatement extends Transaction{
	MiniStatement(int accountNumber){
		super(accountNumber);
		start();
	}
	private void start() {
		ATMSystem.getMiniStatement(accountNumber);
		this.details += "Requested MiniStatement";
		this.status = true;
	}
}
class changePassWord extends Transaction{
	changePassWord(int accountNumber,int passWord) {
		super(accountNumber);
		start(passWord);
	}
	private void start(int passWord) {
		if(ATMSystem.canChangePassword(this.accountNumber)) {			
			System.out.println("Enter the OTP sent to your REGISTERED MOBILE NUMBER");
			int OTP = ATMSystem.getOTP();
			System.out.println(OTP);
			int receivedOTP = ATM.sc.nextInt();
			if(OTP == receivedOTP) {
				ATMSystem.changePassWord(accountNumber, passWord);
				this.details += "Requested Password Change!";
				this.status = true;
			}
		}
		else {
			System.out.println("Out of chances");
			this.details += "Requested Password Change!";
			this.status = false;
		}
	}
}