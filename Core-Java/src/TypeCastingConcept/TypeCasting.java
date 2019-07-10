package TypeCastingConcept;

public class TypeCasting
{
/** Converting one data type to other is type casting
 * type casting is of two types implicit and explicit * 
 * implicit : storing integer in long
 * explicit : storing long in integer
 */
	public static void main(String[] args) 
	{
	
		//Premitive
		
		int i = 128; 
		
		long l = i; //Implicit typecasting
		
		byte b = (byte)i; //explicit typecasting
		
		System.out.println(b);
	}
}
