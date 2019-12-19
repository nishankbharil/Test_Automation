package Assignments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Assignment_List {

	public static void main(String[] args) {
		
		List<String> MyList = new LinkedList<String>();
		MyList.add("nishank");
		MyList.add("bharil");
		MyList.add("abc");
		MyList.add("def");
		
		Collections.sort(MyList);

		for(String temp: MyList)
		{
			System.out.println(temp);
		}
		
		boolean E =MyList.isEmpty();
		System.out.println(E);
		
		MyList.remove(2);
		
		for(String temp: MyList)
		{
			System.out.println(temp);
		}
		
		boolean c = MyList.contains("ABC");
		System.out.println(c);
		
		String a = MyList.get(1);
		System.out.println(a);
		
		int l = MyList.size();
		System.out.println(l);
	}

}
