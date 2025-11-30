package gartic.renderer;

import gartic.Canvas;
import gartic.util.Grayscaler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageRenderer implements Renderer {
    private BufferedImage bufferedImageData;
    private int imageWidth;
    private int imageHeight;
    private String imagePath;
    private boolean downscale = false;

    public ImageRenderer(String imageLocation) {
        this.imagePath = imageLocation;

        File f = new File(this.imagePath);

        try {
            this.bufferedImageData = ImageIO.read(f);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ImageRenderer(String imageLocation, int imageWidth, int imageHeight) {
        this.imagePath = imageLocation;

        File f = new File(this.imagePath);

        try {
            this.bufferedImageData = ImageIO.read(f);
        } catch(IOException e) {
            e.printStackTrace();
        }

        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.downscale = true;
    }

    public BufferedImage getBufferedImageData() {
        return bufferedImageData;
    }

    public void setBufferedImageData(BufferedImage bufferedImageData) {
        this.bufferedImageData = bufferedImageData;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public void render(Canvas canvas) {
        if (this.downscale) {
            Image resizedImage = this.bufferedImageData.getScaledInstance(this.imageWidth, this.imageHeight, Image.SCALE_DEFAULT);
            BufferedImage newImage = new BufferedImage(resizedImage.getWidth(null), resizedImage.getHeight(null), BufferedImage.TYPE_INT_RGB);

            Graphics2D graphics2D = newImage.createGraphics();
            graphics2D.drawImage(resizedImage,0,0,null);
            graphics2D.dispose();

            this.bufferedImageData = newImage;
        }

        int imageHeight = this.bufferedImageData.getHeight();
        int imageWidth = this.bufferedImageData.getWidth();

        System.out.println("Image data: h="+imageHeight+", w="+imageWidth);

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {

                int rgb = this.bufferedImageData.getRGB(x,y);
                Color color = new Color(rgb);


                int grayscaled = Grayscaler.toGrayscale(color.getRed(), color.getGreen(), color.getBlue());
                if (grayscaled >= 30) canvas.drawDot(x, y);
            }
        }
    }
}