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
                <h1>NZ Golf</h1>
                
                <%
                    String welcome = (String) session.getAttribute("welcome");
                    welcome = welcome != null ? welcome : "";
                %>

                <p><%= welcome%></p>

               </main>
	</body>
</html>
