package Collection_Conversion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ArrayList_Map {

	public static void main(String[] args) {

		ArrayList<Object> al = new ArrayList<Object>();

		al.add(10);
		al.add(20);
		al.add("Hello");
		al.add(10);
		al.add(20);
		al.add("Hello");

		System.out.println(al);
		Map<Object, Object> hp = new HashMap<Object, Object>();

		hp.put(1, "AAA");

		hp.put(2, "BBB");

		hp.put(null, null);

		System.out.println(hp);
		

		Collection<Object> hpval = hp.values();

		Set<Object> ks = hp.keySet();
		System.out.println(ks);
		System.out.println(hpval);

		Set<Entry<Object, Object>> es = hp.entrySet();

		System.out.println(es);

		ArrayList<Object> al2 = new ArrayList<Object>(ks);

		System.out.println(al2);

		ArrayList<Object> al3 = new ArrayList<Object>(hpval);

		System.out.println(al3);

		ArrayList<Entry<Object, Object>> al4 = new ArrayList<Entry<Object, Object>>(es);

		System.out.println(al4);
		
		

		for (Entry<Object, Object> entry : al4) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		
		ArrayList<Integer> lo = new ArrayList<Integer>();
		
		lo.add(1);
		lo.add(1);
		lo.add(1);
		lo.add(1);
		lo.add(1);
		
		ArrayList<Integer> lo1 = new ArrayList<Integer>();
		
		lo1.addAll(lo);
		
		System.out.println(lo1);
		

	}

}
