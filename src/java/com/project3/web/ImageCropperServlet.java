/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project3.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageCropperServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int x = Integer.valueOf(request.getParameter("t")).intValue();
        int y = Integer.valueOf(request.getParameter("l")).intValue();
        int w = Integer.valueOf(request.getParameter("w")).intValue();
        int h = Integer.valueOf(request.getParameter("h")).intValue();
        
        String imagePath = getServletContext().getRealPath("") + System.getProperty("file.separator")  + request.getParameter("i");
        
        BufferedImage outImage = ImageIO.read(new File(imagePath));
        BufferedImage cropped = outImage.getSubimage(x, y, w, h);
        
        ByteArrayOutputStream croppedOut = new ByteArrayOutputStream();
        ImageIO.write(cropped, request.getParameter("f"), croppedOut);
        
        response.setContentType("image/" + (request.getParameter("f").equals("jpg") ? "jpeg": request.getParameter("f")));
        
        ServletOutputStream out = response.getOutputStream();
        out.write(croppedOut.toByteArray());
        
        out.flush();
        out.close();
    }
}
