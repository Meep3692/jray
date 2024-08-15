package ca.awoo.jray;

public class Plane implements Shape {
    private final Vector point;
    private final Vector normal;

    public Plane(Vector point, Vector normal) {
        this.point = point;
        this.normal = normal;
    }

    @Override
    public RayHit[] hit(Ray ray) {
        // This code is used to discard rays pointing away from the plane
        // We don't actually want this because of InvertedShape and things
        // that rely on getting _all_ intersections, not just "useful" ones
        // if(ray.direction().dot(normal) > 0){
        //     return new RayHit[0];
        // }
        Vector diff = point.sub(ray.origin());
        double dist = diff.dot(normal) / ray.direction().dot(normal);
        Vector t = ray.direction().mul(dist).add(ray.origin());
        return new RayHit[]{new RayHit(dist, t, normal, ray)};
    }
    
}
