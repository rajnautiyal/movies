# Basic Design:
* Implemented as Domain-Driven Architecture.

# General Flow:
* First, the user will register as a new user in the application, using JWT authentication for the same.

* Then, the user will log in to the application. If the user is using the API, they need to obtain the JWT token.

* The user will have a text box in the UI where they can enter the movie name.

* The application will send a request to an external API to fetch the movie details. It will then extract the title for that movie and check in the database whether that movie has won an Oscar.

* The API will also fetch the top 10 highest-rated movies and display them to the user.

* Both from the UI and the API, the user will have options to rate the movies.

#  To-Do List:
* There are many tasks to complete in order to make this production-ready:

* Implement rate limiting for all API requests.

* When calling the external API, implement the Circuit Breaker pattern. Currently, we can make 100 requests, so we need to handle what will happen after exceeding 100 requests.

* Instead of using an RDBMS database, switch to a NoSQL database where data can be stored and retrieved more efficiently.

* Implement proper test cases to ensure functionality and reliability.





