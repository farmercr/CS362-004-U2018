package calculator;

public class Calculator
{
	public Calculator()
	{
	}
	
	public int add(int num1, int num2)
	{
		return num1 + num2;
	}
		
	public int subtract(int num1, int num2)
	{
		return num1 - num2;
	}

	
	public static void main(String[] args)
	{
		Calculator cal = new Calculator();
		System.out.println("2 + 3 = " + cal.add(2,3));
		System.out.println("4 + 2 = " + cal.add(4,2));
	}

}
