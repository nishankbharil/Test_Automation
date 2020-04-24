package Object_Creation_Ways;

import java.lang.reflect.Method;

public class ParentClass {

	public static void main(String[] args) throws Exception {

		ParentClass pc = new ParentClass();

		Class c = Class.forName("Object_Creation_Ways.ParentClass");
		Test t = (Test) c.newInstance();

		Method m = c.getDeclaredMethod("show", null);
		m.setAccessible(true);
		m.invoke(t, null);

	}

}
