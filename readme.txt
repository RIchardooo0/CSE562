This is the readme file of our 562 project.
All the codes are enclosed in the 562proj.zip. And the two json files are the data for Our   MongoDB NoSQL database. We transferred our data. But we remained using MySQL as the database for our project.

Our project is based on spring boot frame. By using JPA and thymeleaf techniques, we realized a web based library system.

The database we chose is MySQL. We utilized hibernate to realize the connection. And all other settings are specified in the application.properties under src/main/resources folder. And all html files are placed under templates.book folder.

Our main code are divided into three layers, naming controller, entity and repository. 

1.Our repository are interfaces extending JpaRepository. Because all details of query operations have already been implemented in JpaRepository interface, we don't need to write the query code by ourselves. We can just inherite the functions from JpaRepository.

2.The entity layer targeting at defining the "classes" or "entities" for other layer to manipulate. For instance, the attribute of the table would be defined in the entity.

3.The most important layer is the controller layer. It aims at receiving data from the html. And perform queries by calling functions from repository layer. Finally passing processed data to other pages to display.

How to use our database system.

The user can type localhost:8080/ to the login page. By entering username and password, the user can be directed to the page based on user's role. And then the user can use the website based on instructions.