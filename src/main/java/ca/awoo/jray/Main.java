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
        Scene scene = new Scene();
        Solid redSphere = new Solid(new Sphere(new Vector(), 1), new SolidColourMaterial(new Colour(1, 0, 0)));
        scene.addSolid(redSphere);
        Camera camera = new Camera(new Vector(0, 0, -5), Math.PI/3);
        Image image = camera.render(scene, width, height);
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
