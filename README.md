# P6-Full-Stack-reseau-dev

This project was generated with:
> [Angular CLI](https://github.com/angular/angular-cli) version 14.1.0.

> [Spring Boot](https://spring.io/projects/spring-boot) version 3.0.6.

> [OpenJDK](https://openjdk.org/projects/jdk/19/) version 17.0.1.

> [Spring Security](https://spring.io/projects/spring-security) version 6.0.3.

## Start the project

Git clone:

> git clone https://github.com/Asediab/Developpez-une-application-full-stack-complete.git

Install MySQL (MySQL Community Server - GPL):

> Port: 3306

> Create the username and the password

> Create a new schema named: `ocr`

> Give rights for this username on the `ocr` schema


Set the user's environment variables

> Variable for MySQL password: `DATA_SOURCE_PASS`

> Variable for MySQL username: `DATA_SOURCE_USER`

> Variable for the JWT secret: `JWT_SECRET`

Go inside folder:

> cd Developpez-une-application-full-stack-complete

### Back-end

Go inside folder:

> cd back

Install dependencies:

> mvn install

Launch Back-end:

> mvn spring-boot:run

The data tables and test data will be created automatically using FlyWayDB.

If you want to change any configuration you can modify `back-end/src/main/resources/application.properties`

Endpoints are available at http://localhost:8080/

### Front-end

Go inside folder:

> cd front

Install dependencies:

> npm install

Launch Front-end:

> npm run start

Open your browser at http://localhost:4200/

### Test

#### Front-End

Go inside folder:

> cd front

Launching e2e test:

> npm run e2e:ci

Generate a coverage report (you should launch e2e test before):

> npm run e2e:coverage

The Report is available here:

> front/coverage/lcov-report/index.html

#### Back-End

Go inside folder:

> cd back

Launching test:

> mvn clean test

The Report is available here:

> /back/target/site/jacoco/index.html

### Documentation

Generate the Back-End project Javadoc
> Inside ./back execute this Maven command: mvn javadoc:javadoc -e

> Open: ./back/target/site/apidocs/index.html

Back-End API documentation

> Swagger is available at http://localhost:8080/swagger-ui/index.html#/

## Test data set

> Login: `yoga@studio.com`

> Pass: `1F4@sf5s6,18F4@sf5s6`