**Wizer ASSESSMENT**

API for a Wizer Assessment

**PREREQUISITES**

1. mySqlWorkBench must be running locally
2. Request and response objects are stored in mySql database, based on the configured properties.

**Technologies**
1. Java
2. Maven
3. Spring Boot
4. JUnit
5. mySql

**Requirements**
You need the following to build and run the application:

1. JDK 17
2. Maven 3.8.1 (Optional as code already contains maven wrapper)

**How to run**

step 1 - clone project with from [here](https://github.com/Chigozie100/wizer-assessement)

git clone https://github.com/Chigozie100/wizer-assessement

step 2 - move into the project directory cd wizerAssessment

step 3 - Generate the .jar file mvn clean install OR ./mvnw clean install

step 4 - run the project java -jar wizer-assessment.jar

**Testing the API endpoints with various inputs**

**EndPoints**

1. Add Category http://localhost:8080/api/v1/addCategory Request { "name": "dataStructure" }
   Response {
   "headers": {},
   "body": {
   "id": 2,
   "name": "dataStructure"
   },
   "statusCodeValue": 201,
   "statusCode": "CREATED"
   }
2. Edit Category http://localhost:8080/api/v1/editCategory/1 Request {"name": "comic"}
   Response {"name": "comic"}
3. List Categories http://localhost:8080/api/v1/categories?pageNo=0&pageSize=10&sortBy=id 
   Request:  pageNo 0
   pageSize 10
   sortBy id
   Response {
   "content": [
   {
   "id": 1,
   "name": "comic"
   },
   {
   "id": 2,
   "name": "dataStructure"
   }
   ],
   "pageable": {
   "sort": {
   "empty": false,
   "sorted": true,
   "unsorted": false
   },
   "offset": 0,
   "pageNumber": 0,
   "pageSize": 10,
   "paged": true,
   "unpaged": false
   },
   "totalPages": 1,
   "totalElements": 2,
   "last": true,
   "size": 10,
   "number": 0,
   "sort": {
   "empty": false,
   "sorted": true,
   "unsorted": false
   },
   "numberOfElements": 2,
   "first": true,
   "empty": false
   }
4. Add Book http://localhost:8080/api/v1/addBook Request { "title":"gujarati", "author": "gozie", "isbn": 12 }
   Response {
   "headers": {},
   "body": {
   "id": 4,
   "title": "gujarati",
   "author": "gozie",
   "isbn": 12,
   "category": null
   },
   "statusCode": "CREATED",
   "statusCodeValue": 201
   }
5. Edit Book http://localhost:8080/api/v1/editBook/3 Request { "title":"fundamental maths", "author": "prince",
   "isbn": 90 }
   Response {
   "title": "fundamental maths",
   "author": "prince",
   "isbn": 90 }
6. List Books http://localhost:8080/api/v1/books?pageNo=0&pageSize=10&sortBy=id
   Request:  pageNo 0
   pageSize 10
   sortBy id
   {
   "content": [
   {
   "id": 2,
   "title": "maths",
   "author": "gozie",
   "isbn": 12,
   "category": {
   "id": 1,
   "name": "dataStructure"
   }
   },
   {
   "id": 3,
   "title": "fundamental maths",
   "author": "prince",
   "isbn": 90,
   "category": {
   "id": 1,
   "name": "dataStructure"
   }
   },
   {
   "id": 4,
   "title": "gujarati",
   "author": "gozie",
   "isbn": 12,
   "category": null
   }
   ],
   "pageable": {
   "sort": {
   "empty": false,
   "sorted": true,
   "unsorted": false
   },
   "offset": 0,
   "pageNumber": 0,
   "pageSize": 10,
   "paged": true,
   "unpaged": false
   },
   "totalPages": 1,
   "totalElements": 3,
   "last": true,
   "size": 10,
   "number": 0,
   "sort": {
   "empty": false,
   "sorted": true,
   "unsorted": false
   },
   "numberOfElements": 3,
   "first": true,
   "empty": false
   }
7. Add Books to Category http://localhost:8080/api/v1/dataStructure
   Request [{

   "title":"maths",
   "author": "gozie",
   "isbn": 12
   }, {

   "title":"english",
   "author": "kenny",
   "isbn": 4534
   }]
   Response Book successfully added
8. Create List of Favorite Books Request [
   {
   "name": [
   "avatar",
   "the boss",
   "vengers"
   ]
   }
   ]
Response [
   {
   "name": [
   "avatar",
   "the boss",
   "vengers"
   ]
   }
   ]
9. Delete Book http://localhost:8080/api/v1/deleteBook/1
   Response book deleted
   













