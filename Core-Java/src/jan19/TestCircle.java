package jan19;

public class TestCircle {

	public static void main(String[] args) 
	{
		Circle c = new Circle();
		c.getArea(10);
		//c.pi  = 10;
		//c.getArea(10);

		float valueOfPiInCircleClass = c.getpi();
		System.out.println(valueOfPiInCircleClass);
		c.getArea(10);
	}

}
