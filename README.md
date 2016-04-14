# Spring Boot Starter Application

## Getting Started with Boot
1. Go create a [Spring boot app](http://start.spring.io)
2. Choose build tool of choice for project building (We use Gradle)
3. For now leave group and artifact alone
4. Add Web, Actuator, and DevTools as Dependancies
5. Click Generate Project - this generates a starter zip and downloads it.
6. Unzip project contents to chosen directory
7. run
>./gradlew clean build bootRun

8. In a browser,  verify the app health at [http://localhost:8080/health](http://localhost:8080/health).
9. Congratulations! You now have a working basic web application that looks just like this one!

## Hello World Service

1. In project create a package named *controller*
2. Create java class *HelloWorldController.java*
3. Annotate the class with the following *@RestController*
4. Create a public method that returns a string, is annotated with *@RequestMapping(value="helloworld")*, and returns the string "Hello World"

  @RequestMapping(value="/helloworld")
  public String hello() {
      return "Hello World"
  }

5. Build and run the app either in your IDE or from the command line
>./gradlew clean build bootRun
6. In a browser,  verify the service displays "Hello World" at [http://localhost:8080/helloworld](http://localhost:8080/helloworld).
7. Congratulations!  You now have a working basic Rest based webservice with Spring Boot.

## Starting on a Product rating service
This service will allow a client to "rate a product".

## TODO
