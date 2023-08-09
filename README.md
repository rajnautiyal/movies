# Movies API

<p align="left">
Restful API built in Spring Boot that allows users to search for movies and actors. It also allows users to add movies and actors to the database.

The API is built using:

-   Spring Boot: `2.6.2`
-   Java: `11`
-   MySQL: `8.0.18`
-   Maven: `4.0.0`

## Project Setup


Configure the application.properties with your database credentials.

```
spring.datasource.url={DB url}
spring.datasource.username={DB username}
spring.datasource.password={DB password}
```

Build the project and run the tests:

```
mvn clean package
```

Run the project:

```
 mvn spring-boot:run
```

To access the documentation, go to http://localhost:8080/swagger-ui/index.html (or http://localhost:8080/.

## Project Setup with Docker

Create a `.env` file with the following environment variables:

```
MYSQLDB_ROOT_PASSWORD={DB password}
MYSQLDB_DATABASE={DB name}
MYSQLDB_LOCAL_PORT={DB port}
MYSQLDB_DOCKER_PORT={Docker DB port}
MYSQLDB_USER={DB username}
```

Run the project with docker:

```
docker-compose up -d
```

## Authentication

The avaliables endpoints are:

#### Register a user and use email as username 

```
POST /v1/user/singup
Accept: application/json
Content-Type: application/json
{
  "email": "string",
  "password": "stringst",
  "first_name": "string",
  "last_name": "string"
}

RESPONSE: HTTP 201
{
  "email: String,
  "message": String,
}
```

#### Login

```
POST /v1/user/signin
Accept: application/json
Content-Type: application/json
Body:
{
  "email": "string",
  "password": "string",
}

RESPONSE: HTTP 200
{
  "message": "string",
  "email": "string",
  "token": "string"
}
```

## Endpoints

To have access to the API, you need to be authenticated. To do so, you must first create a user using the [Register endpoint](#authentication). Then, send a POST request to the `/login` endpoint. The response will contain a token that you need to send in the `Authorization` header of all your requests: `Bearer <token>`. The token will expire in 24 hours.

### Movies

The avaliables endpoints for movies are:

-   `GET v1/movies`
-   `GET /v1/movies?page=0&size=1`
-   `GET /movies/{id}`
-   `POST /movies`
-   `PUT /movies/{id}`
-   `DELETE /movies/{id}`

### Rating

The avaliables endpoints for rating are:

Create Rating 
```
- POST  /v1/rating/
  Accept: application/json
  Content-Type: application/json
  Body:
  {
  "movieId": Integer,
  "rating": Integer,
  }
 
```
Get Rating for movie
```
- GET /v1/rating/movie/{movieID}