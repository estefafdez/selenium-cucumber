# Selenium-Cucumber

[![CI](https://github.com/estefafdez/selenium-cucumber/actions/workflows/ci.yml/badge.svg)](https://github.com/estefafdez/selenium-cucumber/actions/workflows/ci.yml) Selenium Webdriver integration with Cucumber. 

<img src="http://www.testingexcellence.com/wp-content/uploads/2016/01/selenium-and-cucumber.png" />
_______________________________________

## 1. Stack:

- Selenium WebDriver: __4.35.0__.
- Cucumber (JVM) __1.2.6__ with JUnit __4.13.1__.
- Java 8 or higher and Maven.
- CI with GitHub Actions: the project is compiled on every push and pull request.

## 2. Download the project.

In order to start using the project you need to create your own Fork on Github and then clone the project:

```bash
git clone https://github.com/XXXX/selenium-cucumber
```

## 3. Choose your OS, Browser and Log Level on the POM.

On the pom.xml file you can choose between:
- Several OS: Windows, Mac, Linux.
- Several Browsers: Chrome, Firefox, IE.
- Several log level configuration:  All, Debug, Info, Warn, Error, Fatal, Off.

You just need to change the following lines:

```bash
<!-- Test Browser -->
<!-- This Parameters select where run the test 
[Remote ,Firefox ,Chrome ,Internet Explorer] -->
<browser>YOUR_BROWSER</browser>

<!-- Test Operative System [linux, mac, windows]-->
<os>YOUR_OS</os>

<!-- Log Mode Section -->
<!-- Parameter for logger level use in this order to include the right information 
[ALL > DEBUG > INFO > WARN > ERROR > FATAL > OFF]-->
<log.level>YOUR_LOG_MODE</log.level>
```

## 4. Step Definition By Action. 

On this project you can find the following set of predefined steps ordered by action already done for you. 
The types of actions are:

- Assertion Steps
- Click Steps
- Configuration Steps
- Input Steps
- JavaScript Handling Steps
- Keyboard Steps
- Navigation Steps
- Progress Steps
- Screenshot Steps

If you want more information or more predefined steps to add into your project you can visit: 

```bash
https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
```
