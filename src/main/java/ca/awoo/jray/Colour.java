package ca.awoo.jray;

public class Colour {
    private final double r, g, b;

    public static final Colour BLACK   = new Colour(0, 0, 0);
    public static final Colour RED     = new Colour(1, 0, 0);
    public static final Colour GREEN   = new Colour(0, 1, 0);
    public static final Colour BLUE    = new Colour(0, 0, 1);
    public static final Colour YELLOW  = new Colour(1, 1, 0);
    public static final Colour MAGENTA = new Colour(1, 0, 1);
    public static final Colour CYAN    = new Colour(0, 1, 1);
    public static final Colour WHITE   = new Colour(1, 1, 1);

    public Colour(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public double r(){
        return r;
    }

    public double g(){
        return g;
    }

    public double b(){
        return b;
    }

    public Colour mul(Colour other){
        return new Colour(r*other.r, b*other.b, g*other.g);
    }

    public Colour mul(double scalar){
        return new Colour(r*scalar, g*scalar, b*scalar);
    }

    public Colour add(Colour other){
        return new Colour(r+other.r, b+other.b, g+other.g);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(r);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(g);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Colour other = (Colour) obj;
        if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
            return false;
        if (Double.doubleToLongBits(g) != Double.doubleToLongBits(other.g))
            return false;
        if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b))
            return false;
        return true;
    }

    
}
