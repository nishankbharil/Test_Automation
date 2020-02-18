package Assignments;

import java.util.HashMap;
import java.util.Map;

public class Assignment_Map {

	public static void main(String[] args) {
		
		Map<Integer, String> MyMap = new HashMap<Integer, String>();
		MyMap.put(103, "nishank");
		MyMap.put(101, "bharil");
		MyMap.put(110, "aac");
		MyMap.put(111, "aac");
		
		MyMap.remove(111);
		System.out.println(MyMap.get(110));
		
		System.out.println(MyMap);
		
		
	}

}
