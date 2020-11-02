public class Account { // All the info about an account and some methods
	private int accountNumber;
	private int passWord;
	private double balance;
	Account(int ac,int ps){
		accountNumber = ac;
		passWord = ps;
		balance = 0.0;
	}
	double getBalance() {
		return this.balance;
	}
	void AddFunds(double Amount) {
		balance += Amount;
	}
	void DeductFunds(double Amount) {
		balance -= Amount;
	}
	int getAccountNumber() {
		return this.accountNumber;
	}
	int getPassWord() {
		return this.passWord;
	}
}
