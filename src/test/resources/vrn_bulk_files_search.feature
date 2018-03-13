Feature: 
  
  Scenario: Assert vehicles using file from test data folder
    Given I retrieved supported vrn search files from configured test data folder
    When I search and retrieve vehicle details for each vrn in search files
    Then Assert and write results to configured output folder