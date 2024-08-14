package ca.awoo.jray;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
    public static void main(String[] args){
        int width = 640;
        int height = 480;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Sphere sphere = new Sphere(new Vector(), 1);
        byte[] bytes = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
        double screendist = 0.1;
        double fov = Math.PI/3;
        double xf = Math.tan(fov/2) * screendist;
        double yf = Math.tan(fov/2) * screendist;
        Vector cameraPos = new Vector(0, 0, 5);
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                int index = y*width+x;
                int byteIndex = index*3;
                double xn = (double)(x - width/2) / (double)(width/2);
                double yn = (double)(y - height/2) / (double)(width/2);
                xn *= xf;
                yn *= yf;
                Vector screenpos = new Vector(xn, yn, screendist).normalize();
                Ray ray = new Ray(cameraPos, screenpos);
                RayHit[] hits = sphere.hit(ray);
                //System.out.println("<" + x + ", " + y + ">->" + "<" + xn + ", " + yn + ">->" + ray.direction() + ats(hits));
                if(hits.length == 0){
                    bytes[byteIndex + 0] = 0;
                    bytes[byteIndex + 1] = 0;
                    bytes[byteIndex + 2] = 0;
                }else{
                    RayHit minHit = hits[0];
                    if(hits[1].distance() < minHit.distance()){
                        minHit = hits[1];
                    }
                    double color = 1/minHit.distance();
                    byte colorByte = (byte)(color*255);
                    bytes[byteIndex + 0] = colorByte;
                    bytes[byteIndex + 1] = colorByte;
                    bytes[byteIndex + 2] = colorByte;
                }
            }
        }
        ImageIcon icon = new ImageIcon(image);
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        label.setIcon(icon);
        frame.add(label);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static <T> String ats(T[] array){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < array.length - 1; i++){
            sb.append(array[i]);
            sb.append(", ");
        }
        if(array.length > 0){
            sb.append(array[array.length - 1]);
        }
        sb.append("]");
        return sb.toString();
    }
}
