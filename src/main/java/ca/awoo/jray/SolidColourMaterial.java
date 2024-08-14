package ca.awoo.jray;

public class SolidColourMaterial implements Material{
    private final Colour colour;

    public SolidColourMaterial(Colour colour) {
        this.colour = colour;
    }

    @Override
    public Colour colour(Shape shape, RayHit hit, Scene scene) {
        return colour;
    }
    
}
