package p1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG_Annotation {

	@Test
	public void test1() {
		System.out.println("Test");
	}

	@BeforeTest
	public void test2() {
		System.out.println("BeforeTest");
	}

	@AfterTest
	public void test3() {
		System.out.println("AfterTest");
	}

	@BeforeMethod
	public void test4() {
		System.out.println("BeforeMethod");
	}

	@AfterMethod
	public void test5() {
		System.out.println("AfterMethod");
	}

	@BeforeSuite
	public void test6() {
		System.out.println("BeforeSuite");
	}

	@AfterSuite
	public void test7() {
		System.out.println("AfterSuite");
	}

	@BeforeClass
	public void test8() {
		System.out.println("BeforeClass");
	}

	@AfterClass
	public void test9() {
		System.out.println("AfterClass");
	}

	@BeforeGroups
	public void test10() {
		System.out.println("BeforeGroups");
	}

	@AfterGroups
	public void test11() {
		System.out.println("AfterGroups");
	}

}
