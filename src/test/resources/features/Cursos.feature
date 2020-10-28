Feature: Cursos section on the Homepage
        As a user I can go to the Código Facilito homepage, click on cursos and check the cursos page.

  Scenario: Get the Cursos section on the Código Facilito website.
    Given I navigate to "http://www.codigofacilito.com"
    When I click on element having xpath "cursosMenuLink"
    And I wait for 5 seconds
    Then element having xpath "exploraCursos" should be present

