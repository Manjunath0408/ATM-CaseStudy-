import java.util.ArrayList;
import java.util.HashMap;

class Account { // All the info about an account and some methods
	private int accountNumber;
	private int[] passWord;
	private String[] name;
	private HashMap<Integer,String> mp;
	private double balance;
	private String bankName;
	private ArrayList<String> Transactions;
	private int passwordChangeCount;
	void set(int accountNumber,int[] passWord,String bankName,String[] name){
		this.accountNumber = accountNumber;
		this.passWord = passWord;
		balance = 0.0;
		this.bankName = bankName;
		Transactions = new ArrayList<String>();
		this.name = name;
		mp = new HashMap<Integer,String>();
		for(int i=0;i<this.name.length;i++) {
			mp.put(this.passWord[i], this.name[i]);
		}
		this.passwordChangeCount=0;
	}
	double getBalance() {
		return this.balance;
	}
	void AddFunds(double Amount) {
		this.balance += Amount;
	}
	void DeductFunds(double Amount) {
		this.balance -= Amount;
	}
	boolean checkAccountNumber(int accountNumber) {
		return this.accountNumber == accountNumber;
	}
	boolean checkPassWord(int passWord) {
		boolean ok = false;
		for(int i=0;i<this.passWord.length;i++) {
			if(this.passWord[i] == passWord) {
				ok = true;
				break;
			}
		}
		return ok;
	}
	public String getBankName() {
		return this.bankName;
	}
	public String getName(int passWord) {
		return mp.get(passWord);
	}
	public void addTransaction(String T,int passWord) {
		Transactions.add("[Name: "+getName(passWord)+"]"+T);
	}
	public void getMiniStatement() {
		int cnt = 0;
		System.out.println("-----MINISTATEMENT-----");
		for(int i = (int)Transactions.size()-1;i>=0 && cnt < 3;i--) {
			System.out.println((cnt+1)+". "+Transactions.get(i));
			cnt++;
		}
	}
	public void changePassWord(int oldPassWord) {
		int newPassWord = oldPassWord;
		while(mp.containsKey(newPassWord))
			newPassWord = ATMSystem.getNewPassWord();
		System.out.println(newPassWord);
		System.out.println("New PassWord: "+ATMSystem.decrypt(newPassWord));
		mp.put(newPassWord, mp.get(oldPassWord));
		mp.remove(oldPassWord);
		this.passwordChangeCount++;
	}
	public int getRemainingCount() {
		return 5 - passwordChangeCount;
	}
}
