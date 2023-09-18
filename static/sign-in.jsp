<%-- 
Document   : sign-in
Created on : 18 Aug 2023, 11:23:56 am
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
            <h1>Customer sign in</h1>

            <fieldset>

                <legend>Customer Details</legend>
                <%                     String validation = (String) session.getAttribute("logIn");
                    validation = validation != null ? validation : "";
                %>

                <p><%= validation%></p>

                <form action="sign-in" method="POST">

                    <label>Username:</label><input type="text" name="username"/>
                    <label>Password</label><input type="text" name="password"/>


                    <button type="submit">sign in</button>

                </form>

            </fieldset>

            <div class="center-button">
                <a class="nav" href="index.jsp"><button>Back to Menu</button></a>
            </div>

        </main>
    </body>
</html>