<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
        <title>Customer</title>
    </head>
    <body>
        <%@page import="project_azure.DataHandler"%>
        <%@page import="java.sql.ResultSet"%>
        <%
            // We instantiate the data handler here, and get all the customer from the database
            final DataHandler handler = new DataHandler();
            final ResultSet customer = handler.getAllcust();
        %>
        <!-- The table for displaying all the customer records -->
        <table cellspacing="2" cellpadding="2" border="1">
            <tr> <!-- The table headers row -->
              <td align="center">
                <h4>cust_name</h4>
              </td>
              <td align="center">
                <h4>addr</h4>
              </td>
              <td align="center">
                <h4>category</h4>
              </td>
            </tr>
            <%
               while(customer.next()) { // For each customer record returned...
                   // Extract the attribute values for every row returned
                   final String cust_name = customer.getString("cust_name");
                   final String addr = customer.getString("addr");
                   final String category = customer.getString("category");

                   
                   out.println("<tr>"); // Start printing out the new table row
                   out.println( // Print each attribute value
                        "<td align=\"center\">" + cust_name +
                        "</td><td align=\"center\"> " + addr +
                        "</td><td align=\"center\"> " + category + "</td>");
                   out.println("</tr>");
               }
               %>
          </table>
    </body>
</html>