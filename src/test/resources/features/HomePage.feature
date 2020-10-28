Feature: Home page
        As a user I can go to the estefafdez website homepage
@smoke
  Scenario: Get the estefafdez website homepage
    Given I navigate to "https://estefafdez.github.io/"
    Then I wait for 2 seconds
    And I take screenshot
