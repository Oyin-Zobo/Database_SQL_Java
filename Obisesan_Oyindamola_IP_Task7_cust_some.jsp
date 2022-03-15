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
    final DataHandler handler = new DataHandler();

    // Get the attribute values passed from the input form.
    //String cust_name = request.getParameter("cust_name");
    //String address = request.getParameter("addr");
    String categoryString_1 = request.getParameter("category_1");
    String categoryString_2 = request.getParameter("category_2");
    


    /*
     * If the user hasn't filled out all the time, movie name and duration. This is very simple checking.
     */
    if (categoryString_1.equals("") || categoryString_2.equals("")) {
        response.sendRedirect("get_some_cust.jsp");
    } 
    else {
        int category_1 = Integer.parseInt(categoryString_1);
        int category_2 = Integer.parseInt(categoryString_2);
        
        // Now perform the query with the data from the form.
        //boolean success = handler.somecustomer(category_1, category_2);
        //if (!success) { // Something went wrong
       // } else { // Confirm success to the user
           %>
            <h2>The Customers:</h2>

            <ul>
                <li>category: <%=categoryString_1%></li>
                <li>category: <%=categoryString_2%></li>

            </ul>

            <h2>customers were retrieved.</h2>
            
            <a href="get_all_cust.jsp">See all customers.</a>
        <%
            //final DataHandler handler = new DataHandler();
            final ResultSet customer = handler.somecustomer(category_1, category_2);
           // final ResultSet customer = handler.somecustomer(category_1, category_2);
        %>
        <!-- The table for displaying all the movie records -->
        <table cellspacing="2" cellpadding="2" border="1">
            <tr> <!-- The table headers row -->
              <td align="center">
                <h4>cust_name</h4>
              </td>
            </tr>
            <%
               while(customer.next()) { // For each movie_night record returned...
                   // Extract the attribute values for every row returned
                   final String cust_name = customer.getString("cust_name");

                   
                   out.println("<tr>"); // Start printing out the new table row
                   out.println( // Print each attribute value
                        "<td align=\"center\">" + cust_name + "</td>");
                   out.println("</tr>");
               }
    }
    %>
    </body>
</html>
