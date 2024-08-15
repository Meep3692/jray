package ca.awoo.jray;

public class Vector {
    private double x, y, z;

    public Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(){
        this(0, 0, 0);
    }

    public double x(){
        return x;
    }

    public double y(){
        return y;
    }

    public double z(){
        return z;
    }

    public Vector add(Vector other){
        return new Vector(x + other.x, y + other.y, z + other.z);
    }

    public Vector sub(Vector other){
        return new Vector(x - other.x, y - other.y, z - other.z);
    }

    public double dot(Vector other){
        return x * other.x + y * other.y + z * other.z;
    }

    public Vector mul(double scalar){
        return new Vector(x*scalar, y*scalar, z*scalar);
    }

    public Vector div(double scalar){
        return new Vector(x/scalar, y/scalar, z/scalar);
    }

    public double magnitude(){
        return Math.sqrt(x*x+y*y+z*z);
    }

    public Vector normalize(){
        return div(magnitude());
    }

    @Override
    public String toString() {
        return "<" + x + ", " + y + ", " + z + ">";
    }

    public static Vector normalized(double x, double y, double z){
        double m = Math.sqrt(x*x+y*y+z*z);
        return new Vector(x/m, y/m, z/m);
    }
}
