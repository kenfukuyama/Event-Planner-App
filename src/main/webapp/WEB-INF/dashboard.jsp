<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="java.util.Date, java.util.List, com.kb.chitchat.models.User" %>

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
        <div class="row">
            <div class="col">
                <h4>Welcome! ${user.firstName} ${user.lastName}</h4>
                


                <hr>
                <h3>Here are some of the events in your area</h3>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Date</th>
                            <th>Location</th>
                            <th>Host</th>
                            <th>Action/Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="event" items="${events}">
                            <tr>
                                <td><a href="/events/${event.id}/show">${event.name}</a></td>
                                <td>${event.eventDate}</td>
                                <td>${event.location}</td>
                                <td>${event.user.firstName}</td>
                                <td class="d-flex justify-content-around align-items-center">
                                    <c:choose>
                                        <c:when test="${user.id == event.user.id}">
                                            <div class="edit-button">
                                                <a class="btn btn-primary" href="/events/${event.id}/edit">Edit</a>
                                            </div>
                                            <div class="delete-button">
                                                <form action="/events/${event.id}" method="post">
                                                    <input type="hidden" name="_method" value="delete">
                                                    <input class="btn btn-danger" type="submit" value="Delete">
                                                </form>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${!event.attendingUsers.contains(user)}">
                                                    <form action="/events/addConnect" method="post">
                                                        <input type="hidden" name="userId" value="${user.id}">
                                                        <input type="hidden" name="eventId" value="${event.id}">
                                                        <input class="btn btn-primary" type="submit" value="Join">
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="joining">Joining</div>
                                                    <div class="">
                                                        <form action="/events/removeConnect" method="post">
                                                            <input type="hidden" name="userId" value="${user.id}">
                                                            <input type="hidden" name="eventId" value="${event.id}">
                                                            <input class="btn btn-primary" type="submit" value="Cancel">
                                                        </form>
                                                    </div>
                                
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                    <!-- <div class="">${event.attendingUsers}</div>
                                        <div class="">${user}</div> -->
                                
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <h3>Create an Event <%= new Date() %></h3>
        <hr>
        <div class="card">
            <h4 class="card-header text-primary p-4">Add</h4>
            <div class="card-body">
                <form:form action="/events/add" method="post" modelAttribute="event">
                    <form:input type="hidden" path="user" value="${user.id}"></form:input>
                    <div class="mb-3">
                        <form:label path="name" class="form-label">name </form:label>
                        <form:input path="name" type="text" class="form-control" placeholder="name" />
                        <form:errors class="text-danger" path="name" />
                    </div>
                    <div class="mb-3">
                        <form:label path="eventDate" class="form-label">Event 
                            Date:  </form:label>
                        <form:input path="eventDate" type="date"  value="2022-08-14"/>
                        <form:errors class="text-danger" path="eventDate"/>
                    </div>
                    <div class="mb-3">
                        <form:label path="location" class="form-label">location </form:label>
                        <form:input path="location" type="text" class="form-control" />
                        <form:errors class="text-danger" path="location" />
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