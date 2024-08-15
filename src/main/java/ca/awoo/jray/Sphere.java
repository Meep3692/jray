package ca.awoo.jray;

public class Sphere implements Shape {
    private final Vector center;
    private final double radius;

    public Sphere(Vector center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public RayHit[] hit(Ray ray) {
        Vector diff = ray.origin().sub(center);
        double uoc = ray.direction().dot(diff);
        double w = (uoc*uoc) - (diff.dot(diff) - radius*radius);
        if(w < 0) return new RayHit[0];
        double d1 = -uoc + Math.sqrt(w);
        double d2 = -uoc - Math.sqrt(w);
        Vector t1 = ray.origin().add(ray.direction().mul(d1));
        Vector t2 = ray.origin().add(ray.direction().mul(d2));
        Vector normal1 = t1.sub(center).normalize();
        Vector normal2 = t2.sub(center).normalize();
        return new RayHit[]{new RayHit(d1, t1, normal1, ray), new RayHit(d2, t2, normal2, ray)};
    }
    
}
