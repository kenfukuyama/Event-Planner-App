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
    
    
    <title>Show Event</title>
</head>
<body>
    <div class="d-flex align-items-center justify-content-between gap-3 p-3">
        <div class="home-button"><a class="btn btn-primary" href="/home">Home</a></div>
        <div class="d-flex justify-content-end"><a class="btn btn-danger" href="/logout">Logout</a></div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-6">
                <div class="card">
                    <div class="card-header">
                        ${event.name}
                    </div>
                    <div class="card-body">
                        Host: ${event.user.firstName} ${event.user.lastName} <br/>
                        Date: ${event.eventDate} <br/>
                        Location: ${event.location} <br/>
                        People who are attending this event: ${event.attendingUsers.size()} 
                        <hr>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Location</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${event.attendingUsers}">
                                    <tr>
                                        <td>${user.firstName} ${user.lastName} </td>
                                        <td>${user.email}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <h3>Message Wall</h3>
                <div class="messages">
                    <ul>
                        <c:forEach var="comment" items="${comments}">
                            <li>${comment.user.firstName} says: ${comment.message}</li>
                        </c:forEach>

                    </ul>
                </div>
                <form:form action="/comments/add" method="post" modelAttribute="comment">
                        <form:input type="hidden" path="user" value="${userId}"></form:input>
                        <form:input type="hidden" path="event" value="${event.id}"></form:input>
                        <div class="mb-3">
                            <form:label path="message" class="form-label">message </form:label>
                            <form:input path="message" type="text" class="form-control" placeholder="message" />
                            <form:errors class="text-danger" path="message" />
                        </div>
                        <div class="d-flex justify-content-end">
                            <input type="submit" value="Add" class="btn btn-primary">
                        </div>
                    </form:form>
                    


                
            </div>
        </div>
    </div>
</body>
</html>