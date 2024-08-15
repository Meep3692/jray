package ca.awoo.jray;

public class InvertedShape implements Shape {
    private final Shape base;
    
    public InvertedShape(Shape base) {
        this.base = base;
    }

    @Override
    public RayHit[] hit(Ray ray) {
        RayHit[] hits = base.hit(ray);
        RayHit[] out = new RayHit[hits.length];
        for(int i = 0; i < hits.length; i++){
            RayHit hit = hits[i];
            out[i] = new RayHit(hit.distance(), hit.position(), hit.normal().mul(-1), hit.cause());
        }
        return out;
    }
    
}
