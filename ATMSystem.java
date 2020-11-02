import java.util.ArrayList;

public class ATMSystem {
	static ArrayList<Account> Ac; /// database
	static private int [] Denominations;   //[0] 100s //[1] 500s //[2] 2000s
	ATMSystem(int [] Denominations){
		Ac = new ArrayList<Account>();
		ATMSystem.Denominations = Denominations;
	}
	static private Account getAc(int accountNumber) { // this is called only when there is 
		Account A = null; 
		for(Account a:Ac) {
			if(a.getAccountNumber() == accountNumber) {
				A = a;
				break;
			}
		}
		return A;
	}
	static boolean isValid(int accountNumber,int password) {
		for(Account a: Ac) {
			if(a.getAccountNumber() == accountNumber && a.getPassWord() == password) {
				return true;
			}
		}
		return false;
	}
	static boolean isValid(int accountNumber) {
		for(Account a: Ac) {
			if(a.getAccountNumber() == accountNumber) {
				return true;
			}
		}
		return false;
	}
	static double getBalance(int accountNumber){
		double balance = 0.0;
		for(Account a: Ac) {
			if(a.getAccountNumber()== accountNumber){
				balance = a.getBalance();
				break;
			}
		}
		return balance;
	}
	static void AddFunds(int accountNumber,double amount,int [] denominations) {
		getAc(accountNumber).AddFunds(amount);
		UpdateMachineBalance(denominations);
	}
	static void AddFunds(int accountNumber,double amount) {
		getAc(accountNumber).AddFunds(amount);
	}
	static void DeductFunds(int accountNumber,double amount) {
		getAc(accountNumber).DeductFunds(amount);
		UpdateMachineBalance(amount);
	}
	static int getMachineBalance() {
		return (Denominations[0]*100 + Denominations[1]*500 + Denominations[2]*2000);
	}
	static void getCashAndDenominations() {
		System.out.println(ATMSystem.getMachineBalance());
		System.out.println("100s: "+Denominations[0]);
		System.out.println("500s: "+Denominations[1]);
		System.out.println("2000s: "+Denominations[2]);
	}
	static boolean drawable(double amount) {
		int i = 0;
		int [] bills = new int [3];
		bills[0]=100;
		bills[1]=500;
		bills[2]=2000;
		int [] tempdenom = ATMSystem.Denominations;
		while(amount > 0 && i < 3) {
			if(amount >= bills[i] && tempdenom[i]>0) {
				amount -= bills[i];
				tempdenom[i]--;
			}
			else i++;
		}
		return amount == 0;
	}
	private static void UpdateMachineBalance(double amount) {
		int i = 0;
		int [] bills = new int [3];
		bills[0]=100;
		bills[1]=500;
		bills[2]=2000;
		while(amount >0 && i<3) {
			if(amount >= bills[i] && ATMSystem.Denominations[i]>0) {
				amount -= bills[i];
				ATMSystem.Denominations[i]--;
			}
			else i++;
		}
	}
	private static void UpdateMachineBalance(int [] denominations) {
		for(int i=0;i<3;i++) {
			ATMSystem.Denominations[i] += denominations[i];
		}
	}
}