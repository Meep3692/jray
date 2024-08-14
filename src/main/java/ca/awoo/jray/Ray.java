package ca.awoo.jray;

public class Ray {
    private final Vector origin, direction;

    public Ray(Vector origin, Vector direction){
        this.origin = origin;
        this.direction = direction;
    }

    public Vector origin(){
        return origin;
    }

    public Vector direction(){
        return direction;
    }

    @Override
    public String toString() {
        return "Ray [origin=" + origin + ", direction=" + direction + "]";
    }

    
}
