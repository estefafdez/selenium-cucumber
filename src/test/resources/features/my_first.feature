Feature: Gmail Login
        As a user I should able to login into Gmail.
 
 Scenario: I login with valid credential
        Given I navigate to "http://www.gmail.com"
        And I wait for 15 sec
        Then I close browser