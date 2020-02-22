package Practice2;

public class Print_One_To_Ten_Without_Loop {

	public static void main(String[] args) {

		Print_One_To_Ten_Without_Loop obj1 = new Print_One_To_Ten_Without_Loop();
		obj1.printNum(10);

	}

	public void printNum(int num) {

		if (num > 0) {
			printNum(num - 1);
			System.out.println(num + " ");
			return;
		}

	}

}
