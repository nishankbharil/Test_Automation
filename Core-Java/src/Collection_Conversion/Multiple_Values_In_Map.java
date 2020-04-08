package Collection_Conversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Multiple_Values_In_Map {

	public static void main(String[] args) {

		ArrayList<Object> al = new ArrayList<Object>();

		al.add("Defect Summary");
		al.add("Defect Description");

		ArrayList<Object> al2 = new ArrayList<Object>();

		al2.add("Defect Summary2");
		al2.add("Defect Description2");

		Map<Object, ArrayList<Object>> hp = new HashMap<Object, ArrayList<Object>>();

		hp.put(1, al);
		hp.put(2, al2);

		System.out.println(hp);

	}

}
