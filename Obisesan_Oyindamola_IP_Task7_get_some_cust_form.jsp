
<!DOCTYPE html> 
<html> 
    <head> 
        <meta charset="UTF-8"> 
        <title>Get Customer</title> 
    </head> 
    <body> 
        <h2>Get  Customer</h2> 
        <!-- 
            Form for collecting user input for the new customer record. 
            Upon form submission, cust_some_.jsp file will be invoked. 
        --> 
        <form action="cust_some.jsp"> 
            <!-- The form organized in an HTML table for better clarity. --> 
            <table border=1> 
                <tr> 
                    <th colspan="2">Enter the customer Data:</th> 
                </tr> 
                <tr> 
                    <td>Category_1:</td> 
                    <td><div style="text-align: center;"> 
                    <input type=text name=category_1> 
                    </div></td> 
                </tr>
                <tr> 
                    <td>Category_2:</td> 
                    <td><div style="text-align: center;"> 
                    <input type=text name=category_2> 
                    </div></td> 
                </tr>
                <tr> 
                    <td><div style="text-align: center;"> 
                                 <input type=reset value=Clear> 
                    </div></td> 
                    <td><div style="text-align: center;"> 
                    <input type=submit value=Insert> 
                    </div></td> 
                </tr> 
            </table> 
        </form> 
    </body> 
</html> 