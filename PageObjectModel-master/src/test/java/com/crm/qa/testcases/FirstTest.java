package com.crm.qa.testcases;

import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

public class FirstTest 
{
	
	@Test
	public void Testing1()
	{
		System.out.println("Testing1");
	}
	
	@Test
	public void Testing2()
	{
		System.out.println("Testing2");
	}
	
	@Test
	public void Testing3()
	{
		System.out.println("Testing3");
	}
	
	@Test
	public void Testing4()
	{
		System.out.println("Testing4");
	}
	
	@Test
	public void Testing5()
	{
		int a = 9;
		System.out.println("Testing5");
		assertFalse(a<10);
	}
}
