package ca.awoo.jray;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args){
        int width = 320;
        int height = 240;
        Scene scene = new Scene();

        Colour red = new Colour(1, 0.5, 0.5);
        Colour green = new Colour(0.5, 1, 0.5);
        Colour grey = new Colour(0.5, 0.5, 0.5);
        Colour black = new Colour(0, 0, 0);
        Colour white = new Colour(1, 1, 1);

        Material redMat = new AdvancedMaterial(red, black, black);
        Material greenMat = new AdvancedMaterial(green, green, black);
        Material glow = new AdvancedMaterial(black, black, white);
        Material sunMat = new AdvancedMaterial(black, black, white.mul(5));
        Material groundMat = new AdvancedMaterial(black, black, grey);

        Solid redSphere = new Solid(new Sphere(new Vector(), 1), redMat);
        Solid glowSphere = new Solid(new Sphere(new Vector(2, 0, 0), 0.5), glow);
        Solid greenSphere = new Solid(new Sphere(new Vector(2, 1, 0), 0.5), greenMat);
        Solid sun = new Solid(new Sphere(new Vector(5000, 10000, -3000), 1000), sunMat);
        Solid ground = new Solid(new Plane(new Vector(0, -1, 0), new Vector(0, 1, 0)), groundMat);

        scene.addSolid(redSphere);
        scene.addSolid(glowSphere);
        scene.addSolid(greenSphere);
        scene.addSolid(sun);
        scene.addSolid(ground);

        Camera camera = new Camera(new Vector(0, 0, -5), Math.PI/3);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        ImageIcon icon = new ImageIcon(image);
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        label.setIcon(icon);
        frame.add(label);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        camera.rowCallback(() -> {
            SwingUtilities.invokeLater(() -> {
                frame.repaint();
            });
        });
        camera.render(scene, image);
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
