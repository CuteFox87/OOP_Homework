class MyAccount {
	private String id;
	private int balance;
	
	MyAccount(String _id) {
		//this.id = _id;
		id = _id;
		balance = Bank.getBalance(id);
	}
	
	public int getBalance() {
		return balance;
	}
	
	public String getID() {
		return id;
	}
	
	public boolean deposit(int amount) {
		if(amount <= 0) return false;
		balance = balance + amount;
		// balance += amount;
		System.out.println(amount + " is deposit into "+ id +"'s account"); 
		return true;
	}
	
	public boolean withdraw(int amount) {
		if(amount <= 0) return false;
		if(balance < amount) return false;

		balance -= amount;
		System.out.println(amount + " is withdraw from "+ id +"'s account");
		return true;
	}
	
	public boolean transfer(int amount, MyAccount target) {
		if(amount <= 0) return false;
		if(balance < amount) return false;

		balance -= amount;
		target.deposit(amount);
		System.out.println(amount + " is transfered from "+ id +"'s account to "+ target.getID() +"'s account");
		
		return true;
	}
	
}

class Bank {
	public static int getBalance(String id) {
		return 5;
	}
}

public class AccountDemo {
	
	public static void showBalance(MyAccount a) {
		System.out.println("The balance in "+ a.getID() +"'s account is " 
							+ a.getBalance());
	}
	
	public static void main(String args[]) {
		MyAccount account1 = new MyAccount("HJ");
		
		showBalance(account1);
		account1.deposit(100);
		showBalance(account1);
		
		MyAccount account2 = new MyAccount("Wang");
		
		showBalance(account2);
		account1.transfer(50, account2);
		showBalance(account1);
	}
}