<%-- 
    Document   : view-products
+    Created on : 18 Aug 2023, 12:19:45 pm
    Author     : haydenaish
--%>

<%@page import="dao.JdbiDaoFactory"%>
<%@page import="dao.ProductDAO"%>
<%@page import="dao.ProductCollectionsDAO"%>
<%@page import="java.util.Collection"%>
<%@page import="domain.Product"%>
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
            <h1>Products</h1>
            <%                String welcome = (String) session.getAttribute("welcome");
                welcome = welcome != null ? welcome : "";
            %>

            <p><%= welcome%></p>


            <div class="button-container">
                <a href="view-products.jsp?category=All"><button>All</button></a>
                <%
                    ProductDAO dao = JdbiDaoFactory.getProductDAO();
                    Collection<String> categories = dao.getCategories();

                    for (String category : categories) {
                %>
                <a href="view-products.jsp?category=<%= category%>"><button><%= category%></button></a>
                        <%
                            }
                        %>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Category</th>
                        <th>List Price</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>

                    <%
                        //				ProductDAO dao = new ProductCollectionsDAO();
                        String selectedCategory = request.getParameter("category");

                        Collection<Product> products = dao.getProducts();

                        if (selectedCategory == null || selectedCategory.equals("All")) {
                            products = dao.getProducts();
                        } else {
                            // otherwise, get the students for the requested major
                            products = dao.filterByCategory(selectedCategory);
                        }

                        for (Product product : products) {
                    %>
                    <tr>
                        <td><%= product.getProductId()%></td>
                        <td><%= product.getName()%></td>
                        <td><%= product.getDescription()%></td>
                        <td><%= product.getCategory()%></td>
                        <td><%= product.getListPrice()%></td>
                        <td><%= product.getQuantityInStock()%></td>
                    </tr>
                    <%
                        }
                    %>


                </tbody>
            </table>

            <div class="center-button">
                <a class="nav" href="index.jsp"><button>Back to Menu</button></a>
            </div>
        </main>
    </body>
</html>