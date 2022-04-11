# library

For the task I used User and Book tables.
For these 2 table I used ManyToMany relations. This is because 1 user can have many books, and one book can have many users.

The code doesn't contain UI part. we discussed that I implement only Backend.
To test code please use library.postman_collection.json in Postman.

I added User controller to show users that we can have in system.
For UserController and UserService I didn't use TDD. TDD was used only for stories. 
In UserController I return Dto so that user cannot see all information about book.
In all controllers I return Ids to make testing simple. 
I tested also negative scenarious and threw exceptions where needed. 

gradlew, gradlew.bat , settings.gradle were added by idea