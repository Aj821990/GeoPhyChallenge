# GeoPhyChallenge

Selenium Automation script with Java8, Maven, TestNG, docker-compose, gitlab-ci and circleci

## Requirements:
Below dependencies needs to be installed and set environmental variables
- Java 8 or higher (JAVA_HOME and PATH)
- Maven (M2, MAVEN_HOME and PATH)
- IDE (IntelliJ is preferred)
- Chrome browser (latest)
- Chrome driver (is already added but in case if your chrome doesnot support, please download and add a suitable chromedriver)

## Downloading Project:
- Using git command: git clone https://github.com/Aj821990/GeoPhyChallenge.git

*or*

- Download the project from https://github.com/Aj821990/GeoPhyChallenge

## Execution:
- Run via TestNG
````sh
Click on the run button on your IDE or right click on your testNG file and click RUN

Below scenarios are mentioned in TestNG file:
<class name="Frontend.testCases.ScenarioLogin"> </class>
<class name="Frontend.testCases.ScenarioValuation"> </class>
<class name="Frontend.testCases.ScenarioValuationReport"> </class>
````
- Run via terminal
```sh 
This will execute only one scenario i.e. ScenarioValuationReport
mvn clean -Dtest=ScenarioValuationReport test -DreTry=1 -Dbrowser -Denv
```
- Run via gitlab CI pipeline
```sh 
This will execute only one scenario i.e. ScenarioValuationReport in headless mode and on selenium hub server
mvn clean -Dtest=ScenarioValuationReport test -DreTry=2 -Dbrowser -Denv -DgridUrl=http://selenium__standalone-chrome:4444/wd/hub
```
- Run via circleci pipeline ()
```sh 
This will execute only one scenario i.e. ScenarioValuationReport in headless mode and on selenium hub server
mvn clean -Dtest=ScenarioValuationReport test -DreTry=2 -Dbrowser -Denv -DgridUrl=http://selenium__standalone-chrome:4444/wd/hub
```
**<ins>NOTE:<ins>** _When executing from pipeline make sure you change remote=true in path src\main\java\resources\config.properties_

