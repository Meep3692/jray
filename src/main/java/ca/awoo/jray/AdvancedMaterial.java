package ca.awoo.jray;

public class AdvancedMaterial implements Material{
    private final Colour diffuse;
    private final Colour specular;
    private final Colour emmisive;
    
    public AdvancedMaterial(Colour diffuse, Colour specular, Colour emmisive) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.emmisive = emmisive;
    }

    @Override
    public Colour colour(Solid solid, RayHit hit, Scene scene) {
        Colour specularColour;
        if(!specular.equals(Colour.BLACK)){
            Vector specularDir = hit.cause().direction().add(hit.normal().mul(hit.normal().dot(hit.cause().direction())*-2));
            Ray specularRay = new Ray(hit.position(), specularDir, hit.cause().depth()+1);
            specularColour = scene.castColour(specularRay, solid).mul(specular);
        }else{
            specularColour = Colour.BLACK;
        }
        
        //TODO: Lambert's cosine law
        Colour diffuseColour;
        if(!diffuse.equals(Colour.BLACK)){
            int diffuseRays = 20;
            double diffuseR = 0;
            double diffuseG = 0;
            double diffuseB = 0;
            for(int i = 0; i < diffuseRays; i++){
                Vector diffuseDir = new Vector(Math.random(), Math.random(), Math.random()).normalize();
                if(diffuseDir.dot(hit.normal()) < 0){
                    diffuseDir = diffuseDir.add(hit.normal());
                }
                Ray diffuseRay = new Ray(hit.position(), diffuseDir, hit.cause().depth()+1);
                Colour diffuseRayColour = scene.castColour(diffuseRay, solid);
                diffuseR += diffuseRayColour.r()/diffuseRays;
                diffuseG += diffuseRayColour.g()/diffuseRays;
                diffuseB += diffuseRayColour.b()/diffuseRays;
            }
            diffuseColour = new Colour(diffuseR, diffuseG, diffuseB).mul(diffuse);
        }else{
            diffuseColour = Colour.BLACK;
        }
        
        Colour finalColour = emmisive.add(diffuseColour).add(specularColour);
        return finalColour;
    }
}
