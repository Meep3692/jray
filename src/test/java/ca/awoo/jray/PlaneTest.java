package ca.awoo.jray;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlaneTest {
    @Test
    public void planeIntersection(){
        Plane plane = new Plane(new Vector(), new Vector(0, 0, -1));
        Ray ray = new Ray(new Vector(0, 0, -5), new Vector(0, 0, 1), 0);
        RayHit[] hits = plane.hit(ray);
        assertEquals("1 hits", 1, hits.length);
        assertEquals("hit at 5 units", 5.0, hits[0].distance(), 0.01);
    }

    @Test
    public void planeIntersectionOffOrigin(){
        Plane plane = new Plane(new Vector(0, 0, 3), new Vector(0, 0, -1));
        Ray ray = new Ray(new Vector(0, 0, -5), new Vector(0, 0, 1), 0);
        RayHit[] hits = plane.hit(ray);
        assertEquals("1 hits", 1, hits.length);
        assertEquals("hit at 8 units", 8.0, hits[0].distance(), 0.01);
    }

    @Test
    public void planeIntersectionOffAngle(){
        Plane plane = new Plane(new Vector(0, 0, 0), new Vector(0, 1, -1).normalize());
        Ray ray = new Ray(new Vector(0, 0, -5), new Vector(0, 0, 1), 0);
        RayHit[] hits = plane.hit(ray);
        assertEquals("1 hits", 1, hits.length);
        assertEquals("hit at 5 units", 5.0, hits[0].distance(), 0.01);
    }
}
