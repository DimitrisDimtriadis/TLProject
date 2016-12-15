package com.example.johnywalker.adventure_go.miscellaneous;

/**
 * Created by JohnyWalker94 on 15-Dec-16.
 */
public class ValidateString
{
	public int validateString(String s)
	{
		if(s.equals(null))
		{
			return 200;
		}
		
		s = s.trim();
		
		if(s.length() == 0)
		{
            return 700;
		}
		else if(s.length() < 3)
		{
			return 300;
		}
		else if(s.length() > 16)
		{
			return 400;
		}
		
		for(int i=0; i<s.length(); i++)
		{
			if(s.substring(i, i+1).equals(" "))
			{
				return 500;
			}
			else if(s.substring(i, i+1).matches("[^a-zA-Z0-9]"))
			{
				return 600;
			}
		}
		
		return 100;
	}

	public String getErrorMessage(int error)
	{
		if(error == 100)
		{
			return "successful";
		}
		else if(error == 200)
		{
			return "is null";
		}
		else if(error == 300)
		{
			return "is too small";
		}
		else if(error == 400)
		{
			return "is too long";
		}
		else if(error == 500)
		{
			return "can't contain space character(s)";
		}
		else if(error == 600)
		{
			return "can't contain special character(s)";
		}
		else if(error == 700)
		{
			return "field is required";
		}
		else
		{
			return null;
		}
	}
	
	public void printReturnValues()
	{
		System.out.println("Return values express the error in the string provided, if any");
		System.out.println("--------------------------------------------------------------");
		System.out.println("100 = STRING VALID");
		System.out.println("200 = STRING NULL");
		System.out.println("300 = STRING LENGTH LESS THAN 3 CHARACTERS");
		System.out.println("400 = STRING LENGTH GREATER THAN 16 CHARACTERS");
		System.out.println("500 = STRING CONTAINS WHITESPACE");
		System.out.println("600 = STRING CONTAINS SPECIAL CHARACTER(S)");
		System.out.println("700 = STRING EMPTY");
	}
}