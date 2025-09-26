package com.it.utils;

import java.util.Random;

public class UtilFuns 
{
	public static int generatePWD() 
	{
		 	Random random = new Random();
	        // Generate a number between 10000 and 99999 (inclusive)
	        int number = 10000 + random.nextInt(90000);
	        return number;
	} 
}
