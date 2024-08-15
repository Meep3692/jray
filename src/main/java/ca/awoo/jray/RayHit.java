package ca.awoo.jray;

public class RayHit {
    private final double distance;
    private final Vector position;
    private final Vector normal;
    private final Ray cause;

    public RayHit(double distance, Vector position, Vector normal, Ray cause) {
        this.distance = distance;
        this.position = position;
        this.normal = normal;
        this.cause = cause;
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

    public Ray cause(){
        return cause;
    }

    @Override
    public String toString() {
        return "RayHit [distance=" + distance + ", position=" + position + ", normal=" + normal + ", cause=" + cause + "]";
    }
}
