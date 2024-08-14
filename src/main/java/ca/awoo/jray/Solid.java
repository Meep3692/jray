package ca.awoo.jray;

public class Solid {
    private final Shape shape;
    private final Material mat;

    public Solid(Shape shape, Material mat) {
        this.shape = shape;
        this.mat = mat;
    }
    
    public Shape shape(){
        return shape;
    }

    public Material material(){
        return mat;
    }
}
