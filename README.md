# EmployeeCSVPOC
Spring boot based solution

# Problem Statement : 
1.	Read a CSV file (as part of the class path) at application startup, store each record(Employee record) in an in-memory cache solution(choose any caching solution that u are comfortable with).
                
                                Each CSV record is an employee record, with below columns:
                                EmployeeID | EmployeeName | Place | Salary
 
2.	Create a /employee/place/{place}/salary/{percentage} PUT endpoint that will update the in-memory cache by increasing their salary by percentage(percentage path param) for employees that have place matching with place path param.(Use java 8 for transformation)
 
3.	The end point will return the refreshed list of employee record.
 
 
4.	The code must be modular/follow design principles(solid)
 
5.	It must have unit tests.

# Swagger URL : 
    http://<host>:8080/swagger-ui.html
    
# Application endpoints
# PUT :  http://<host>:8080/employee/place/{place}/salary/{percentage}
# GET : http://<host>:8080/employee/{id}
# GET : http://<host>:8080/employee

