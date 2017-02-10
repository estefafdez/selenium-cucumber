Feature: CodigoFacilito | Cursos
        As a user I can go to the CodigoFacilito homepage, click on Cursos and see the Cursos page.

  Scenario: I type the url of CodigoFacilito on the browser
    Given I navigate to "http://www.codigofacilito.com"
    And I should see page title as ""
    And The element having xpath "" should be present
    When I click on element having xpath "(.*?)"
    And I wait for 3 sec
    Then I can see the cursos page
