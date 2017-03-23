Feature: Home page
        As a user I can go to the CodigoFacilito homepage

  Scenario: I type the url of CodigoFacilito on the browser
        Given I navigate to "http://www.codigofacilito.com"
        Then I wait for 15 seconds
        Then I close browser