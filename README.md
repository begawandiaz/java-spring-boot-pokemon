# Pokemon API (Spring Boot + PostgreSQL)

This project is a beginner-friendly REST API for Pokemon using Java Spring Boot and PostgreSQL.

## 1. What this API supports

- Get all Pokemon
- Get one Pokemon by ID
- Update one Pokemon by ID
- Delete one Pokemon by ID

Base URL:

- `http://localhost:8080/api/pokemon`

## 2. Prerequisites

Make sure these are installed:

- Java 17+
- Maven 3.9+
- PostgreSQL 14+ (or compatible)
- Postman

Check installed versions:

```bash
java -version
mvn -version
psql --version
```

## 3. Create PostgreSQL database

Open PostgreSQL terminal (`psql`) and run:

```sql
CREATE DATABASE pokemon_db;
```

The app is configured with:

- DB name: `pokemon_db`
- Username: `postgres`
- Password: `postgres`
- Port: `5432`

You can see this in:

- `src/main/resources/application.properties`

If your local DB username/password is different, update these lines in `application.properties`:

```properties
spring.datasource.username=postgres
spring.datasource.password=postgres
```

## 4. How table + seed data are created

When app starts, Spring runs SQL files automatically:

- `src/main/resources/schema.sql` -> creates table `pokemon`
- `src/main/resources/data.sql` -> inserts 10 Pokemon rows

You do NOT need to manually create table or insert data.

## 5. Run the application

From project root:

```bash
mvn spring-boot:run
```

Wait until you see logs that indicate app started, usually includes:

- `Tomcat started on port 8080`
- `Started PokemonApiApplication`

## 6. Quick DB check (optional)

In PostgreSQL:

```sql
\c pokemon_db
SELECT * FROM pokemon;
```

You should see at least 10 rows.

## 7. Test in Postman (step-by-step)

### 7.1 Get all Pokemon

- Method: `GET`
- URL: `http://localhost:8080/api/pokemon`
- Body: none

Expected:

- Status `200 OK`
- JSON array of Pokemon

### 7.2 Get one Pokemon by ID

- Method: `GET`
- URL: `http://localhost:8080/api/pokemon/1`
- Body: none

Expected:

- Status `200 OK` if ID exists
- Status `404 Not Found` if ID does not exist

### 7.3 Update one Pokemon by ID

- Method: `PUT`
- URL: `http://localhost:8080/api/pokemon/1`
- Header: `Content-Type: application/json`
- Body (raw JSON):

```json
{
  "name": "Bulbasaur Updated",
  "type": "Grass/Poison",
  "level": 10
}
```

Expected:

- Status `200 OK`
- Response JSON with updated fields

Notes:

- You can send partial update values too (for example only `level`).
- Current service updates only non-null fields.

### 7.4 Delete one Pokemon by ID

- Method: `DELETE`
- URL: `http://localhost:8080/api/pokemon/1`
- Body: none

Expected:

- Status `204 No Content` when delete succeeds
- Status `404 Not Found` if ID does not exist

After delete, test again:

- `GET http://localhost:8080/api/pokemon/1` -> should return `404`

## 8. Common problems and fixes

### Problem: `mvn: command not found`

Maven is not installed or not in PATH.

Fix:

- Install Maven
- Re-open terminal
- Confirm with `mvn -version`

### Problem: DB connection error (`Connection refused` / auth error)

Fix:

1. Ensure PostgreSQL service is running.
2. Verify DB exists: `pokemon_db`.
3. Check username/password in `application.properties`.
4. Confirm PostgreSQL is listening on port `5432`.

### Problem: Port 8080 already used

Fix options:

1. Stop the process using `8080`, or
2. Change app port in `application.properties`:

```properties
server.port=8081
```

Then call Postman with `http://localhost:8081/...`.

## 9. Useful cURL alternatives (without Postman)

Get all:

```bash
curl http://localhost:8080/api/pokemon
```

Get one:

```bash
curl http://localhost:8080/api/pokemon/1
```

Update:

```bash
curl -X PUT http://localhost:8080/api/pokemon/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Bulbasaur Updated","type":"Grass/Poison","level":10}'
```

Delete:

```bash
curl -X DELETE http://localhost:8080/api/pokemon/1
```
