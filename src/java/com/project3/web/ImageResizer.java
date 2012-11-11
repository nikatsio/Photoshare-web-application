
package com.project3.web;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageResizer extends HttpServlet {
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String imageFile = getServletContext().getRealPath("") + System.getProperty("file.separator")  + request.getParameter("i");

        int width = Integer.valueOf(request.getParameter("width")).intValue();
        int height = Integer.valueOf(request.getParameter("height")).intValue();

        try {

            BufferedImage bufferedImage = ImageIO.read(new File(imageFile));

            int calcHeight = height > 0 ? height : (width * bufferedImage.getHeight() / bufferedImage.getWidth());
 
            ImageIO.write(createResizedCopy(bufferedImage, width, calcHeight), request.getParameter("f"), response.getOutputStream());
        } catch (Exception e) {
            log("Problem with image: " + e);
        }
        
        
    }
    BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight) {
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaledBI.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }

}
