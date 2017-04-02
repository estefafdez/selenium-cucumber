# Selenium-Cucumber

Selenium Webdriver 3.0.1 integration with Cucumber. 

<img src="http://www.testingexcellence.com/wp-content/uploads/2016/01/selenium-and-cucumber.png" />
_______________________________________

## 1. Download the project

In order to start using the project you need to create your own Fork on github and then clone the project:

```bash
git clone https://github.com/XXXX/selenium-cucumber
```

## 2. Choose your OS, Browser  and Log Level on the POM

On the pom.xml file you can choose between:
- Several OS: Windows, Mac, Linux 
- Several Browsers: Chrome, Firefox, IE to run your test. 
- Several log level configuration:  All, Debug, Info, Warn, Error, Fatal, Off.

You just need to change the following lines:

```bash
<!-- Test Browser -->
<!-- This Parameters select where run the test 
[Remote ,Firefox ,Chrome ,Internet Explorer] -->
<browser>YOUR_BROWSER</browser>

<!-- Test Operative system [linux, mac, windows]-->
<os>YOUR_OS</os>

<!-- Log Mode section -->
<!-- Parameter for logger level use in this order to include the right information 
[ALL > DEBUG > INFO > WARN > ERROR > FATAL > OFF]-->
<log.level>YOUR_LOG_MODE</log.level>
```
## 3. Step Definition By Action. 

On this project you can find the following set of predefined steps ordered by action already done for you. 
The actions already done are:

- Navigation Steps
- Assertion Steps
- Input Steps
- Click Steps
- Progress Steps
- Screenshot Steps
- Configuration Steps

If you want more information or more predefined steps to add into your project you can visit: 

```bash
https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md

## 4. SonarQube included.

In order to maintain the quality code, we include SonarQube on the project to improve and maintain the quality.
You can install in two minutes using the SonarQube tutorial available here: 

```bash
https://docs.sonarqube.org/display/SONAR/Get+Started+in+Two+Minutes

Once the SonarQube platform has been installed, you're ready to install an analyzer your project. First,  run your local server:

```bash
http://localhost:9000/about

And  then, run the project in order to check the quality of your project:

```bash
mvn clean verify sonar:sonar
 
 