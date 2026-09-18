class q2{
	public static void main(String[] args){
		try{
			try{
				int a=10/0;
			}
			catch(ArithmeticException e){
				System.out.println("Inner: ArithmeticException");
			}

			int arr[]={1,2,3};
			System.out.println(arr[5]);
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Outer: ArrayIndexOutOfBoundsException");
		}
	}
}