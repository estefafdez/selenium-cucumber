Feature: Cursos section on the Homepage
        As a user I can go to the Código Facilito homepage, click on cursos and check the cursos page.

  Scenario: Get the Cursos section on the Código Facilito website.
    Given I navigate to "http://www.codigofacilito.com"
    Then I wait 3 seconds for element having xpath "cursosMenuLink" to be clickable
    When I click on element having xpath "cursosMenuLink"
    And I wait for 2 seconds
    Then element having xpath "movilCourses" should be present
    Then I close browser
