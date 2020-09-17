package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bitmap {

    public static BufferedImage bitmapImport (){

        try {
            BufferedImage image = ImageIO.read(new File("src/main/resources/mario.bmp"));
            return image;
        } catch (IOException e) {
            System.out.println("DID NOT WORK:");
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage rotate (BufferedImage image){

//        Rotate Image:
//        https://blog.idrsolutions.com/2019/05/image-rotation-in-java/

        final double rads = Math.toRadians(90);
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);

        final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());

        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads,0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);

        return rotateOp.filter(image,rotatedImage);

    }



}
