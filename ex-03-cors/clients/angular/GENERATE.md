### Angular

Here are the commands and the steps that were used to create this project 

### Main application BookStore

#### NG CLI Commands

```
# Create a new Angular project with Angular CLI
$ ng new bookstore --directory angular --routing true --skip-tests true --inline-style true

# Add the needed dependencies
$ yarn add @fortawesome/fontawesome-free
$ yarn add @fortawesome/angular-fontawesome
$ yarn add @fortawesome/fontawesome-svg-core
$ yarn add @fortawesome/free-solid-svg-icons
$ yarn add jquery
$ yarn add bootstrap
$ yarn add @ng-bootstrap/ng-bootstrap
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
  "node_modules/bootstrap/dist/css/bootstrap.css",
  "node_modules/@fortawesome/fontawesome-free/css/all.css",
  "src/styles.css"
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
    NgbModule
  ],
```

#### Menu

Change the code in `app.component.html` and add the nav bar CSS styles under style.css

```
 
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
