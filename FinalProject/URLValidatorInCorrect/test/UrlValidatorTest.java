

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!


public class UrlValidatorTest extends TestCase
{


   public UrlValidatorTest(String testName)
   {
      super(testName);
   }

   class urlPart
   {
	    public String strValue;
	    public int intValue;
	    
	    urlPart(String strValue, int intValue)
		{
	   	 this.strValue = strValue;
	   	 this.intValue = intValue;
	    }
	}

   // assert helper function that returns a boolean
   public boolean AssertResultsProgram(boolean test, boolean expected, String subject)
   {
	   if(test == expected)
		{
	   	 // do nothing for now
	    	//System.out.println("     PASSED: test = " + test + "; expected = " + expected + "; " + subject);
	   	 return true;
		}
		else
		{
			System.out.println("     FAILED: test = " + test + "; expected = " + expected  + "; " + subject);
	    	return false;
		}
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
   
   // Assert help function with string for subject
   public void AssertResults(boolean test, boolean expected, String subject)
   {
	   if(test == expected)
	   {
		   System.out.println("     PASSED: test = " + test + "; expected = " + expected + "; " + subject);
	   }
	   else
	   {
		   System.out.println("     FAILED: test = " + test + "; expected = " + expected  + "; " + subject);
	   }
   }

  
   
   public void testManualTest()
   {
	   System.out.println("---------- Manual Tests -----------");
	 
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   // Basic URLs
	   System.out.println("Basic URLs");
	   AssertResults((urlVal.isValid("http://www.amazon.com")),true);
	   AssertResults((urlVal.isValid("http://www.google.com/")),true);
	
	   
	   // Specific Path
	   System.out.println("Specific Path");
	   AssertResults((urlVal.isValid("http://www.amazon.com/index.html")),true);
	   
	   
	   // IP Address with Scheme
	   System.out.println("IP Address with Scheme");
	   AssertResults((urlVal.isValid("http://1.1.1.1")),true);
	   
	   
	   // Port with Specific Path
	   System.out.println("Port with Specific Path");
	   AssertResults((urlVal.isValid("http://www.google.com:80")),true);
	   AssertResults((urlVal.isValid("http://www.google.com:655")),true);
	   AssertResults((urlVal.isValid("http://www.amazon.com:80/test/index.html")),true);
	   AssertResults((urlVal.isValid("http://www.google.com:65535")),true);
	   AssertResults((urlVal.isValid("http://www.amazon.com:8080")),true);
	
	   // Query Path
	   System.out.println("Query Path");
	   AssertResults((urlVal.isValid("http://www.google.com/abcd?e=f&g=h")),true);
	   
	   // No Scheme
	   System.out.println("No Scheme");
	   AssertResults((urlVal.isValid("www.amazon.com")),false);
	   
	   // Invalid Scheme
	   System.out.println("Invalid Scheme");
	   AssertResults((urlVal.isValid("5tg://www.google.com")),false);
	   
	   // Invalid Path
	   System.out.println("Invalid Path");
	   AssertResults((urlVal.isValid("http://amazon.com///test")),false);
	   AssertResults((urlVal.isValid("http://www.google.com/a/b/c")),false);
	
	   // IP Address number cannot be greater than 225
	   System.out.println("IP Address Number Greater Than 255");
	   AssertResults((urlVal.isValid("1.1.1.400")),false);
	   AssertResults((urlVal.isValid("http://22.22.22.400")),false);
	   AssertResults((urlVal.isValid("http://22.22.22.22")),true);
	   
	   // No address
	   System.out.println("No Address");
	   AssertResults((urlVal.isValid(null)),false);	   
   }

   public void testPartition1()
   {
	   System.out.print("\n\n---------- Partition Tests -----------\n");
	   System.out.println("Scheme Partition Test -- Default Schemes");
	   UrlValidator urlInVal = new UrlValidator();
	   AssertResults(urlInVal.isValid("htt://www.amazon.com"), false, "scheme = htt://");
	   AssertResults(urlInVal.isValid("https://www.google.com"), true, "scheme = https://");
	   AssertResults(urlInVal.isValid("://www.google.com"), false, "scheme = ://");
	      
	   System.out.println("Scheme Partition Test -- Allow All Schemes");
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   AssertResults(urlVal.isValid("http://www.google.com"), true, "scheme = http://");
	   AssertResults(urlVal.isValid("5tg://www.google.com"), false, "scheme = 5tg://");
   }

   
   public void testPartition2()
   {
      System.out.print("Authority Partition Test\n");
      UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
      AssertResults(urlVal.isValid("http://www.amazon.com"), true);
      AssertResults(urlVal.isValid("http://google.com"), true);
      AssertResults(urlVal.isValid("http://"), true);
      AssertResults(urlVal.isValid("1.2.3.4"), false);
   }
   

   public void testPartition3()
   {
      System.out.print("Port Partition Test\n");
      UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
      AssertResults(urlVal.isValid("http://www.amazon.com:0"), false, "port = :0");
      AssertResults(urlVal.isValid("http://google.com:65535"), true, "port = :65535");
      AssertResults(urlVal.isValid("http://google.com:a80b90"), false, "port = :a80b90");
      AssertResults(urlVal.isValid("http://www.amazon.com:80"), true, "port = :80");
   }

   public void testPartition4()
   {
      System.out.print("Path Partition Test\n");
      UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
      AssertResults(urlVal.isValid("http://www.amazon.com/index"), true, "path = /index");
      AssertResults(urlVal.isValid("http://google.com//test"), false, "path = //test");
      AssertResults(urlVal.isValid("http://google.com/a/b/c/d"), false, "path = /a/b/c/d");
      AssertResults(urlVal.isValid("http://www.amazon.com/../file"), false, "path = /../file");
      AssertResults(urlVal.isValid("http://www.amazon.com/index/test"), true, "path = /index/test");
   }

   public void testPartition5()
   {
      System.out.print("Query Partition Test\n");
      UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
      AssertResults(urlVal.isValid("http://www.google.com/abcd?e=f&g=h"), true, "query = abcd?e=f&g=h");
      AssertResults(urlVal.isValid("http://www.google.com/abcd?"), false, "query = abcd?");
      AssertResults(urlVal.isValid("http://www.google.com/abcde=f&g=h"), false, "query = abcde=f&g=h");
   }
   
	   public void testIsValid()
	   {
	   	//You can use this function for programming based testing
	   	// you want to mix and match the different portions, but keep track of what
	   	// has illegal characters
	    
		System.out.print("\n\n---------- Programming-Based Tests -----------\n");   
	   	int testPassCount=0;
	   	int testFailCount=0;
	   	UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   	//UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_2_SLASHES);
	       
	   	// if the part has a zero value it means it's valid, that way any summed up list of parts that's
	   	// nonzero means there is an invalid part - no need to individually test and figure it out
	   	urlPart[] schemeTestArray =
		{
	   			new urlPart("http://",  0),
	   			new urlPart("HTTP://",  0),
	   			new urlPart("https://", 0),
	   			new urlPart("ftp://",   0),
	   			new urlPart("ftps://",  0),
	   			new urlPart("http", 	1),
	   			new urlPart("://",  	1),
	   			new urlPart("",     	0) // fails in URLValidatorCorrect
	   	};

	   	urlPart[] authorityTestArray =
		{
	   			new urlPart("www.google.com",  0),
	   			new urlPart("go.com",    	   0),
	   			new urlPart("0.0.0.0",   	   0),
	   			new urlPart("255.255.255.255", 0),
	   			new urlPart("1.1.1.400",   	   1),
	   			new urlPart("aaa",   	   	1),
	   			new urlPart(".aaa",   	   	1),
	   			new urlPart("",   	       	1)
	   	};       

	   	urlPart[] pathTestArray = 
		{
	   			new urlPart("",   			 0),
	   			new urlPart("/",   			 0),
	   			new urlPart("/test",   		 0),
	   			new urlPart("/test1",   	 0),
	   			new urlPart("aaa",   		 1),
	   			new urlPart("/~test",   	 0),
	   			new urlPart("/^test",   	 1),
	   			new urlPart("/..",   		 1),
	   			new urlPart("/test1/test",    0),
	   			new urlPart("/test1//test",    0)
	   	};    
	       
	   	urlPart[] queryTestArray =
		{
	   			new urlPart("",   						 0),
	   			new urlPart("?action=view",   			 0),
	   			new urlPart("?action=edit&mode=up",   	 0)   		    
	   	};
	    
	   	boolean assertValue = false;
	   	for (int i = 0; i< schemeTestArray.length; i++)
		{
	   		for (int j=0; j < authorityTestArray.length; j++)
			{
	   			for (int k=0; k < pathTestArray.length; k++)
				{
	   				for (int m=0; m < queryTestArray.length; m++)
					{
	   					// reset to false every time
	   					assertValue = false;
	   					// string value concatenates actual test case for URL
	   					final String testURL = schemeTestArray[i].strValue+
	   					authorityTestArray[j].strValue +
	   					pathTestArray[k].strValue +
	   					queryTestArray[m].strValue;
	   				    
	   					// FOR DEBUGGING set a break point inside the conditional
	   					//System.out.print(testURL + "\n");
	   					//https://www.google.com/test1//test
	   					//https://www.google.com/~test
	   					//HTTP://www.google.com
	   					//http://www.google.com/test1//test
	   					if (testURL.equals("https://www.google.com/~test"))
						{
	   						System.out.print(testURL + "\n");
	   					}

	   					// not testing ports (bugs discovered in manual testing)
	   					int isNotValid = schemeTestArray[i].intValue+
	   							authorityTestArray[j].intValue +
	   							pathTestArray[k].intValue +
	   							queryTestArray[m].intValue;
	   				    
	   					// need '/' if there is going to be a query (i.e. www.google.com?search="hello" is not valid
	   					if (pathTestArray[k].strValue.equals("") && !queryTestArray[m].strValue.equals("")) 
						{
	   						isNotValid=1;
	   					}
	   				    
	   					// if the sum is still zero, then it means all the parts were individually valid
	   					if (isNotValid == 0)
						{
	   					   assertValue = true;
	   					}
	   					
	   					String outputString = testURL +", value=" + isNotValid;
	   					
	   					// do the assertion
	   					if (!AssertResultsProgram(urlVal.isValid(testURL), assertValue, outputString))
						{
	   						//System.out.print(testURL +", value=" + isNotValid + "\n");
	   						// keep a record of how you're doing
	   						testFailCount++;
	   					}
	   					else
						{
	   						testPassCount++;
	   					}
	   				}
	   			}
	   		}
	   	}
	   	// report test metrics (if you could actually complete testing, that is)
	   	System.out.print("Total Tests= " + (testPassCount + testFailCount) + ", percent pass= "+ Math.round((double)100*testPassCount/(testPassCount + testFailCount)) + "\n");
	   }
}

  