package ca.awoo.jray;

public class RayHit {
    private final double distance;
    private final Vector position;
    private final Vector normal;

    public RayHit(double distance, Vector position, Vector normal) {
        this.distance = distance;
        this.position = position;
        this.normal = normal;
    }

    public double distance(){
        return distance;
    }

    public Vector position(){
        return position;
    }

    public Vector normal(){
        return normal;
    }

    @Override
    public String toString() {
        return "RayHit [distance=" + distance + ", position=" + position + ", normal=" + normal + "]";
    }
}
