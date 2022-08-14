<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- bootstrap css -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    <!-- css -->
    <link rel="stylesheet" href="/css/style.css">
    <!-- bootstrap javascript -->
    <script defer src="/webjars/jquery/jquery.min.js"></script>
    <script defer src="/webjars/bootstrap/js/bootstrap.min.js"></script>

    <!-- javascript -->
    <script defer type="text/javascript" src="/js/script.js"></script>
    
    
    <title>Show Product</title>
</head>
<body>
    <div class="d-flex align-items-center justify-content-between gap-3 p-3">
        <div class="home-button"><a class="btn btn-primary" href="/home">Home</a></div>
        <div class="d-flex justify-content-end"><a class="btn btn-danger" href="/logout">Logout</a></div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        ${product.name}
                    </div>
                    <div class="card-body">
                        Descirption: ${product.description} <br/>
                        Price: ${product.price}
                        <hr>
                        Categories:
                        <ul>
                            <c:forEach var="category" items="${product.categorys}">
                                <li>${category.name}</li>
                            </c:forEach>
                        </ul>
                        <hr>
                        <div class="card-title">Add Category</div>
                        <form action="/products/addConnect" method="post">
                            <input type="hidden" name="productId" value=${product.id}>
                            <select class="form-select mb-2" aria-label="Default select example" name="categoryId">
                                <c:forEach var="category" items="${categorys}">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                            <input type="submit" value="Add" class="btn btn-primary" />

                        </form>
                    </div>
                </div>


            
            </div>
        </div>
    </div>
</body>
</html>