# Demo 99

Final demo with the entire application up and running

### Cleaning Docker images

If you need to clean all the Docker images, use the following commands:

* `docker image ls | grep bookstore`
* `/bin/bash -c 'docker image rm $(docker image ls -q "bookstore/*") -f'`


## Infrastructure

The microservice architecture needs a few tools running before it gets to work.

### Consul

Consul is used to register and discover all the microservices

```
$ docker-compose -f consul/consul.yml up
```

Then go to the web interface at http://localhost:8500

## Microservices

All microservices can get built and executed with the following commands:

To build: 

```
$ mvn clean install
```

To execute you can either use the Spring Boot maven plugin or just execute the Jar:

```
$ mvn spring-boot:run
```

```
$ mvn clean install
$ java -jar services/generator-api/target/xxx-api-xx-thorntail.jar
```

To build a Docker image out of a microservice

```
$ mvn docker:build
```

### Generator API

The Generator API generates book numbers. Check the following URLs:

* API at [http://localhost:8081/generator/api/numbers]()
* Swagger UI at [http://localhost:8081/generator/swagger-ui.html]()
* Swagger contract at [http://localhost:8081/generator/v2/api-docs]()

### Inventory API

The Inventory API manages the number of books available in the warehouses. Check the following URLs:

* API at [http://localhost:8082/inventory/api/books]()
* Swagger UI at [http://localhost:8082/inventory/swagger-ui.html]()
* Swagger contract at [http://localhost:8082/inventory/v2/api-docs]()

### Top Rated API

The Top Rated API manages the top rated books. Check the following URLs:

* API at [http://localhost:8084/toprated/api/books]()
* Swagger UI at [http://localhost:8084/toprated/swagger-ui.html]()
* Swagger contract at [http://localhost:8084/toprated/v2/api-docs]()

## Databases

Drop database

```
$ mvn liquibase:dropAll
```

Liquibase

```
$ mvn clean compile
$ mvn liquibase:update
```

## Clients

### Angular

Here are the commands and the steps that were used to create this project 

### Main application BookStore

#### NG CLI Commands

```
# Create a new Angular project with Angular CLI
$ ng new bookstore --directory bookstore --routing true --skip-tests true --inline-style true

# Add the needed dependencies
$ yarn add @fortawesome/fontawesome-free@5.3.1
$ yarn add @fortawesome/angular-fontawesome@0.1.1
$ yarn add @fortawesome/fontawesome-svg-core@1.2.2
$ yarn add @fortawesome/free-solid-svg-icons@5.2.0
$ yarn add jquery@3.3.1
$ yarn add bootstrap@4.1.3
$ yarn add @ng-bootstrap/ng-bootstrap@3.0.0
```

The main application has two components

```
$ ng generate component home --inline-style true --spec false
$ ng generate component about --inline-style true --spec false
```

#### Configuration

In `angular.json` file add :

```
"styles": [
  "node_modules/bootstrap/dist/css/bootstrap.min.css",
  "node_modules/@fortawesome/fontawesome-free/css/all.css",
  "src/jumbotron.css",
  {
    "input": "src/styles.css"
  }
],
"scripts": [
  "node_modules/jquery/dist/jquery.slim.js",
  "node_modules/bootstrap/dist/js/bootstrap.bundle.js"
]
```

In `app.module.ts` add NG Bootstrap

```
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

  imports: [
    NgbModule.forRoot()
  ],
```

### Number Generator Library

```
# Create a new Angular library with Angular CLI
$ ng generate library generator --prefix gen
```

The Generator library has several components

```
$ ng generate component Home --project generator --inline-style true --spec false
$ ng generate component BookNumber --project generator --inline-style true --spec false
```

Build the library so you can use it

```
$ ng build generator
```

Use Open API Codegen to generate the Client stubs

```
$ openapi-generator generate -i http://localhost:8081/generator/v2/api-docs -g typescript-angular  -o ./projects/generator/src/lib/shared
```

### Inventory Library

```
# Create a new Angular library with Angular CLI
$ ng generate library inventory --prefix inv
```

The Inventory library has several components

```
$ ng generate component Home --project inventory --inline-style true --spec false
$ ng generate component Book --project inventory --inline-style true --spec false
```

Build the library so you can use it

```
$ ng build inventory
```

### Store Library

```
# Create a new Angular library with Angular CLI
$ ng generate library store --prefix str
$ ng generate component store --project store --inline-style true
```

The Store library has several components

```
$ ng generate component Home --project store --inline-style true --spec false
$ ng generate component Author --project store --inline-style true --spec false
$ ng generate component Book --project store --inline-style true --spec false
$ ng generate component Category --project store --inline-style true --spec false
$ ng generate component Publisher --project store --inline-style true --spec false
```

Build the library so you can use it

```
$ ng build store
```

### Top Rated Library

```
# Create a new Angular library with Angular CLI
$ ng generate library toprated --prefix top
```

The Generator library has several components

```
$ ng generate component Home --project toprated --inline-style true --spec false
$ ng generate component Book --project toprated --inline-style true --spec false
```

Build the library so you can use it

```
$ ng build generator
```

