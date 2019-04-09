package TypeCastingConcept;

public class ExplicitTypeCastingChild extends ExplicitTypeCastingParent
{

	public static void main(String[] args)
	{
		ExplicitTypeCastingChild obj1 = (ExplicitTypeCastingChild)new ExplicitTypeCastingParent(); //explicit type casting
		ExplicitTypeCastingParent obj2 = new ExplicitTypeCastingChild(); //implicit type casting

	}
}
