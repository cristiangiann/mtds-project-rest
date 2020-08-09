# Image Server with REST API
#### REST project of Middleware Technologies for Distributed Systems course at Politecnico di Milano

The server allows the user to register, to login, to upload, retrieve and delete their images through REST APIs.
Moreover, it serves a simple website to easily interact with them.
The APIs comply with the third level of HATEOAS principles, using http methods, status codes and hypermedia.

The requirements of the project are listed in [this pdf file](https://github.com/cristiangiann/mtds-project-rest/blob/master/deliverables/requirements.pdf).

#### [Presentation slides](https://github.com/cristiangiann/mtds-project-rest/blob/master/deliverables/presentation.pdf)
---
A complete description of all the served APIs is in [this yaml file](https://github.com/cristiangiann/mtds-project-rest/blob/master/src/main/resources/api_documentation.yaml).
The implemented APIs are the following (icon 🔒 means that a non-logged user is not able to get a successful response):
+ #### Login
  Send user credentials to login. The client session is updated with a cookie.  
  Method: POST  
  Endpoint: /login
+ #### Logout 🔒
  End the current session.  
  Method: POST  
  Endpoint: /logout
+ #### Get list of users
  Get the list of all the users of the application.  
  Method: GET  
  Endpoint: /api/users
+ #### Add new User
  Create new user with name, id and password.  
  Method: POST  
  Endpoint: /api/users
+ #### Get images of logged User 🔒
  Get a list of snapshots with information about the images of the logged user.  
  Method: GET  
  Endpoint: /api/images
+ #### Add single image 🔒
  Add an image file to the logged user's repository.  
  Method: POST  
  Endpoint: /api/images
+ #### Get single image 🔒
  Retrieve an image file.  
  Method: GET  
  Endpoint: /api/images/:id
+ #### Delete single Image 🔒
  Delete a single image from the logged user's repository.  
  Method: DELETE  
  Endpoint: /api/images/:id
+ #### Get preview of single Image 🔒
  Retrieve a small preview of an image.  
  Method: GET  
  Endpoint: /api/images/:id/preview
+ #### Get list of images of single User 🔒
  Get a list of snapshots with information about the images of the selected user, if authorized.  
  Method: GET  
  Endpoint: /api/users/:userId
