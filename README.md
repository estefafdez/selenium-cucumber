# Selenium-Cucumber

Selenium Webdriver 3.0.1 integration with Cucumber. 

<img src="http://www.testingexcellence.com/wp-content/uploads/2016/01/selenium-and-cucumber.png" />
_______________________________________

## 1. Download the project

In order to start using the project you need to create your own Fork on github and then clone the project:

```bash
git clone forkUrl
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

https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md