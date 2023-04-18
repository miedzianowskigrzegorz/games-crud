# Spring Boot CRUD REST API with JUnit5
This is a sample project for a Spring Boot CRUD REST API with JUnit 5 tests. The project uses Spring Boot, Spring Data JPA, and H2 Database to implement a RESTful API for managing records(In this sample i created Game) in a database.
# Requirements:
To run the project, you will need the following installed on your system:

*JDK 17

*Maven

*MySQL

# API Endpoints
The following endpoints are available for the API:

- GET /games/ - get all games
- GET /games/{id} - get game by id
- POST /games/ - create a new game
- PUT /games/{id} - update an existing game
- DELETE /games/{id} - delete an game by id

- Each endpoint returns JSON data. See the GameController class in the src/main/java/pl/gm/games/game/controller/ directory for details on the request and response formats.
