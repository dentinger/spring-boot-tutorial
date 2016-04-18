# Spring Boot Starter Application

## Getting Started with Boot

1. Go create a [Spring boot app](http://start.spring.io)
2. Choose build tool of choice for project building (We use Gradle)
3. For now leave group and artifact alone
4. Add Web, Actuator, and DevTools as Dependancies
5. Click Generate Project - this generates a starter zip and downloads it.
6. Unzip project contents to chosen directory
7. run

        ./gradlew clean build bootRun

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

         ./gradlew clean build bootRun

6. In a browser,  verify the service displays "Hello World" at [http://localhost:8080/helloworld](http://localhost:8080/helloworld).
7. Congratulations!  You now have a working basic Rest based webservice with Spring Boot.

## Customer Feedback Service

This service will allow a client to "rate a product".

### Requirements

* Customer should be able to give feedback on a product via a rating (stars?) and description.
* Customer should be able to see the current average rating ofa product

## Steps:

### Getting Redis Setup:

Install Redis on your machine.
On a mac *brew install redis*.
On a Windows machine go [here](https://github.com/MSOpenTech/redis/releases)
Once installed run:

    redis-server

### Add Redis to the application
Modify the build.gradle file to have the following compile time dependancy:

    compile('org.springframework.boot:spring-boot-starter-redis')

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

### Create some new endpoints for feedback

1. Create an endpoint under **/feedback** that will return a Product's rating by a specific user.
This is a GET method and takes the following path parameters:

  * pid - Product Id
  * uid - User Id

2. Create an endpoint under **/feedback** that will update a Product's rating for a specific user.
THis is a POST method and takes the following path parameters:

  * pid - Product Id
  * uid - User Id
  * rating - floating point rating

3. Create an endpoint under **/feedback** that returns the average rating for a product id.  This is a GET method and takes just pid.
4. Create and endpoint under **/feedback** that given a Product id returns a histogram of the ratings. This is  GET method and takes just pid.
5. Create a service layer that will interact Redis.  (We don't want business logic mixing with the controller)

### Let's have a jdbc database also

Let's allow a user to add rating comments with their rating and keep their ratings around for a while.
That seems like it is something useful for a customer rating app to handle.  For this part of the tutorial,
the objective is to have the app save reviews, products, and users in a database and also be able to
retrieve them. (Why save if you won't retrieve, right?')  Let's get started...

1. Modify the build.gradle file to have the following compile time dependancy:

       compile('org.springframework.boot:spring-boot-starter-data-jpa')
       compile('org.springframework.boot:spring-boot-starter-data-rest')
       compile("com.h2database:h2")

2. Create a few JPA based repositories.  One for each of the underlying pieces of data we care about.
 Let's start with a repo for Products, Users, and finally one for UserReviews.
3. Integrate the new Repositories into the the service layer where appropriate.
4. Update the domain objects to have the desired JPA based annotations so the data you want to store is persisted.
5. Update domainc objects to include a few Spring Data Rest projections.
6. Submit a handful of "reviews"

        http://localhost:8080/feedback/123/123/2.1
        http://localhsot:8080/feedback/123/345/5
        http://localhost:8080/feedback/999/123/4.2

7. Verify that the Spring Data Rest Repository is returning the data you want. If you hit http://localhost:8080/products the following data should be returned:

        {
          productId: "999",
          _embedded: {
            reviews: [
             {
                userId: "123",
                productId: "999",
                rating: 4.2,
                comments: "None",
                _links: {
                  self: {
                    href: "http://localhost:8080/userReviews/3{?projection}",
                    templated: true
                  },
                  user: {
                    href: "http://localhost:8080/userReviews/3/user"
                  },
                  product: {
                    href: "http://localhost:8080/userReviews/3/product"
                  }
              }
             }
            ]
        }

