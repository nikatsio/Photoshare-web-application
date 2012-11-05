
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.servletsuite.com/servlets/albumtag" prefix="im" %> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Photo creator</title>
        <link href="style3.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        
        <center>
            <h3>These are the uploaded pictures </h3>
            <im:imagesGrid dir="/usr/share/apache-tomcat-7.0.32/webapps/web/"/>  
        </center>
    
        <div class="select">
        <form action="form.jsp" method="post">

            <table >
                <tr>
                <th>Select a image to process: </th>
                </tr>
                <tr>
                <td><input type="text" name="image" id="image" size="25" /></td>    
                <td><input type="submit" value="select" /></td>
                </tr>
            </table>
         </form>
        <br />
        <form action="index.html" method="post">

            <input type="submit" value="Back" />
        </form>	
        </div>
        
    </body>
</html>

