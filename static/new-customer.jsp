<%-- 
    Document   : new-customer
    Created on : 18 Aug 2023, 11:35:08 am
    Author     : haydenaish
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <main>
            <%@include file="WEB-INF/jspf/navigation.jspf"%>
            <h1>Register Customer</h1>

            <fieldset>

                <legend>Customer Details</legend>
                <%                    String validation = (String) session.getAttribute("validation");
                    validation = validation != null ? validation : "";
                %>

                <p><%= validation%></p>

                <form action="new-customer" method="POST">

                    <!--<label>ID:</label><input type="number" name="id" required/>-->
                    <label>Username</label><input type="text" name="username" required/>
                    <label>First Name</label><input type="text" name="firstName"/>
                    <label>Surname</label><input type="text" name="surname"/>
                    <label>Shipping Address</label><textarea name="address"></textarea>
                    <label>Password</label><input type="text" name="password"/>
                    <label>Email Address</label><input type="text" name="emailAddress"/>
                    <br>
                    <button type="submit">Create Account</button>

                </form>

            </fieldset>

            <div class="center-button">
                <a class="nav" href="index.jsp"><button>Back to Menu</button></a>
            </div>

        </main>
    </body>
</html>