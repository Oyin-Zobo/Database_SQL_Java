<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Query Result</title>
</head>
    <body>
    <%@page import="project_azure.DataHandler"%>
    <%@page import="java.sql.ResultSet"%>
    <%@page import="java.sql.Array"%>
    <%
    // The handler is the one in charge of establishing the connection.
    DataHandler handler = new DataHandler();

    // Get the attribute values passed from the input form.
    String cust_name = request.getParameter("cust_name");
    String address = request.getParameter("addr");
    String categoryString = request.getParameter("category");


    /*
     * If the user hasn't filled out all the time, movie name and duration. This is very simple checking.
     */
    if (cust_name.equals("") || address.equals("") || categoryString.equals("")) {
        response.sendRedirect("add_cust_form.jsp");
    } else {
        int category = Integer.parseInt(categoryString);
        
        // Now perform the query with the data from the form.
        boolean success = handler.addcustomer(cust_name, address, category);
        if (!success) { // Something went wrong
            %>
                <h2>There was a problem inserting the course</h2>
            <%
        } else { // Confirm success to the user
            %>
            <h2>The Customer:</h2>

            <ul>
                <li>cust_name: <%=cust_name%></li>
                <li>address: <%=address%></li>
                <li>category: <%=categoryString%></li>

            </ul>

            <h2>Was successfully inserted.</h2>
            
            <a href="get_all_cust.jsp">See all customers.</a>
            <%
        }
    }
    %>
    </body>
</html>
