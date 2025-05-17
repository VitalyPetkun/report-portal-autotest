# Setup configuration
1. Open file ./src/test/resources/testData.properties
2. Set values for parameters:
- login
- password
- apiKey

# Quick start
Running file:
- ./src/test/resources/testng.xml

Running file from the command line:
- mvn clean test

Generate allure-report:
- mvn allure:report

Open allure-report in browser:
- target\site\allure-maven-plugin\index.html

# Author
Vitali Petkun

