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
    
    
    <title>New Product</title>
</head>
<body>
    <div class="d-flex align-items-center justify-content-between gap-3 p-3">
        <div class="home-button"><a class="btn btn-primary" href="/home">Home</a></div>
        <div class="d-flex justify-content-end"><a class="btn btn-danger" href="/logout">Logout</a></div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col">
                <hr/>
                <div class="card">
                    <h4 class="card-header text-primary p-4">Add</h4>
                    <div class="card-body">    
                        <form:form action="/products/add" method="post" modelAttribute="product">
                            <div class="mb-3">
                                <form:label path="name" class="form-label">name </form:label>
                                <form:input path="name" type="text" class="form-control" placeholder="name" />
                                <form:errors  class="text-danger" path="name" />
                            </div>
                            <div class="mb-3">
                                <form:label path="description" class="form-label">description </form:label>
                                <form:input path="description" type="text" class="form-control" placeholder="description" />
                                <form:errors  class="text-danger" path="description" />
                            </div>

                            <div class="mb-3">
                                <form:label path="price" class="form-label">price </form:label>
                                <form:input path="price" type="number" class="form-control" step="0.01"/>
                                <form:errors  class="text-danger" path="price" />
                            </div>
                            
                            <div class="d-flex justify-content-end">
                                <input type="submit" value="Add" class="btn btn-primary">
                            </div>
                        </form:form>

                        
                    </div>
                </div>
            
            </div>
        </div>
    </div>
</body>
</html>