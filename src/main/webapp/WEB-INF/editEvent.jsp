<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="java.util.Date" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- for forms -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!--  mgiht show error-->
<%@ page isErrorPage="true" %>  



<!DOCTYPE html>
<html>

<head>
    <!-- bootstrap -->
    <!-- <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" /> -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"  crossorigin="anonymous">
    <!-- regularcss -->
    <link rel="stylesheet" type="text/css" href="/css/style.css">

    <!-- bootstrap js -->
    <!-- <script src="/webjars/jquery/jquery.min.js"></script> -->
    <!-- <script src="/webjars/bootstrap/js/bootstrap.min.js"></script> -->

    <!-- regular js -->
    <script type="text/javascript" src="/javascript/script.js"></script>

    <meta charset="UTF-8">
    <title>Home</title>
</head>

<body>

    <div class="d-flex align-items-center justify-content-between gap-3 p-3">
        <div class="home-button"><a class="btn btn-primary" href="/home">Home</a></div>
        <div class="d-flex justify-content-end"><a class="btn btn-danger" href="/logout">Logout</a></div>
    </div>
    
    <div class="container">
        <h3>${event.name}</h3>
        <div class="card">
            <h4 class="card-header text-primary p-4">Edit</h4>
            <div class="card-body">
                <form:form action="/events/${event.id}" method="post" modelAttribute="event">
                    <input type="hidden" name="_method" value="put">
                    <form:input type="hidden" path="user" value="${user.id}"></form:input>
                    <div class="mb-3">
                        <form:label path="name" class="form-label">name </form:label>
                        <form:input path="name" type="text" class="form-control" placeholder="name" />
                        <form:errors class="text-danger" path="name" />
                    </div>
                    <div class="mb-3">
                        <form:label path="eventDate" class="form-label">Event 
                            Date:  </form:label>
                        <form:input path="eventDate" type="date"  value="2022-08-22"/>
                        <form:errors class="text-danger" path="eventDate"/>
                    </div>
                    <div class="mb-3">
                        <form:label path="location" class="form-label">location </form:label>
                        <form:input path="location" type="text" class="form-control" />
                        <form:errors class="text-danger" path="location" />
                    </div>
                    <div class="d-flex justify-content-end">
                        <input type="submit" value="Update" class="btn btn-primary">
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</body>

</html>