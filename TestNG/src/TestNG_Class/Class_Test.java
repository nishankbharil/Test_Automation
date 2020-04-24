package TestNG_Class;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class Class_Test {
	
	@Parameters("Browser")
	@Test(groups = {"Sanity"})
	public void test1() {

		System.out.println("Test1 executing");
	}
	
	@Test(groups = {"Regression"})
	public void test2() {

		System.out.println("Test2 executing");
	}
	@Test(groups = {"Sanity"})
	public void test3() {

		System.out.println("Test3 executing");
	}
	@Test(groups = {"Regression"})
	public void test4() {

		System.out.println("Test4 executing");
	}
	
	@Test(groups = {"Regression", "Sanity"})
	public void test5() {

		System.out.println("Test5 executing");
	}

}
