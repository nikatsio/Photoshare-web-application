package com.project3.web;

import java.io.*;
import java.util.*;
//import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.io.output.*;

public class UploadServlet extends HttpServlet {
   
   private boolean isMultipart;
   private String filePath;
   private int maxFileSize = 1000 * 1024;
   private int maxMemSize = 50 * 1024;
   private File file ;

    @Override
   public void init(){

      filePath = 
             getServletContext().getInitParameter("file-upload"); 
   }
    @Override
   public void doPost(HttpServletRequest request, 
               HttpServletResponse response)
              throws ServletException, java.io.IOException {

      isMultipart = ServletFileUpload.isMultipartContent(request);
      
      if( !isMultipart ){

         PrintWriter out = response.getWriter( );
         out.println("No file uploaded"); 
         
      }
      DiskFileItemFactory factory = new DiskFileItemFactory();
      factory.setSizeThreshold(maxMemSize);
      factory.setRepository(new File("C:/Users/Nikatsio/Documents/NetBeansProjects/WebProject3/web"));

      ServletFileUpload upload = new ServletFileUpload(factory);
      upload.setSizeMax( maxFileSize );

      try{ 

      List fileItems = upload.parseRequest(request);
	
      Iterator i = fileItems.iterator();

      while ( i.hasNext () ) 
      {
         FileItem fi = (FileItem)i.next();
         if ( !fi.isFormField () )	
         {
            String fileName = fi.getName();

            if( fileName.lastIndexOf("\\") >= 0 ){
               file = new File( filePath + 
               fileName.substring( fileName.lastIndexOf("\\"))) ;
            }else{
               file = new File( filePath + 
               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
            }
            fi.write( file ) ;

         }
      }

   }catch(Exception ex) {
       System.out.println(ex);
   }
      
   getServletContext().getRequestDispatcher("/index.jsp").forward
           (request, response);
   }

}
