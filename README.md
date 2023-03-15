# MovieRama

MovieRama is yet another social sharing platform where users can share their favorite movies and interact with likes and hates.

##### Stack

* Java 17
* Angular 14
* Spring Boot 3
* PostgreSQL 15.2
* Maven 3
* Docker

##### Application Information

The MovieRama app consists of two main components
* /web directory that Angular 14 resides upon, and is used as the presentation and UI layer
* /app directory that Spring Boot resides upon, and is used as the backend providing the authentication with JWT, REST Api and repository with JPA

##### Application Execution

MovieRama app is proposed to be executed using the docker compose tool, in order for all the services (Angular with Node for the frontend, Spring Boot with integrated Tomcat for the backend, PostgreSQL for the database)\
to be built and initialized with their intended configuration and order.

```
docker-compose -f .\docker.compose.yml build --no-cache
docker-compose -f .\docker.compose.yml up --build
```

##### Application Startup

After building the app, then navigate to:
```
http://localhost:4200/movies/
```

##### Known issues

* The filtering using likes/ hates/ dates cannot be applied one after another. As a workaround one should apply a filter go back to unfiltered list and apply the same or another filter.
* The like / hate / unlike / unhate action takes some seconds to perform the persistence and refresh of data, without any spinner to inform the user to wait.

##### Future Improvements

* Utilize a NoSQL database to replace Postgre
* More robust and asynchronous UI to improve UX
* More thorough unit testing to achieve e2e coverage
* Improve performance on JPA Queries
* Use more helpers and convert authentication towards aspect orientation