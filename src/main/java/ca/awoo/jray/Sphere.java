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
        double cx = center.x();
        double cy = center.y();
        double cz = center.z();
        double rox = ray.origin().x();
        double roy = ray.origin().y();
        double roz = ray.origin().z();
        double dx = rox - cx;
        double dy = roy - cy;
        double dz = roz - cz;
        double rdx = ray.direction().x();
        double rdy = ray.direction().y();
        double rdz = ray.direction().z();
        double uoc = rdx*dx+rdy*dy+rdz*dz;
        double w = (uoc*uoc) - (dx*dx+dy*dy+dz*dz) + radius*radius;
        if(w < 0) return new RayHit[0];
        double d1 = -uoc + Math.sqrt(w);
        double d2 = -uoc - Math.sqrt(w);
        Vector t1 = new Vector(rox + rdx * d1, roy + rdy * d1, roz + rdz * d1);
        Vector t2 = new Vector(rox + rdx * d2, roy + rdy * d2, roz + rdz * d2);
        Vector normal1 = Vector.normalized(t1.x()-cx, t1.y()-cy, t1.z()-cz);
        Vector normal2 = Vector.normalized(t2.x()-cx, t2.y()-cy, t2.z()-cz);
        return new RayHit[]{new RayHit(d1, t1, normal1, ray), new RayHit(d2, t2, normal2, ray)};
    }

    // @Override
    // public RayHit[] hit(Ray ray) {
    //     Vector diff = ray.origin().sub(center);
    //     double uoc = ray.direction().dot(diff);
    //     double w = (uoc*uoc) - (diff.dot(diff) - radius*radius);
    //     if(w < 0) return new RayHit[0];
    //     double d1 = -uoc + Math.sqrt(w);
    //     double d2 = -uoc - Math.sqrt(w);
    //     Vector t1 = ray.origin().add(ray.direction().mul(d1));
    //     Vector t2 = ray.origin().add(ray.direction().mul(d2));
    //     Vector normal1 = t1.sub(center).normalize();
    //     Vector normal2 = t2.sub(center).normalize();
    //     return new RayHit[]{new RayHit(d1, t1, normal1, ray), new RayHit(d2, t2, normal2, ray)};
    // }
    
}
