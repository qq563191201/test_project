package image.press;

import java.awt.Image;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
  
import javax.imageio.ImageIO;  
  
import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGEncodeParam;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;  
  
public class ImageZipUtil {  
  
	/**
     * 创建图片缩略图(等比缩放)
     * 
     * @param src
     *            源图片文件完整路径
     * @param dist
     *            目标图片文件完整路径
     * @param width
     *            缩放的宽度
     * @param height
     *            缩放的高度
     */
    public static void createThumbnail(String src, String dist, float width,
            float height) {
        try {
            File srcfile = new File(src);
            if (!srcfile.exists()) {
                System.out.println("文件不存在");
                return;
            }
            Image image = ImageIO.read(srcfile);

            // 获得缩放的比例
            double ratio = 1.0;
            // 判断如果高、宽都不大于设定值，则不处理
            if (image.getHeight(null) > height || image.getWidth(null) > width) {
                if (image.getHeight(null) > image.getWidth(null)) {
                    ratio = height / image.getHeight(null);
                } else {
                    ratio = width / image.getWidth(null);
                }
            }
            // 计算新的图面宽度和高度
            int newWidth = (int) (image.getWidth(null) * ratio);
            int newHeight = (int) (image.getHeight(null) * ratio);

            BufferedImage bfImage = new BufferedImage(newWidth, newHeight,
                    BufferedImage.TYPE_INT_RGB);
            bfImage.getGraphics().drawImage(
                    image.getScaledInstance(newWidth, newHeight,
                            Image.SCALE_SMOOTH), 0, 0, null);

            FileOutputStream os = new FileOutputStream(dist);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
            encoder.encode(bfImage);
            os.close();
        } catch (Exception e) {
            System.out.println("创建缩略图发生异常" + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
    	Long l1 = System.currentTimeMillis();
        createThumbnail("D:\\Users\\zhangliju\\Desktop\\123\\DAimG_2014101440691908V21T.jpg", 
        		"D:Tomcat6.0\\webapps\\scgatqtDocument\\cropimage\\thum\\apic5489111.jpg", 439, 439);
        Long l2 = System.currentTimeMillis();
        System.out.println((l2-l1)+"ms");
//        createThumbnail("E:\\test_1920x1200.jpg", "D:\\b.png", 2000, 2000);
//        createThumbnail("E:\\test_1920x1200.jpg", "D:\\c.jpg", 800, 600);
    }
    
//    public static void main(String args[]){
//    	zipWidthHeightImageFile(new File("D:\\Tomcat6.0\\webapps\\sdrzqxDocument\\cropimage\\original\\1408615202678.png"), 
//    			new File("D:\\Tomcat6.0\\webapps\\sdrzqxDocument\\cropimage\\thum\\1408615202678.png"), 200, 200, 0.3f);
//    }
}