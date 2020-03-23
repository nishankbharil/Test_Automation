package CollectionConcept;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynchronisedMap {

	public static void main(String[] args) {

		
		Map<Integer, String> hm = new HashMap<Integer, String>();
		
		Collections.synchronizedMap(hm);

	}

}
