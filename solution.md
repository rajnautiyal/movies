# Basic Design:
* Implemented as Domain-Driven Architecture.
* Spring boot + Mysql for API design
* React of the Web desgin

# General Flow:
* First, the user will register as a new user in the application, using JWT authentication for the same.

* Then, the user will log in to the application. If the user is using the API, they need to obtain the JWT token.

* The user will have a text box in the UI where they can enter the movie name.

* The application will send a request to an external API to fetch the movie details. It will then extract the title for that movie and check in the database whether that movie has won an Oscar.

* The API will also fetch the top 10 highest-rated movies and display them to the user.

* Both from the UI and the API, the user will have options to rate the movies.

#  To-Do List:
* There are many tasks to complete in order to make this production-ready:
* Implement proper caching so every time we are not making external calls or data base call
* Once we are data from the external API we can store that locally so everytime we are not calling the external API
if it is not not so concern about the rating in real time, or we can set the timeline to call the external API
for specific movie
* Implement rate limiting for all API requests, so not one will overload the services, Implement the load balancer to manage the load
* When calling the external API, implement the Circuit Breaker pattern. Currently, we can make 100 requests, so we need to handle what will happen after exceeding 100 requests.
* Instead of using an RDBMS database, switch to a NoSQL database where data can be stored and retrieved more efficiently.
* Implement the proper sharding mechanism so we can partition the data
* Implement proper test cases to ensure functionality and reliability.





