package jan6;

public class TestArraya 
{

	public static void main(String[] args) 
	{
		//<type> [] <array-name> = new <type>[size];
		int [] studentRollNum = new int[5];
		studentRollNum[0] = 101;
		studentRollNum[1] = 102;
		studentRollNum[2] = 103;
		studentRollNum[3] = 104;
		studentRollNum[4] = 105;
		//studentRollNum[5] = studentRollNum[3] + studentRollNum[4];
		
		int lastStudentRollNumber = studentRollNum[4];
		
		System.out.println("Roll no. of last student is - "+lastStudentRollNumber);
		//System.out.println("Roll no. of last student is - "+studentRollNum[5]);
		
		int size = studentRollNum.length;
		System.out.println(size);
		
		String[] names = {"Tom", "Peter", "James"};
		System.out.println("Name of student at index 2 - "+names[2]);
		System.out.println("Name of student at index 2 - "+names.length);//array index out of bound
	}

}
