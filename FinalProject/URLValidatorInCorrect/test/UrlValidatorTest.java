

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
	   //You can use this function to implement your manual testing	 
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

	   // Port with Specific Path
	   //System.out.println("\nPort with Specific Path");
	   //boolean output = urlVal.isValid("http://www.google.com:80");
	   //System.out.println("Parameter = \"http://www.google.com:80\", result = " + output);
	   
	   System.out.println("---------- Manual Tests -----------");
	   
	   // Basic URLs
	   System.out.println("Basic URLs");
	   //assertTrue(urlVal.isValid("http://www.amazon.com"));
	   //assertTrue(urlVal.isValid("http://www.google.com/"));
	   AssertResults((urlVal.isValid("http://www.amazon.com")),true);
	   AssertResults((urlVal.isValid("http://www.google.com/")),true);
	
	   
	   // Specific Path
	   System.out.println("Specific Path");
	   //assertTrue(urlVal.isValid("http://www.amazon.com/index.html"));
	   AssertResults((urlVal.isValid("http://www.amazon.com/index.html")),true);
	   
	   
	   // IP Address with Scheme
	   System.out.println("IP Address with Scheme");
	   //assertTrue(urlVal.isValid("http://1.1.1.1"));
	   AssertResults((urlVal.isValid("http://1.1.1.1")),true);
	   
	   
	   // Port with Specific Path
	   System.out.println("Port with Specific Path");
	   // boolean output = urlVal.isValid("http://www.google.com:80");
	   // System.out.println("Parameter = \"http://www.google.com:80\", result = " + output);
	   // System.out.println("\n\n");
	   AssertResults((urlVal.isValid("http://www.google.com:80")),true);
	   AssertResults((urlVal.isValid("http://www.google.com:655")),true);
	   AssertResults((urlVal.isValid("http://www.amazon.com:80/test/index.html")),true);
	   AssertResults((urlVal.isValid("http://www.google.com:65535")),true);
	   AssertResults((urlVal.isValid("http://www.amazon.com:8080")),true);
	
	   // Query Path
	   System.out.println("Query Path");
	   //assertTrue(urlVal.isValid("http://www.google.com/abcd?e=f&g=h"));
	   AssertResults((urlVal.isValid("http://www.google.com/abcd?e=f&g=h")),true);
	   
	   // No Scheme
	   System.out.println("No Scheme");
	   //assertFalse(urlVal.isValid("www.amazon.com"));
	   AssertResults((urlVal.isValid("www.amazon.com")),false);
	   
	   // Invalid Scheme
	   System.out.println("Invalid Scheme");
	   //assertFalse(urlVal.isValid("5tg://www.google.com"));
	   AssertResults((urlVal.isValid("5tg://www.google.com")),false);
	   
	   // Invalid Path
	   System.out.println("Invalid Path");
	   //assertFalse(urlVal.isValid("http://amazon.com///test"));
	   //assertFalse(urlVal.isValid("http://www.google.com/a/b/c"));
	   AssertResults((urlVal.isValid("http://amazon.com///test")),false);
	   AssertResults((urlVal.isValid("http://www.google.com/a/b/c")),false);
	
	   // IP Address number cannot be greater than 225
	   System.out.println("IP Address Number Greater Than 255");
	   //assertFalse(urlVal.isValid("1.1.1.400"));
	   AssertResults((urlVal.isValid("1.1.1.400")),false);
	   AssertResults((urlVal.isValid("http://22.22.22.400")),false);
	   AssertResults((urlVal.isValid("http://22.22.22.22")),true);
	   
	   // No address
	   System.out.println("No Address");
	   //assertFalse(urlVal.isValid(null));
	   AssertResults((urlVal.isValid(null)),false);
	   
   }

     
   public void testSchemePartition()
   		{
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

   public void testAuthorityPartition()
	   {
		  System.out.print("\n\n---------- Partition Tests -----------");
	      System.out.print("\nAuthority Partition Test\n");
	      UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	      AssertResults(urlVal.isValid("http://www.amazon.com"), true);
	      AssertResults(urlVal.isValid("http://google.com"), true);
	      AssertResults(urlVal.isValid("http://"), true);
	      AssertResults(urlVal.isValid("1.2.3.4"), false);
	   }

   public void testPortPartition()
	   {
	      System.out.print("Port Partition Test\n");
	      UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	      AssertResults(urlVal.isValid("http://www.amazon.com:0"), false, "port = :0");
	      AssertResults(urlVal.isValid("http://google.com:65535"), true, "port = :65535");
	      AssertResults(urlVal.isValid("http://google.com:a80b90"), false, "port = :a80b90");
	      AssertResults(urlVal.isValid("http://www.amazon.com:80"), true, "port = :80");
	   }

   public void testPathPartition()
	   {
	      System.out.print("Path Partition Test\n");
	      UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	      AssertResults(urlVal.isValid("http://www.amazon.com/index"), true, "path = /index");
	      AssertResults(urlVal.isValid("http://google.com//test"), false, "path = //test");
	      AssertResults(urlVal.isValid("http://google.com/a/b/c/d"), false, "path = /a/b/c/d");
	      AssertResults(urlVal.isValid("http://www.amazon.com/../file"), false, "path = /../file");
	      AssertResults(urlVal.isValid("http://www.amazon.com/index/test"), true, "path = /index/test");
	   }

   public void testQueryPartition()
	   {
	      System.out.print("Query Partition Test\n");
	      UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	      AssertResults(urlVal.isValid("http://www.google.com/abcd?e=f&g=h"), true, "query = abcd?e=f&g=h");
	      AssertResults(urlVal.isValid("http://www.google.com/abcd?"), false, "query = abcd?");
	      AssertResults(urlVal.isValid("http://www.google.com/abcde=f&g=h"), false, "query = abcde=f&g=h");
	   }
	   
   public void testIsValid()
	   {
	   	  System.out.print("\n\n---------- Programming-Based Tests -----------");	
	      //You can use this function for programming based testing
	      // you want to mix and match the different portions, but keep track of what
	      // has illegal characters
	      
	      int testPassCount=0;
	      int testFailCount=0;
	      UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	      //UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_2_SLASHES);
	      
	      // if the part has a zero value it means it's valid, that way any summed up list of parts that's
	      // nonzero means there is an invalid part - no need to individually test and figure it out
	      ResultPair[] schemeTestArray = {
	      		new ResultPair("http://",  false),
	      		//new ResultPair("HTTP://",  false),
	      		new ResultPair("https://", false),
	      		new ResultPair("ftp://",   false),
	      		new ResultPair("ftps://",  false),
	      		new ResultPair("http", 	true),
	      		new ResultPair("://",  	true)
	      		//new ResultPair("",     	false) // fails in URLValidatorCorrect
	      	    
	      };
	      ResultPair[] authorityTestArray = {
	      		new ResultPair("www.google.com",  false),
	      		new ResultPair("go.com",    	   false),
	      		new ResultPair("0.0.0.0",   	   false),
	      		new ResultPair("255.255.255.255", false),
	      		new ResultPair("1.1.1.400",   	   true),
	      		new ResultPair("aaa",   	   	true),
	      		new ResultPair(".aaa",   	   	true),
	      		new ResultPair("",   	       	true)
	      	    
	      };       
	      ResultPair[] pathTestArray = {
	      		new ResultPair("",   			 false),
	      		new ResultPair("/",   			 false),
	      		new ResultPair("/test",   		 false),
	      		new ResultPair("/test1",   	 false),
	      		//new ResultPair("aaa",   		 true),
	      		new ResultPair("/~test",   	 false),
	      		new ResultPair("/^test",   	 true),
	      		new ResultPair("/..",   		 true),
	      		new ResultPair("/test1/test",    false),
	      		new ResultPair("/test1//test",    false)
	      	    
	      };    
	      // fixme - how to deal with preceding / character? - you just added a switch later
	      ResultPair[] queryTestArray = {
	      		new ResultPair("",   						 false),
	      		new ResultPair("?action=view",   			 false),
	      		new ResultPair("?action=edit&mode=up",   	 false)   		    
	      };

	      boolean assertValue = false;
	      for (int i = 0; i < schemeTestArray.length; i++) {
	      	for (int j = 0; j < authorityTestArray.length; j++) {
	      		for (int k=0; k < pathTestArray.length; k++) {
	      			for (int m=0; m < queryTestArray.length; m++) {
	      				// reset to false every time
	      				assertValue = false; 
	      				final String testURL = schemeTestArray[i].strValue+
	      				authorityTestArray[j].strValue +
	      				pathTestArray[k].strValue +
	      				queryTestArray[m].strValue;
	      			    
	      				System.out.print(testURL + "\n");
	      				// FOR DEBUGGING set a break point inside the conditional
	      				//https://www.google.com/test1//test
	      				//https://www.google.com/~test
	      				//HTTP://www.google.com
	      				//http://www.google.com/test1//test
	      				if (testURL.equals("https://www.google.com/~test")) {
	      					System.out.print(testURL + "\n");
	      				}

	      				// not testing ports yet (bugs discovered in manual testing)
	      				int isNotValid = schemeTestArray[i].intValue+
	      						authorityTestArray[j].intValue +
	      						pathTestArray[k].intValue +
	      						queryTestArray[m].intValue;
	      			    
	      				// need '/' if there is going to be a query (i.e. www.google.com?search="hello" is not valid
	      				if (pathTestArray[k].strValue.equals("") && !queryTestArray[m].strValue.equals("")) {
	      					isNotValid=1;
	      				}
	      			    
	      				// if the sum is still zero, then it means all the parts were individually valid
	      				if (isNotValid == 0) {
	      				   assertValue = true;
	      				}

	      				// do the assertion
	      				if (!AssertResultsProgram(urlVal.isValid(testURL), assertValue)) {
	      					System.out.print(testURL +", value=" + isNotValid + "\n");
	      					testFailCount++;
	      				}
	      				else {
	      					testPassCount++;
	      				}
	      			}
	      		}
	      	}
	      }
	      System.out.print("Total Tests= " + (testPassCount + testFailCount) + ", percent pass= "+ Math.round((double)100*testPassCount/(testPassCount + testFailCount)) + "\n");
	      
	   }


}

