import java.util.Scanner;

class ApplicationException extends Exception{
	ApplicationException(String message){
		super(message);
	}
}

class ProductException extends ApplicationException{
	ProductException(String message){
		super(message);
	}
}

class ProductNotFoundException extends ProductException{
	ProductNotFoundException(String message){
		super(message);
	}
}

class OutOfStockException extends ProductException{
	OutOfStockException(String message){
		super(message);
	}
}

class PaymentException extends ApplicationException{
	PaymentException(String message){
		super(message);
	}
}

class InvalidPaymentException extends PaymentException{
	InvalidPaymentException(String message){
		super(message);
	}
}

class InsufficientFundsException extends PaymentException{
	InsufficientFundsException(String message){
		super(message);
	}
}

class OrderException extends ApplicationException{
	OrderException(String message){
		super(message);
	}
}

class EmptyCartException extends OrderException{
	EmptyCartException(String message){
		super(message);
	}
}

class ShoppingCart{
	static String[] products={"Laptop","Phone","Headphones","Keyboard"};
	static double[] prices={60000,30000,2000,1500};
	static int[] stock={5,3,0,10};

	static String[] cart=new String[10];
	static double[] cartPrice=new double[10];
	static int cartCount=0;

	static void searchProduct(String name) throws ProductNotFoundException{
		for(int i=0;i<products.length;i++){
			if(products[i].equalsIgnoreCase(name)){
				System.out.println("Product found: "+products[i]);
				System.out.println("Price: "+prices[i]);
				System.out.println("Stock: "+stock[i]);
				return;
			}
		}
		throw new ProductNotFoundException("Product not found");
	}

	static void addProduct(String name) throws ProductNotFoundException,OutOfStockException{
		for(int i=0;i<products.length;i++){
			if(products[i].equalsIgnoreCase(name)){
				if(stock[i]==0)
					throw new OutOfStockException("Product is out of stock");

				cart[cartCount]=products[i];
				cartPrice[cartCount]=prices[i];
				cartCount++;
				stock[i]--;

				System.out.println("Product added to cart");
				return;
			}
		}
		throw new ProductNotFoundException("Product not found");
	}

	static void removeProduct(String name) throws ProductNotFoundException{
		for(int i=0;i<cartCount;i++){
			if(cart[i].equalsIgnoreCase(name)){
				for(int j=0;j<products.length;j++){
					if(products[j].equalsIgnoreCase(cart[i]))
						stock[j]++;
				}

				for(int j=i;j<cartCount-1;j++){
					cart[j]=cart[j+1];
					cartPrice[j]=cartPrice[j+1];
				}

				cartCount--;
				System.out.println("Product removed from cart");
				return;
			}
		}
		throw new ProductNotFoundException("Product not found in cart");
	}

	static void showCart(){
		if(cartCount==0){
			System.out.println("Cart is empty");
			return;
		}

		System.out.println("Cart:");
		for(int i=0;i<cartCount;i++)
			System.out.println(cart[i]+" - "+cartPrice[i]);
	}

	static double getTotal() throws EmptyCartException{
		if(cartCount==0)
			throw new EmptyCartException("Cannot place order. Cart is empty");

		double total=0;
		for(int i=0;i<cartCount;i++)
			total+=cartPrice[i];

		return total;
	}

	static void payment(double amount,double balance) throws InvalidPaymentException,InsufficientFundsException,EmptyCartException{
	if(amount<=0)
		throw new InvalidPaymentException("Invalid payment amount");

	double total=getTotal();

	if(amount<total)
		throw new InsufficientFundsException("Insufficient funds");

	System.out.println("Payment successful");
	System.out.println("Remaining balance: "+(balance-total));
}

	static void placeOrder() throws EmptyCartException{
		if(cartCount==0)
			throw new EmptyCartException("Cannot place order. Cart is empty");

		System.out.println("Order placed successfully");
		cartCount=0;
	}
}

class q7{
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		double balance=65000;
		int choice;

		do{
			System.out.println("\n1. Search Product");
			System.out.println("2. Add Product");
			System.out.println("3. Remove Product");
			System.out.println("4. Show Cart");
			System.out.println("5. Make Payment");
			System.out.println("6. Place Order");
			System.out.println("7. Exit");
			System.out.print("Enter choice: ");
			choice=sc.nextInt();
			sc.nextLine();

			try{
				if(choice==1){
					System.out.print("Enter product name: ");
					String name=sc.nextLine();
					ShoppingCart.searchProduct(name);
				}
				else if(choice==2){
					System.out.print("Enter product name: ");
					String name=sc.nextLine();
					ShoppingCart.addProduct(name);
				}
				else if(choice==3){
					System.out.print("Enter product name: ");
					String name=sc.nextLine();
					ShoppingCart.removeProduct(name);
				}
				else if(choice==4){
					ShoppingCart.showCart();
				}
				else if(choice==5){
					System.out.print("Enter payment amount: ");
					double amount=sc.nextDouble();
					ShoppingCart.payment(amount,balance);
					balance-=ShoppingCart.getTotal();
				}
				else if(choice==6){
					ShoppingCart.placeOrder();
				}
				else if(choice==7){
					System.out.println("Thank you");
				}
				else{
					System.out.println("Invalid choice");
				}
			}
			catch(ProductNotFoundException e){
				System.out.println("Product Error: "+e.getMessage());
			}
			catch(OutOfStockException e){
				System.out.println("Stock Error: "+e.getMessage());
			}
			catch(InvalidPaymentException e){
				System.out.println("Payment Error: "+e.getMessage());
			}
			catch(InsufficientFundsException e){
				System.out.println("Payment Error: "+e.getMessage());
			}
			catch(EmptyCartException e){
				System.out.println("Order Error: "+e.getMessage());
			}
		}while(choice!=7);
	}
}