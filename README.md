# GeoPhyChallenge

Selenium Automation script in Chrome with Java8, Maven, TestNG, docker-compose, gitlab-ci and circleci

## Requirements:
Below dependencies needs to be installed/configured
- Java 8 or higher (JAVA_HOME and PATH in environmental variables)
- Maven (M2, MAVEN_HOME and PATH in environmental variables)
- IDE (IntelliJ is preferred)
- Chrome browser (latest)
- Chrome driver (chrome driver is already added but in case if chrome browser and chrome driver are not compatible, please download and add a suitable chromedriver)

## Downloading Project:
- Using git command: git clone https://github.com/Aj821990/GeoPhyChallenge.git

*or*

- Download the project from https://github.com/Aj821990/GeoPhyChallenge

## Execution:
1.. Run via TestNG
````sh
Click on the run button on your IDE or right click on your testNG file and click RUN

Below scenarios are mentioned in TestNG file:
<class name="Frontend.testCases.ScenarioLogin"> </class>
<class name="Frontend.testCases.ScenarioValuation"> </class>
<class name="Frontend.testCases.ScenarioValuationReport"> </class>
````
2.. Run via terminal
```sh
mvn clean -Dtest=<tests> test -DreTry=<max retry> -Dbrowser=<browser> -Denv=<environment>
example:
mvn clean -Dtest=ScenarioLogin,ScenarioValuation,ScenarioVa*uationReport test -DreTry=2 -Dbrowser -Denv
```
3.. Run via gitlab CI pipeline
```sh
This will execute in headless mode and on selenium hub server
mvn clean -Dtest=<tests> test -DreTry=<max retry> -Dbrowser=<browser> -Denv=<environment> -DgridUrl=<gridurl>
example:
mvn clean -Dtest=ScenarioLogin,ScenarioValuation,ScenarioVa*uationReport test -DreTry=2 -Dbrowser -Denv -DgridUrl=http://selenium__standalone-chrome:4444/wd/hub
```
4.. Run via circleci pipeline ()
```sh 
This will execute in headless mode and on selenium hub server
mvn clean -Dtest=<tests> test -DreTry=<max retry> -Dbrowser=<browser> -Denv=<environment> -DgridUrl=<gridurl>
example:
mvn clean test -Dtest=ScenarioLogin,ScenarioValuation,ScenarioVa*uationReport -DreTry=2 -Dbrowser -Denv -DgridUrl=http://selenium__standalone-chrome:4444/wd/hub
```
**<ins>NOTE:<ins>** _When executing from pipeline make sure you change **remote=true** in path src\main\java\resources\config.properties_


##Important points to remember:
- ***Logging:*** Logging currently logs with INFO level. Log level can be modified by updating *log4j.rootLogger=INFO, FILE, CONSOLE* from log4j.properties
- ***docker-compose:*** This project focuses only on Selenium and chrome in docker-compose file 
- ***Unused Classes:*** Queries and Database Connection classes are currently not used but can be used to validate data from database
- ***Screenshot:*** Screenshot is taken only for failed scenarios and is saved as Base64 image in the test report
- ***Test Report:*** Test Report is saved in location - reports
- ***Screenshots:*** Screenshots are saved in location - screenshots
- ***Logs:*** Logs are saved in location - log
- ***.gitignore:*** reports/, .idea/, log/, target/, screenshots/ are covered in .gitignore file
 
