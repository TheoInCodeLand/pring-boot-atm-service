## 200 OK: -> (HttpStatus.OK) -> Everything worked normally
### Used when: Fetching an account, depositing, updating, deleting.
Request completed successfully. Use for successful GET, PUT, PATCH, or DELETE operations when returning a response body. 

## 201 Created: -> (HttpStatus.CREATED) -> A brand-new item was created
### Used when: Only when creating a new resource (createAccount).
A new resource was successfully created. Use for successful POST operations that create a new resource. Include a 
Location header to provide the new resource's URL. 

## 404 Not Found: -> (HttpStatus.NOT_FOUND) -> Could not find what you asked for
### Used when: When findAccountByNumber or getAccountStatement comes back null.
Requested resource could not be found. Use for successful GET operations that return a resource not found. 

## 400 Bad Request: -> (HttpStatus.BAD_REQUEST) -> User typed a bad amount / rule failed
### Used when: Withdrawing more money than you have, or a negative amount.   
Request contains invalid data or syntax. Use for successful POST operations that create a new resource but require 
validation. 
 
# ________________________________________________
**These status codes are essential for API testing, debugging, and error handling, providing clear communication between 
the client and the server.

**401 Unauthorized: **
Authentication is required or invalid. Use for successful POST operations that require authentication.

**403 Forbidden: **
Server understood the request but refuses access. Use for successful POST operations that require authorization but are
not authorized.
