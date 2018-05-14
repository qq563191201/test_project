package image.drawimage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class drawimage {
	 public static void main(String[] args) throws IOException {
	        int width = 800;
	        int height = 800;
	        BufferedImage image = new BufferedImage(
	                width, height, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2d = image.createGraphics();
	        g2d.setColor(Color.white);
	        g2d.fillRect(0, 0, width, height);
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
	                RenderingHints.VALUE_ANTIALIAS_ON);
	        Random random = new Random();
	        int wo = 0;
	        int ho = 0;
	        for (int i = 0; i < 10; i++) {
	         int x = random.nextInt(width);
	         int y = random.nextInt(height);
	            g2d.setColor(new Color(random.nextInt(256), 
	                    random.nextInt(256), random.nextInt(256)));
	            g2d.setStroke(new BasicStroke(1));

	            g2d.drawString("node"+i, x, y);
	            g2d.drawArc(x, y, 20, 20, 0, 360);
	            if(i!=0){
	             g2d.drawLine(wo, ho, x, y);
	            }
	            wo = x;
	            ho = y;
	        }
	        g2d.dispose();
	       
	        ImageWriter writer = null;
	        ImageTypeSpecifier type =
	            ImageTypeSpecifier.createFromRenderedImage(image);
	        Iterator iter = ImageIO.getImageWriters(type, "jpg");
	        if (iter.hasNext()) {
	            writer = (ImageWriter)iter.next();
	        }
	        if (writer == null) {
	            return;
	        }
	        IIOImage iioImage = new IIOImage(image, null, null);
	        ImageWriteParam param = writer.getDefaultWriteParam();
	        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	        param.setCompressionQuality((float) ((10) / 10.0));
	        ImageOutputStream outputStream = 
	          ImageIO.createImageOutputStream(new File("E:/Game/test.jpg"));
	        writer.setOutput(outputStream);
	        writer.write(null, iioImage, param);
	    }
}
