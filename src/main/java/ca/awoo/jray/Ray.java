package ca.awoo.jray;

public class Ray {
    private final Vector origin, direction;
    private final int depth;

    public Ray(Vector origin, Vector direction, int depth){
        this.origin = origin;
        this.direction = direction;
        this.depth = depth;
    }

    public Vector origin(){
        return origin;
    }

    public Vector direction(){
        return direction;
    }

    public int depth(){
        return depth;
    }

    @Override
    public String toString() {
        return "Ray [origin=" + origin + ", direction=" + direction + "]";
    }

    
}
