# Spring Boot Starter Application

## Getting Started with Boot
1. Go create a [Spring boot app](http://start.spring.io)
2. Choose build tool of choice for project building (We use Gradle)
3. For now leave group and artifact alone
4. Add Web, Actuator, and DevTools as Dependancies
5. Click Generate Project - this generates a starter zip and downloads it.
6. Unzip project contents to chosen directory
7. run ./gradlew clean build bootRun and wait for internet to finish downloading
8. In a browser,  verify the app health at [http://localhost:8080/health](http://localhost:8080/health).
9. Congratulations! You now have a working basic web application that looks just like this one!