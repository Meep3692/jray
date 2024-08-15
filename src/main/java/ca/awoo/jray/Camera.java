package ca.awoo.jray;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Camera {
    private Vector position;
    private double fov;

    public Camera(Vector position, double fov) {
        this.position = position;
        this.fov = fov;
    }

    public void render(Scene scene, BufferedImage image){
        //BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        int width = image.getWidth();
        int height = image.getHeight();
        byte[] bytes = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
        double screendist = 0.1;
        double xf = Math.tan(fov/2) * screendist;
        double yf = -(Math.tan(fov/2) * screendist);
        ExecutorService threadPool = Executors.newFixedThreadPool(height);
        for(int y = 0; y < height; y++){
            final int row = y;
            threadPool.submit(() -> {
                for(int x = 0; x < width; x++){
                    int index = row*width+x;
                    int byteIndex = index*3;
                    double xn = (double)(x - width/2) / (double)(width/2);
                    double yn = (double)(row - height/2) / (double)(width/2);
                    xn *= xf;
                    yn *= yf;
                    Vector screenpos = new Vector(xn, yn, screendist).normalize();
                    Ray ray = new Ray(position, screenpos, 0);
                    Colour colour = scene.castColour(ray);
                    byte r = (byte) (Math.min(1, colour.r()) * 255);
                    byte g = (byte) (Math.min(1, colour.g()) * 255);
                    byte b = (byte) (Math.min(1, colour.b()) * 255);
                    //System.out.println("" + x + ", " + y + "->" + byteIndex);
                    bytes[byteIndex + 0] = b;
                    bytes[byteIndex + 1] = g;
                    bytes[byteIndex + 2] = r;
                    if(rowCallback != null){
                        rowCallback.run();
                    }
                }
                System.out.println("Done row " + row + "/" + height);
            });
        }
    }

    private Runnable rowCallback = null;
    public void rowCallback(Runnable callback){
        this.rowCallback = callback;
    }
}
