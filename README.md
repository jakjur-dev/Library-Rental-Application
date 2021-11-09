# IT Library and Bookstore REST API - Backend
## Description
###In IT Library rental app, You can:
* Rent available books from a number of titles
* Search for more books and Ebooks and buy them on Amazon
* Search for more books and Ebooks and buy or get them free from GoogleBooks
* Add GoogleBooks and ITBookstore entries you are interested in to your Watchlist
###[Live Backend Demo!](https://dashboard.heroku.com/apps/it-bookstore-library)
###[Live Frontend Demo!](https://it-library-rental.netlify.app/)
####To test client-side functionalities, log in as:
*email:* **client@gmail.com**

*password:* **password**
####To test administration panel functionalities, log in as:
*email:* **admin@gmail.com**

*password:* **password**
## Frameworks and Technologies
Java 8, Spring Boot, Hibernate, JUnit4, Mockito, Lombok, Gradle, H2 In Memory Database
## Design Patterns
Facade, Observer
## External Services
1. ITBooks from **_https://api.itbook.store/_**
2. Google Books from **_https://developers.google.com/books/docs/v1/using_**
## Instalation
#### Backend - REST API
1. Source code: https://github.com/jakjur-dev/Library-Rental-Application
2. git clone https://github.com/jakjur-dev/Library-Rental-Application.git
3. Download and install [pgAdmin](https://www.pgadmin.org/download/)
4. Create library-app database
5. Run main method in the **LibraryRentalApplication** class
6. Application will be run on the localhost:8080 address

* please note, that database is running only with pgAdmin on. If you want to change database system to MySQL, please modify *application.properties* file accordingly (some named querries might not work)
## Frontend
1. Source code: https://github.com/jakujur/library-app-front
2. git clone https://github.com/jakujur/library-app-front.git
3. type `npm start` in terminal
4. Application will be run on the localhost:3000
