package Java_Concepts1;

public class Java_Concept9 {

	public static void main(String[] args) {
		
		Java_Concept9.divide(4, 0);
	}
	
	public static int divide(int a, int b) {
	    int c = -1;
	    
	    try {
	        c = a / b;
	    } 
	    catch (Exception e) {
	        System.err.print("Exception ");
	    } 
	    finally {
	        System.err.println("Finally ");
	    }
	    
	    return c;
	}

}
