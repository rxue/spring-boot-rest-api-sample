# Assumption of Business Logic
Each restaurant has its unique pizzas, i.e. `Restaurant` to `Pizza` is a bidirectional `OneToMany` relationship 

# Environment Requirement
* Internet connection
* Preferably Linux with Maven, Curl and Java 8 (Compiler and JRE)

# How to Compile and Run
`cd` to this project home directory, where pom.xml is located
* Compile with command: `mvn clean package -Dspring.profiles.active=derby`
* Run With command: `java -Dspring.profiles.active=derby -jar target/restaurant-0.0.1-SNAPSHOT.jar`

# Commands to test with Curl
* List all restaurants: `curl -v -H "Content-Type: application/json" -X GET http://localhost:8080/rest/restaurants`
* Add a new restaurant:
  * `curl -v -H "Content-Type: application/json" -X POST -u "admin:admin" -d '{"name":"Amarillo","city":"Tampere","address":"Hatanpäänvaltatie 1","email":"pizza@amarillo.fi","phone":"0207 716 328","open_at":"11:00:00","close_at":"22:00:00"}' http://localhost:8080/rest/restaurant`
  * `curl -v -H "Content-Type: application/json" -X POST -u "admin:admin" -d '{"name":"Rax","city":"Tampere","address":"Hatanpäänvaltatie 1","email":"pizza@rax.fi","phone":"0207 716 328","open_at":"11:00:00","close_at":"22:00:00"}' http://localhost:8080/rest/restaurant` 
  * `curl -v -H "Content-Type: application/json" -X POST -u "admin:admin" -d '{"name":"Amarillo","city":"Tampere","address":"Hatanpäänvaltatie 1","email":"pizza@amarillo.fi","phone":"0207 716 328","open_at":"10:00:00","close_at":"23:30:00"}' http://localhost:8080/rest/restaurant`
* Get specific restaurant (by id with all pizza details listed): `curl -v -H "Content-Type: application/json" -X GET http://localhost:8080/rest/restaurant/full/1`
## Delete a restaurant
* `curl -v -H "Content-Type: application/json" -X DELETE -u "admin:admin" http://localhost:8080/rest/restaurant/3

## create pizza
curl -v -H "Content-Type: application/json" -X POST -u "admin:admin" -d '{"name":"California", "size":"L", "key_ingredients":"fetajuusto", "price":10, "restaurant_id":2}' http://localhost:8080/rest/private/pizza

curl -H "Content-Type: application/json" -X POST -d '{"id":null,"name":"Amarillo second","city":"Tampere","address":"Hatanpäänvaltatie 1","email":"pizza@amarillo.fi","phone":"0207 716 328","open_at":"10:00:00","close_at":"23:30:00"}' http://localhost:8080/rest/restaurant/





