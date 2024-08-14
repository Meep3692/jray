package ca.awoo.jray;

public interface Material {
    public Colour colour(Shape shape, RayHit hit, Scene scene);
}
