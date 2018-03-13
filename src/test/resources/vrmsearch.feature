Feature: 

  Scenario Outline: Search Vehicles
  	Given I landed in VRM Search home page
    When I search for each <VRN> 
    Then I can view each vehicle <model> and <colour>    
    
    Examples:
       	| VRN 	| model 		| colour	|
      	| AB 12 |MERCEDES-BENZ 	| WHITE 	|
      	| BB 34 | BMW 			| BLACK 	|
      	
    	