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
    <div class="container">
        <div class="row mt-2">
            <h2>Hello, Welcome!</h2>
        </div>
        <div class="row mt-2">
            <!-- ! register -->
            <div class="col">
                <div class="card">
                    <h4 class="card-header text-primary p-4">REGISTER</h4>
                    <div class="card-body">
                                                
                        <form:form action="/register" method="post" modelAttribute="newUser">
                            <div class="mb-3">
                                <form:label path="firstName" class="form-label">First Name </form:label>
                                <form:input path="firstName" type="text" class="form-control" placeholder="firstName" />
                                <form:errors  class="text-danger" path="firstName" />
                            </div>
                            <div class="mb-3">
                                <form:label path="lastName" class="form-label">Last Name </form:label>
                                <form:input path="lastName" type="text" class="form-control" placeholder="lastName" />
                                <form:errors  class="text-danger" path="lastName" />
                            </div>
                            <div class="mb-3">
                                <form:label path="email" class="form-label">Email </form:label>
                                <form:input path="email" type="text" class="form-control" placeholder="email" />
                                <form:errors  class="text-danger" path="email" />
                            </div>

                            <div class="mb-3">
                                <form:label path="password" class="form-label">Password </form:label>
                                <form:input path="password" type="password" class="form-control" placeholder="password" />
                                <form:errors  class="text-danger" path="password" />
                            </div>

                            <div class="mb-3">
                                <form:label path="confirm" class="form-label">Confirm password </form:label>
                                <form:input path="confirm" type="password" class="form-control" placeholder="confirm" />
                                <form:errors  class="text-danger" path="confirm" />
                            </div>
                            
                            <div class="d-flex justify-content-end">
                                <input type="submit" value="register" class="btn btn-primary">
                            </div>
                        </form:form>

                        
                    </div>
                </div>
            </div>
            <!-- ! login -->
            <div class="col">
                <div class="card">
                    <h4 class="card-header text-primary p-4">
                        LOGIN
                    </h4>
                    <div class="card-body">
                        <form:form action="/login" method="post" modelAttribute="newLogin">

                            <div class="mb-3">
                                <form:label path="email" class="form-label">Email </form:label>
                                <form:input path="email" type="text" class="form-control" placeholder="email" />
                                <form:errors  class="text-danger" path="email" />
                            </div>

                            <div class="mb-3">
                                <form:label path="password" class="form-label">Password </form:label>
                                <form:input path="password" type="password" class="form-control" placeholder="password" />
                                <form:errors  class="text-danger" path="password" />
                            </div>
                            
                            <div class="d-flex justify-content-end">
                                <input type="submit" value="Login" class="btn btn-primary">
                            </div>
                        </form:form>
                    </div>
                </div>
                
            </div>
        </div>


        
    </div>

</body>

</html>