

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   // Assert helper function 
   public void AssertResults(boolean test, boolean expected)
   {
	   if(test == expected)
	   {
		   System.out.println("     PASSED: test = " + test + "; expected = " + expected);
	   }
	   else
	   {
		   System.out.println("     FAILED: test = " + test + "; expected = " + expected);
	   }
   }

   
   public void testManualTest()
   {
	   //You can use this function to implement your manual testing	 
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

	   // Port with Specific Path
	   System.out.println("\nPort with Specific Path");
	   //boolean output = urlVal.isValid("http://www.google.com:80");
	   //System.out.println("Parmeter = \"http://www.google.com:80\", result = " + output);
	   
	   
	   
	   // Basic URLs
	   System.out.println("Basic URLs");
	   //assertTrue(urlVal.isValid("http://www.amazon.com"));
	   //assertTrue(urlVal.isValid("http://www.google.com/"));
	   AssertResults((urlVal.isValid("http://www.amazon.com")),true);
	   AssertResults((urlVal.isValid("http://www.google.com/")),true);
	
	   
	   // Specific Path
	   System.out.println("\nSpecific Path");
	   //assertTrue(urlVal.isValid("http://www.amazon.com/index.html"));
	   AssertResults((urlVal.isValid("http://www.amazon.com/index.html")),true);
	   
	   
	   // IP Address with Scheme
	   System.out.println("\nIP Address with Scheme");
	   //assertTrue(urlVal.isValid("http://1.1.1.1"));
	   AssertResults((urlVal.isValid("http://1.1.1.1")),true);
	   
	   
	   // Port with Specific Path
	   System.out.println("\nPort with Specific Path");
	  // boolean output = urlVal.isValid("http://www.google.com:80");
	  // System.out.println("Parmeter = \"http://www.google.com:80\", result = " + output);
	   
	   
	   
	   
	   
	   //System.out.println("\n\n");
	   AssertResults((urlVal.isValid("http://www.google.com:80")),true);
	   AssertResults((urlVal.isValid("http://www.google.com:655")),true);
	   AssertResults((urlVal.isValid("http://www.amazon.com:80/test/index.html")),true);
	   AssertResults((urlVal.isValid("http://www.google.com:65535")),true);
	   AssertResults((urlVal.isValid("http://www.amazon.com:8080")),true);
	
	   
	   // Query Path
	   System.out.println("\nQuery Path");
	   //assertTrue(urlVal.isValid("http://www.google.com/abcd?e=f&g=h"));
	   AssertResults((urlVal.isValid("http://www.google.com/abcd?e=f&g=h")),true);
	   
	   
	   // No Scheme
	   System.out.println("\nNo Scheme");
	   //assertFalse(urlVal.isValid("www.amazon.com"));
	   AssertResults((urlVal.isValid("www.amazon.com")),false);
	   
	   
	   // Invalid Scheme
	   System.out.println("\nInvalid Scheme");
	   //assertFalse(urlVal.isValid("5tg://www.google.com"));
	   AssertResults((urlVal.isValid("5tg://www.google.com")),false);
	   
	   // Invalid Path
	   System.out.println("\nInvalid Path");
	   //assertFalse(urlVal.isValid("http://amazon.com///test"));
	   //assertFalse(urlVal.isValid("http://www.google.com/a/b/c"));
	   AssertResults((urlVal.isValid("http://amazon.com///test")),false);
	   AssertResults((urlVal.isValid("http://www.google.com/a/b/c")),false);
	
	   
	   // IP Address number cannot be greater than 225
	   System.out.println("\nIP Address Number Greater Than 255");
	   //assertFalse(urlVal.isValid("1.1.1.400"));
	   AssertResults((urlVal.isValid("1.1.1.400")),false);
	   
	   // No address
	   System.out.println("\nNo Address");
	   //assertFalse(urlVal.isValid(null));
	   AssertResults((urlVal.isValid(null)),false);
	   
   }

      
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   

   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   

   }
   //You need to create more test cases for your Partitions if you need to 
   /*
   public void testIsValid()
   {
	   //You can use this function for programming based testing

   }
   */

   
}

