Feature: Registro section on the CódigoFacilito website
        As a user I can go to the Código Facilito homepage, click on registro and check the register page.

@smoke
  Scenario: Get the Register section on the Código Facilito website.
    Given I navigate to "http://www.codigofacilito.com"
    When I click on element having xpath "creaCuentaLink"
    And I wait for 5 seconds
    Then element having xpath "registrateParagraph" should be present

