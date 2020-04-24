package TypeCastingConcept;

public class ExplicitTypeCastingChild extends ExplicitTypeCastingParent
{

	public static void main(String[] args) throws Throwable
	{
		ExplicitTypeCastingChild obj1 = (ExplicitTypeCastingChild)new ExplicitTypeCastingParent(); //explicit type casting
		ExplicitTypeCastingParent obj2 = new ExplicitTypeCastingChild(); //implicit type casting
		obj1.toString();
		obj1.finalize();
	}
}
