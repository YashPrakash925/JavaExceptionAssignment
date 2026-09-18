import java.util.Scanner;

class InvalidUsernameException extends Exception{
	InvalidUsernameException(String message){
		super(message);
	}
}

class InvalidPasswordException extends Exception{
	InvalidPasswordException(String message){
		super(message);
	}
}

class AccountLockedException extends Exception{
	AccountLockedException(String message){
		super(message);
	}
}

class q6{
	static int failedAttempts=0;
	static final int MAX_ATTEMPTS=3;

	static void login(String username,String password) throws InvalidUsernameException,InvalidPasswordException,AccountLockedException{
		if(failedAttempts>=MAX_ATTEMPTS)
			throw new AccountLockedException("Account is locked");

		if(!username.equals("admin"))
			throw new InvalidUsernameException("Invalid username");

		if(!password.equals("1234")){
			failedAttempts++;
			if(failedAttempts>=MAX_ATTEMPTS)
				throw new AccountLockedException("Maximum attempts reached. Account is locked");
			throw new InvalidPasswordException("Invalid password. Attempts left: "+(MAX_ATTEMPTS-failedAttempts));
		}

		failedAttempts=0;
		System.out.println("Login successful");
	}

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);

		try{
			System.out.print("Enter username: ");
			String username=sc.nextLine();

			System.out.print("Enter password: ");
			String password=sc.nextLine();

			login(username,password);
		}
		catch(InvalidUsernameException e){
			System.out.println("Error: "+e.getMessage());
		}
		catch(InvalidPasswordException e){
			System.out.println("Error: "+e.getMessage());
		}
		catch(AccountLockedException e){
			System.out.println("Error: "+e.getMessage());
		}
		finally{
			System.out.println("Login attempt completed");
		}
	}
}