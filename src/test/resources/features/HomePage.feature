Feature: Home page
        As a user I can go to the estefafdez website homepage

  Scenario: I type the url of estefafdez website on the browser
    Given I navigate to "http://estefafdez.com"
    Then I wait for 2 seconds
    When I click on element having xpath "englishFlag"
    Then I wait for 4 seconds
    And I take screenshot
    Then I close browser
