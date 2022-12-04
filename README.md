# UniProject

Made by:
- Georgi Georgiev - 2101321017
- Hristiyan Tanev - 2101321020 

The project is made with:
- Java
- Spring Boot
- Spring Security
- Flyway
- Thymeleaf
- HTML(Bootstrap)
- PosgreSQL
- Docker

Test users:
- ivan_barselona@abv.bg : 123456
- naruto_gamer@abv.bg : 123456
- georgigeorgiev@gmail.com : admin
- hristiyantanev@gmail.com : admin

Install notes:

- Start the env file - wthich will set the needed env variables
- create a network with - `docker create network uni_library`
- run the docker compose file with - `docker-compose up -d`
- Start the project - it will create the database and excecute the migration script for the users

Current database desing: 
![image](https://user-images.githubusercontent.com/32382605/205413878-a425acdf-3766-432f-a6b8-143325541ab0.png)

