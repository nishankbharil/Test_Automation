package inter;

public class Test_Interface implements abc1, abc2
{

	public static void main(String[] args) 
	{
	
		abc1 obj1 = new Test_Interface();
		obj1.mul(10, 20);

	}
	
	public int sum(int a, int b) {

		return 0;
	}

	@Override
	public int sum1(int a, int b) {

		return 0;
	}

	@Override
	public int mul(int c, int d) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void sayHello() {
		System.out.println("Hello");
	}
}