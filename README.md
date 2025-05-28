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

- [**Node.js**](https://nodejs.org/en)
- **npm**
- [**Angular CLI**](https://github.com/angular/angular-cli) version 14.1.0
- [**MySQL**](https://www.mysql.com/)

### Steps to Setup

**1. Clone the application**

```bash
https://github.com/danchaud-vincent/oc_project2_chatop.git
```

**2. Create MySQL Database**

```bash
create database chatop_db;
```

**3. Setup application.properties**

- Open `api/src/main/resources/application.properties`
- Change the environment variables `${DB_NAME}`, `${DB_USERNAME}`, `${DB_PASSWORD}`, `${JwtKey}` by your setup variables

**4. Build and run the app using maven**

- Open the folder `api` and run maven:

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

## Ressources :

### Mockoon

### Postman

### MySQL

## Author :

**Danchaud Vincent**