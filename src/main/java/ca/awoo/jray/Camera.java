package ca.awoo.jray;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Camera {
    private Vector position;
    private double fov;

    public Camera(Vector position, double fov) {
        this.position = position;
        this.fov = fov;
    }

    public void render(Scene scene, BufferedImage image, int passes){
        //BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        int width = image.getWidth();
        int height = image.getHeight();
        byte[] bytes = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
        double[] doubles = new double[bytes.length];
        double screendist = 0.1;
        double xf = Math.tan(fov/2) * screendist;
        double yf = -(Math.tan(fov/2) * screendist);
        ExecutorService threadPool = Executors.newFixedThreadPool(height);
        Future<?>[] futures = new Future<?>[height];
        for(int runs = 1; runs < passes; runs++){
            System.out.println("Pass: " + runs);
            for(int y = 0; y < height; y++){
                final int row = y;
                final double runsd = (double)runs;
                futures[y] = threadPool.submit(() -> {
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
                        double r = colour.r();
                        double g = colour.g();
                        double b = colour.b();
                        //System.out.println("" + x + ", " + y + "->" + byteIndex);
                        if(runsd> 1){
                            doubles[byteIndex + 0] = doubles[byteIndex + 0] * (runsd-1)/runsd + b / runsd;
                            doubles[byteIndex + 1] = doubles[byteIndex + 1] * (runsd-1)/runsd + g / runsd;
                            doubles[byteIndex + 2] = doubles[byteIndex + 2] * (runsd-1)/runsd + r / runsd;
                        }else{
                            doubles[byteIndex + 0] = b;
                            doubles[byteIndex + 1] = g;
                            doubles[byteIndex + 2] = r;
                        }
                        bytes[byteIndex + 0] = (byte)(Math.min(1, doubles[byteIndex+0]) * 255);
                        bytes[byteIndex + 1] = (byte)(Math.min(1, doubles[byteIndex+1]) * 255);
                        bytes[byteIndex + 2] = (byte)(Math.min(1, doubles[byteIndex+2]) * 255);
                        
                    }
                    //System.out.println("Done row " + row + "/" + height);
                });
            }
            for(Future<?> future : futures){
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
            
            if(rowCallback != null){
                rowCallback.run();
            }
        }
    }

    private Runnable rowCallback = null;
    public void rowCallback(Runnable callback){
        this.rowCallback = callback;
    }
}
