# Logic EA backend challenge

## Problem definition
Assume an application named Cards that allows users to create and manage tasks in the form of cards:

- Application users are identified uniquely by their mail address, have a role (`Member` or `Admin`) and use a password to authenticate themselves before accessing cards
    - Members have access to cards they created
    - Admins have access to all cards
- A user creates a card by providing a name for it and, optionally, a description and a color
    - Name is mandatory
    - Color, if provided, should conform to a `6 alphanumeric characters prefixed with a #` format
    - Upon creation, the status of a card is **To Do**
- A user can search through cards they have access to
    - Filters include name, color, status and date of creation
    - Optionally limit results using page & size or offset & limit options
    - Results may be sorted by name, color, status, date of creation
- A user can request a single card they have access to
- A user can update the name, the description, the color and/or the status of a card they have access to
    - Contents of the description and color fields can be cleared out
    - Available statuses are To Do, In Progress and Done
- A user can delete a card they have access to

**Build a RESTFUL web service that satisfies the aforementioned requirements using Java, Spring Boot and MySQL**
### Tools used
- Java 17
- Springboot
- MySQL 8.xx
- Insomnia REST client for testing
- Liquibase for database migrations


## Design context

The API was developed with SpringBoot and uses  ***application/json*** format for data exchange.

This request and response data is also annotated with jackson for ease of field naming for frontend applications while prserving maintainability of the backend code.


## Assumptions

- [x] Migrations are used to create the database tables
- [x] User seeding scripts will be provided if required
- [x] Deployment target is open-ended but provision have been made to provide docker compose deployment for quick testing
- [x] CI/CD integration is out of scope of the task
- [x] Data will always be served in application/json format

## Building and deployment

The application is written using Java and deployment is via Docker.

### Requirements

- JDK 17
- IntelliJ IDE is recommended as it has better support for Java and will reduce development Pain
- Docker should be installed on the machine
- The project uses MySQL database however if required you can leverage any RDBMS of your choice

### Running the application

Launch your instance of IntelliJ got to `OPEN` and select the folder with the source code, the project will be loaded and IntelliJ will automatically set up the gradle build tool and configure all dependencies, you will receive a notification if the JDK version do not match and a prompt to set the correct one.

Configure the database parameters under `datasource` in the ``application.yml`` or set them using the following **ENV** variables
```yaml
    url: ${DB_URL:jdbc:mysql://localhost:3306/cards}
    username: ${DB_USER:root}
    password: ${DB_PASS:}
```

To run migrations under the application profile in project settings set the active spring profile to ``dev``, this will create the necessary tables.

Additional setting can be referred to under ``application-dev.yml``

### additional you can run the SQL scripts under data folder to create all tables and insert test data

password for the user accounts is: **andalite6**

Once this is done you can launch it in debug mode to verify there are no runtime or build errors, at this point the default migration will run and create the `USERS` table using within the In memory H2 database.

If you choose to not use the IDE and user the terminal by running `./gradlew build` ensure that **JDK17** is set in your environment path.


If you want a disk based storage that won't lose data when you stop the application.

>Go to the **application.yml** under `src\main\resources\application.yml` 
> 
> Replace the URL line value `url: "jdbc:h2:mem:logicea_db;DB_CLOSE_ON_EXIT=FALSE"` with this `url: "jdbc:h2:~/logicea_db;DB_CLOSE_ON_EXIT=FALSE"`

The application will start and run under port  **8000**

### Available endpoints

The following endpoints are available in the application

| Endpoint                       | Function                                               | ROLE          |
|--------------------------------|--------------------------------------------------------|---------------|
| **v1/users/auth** `POST`       | This performs user authentication to get the JWT token | MEMBER,ADMIN  |
| **api/v1/users** `GET`         | Get a list of all USERS                                | ADMIN         |
| **api/v1/cards** `GET`         | Allows retrieval of list of cards based on user roles  | MEMBER, ADMIN |
| **api/v1/cards** `POST`        | Add new card                                           | MEMBER, ADMIN |
| **api/v1/cards/{id}** `PUT`    | Update existing card                                   | MEMBER        |
| **api/v1/cards/{id}** `GET`    | Allows retrieval a single  card based on user roles    | MEMBER        |
| **api/v1/cards/{id}** `DELETE` | Allows deletion a single  card based on user roles     | MEMBER        |

The card endpoint **api/v1/cards** `GET` has these further optional parameters

#### Pagination and Sorting

**/v1/cards?page=1&size=2&sortField=updatedAt&sortOrder=desc** `GET`

Sortable fields are:

``` yaml
  cardName
  cardColor
  cardStatuS
  createdAt
  updatedAt
```

#### Data filtering

**/v1/cards?createdAt=2023-08-27&cardName=new&cardStatus=DONE&cardColor=FF** `GET`

Filter fields are:

``` yaml
  cardName -  wildcard search
  cardColor - wildcard search
  cardStatuS
  createdAt
```

### Build artifacts

To generate the artifact for deployment, launch your terminal with the project directory as the active path and type the following

```bash
./gradlew bootJar
```
This generates a .jar file that will be found under the **build/libs** directory, the Dockerfile will reference it from there.

The next step is to build the docker image  using this command `docker build -t logicea .` this will generate a docker image called **logiceea**


## Known issues

- [x] Repository unit tests fail when using in memory database

## Additional documentation

For openAPI endpoint definition you can acces it via two methos

- Launch the pplication then in the browser use the following url http://localhost:8080/swagger-ui/index.html
- 
- View the archived version at https://app.swaggerhub.com/apis/masgeek/cards-management_api/1.0.0

For insomnia endpoint definition refer to the ``docs/logice-ea.json`` and import it into Insomnia REST client


