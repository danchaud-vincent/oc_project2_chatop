# ChâTop Application

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.1.0.

## Project Description :

ChaTop is a seasonal rental platform connecting future tenants and property owners -- starting from the Basque coast and expanding across France.

### Project Goals 

Set up a complete back-end server.

Provide a secure RESTful API to handle:
- User management (authentication required)
- Rental listings
- Other entities described in the provided database schema
- Deliver accessible Swagger documentation for front-end developers and testers


## Installation :

Before running the project, make sur your environment meets the following requirements:

### Prerequisites 

- **Java** version 17+
- [**Maven**](https://maven.apache.org/) 
- [**MySQL**](https://www.mysql.com/)
- [**Node.js**](https://nodejs.org/en)
- [**Angular CLI**](https://github.com/angular/angular-cli) version 14.1.0

### Steps to Setup

**1. Clone the application**

```bash
https://github.com/danchaud-vincent/oc_project2_chatop.git
```

**2. Create MySQL Database**

```bash
create database DB_NAME;
```

**3. Setup application.properties**

- Open `api/src/main/resources/application.properties`
- Change the following environment variables by your information:
    - `${DB_NAME}`
    - `${DB_USERNAME}`
    - `${DB_PASSWORD}`
    - `${JwtKey}`

**4. Build and run the app using maven**

- Open the folder `api`:
> cd api

- Install dependencies:
> mvn clean install

- Launch the api:
```bash
mvn spring-boot:run
```

**5. Install dependencies and launch Frontend**

- Go inside the folder `frontend`:
> cd frontend

- Install the dependencies :
> npm install

- Launch Front-end :
> ng serve --open


**Other :**

A picture of a location is available in the folder ```api/src/main/resources/static/images```.

## API Documentation

The API is documented with Swagger.

Launch the API and access to the documentation :

http://localhost:3001/swagger-ui/index.html#/

You will need a token to access and use some routes:
- Use the register route to register
- Copy the **token** you received and paste it in the button **Authorize** at the top of the page.

## Technologies :

- Angular version 14.1.0
- HTML
- CSS
- MySQL
- Java
- Spring (dependencies):
    - Spring Web
    - Spring Data JPA
    - MySQL Driver
    - Spring Security
    - OAuth2 Resource Server
    - Lombok
    - SpringDoc OpenAPI WebMVC
- Postman
- Mockoon

## Author :

**Danchaud Vincent**