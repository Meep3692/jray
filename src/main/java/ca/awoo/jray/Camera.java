package ca.awoo.jray;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Camera {
    private Vector position;
    private double fov;

    public Camera(Vector position, double fov) {
        this.position = position;
        this.fov = fov;
    }

    public BufferedImage render(Scene scene, int width, int height){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        byte[] bytes = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
        double screendist = 0.1;
        double xf = Math.tan(fov/2) * screendist;
        double yf = Math.tan(fov/2) * screendist;
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int index = y*width+x;
                int byteIndex = index*3;
                double xn = (double)(x - width/2) / (double)(width/2);
                double yn = (double)(y - height/2) / (double)(width/2);
                xn *= xf;
                yn *= yf;
                Vector screenpos = new Vector(xn, yn, screendist).normalize();
                Ray ray = new Ray(position, screenpos);
                Colour colour = scene.castColour(ray);
                byte r = (byte) (Math.min(1, colour.r()) * 255);
                byte g = (byte) (Math.min(1, colour.g()) * 255);
                byte b = (byte) (Math.min(1, colour.b()) * 255);
                //System.out.println("" + x + ", " + y + "->" + byteIndex);
                bytes[byteIndex + 0] = b;
                bytes[byteIndex + 1] = g;
                bytes[byteIndex + 2] = r;
            }
        }
        return image;
    }
}
