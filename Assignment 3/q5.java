import java.util.Scanner;

class InsufficientBalanceException extends Exception{
	InsufficientBalanceException(String message){
		super(message);
	}
}

class InvalidAmountException extends Exception{
	InvalidAmountException(String message){
		super(message);
	}
}

class AccountNotFoundException extends Exception{
	AccountNotFoundException(String message){
		super(message);
	}
}

class Bank{
	double balance=5000;

	void checkAccount(int accountNo) throws AccountNotFoundException{
		if(accountNo!=1001)
			throw new AccountNotFoundException("Account not found");
	}

	void deposit(double amount) throws InvalidAmountException{
		if(amount<=0)
			throw new InvalidAmountException("Deposit amount must be greater than zero");
		balance+=amount;
	}

	void withdraw(double amount) throws InvalidAmountException,InsufficientBalanceException{
		if(amount<=0)
			throw new InvalidAmountException("Withdrawal amount must be greater than zero");
		if(amount>balance)
			throw new InsufficientBalanceException("Insufficient balance");
		balance-=amount;
	}

	void transaction(int accountNo,int choice,double amount) throws AccountNotFoundException,InvalidAmountException,InsufficientBalanceException{
		checkAccount(accountNo);

		if(choice==1)
			deposit(amount);
		else if(choice==2)
			withdraw(amount);
		else
			System.out.println("Invalid choice");

		System.out.println("Account Balance: "+balance);
	}
}

class q5{
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		Bank bank=new Bank();

		try{
			System.out.print("Enter account number: ");
			int accountNo=sc.nextInt();

			System.out.print("Enter 1 for Deposit or 2 for Withdrawal: ");
			int choice=sc.nextInt();

			System.out.print("Enter amount: ");
			double amount=sc.nextDouble();

			bank.transaction(accountNo,choice,amount);
		}
		catch(AccountNotFoundException e){
			System.out.println("Error: "+e.getMessage());
		}
		catch(InvalidAmountException e){
			System.out.println("Error: "+e.getMessage());
		}
		catch(InsufficientBalanceException e){
			System.out.println("Error: "+e.getMessage());
		}
	}
}