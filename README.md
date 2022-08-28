
# Java Spring Practicum

> ## Technology Used
> - Java 8
> - Spring Boot 2.7.3
> - Maven

### Our entities
> #### We have a user!
>
> - Id
> - Name(50 char)
> - Surname(50 char)
> - Email(50 char)
> - Phone(15 char)

> #### We have a product!
>
> - Id
> - Name
> - Price
> - Expiration Date

> #### We have a product comment!
>
> - Id
> - Comment(500 char)
> - Comment Date(Date)
> - Product Id
> - User Id

> #### Our repositories contain the following states
>
> - Write a method that lists comments for a product.
> - Write a method that displays the comments made to a particular product in the given date ranges.
> - Write a method that lists comments made by a user.
> - Write a method that displays the comments made by a user within a certain date range.
> - Write a method that lists expired products.
> - Write a method that lists products that have not expired. (Those with empty expiration dates should also come.)

> ### Our endpoints
```
users-controller


PUT
/api/1.0/users/update

POST
/api/1.0/users/add

GET
/api/1.0/users/getall

GET
/api/1.0/users/findCommentByUserId/{userId}

GET
/api/1.0/users/findById/{id}

DELETE
/api/1.0/users/delete/{id}
```

```
products-controller


POST
/api/1.0/products/update

POST
/api/1.0/products/add

GET
/api/1.0/products/getall

GET
/api/1.0/products/findCommentByProductId/{productId}

GET
/api/1.0/products/findCommentByDate

GET
/api/1.0/products/findCommentByDateWithUser

GET
/api/1.0/products/findById/{id}

GET
/api/1.0/products/expiryDateNotExpired

GET
/api/1.0/products/expiryDateExpired

DELETE
/api/1.0/products/delete/{id}
```
```
comments-controller


POST
/api/1.0/comments/update

POST
/api/1.0/comments/add

GET
/api/1.0/comments/getall

GET
/api/1.0/comments/findbyId/{id}

DELETE
/api/1.0/comments/delete/{id}
```
## To clone the project;
```https://github.com/ErtanSidar/Java-Spring-Practicum.git```
