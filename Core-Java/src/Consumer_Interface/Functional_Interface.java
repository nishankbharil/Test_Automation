package Consumer_Interface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Functional_Interface {

	public static void main(String[] args) {

		List<Integer> li = new ArrayList<>();
		li.add(10);
		li.add(20);
		li.add(30);
		li.add(40);

		li.forEach(i -> System.out.println(i));

		
//		Represents an operation that accepts a single input argument and returns no result. Unlike most other functional interfaces, Consumer is expectedto operate via side-effects. 
//		This is a functional interface whose functional method is accept(Object).

	}

}
