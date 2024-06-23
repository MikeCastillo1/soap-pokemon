# Pokemon Test
This projects uses java 21, please install the java 21 jdk, you can use [SDK](https://sdkman.io/usage) for multiple java versions

## Instructions
1. compile: `mvn clean compile`
2. run the application, go to the target directory and execute the following command
   `java -jar pokemon-0.0.1-SNAPSHOT.jar` or if you are using Intellij go the PokemonApplication class and run it directly
3. Go to the wsdl url: `http://localhost:8080/ws/pokemons.wsdl`
4. Go to the api directory, there you can find a services.http file,
    you can use it for testing all the endpoints using post, you  can also use curl, or other soap client
5. On the console you will see the methods that are getting called, and the request logs.
6. This application uses AOP for saving the ApiRequestLogs information, in an H2 db, so after doing some requests, review 
    the requests logs in the [h2-console](http://localhost:8080/h2-console/) to access the database console, 
    use this data for connection, that is also provided on the application.properties file:
    - Driver class: org.h2.Driver
    - JDBC URL: jdbc:h2:mem:testdb
    - username: sa
    - password: password
   
    Then go the table API_REQUEST_LOG, and use this query
   `SELECT * FROM API_REQUEST_LOG`
    
It was added an integration test for only one method, you can check on PokemonEndpointTest





