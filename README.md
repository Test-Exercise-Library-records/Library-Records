# Library-Records
This Spring Boot microservice manages library records with a PostgreSQL database. It includes CRUD operations and API documentation via Swagger.

## Features
  * Entity Class: LibraryRecord with id, title, and author.
  * RESTful CRUD API: Create, Read, Update, Delete library records.
  * Data Mapping: Uses MapStruct for easy data mapping.
  * Java 17: Developed with Java 17.
  * Test Coverage: Achieves over 80% test coverage.
  * Git Repository: Code hosted on Git platform (e.g., GitHub).

## How to Run
  * Clone the repository.
  * Configure PostgreSQL, create ``` library ``` database.
  * Start config server and discovery server.
  * Build and run the application usnig
    ```
    mvn clean spring-boot:run
    ```
  * Access Swagger at [SwaggerDocs](http://localhost:8085/swagger-ui/index.html)

## What next should be done if there will be more time
  * Clean up unnecassary code 
  * Carry out api documentation from controller to configs
  * Add test data for integration tests and make few test strategies
  * Complete second part of the task
  * Create and configure kubernetes cluster 
  * Add gateway microservice
  * Add author entity many to many to lib-records
  * One command project start
    
## Contributions
  Feedback and contributions are welcome via the project's repository.

## License
  MIT License. See LICENSE.
