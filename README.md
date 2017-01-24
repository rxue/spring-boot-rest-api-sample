# Assumption of Business Logic
Each restaurant has its unique pizzas, i.e. `Restaurant` to `Pizza` is a bidirectional `OneToMany` relationship 

# Environment Requirement
* Internet connection
* Preferably Linux with Maven, Curl and Java 8 (Compiler and JRE)

# How to Compile and Run
`cd` to this project home directory, where pom.xml is located
* Compile with command: `mvn clean package -Dspring.profiles.active=derby`
* Run With command: `java -Dspring.profiles.active=derby -jar target/restaurant-0.0.1-SNAPSHOT.jar`

# REST API Instruction (POST, PUT, DELTE request is secured with username `admin` and password `admin` and CSRF disabled just for testing purpose)
* List all restaurants: `GET http://localhost:8080/rest/restaurants`
* Add a new restaurant: `POST http://localhost:8080/rest/restaurant`
* Get specific restaurant (by id with all pizza details listed i.e. list all pizzas with a specific restaurant): `GET http://localhost:8080/rest/restaurant/full/{id}`
* Update restaurant details (identified by id): `PUT http://localhost:8080/rest/restaurant/{id}`
* Delete specific restaurant (identified by id): `DELETE http://localhost:8080/rest/restaurant/{id}`
* List all pizzas of specific restaurant: `GET http://localhost:8080/rest/pizzas?restaurant_id={id}`
* Add new pizza to specific restaurant: `POST http://localhost:8080/rest/pizza`
* Get specific pizza details: `GET http://localhost:8080/rest/pizza/{id}`
* Update pizza details (by pizza id): `PUT http://localhost:8080/rest/pizza/{id}`
* Delete specific pizza: `DELETE http://localhost:8080/rest/pizza/{id}`
* List all pizzas of Web store: `GET http://localhost:8080/rest/pizzas`
* Basic search by pizza name (should return all pizzas whose name contains search term): `GET http://localhost:8080/rest/pizzas?name={pizza name}`

# Example commands to test with Curl
* List all restaurants: `curl -v -H "Content-Type: application/json" -X GET http://localhost:8080/rest/restaurants`
* Add a new restaurant:
  * `curl -v -H "Content-Type: application/json" -X POST -u "admin:admin" -d '{"name":"Amarillo","city":"Tampere","address":"Hatanpäänvaltatie 1","email":"pizza@amarillo.fi","phone":"0207 716 328","open_at":"11:00:00","close_at":"22:00:00"}' http://localhost:8080/rest/restaurant`
  * `curl -v -H "Content-Type: application/json" -X POST -u "admin:admin" -d '{"name":"Rax","city":"Tampere","address":"Hatanpäänvaltatie 1","email":"pizza@rax.fi","phone":"0207 716 328","open_at":"11:00:00","close_at":"22:00:00"}' http://localhost:8080/rest/restaurant` 
  * (without authentication, thus expected to fail) `curl -v -H "Content-Type: application/json" -X POST -d '{"name":"Amarillo","city":"Tampere","address":"Hatanpäänvaltatie 1","email":"pizza@amarillo.fi","phone":"0207 716 328","open_at":"11:00:00","close_at":"22:00:00"}' http://localhost:8080/rest/restaurant` 
* Get specific restaurant (by id with all pizza details listed i.e. list all pizzas with a specific restaurant): `curl -v -H "Content-Type: application/json" -X GET http://localhost:8080/rest/restaurant/full/1`
* Update restaurant details (identified by id): 
  * `curl -v -H "Content-Type: application/json" -X PUT -u "admin:admin" -d '{"name":"Updated","city":"Kuopio","address":"Hatanpäänvaltatie 1","email":"pizza_kuopio@amarillo.fi","phone":"0210 716 328","open_at":"12:00:00","close_at":"23:00:00"}' http://localhost:8080/rest/restaurant/1`
  * (with wrong authentication, thus expected to fail) `curl -v -H "Content-Type: application/json" -X PUT -u "admin:admi" -d '{"name":"Updated","city":"Kuopio","address":"Hatanpäänvaltatie 1","email":"pizza_kuopio@amarillo.fi","phone":"0210 716 328","open_at":"12:00:00","close_at":"23:00:00"}' http://localhost:8080/rest/restaurant/1`
* Delete specific restaurant (identified by id)
  * `curl -v -H "Content-Type: application/json" -X DELETE -u "admin:admin" http://localhost:8080/rest/restaurant/1`
  * (without authentication, thus expected to fail) `curl -v -H "Content-Type: application/json" -X DELETE http://localhost:8080/rest/restaurant/2`
* List all pizzas of specific restaurant: `curl -v -H "Content-Type: application/json" -X GET http://localhost:8080/rest/pizzas?restaurant_id=1`
* Add new pizza to specific restaurant: 
  * `curl -v -H "Content-Type: application/json" -X POST -u "admin:admin" -d '{"name":"California", "size":"L", "key_ingredients":"fetajuusto", "price":10, "restaurant_id":2}' http://localhost:8080/rest/pizza`
  * (with wrong authentication, thus expected to fail) `curl -v -H "Content-Type: application/json" -X POST -u "admi:admin" -d '{"name":"California II", "size":"L", "key_ingredients":"fetajuusto", "price":10, "restaurant_id":2}' http://localhost:8080/rest/pizza`
* Get specific pizza details: `curl -v -H "Content-Type: application/json" -X GET http://localhost:8080/rest/pizza/1`
* Update pizza details (by pizza id)
  * `curl -v -H "Content-Type: application/json" -X PUT -u "admin:admin" -d '{"name":"Updated", "size":"L", "key_ingredients":"fetajuusto", "price":10, "restaurant_id":2}' http://localhost:8080/rest/pizza/1`
  * (without authentication, thus expected to fail) `curl -v -H "Content-Type: application/json" -X PUT -d '{"name":"Updated", "size":"L", "key_ingredients":"fetajuusto", "price":10, "restaurant_id":2}' http://localhost:8080/rest/pizza/1`
* Delete specific pizza 
  * `curl -v -H "Content-Type: application/json" -X DELETE -u "admin:admin" http://localhost:8080/rest/pizza/1`
  * (with wrong authentication, thus expected to fail) `curl -v -H "Content-Type: application/json" -X DELETE -u "d:d" http://localhost:8080/rest/restaurant/2`
* List all pizzas of Web store: `curl -v -H "Content-Type: application/json" -X GET http://localhost:8080/rest/pizzas`
* Basic search by pizza name (should return all pizzas whose name contains search term): `curl -v -H "Content-Type: application/json" -X GET http://localhost:8080/rest/pizzas?name=PIZZA`



