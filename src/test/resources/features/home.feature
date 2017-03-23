Feature: CodigoFacilito | Cursos
        As a user I can go to the CodigoFacilito homepage, click on Cursos and see the Cursos page.

  Scenario: I type the url of CodigoFacilito on the browser
        Given I navigate to "http://www.codigofacilito.com"
        And I wait for 15 sec
        Then I close browser