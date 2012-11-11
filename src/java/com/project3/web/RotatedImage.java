/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project3.web;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.util.Iterator;

import javax.imageio.ImageIO;
//import javax.imageio.ImageWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RotatedImage extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = getServletContext().getRealPath("") + System.getProperty("file.separator")  + request.getParameter("i");

        String r;
        r=request.getParameter("rot");
        if(r==null){
            r="0";
        }
        double rot = Double.parseDouble( r);

        BufferedImage image = ImageIO.read(new File(path));
        AffineTransform tx = new AffineTransform();

        tx.rotate(Math.toRadians(rot), image.getWidth()/2, image.getHeight()/2);

        AffineTransformOp op = new AffineTransformOp(tx, null);
        image = op.filter(image, null);

        ServletContext sc = getServletContext();
        String filename = getServletContext().getRealPath(path);

        String mimeType = sc.getMimeType(filename);
        if (mimeType == null) {
                sc.log("Could not get MIME type of "+filename);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
        }

        response.setContentType(mimeType);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        

    }

}
