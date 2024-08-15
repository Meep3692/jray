package ca.awoo.jray;

public interface Material {
    public Colour colour(Solid solid, RayHit hit, Scene scene);
}
