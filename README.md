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

## Customer Feedback Service
This service will allow a client to "rate a product".
### Requirements
* Customer should be able to give feedback on a product via a rating (stars?) and description.
* Customer should be able to see the current average rating ofa product
### Steps:
#### Getting Redis Setup:
Install Redis on your machine.
On a mac *brew install redis*.
On a Windows machine go [here](https://github.com/MSOpenTech/redis/releases)
Once installed run:
>redis-server
### Add Redis to the application
Modify the build.gradle file to have the following compile time dependancy:
>compile('org.springframework.boot:spring-boot-starter-redis')

After rebuilding the app and restarting it you can hit the health page again [here](http://localhost:8080/health) to see something like:
  {
    status: "UP",
    diskSpace: {
      status: "UP",
      total: 499283816448,
      free: 446935990272,
      threshold: 10485760
    },
    redis: {
      status: "UP",
      version: "3.0.6"
    }
  }

## TODO
