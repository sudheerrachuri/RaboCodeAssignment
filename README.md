# RaboCodeAssignment
CustomerManagement

# Getting Started

### Reference Documentation
To build the application:
------------------------
1)  Navigate to formsubmission

2)  Build the project by using the following command:

       mvn clean install

To run the application:
------------------------

1)  Navigate to src/main/java/com.cts.codeassignment.formsubmission.controller;
2)  Right click on FormSubmissionApplication file and select Run

   
Technical Details for technical team:
-------------------------------------

1) To create a user:
   
    localhost:8080/customerManagement/user/registeruser 
    
  and use the following json format:
  {
  	"id":"78",
  	"name":"praveen",
  	"mobileNumber":"9597415834",
  	"language":"tamil",
  	"email":"ssssdd@gmail.com",
  	"gender":"male"
  }
2)  To get the list of users:

        localhost:8080//customerManagement/user/listofusers
    
3)  To update the user details:

        localhost:8080//customerManagement/user/updateuser
   
4) To delete the user:

        localhost:8080//customerManagement/user/removeuser/161
        
5) The database will be prepopulated with 3 registered users as I inlcuded data.sql in resources.

