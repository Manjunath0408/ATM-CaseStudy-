//1.Transfer Funds
//2.Add Funds
//3.Withdraw Funds
//4.View Balance
// Transaction class is extended by other classes
class Transaction{
	String details;
	boolean status;
	int accountNumber,PIN;
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
			if(ATMSystem.getBalance(this.accountNumber) >= amount) {
				ATMSystem.AddFunds(otherAccountNumber, amount);
				ATMSystem.DeductFunds(this.accountNumber, amount);
				this.status = true;
				this.details += "Successfull! ";
			}
			else {
				this.status = false;
				this.details += "Failed! ";
			}
			this.details += "From: "+accountNumber + " To: "+otherAccountNumber+" Amount: "+amount;
		}
		else {
			this.status = false;
			this.details += "Failed! ";
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
		this.details += "Successfull! "+"Added Rs: "+amount+ " To: "+accountNumber;
		this.status = true;
		ATMSystem.AddFunds(accountNumber, amount,denom);
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
			this.details+= "Successfull! Withdrawal of Rs: "+amount;						
		}
		else {
			System.out.println("Not Enough Funds");
			this.details += "Failed! Requested Amount: "+amount;
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