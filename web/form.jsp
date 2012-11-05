
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.servletsuite.com/servlets/albumtag" prefix="im" %> 
<%
String image=request.getParameter("image");
%>
 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Photo creator</title>
        <link href="style2.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        
        
      <div class="all">      
        <div class="picture">
        <center>
        <h2>This is the picture you choose</h2>        
        <img src="<%= image %>.jpg" width="800" height="800"/>
        </center>
        </div>
    
        
        <div class="tools">
        <h3>rotate picture:</h3>
        <form action="RotatedImage" method="get">
            <input type="hidden" size="4" id="i" name="i" value="<%= image %>.jpg"/>
            <table >
            <tr>
            <th>Choose in degrees the rotation:</th>
            </tr>
            <tr>
            <td><b>Degrees</b></td>    
            <td><input type="text" size="4" id="rot" name="rot" /></td>
            </tr>
            </table>
             
            <br />
            
            <input type="submit" value="rotate" />
            
        </form>
        
            
        
        <h3>Crop image:</h3>
        <form action="ImageCropperServlet" method="get">
        <input type="hidden" size="10"id="f" name="f" value="jpg" />
        <input type="hidden" size="4" id="i" name="i" value="<%= image %>.jpg"/>
        <table >
            <tr>
            <th>Choose the start points:</th>
            </tr>
            <tr>
            <td><b>X</b></td>    
            <td><input type="text" size="4" id=”t” name="t" /></td>
            </tr>
            <tr>
            <td><b>Y</b></td> 
            <td><input type="text" size="4" id=”l” name="l" /></td>
            </tr>
        </table>
        <table >
            <tr>
            <th>Choose the dimensions:</th>
            </tr>
            <tr>
            <td><b>Weight</b></td>    
            <td><input type="text" size="4" id=”w” name="w" /></td>
            </tr>
            <tr>
            <td><b>Height</b></td> 
            <td><input type="text" size="4" id=”h” name="h" /></td>
            </tr>
        </table> 
        <br />
        
        <input type="submit" value="crop" />
        
        </form>
        
        
        
        <h3>resize picture:</h3>
        <form action="ImageResizer" method="get">
            <input type="hidden" size="4" id="i" name="i" value="<%= image %>.jpg"/>
            <input type="hidden" size="10"id="f" name="f" value="jpg" />
            <table >
            <tr>
            <th>Choose the dimensions:</th>
            </tr>
            <tr>
            <td><b>Weight</b></td>    
            <td><input type="text" size="4" id="width" name="width" /></td>
            </tr>
            <tr>
            <td><b>Height</b></td> 
            <td><input type="text" size="4" id="height" name="height" /></td>
            </tr>
            </table> 
            <br />
            <input type="submit" value="resize" />
            
        </form>
        <br />
        <form action="index.jsp" method="post">
            <input type="submit" value="Back" />
        </form>
      
        </div>
      </div>
    </body>
</html>
