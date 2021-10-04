Feature: Open Google, search for “J. P. Morgan”, click the first result returned by Google, verify that the J.P. Morgan logo is shown
      
    @Google
 	Scenario Outline: Google Search given text and verify logo displayed as expected
    Given Read the test data  "<TestData>" from Excel file 
    When Navigate to the url
		And  Search the given text
		And  Add Click the first link
		Then Verify Logo is Displayed 
	
    Examples: 
      | TestData  |	
      | TestData2 |

   
  
     
      
