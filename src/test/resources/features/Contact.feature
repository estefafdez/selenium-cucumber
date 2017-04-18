Feature: Contact section on the Homepage
        As a user I can go to the estefafdez website homepage, click on contact and check the contact form.

  Scenario: Get the Cursos section on the Código Facilito website.
    Given I navigate to "http://www.codigofacilito.com"
    Then I wait 3 seconds for element having xpath "cursosMenuLink" to be clickable
    When I click on element having xpath "cursosMenuLink"
    And I wait for 2 seconds
    Then element having xpath "specialCourses" should be present
    Then I close browser
