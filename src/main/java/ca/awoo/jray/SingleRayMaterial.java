package ca.awoo.jray;

public class SingleRayMaterial implements Material {
    private final double specular;
    private final Colour base;
    private final Colour emission;

    public SingleRayMaterial(double specular, Colour base, Colour emission) {
        this.specular = specular;
        this.base = base;
        this.emission = emission;
    }

    @Override
    public Colour colour(Solid solid, RayHit hit, Scene scene) {
        if(base.equals(Colour.BLACK)){
            return emission;
        }
        Vector specularDir = hit.cause().direction().add(hit.normal().mul(hit.normal().dot(hit.cause().direction())*-2));
        Vector diffuseDir = new Vector(Math.random(), Math.random(), Math.random()).normalize();
        if(diffuseDir.dot(hit.normal()) < 0){
            diffuseDir = diffuseDir.add(hit.normal());
        }
        Vector reflectDir = diffuseDir.add(specularDir.mul(specular));
        Ray reflectRay = new Ray(hit.position(), reflectDir, hit.cause().depth()+1);
        Colour reflectedColour = scene.castColour(reflectRay, solid);
        return emission.add(reflectedColour.mul(base));
    }
    
}
