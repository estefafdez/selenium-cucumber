# Selenium-Cucumber

[![Build Status](https://travis-ci.org/estefafdez/selenium-cucumber.svg?branch=master)](https://travis-ci.org/estefafdez/selenium-cucumber)   Selenium Webdriver 3.11.0 integration with Cucumber. 

<img src="http://www.testingexcellence.com/wp-content/uploads/2016/01/selenium-and-cucumber.png" />
_______________________________________

## 1. Latest changes:

- Selenium Webdriver Version: __3.11.0__ (latest version of Selenium!).
- Firefox Version:  58.0.2 -> You need to update into this version!
- Gekodriver Version: 0.19.1 (included on this project!)
- ChromeDriver updated into version 2.35 (included on this project!)
- InternetExplorerDriver updated into version 3.9.0 (included on this project!)
- Added commons-fileupload 1.3 into the POM.
- Added guava 24.0-jre into the POM
- Updated several libraries on the POM.
- Added TravisCI.

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

## 5. SonarQube included.

In order to maintain the quality of your code and your project, we include SonarQube on this repository.
You can install in two minutes using the SonarQube tutorial available here: 
https://docs.sonarqube.org/display/SONAR/Get+Started+in+Two+Minutes

Once the SonarQube platform has been installed, you're ready to install and analyse your project. First, run your local server:
http://localhost:9000/about

And then, run the project in order to check the code quality:

```bash
mvn clean verify sonar:sonar
```

## 6. Run SonarQube on a Docker easily.

<img src="http://i.imgur.com/e6T8aQH.png" />

In case you don't want to install SonarQube on your local machine, you can run sonar remotely using <b>Docker</b>. 

You have two choices to create your own Docker:

1) Create a new instance on: http://labs.play-with-docker.com/. 
It's free, easy to use and you don't need to install anything on your computer. 

2) Download Docker from its official Website: https://www.docker.com/
Download, install Docker and run everything you need. 

Once you have Docker, you need to run the followings lines:

```bash
docker pull sonarqube
docker run -d --name sonarqube -p 9000:9000 sonarqube
```

And then, you are ready to run SonarQube:

```bash
mvn clean install sonar:sonar
```

